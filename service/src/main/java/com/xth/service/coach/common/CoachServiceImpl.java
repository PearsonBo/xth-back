package com.xth.service.coach.common;

import com.alibaba.fastjson.JSON;
import com.xth.dao.coach.CoachDao;
import com.xth.dao.store.StoreDao;
import com.xth.model.base.AbstractVo;
import com.xth.model.base.PageList;
import com.xth.model.bo.coach.Coach;
import com.xth.model.bo.store.Store;
import com.xth.model.so.coach.CoachSo;
import com.xth.model.so.store.StoreSo;
import com.xth.model.vo.coach.CoachVo;
import com.xth.model.vo.coach.ExportCoachVo;
import com.xth.model.vo.store.StoreVo;
import com.xth.service.AbstractTransactionalService;
import com.xth.service.CheckEmptyHelper;
import com.xth.service.ImportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 系统生成Service实现类
 *
 * @author admin
 * @date 2018/09/17
 */
@Service
public class CoachServiceImpl extends AbstractTransactionalService implements CoachService {

    @Autowired
    private CoachDao coachDao;

    @Autowired
    private StoreDao storeDao;

    /**
     * 新增
     *
     * @param coach 新增对象
     * @return 新增对象的id
     */
    @Override
    public Long create(CoachVo coach) {
        checkBeforeCreate(coach);
        prepareBeforeCreate(coach);
        Coach coachBo = dozer.convert(coach, Coach.class);
        return coachDao.insert(coachBo);
    }

    private void prepareBeforeCreate(CoachVo coach) {
        fillCityId(coach);
    }

    private void checkBeforeCreate(CoachVo coach) {
        checkRequired(coach);
        checkDuplicate4Create(coach);
    }


    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    @Override
    public void delete(Long id) {
        coachDao.delete(id);
    }

    /**
     * 修改
     *
     * @param coach 修改对象
     */
    @Override
    public void update(CoachVo coach) {
        checkBeforeUpdate(coach);
        fillCityId(coach);
        Coach coachBo = dozer.convert(coach, Coach.class);
        coachDao.update(coachBo);
    }

    private void fillCityId(CoachVo coach) {
        String storeId = coach.getStoreId();
        CheckEmptyHelper.checkObject("教练的场馆id为空", storeId);
        Store store = storeDao.findBo(Long.parseLong(storeId));
        coach.setCityId(store.getCityId());
    }

    private void checkBeforeUpdate(CoachVo coach) {
        checkRequired(coach);
        checkDuplicate4Update(coach);
    }

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    @Override
    public CoachVo find(Long id) {
        CoachVo vo = coachDao.findVo(id);
        fillStore(vo);
        return vo;
    }

    /**
     * 按条件查询
     *
     * @param coachSo 查询条件
     * @return 查询结果
     */
    @Override
    public PageList<CoachVo> listPagination(CoachSo coachSo) {
        List<CoachVo> list = coachDao.listPaginationVoBySo(coachSo);
        if (!CollectionUtils.isEmpty(list)) {
            for (CoachVo coachVo : list) {
                fillStore(coachVo);
            }
        }
        int count = coachDao.countBySo(coachSo);
        return new PageList<>(list, count);
    }

    private void fillStore(CoachVo coachVo) {
        String storeId = coachVo.getStoreId();
        StoreVo vo = storeDao.findVo(Long.parseLong(storeId));
        coachVo.setStoreVo(vo);
    }

    @Override
    public void batchImport(MultipartFile file) {
        ExportCoachVo coachVo = new ExportCoachVo();
        List<Map<String, Object>> list = null;
        try {
            list = ImportExcelUtil.importExcel(file, coachVo);
        } catch (Exception e) {
            log.error("导入教练数据失败", e.getMessage(), e);
            e.printStackTrace();
        }
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (Map<String, Object> vo : list) {
            CoachVo newCoach = JSON.parseObject(JSON.toJSONString(vo), CoachVo.class);
            if (!StringUtils.isEmpty(newCoach.getStoreId())) {
                String storId;
                if (newCoach.getStoreId().contains(".")) {
                    storId = newCoach.getStoreId().substring(0, newCoach.getStoreId().indexOf("."));
                } else {
                    storId = newCoach.getStoreId();
                }
                newCoach.setStoreId(storId);
            }

            if (!StringUtils.isEmpty(newCoach.getStarLevel())) {
                String starLevel;
                if (newCoach.getStarLevel().contains(".")) {
                    starLevel = newCoach.getStarLevel().substring(0, newCoach.getStarLevel().indexOf("."));
                } else {
                    starLevel = newCoach.getStarLevel();
                }
                newCoach.setStarLevel(starLevel);
            }
            newCoach.setLockVersion(0);
            coachDao.insert(dozer.convert(newCoach, Coach.class));
        }
    }

    @Override
    public PageList<CoachVo> listPagination4App(CoachSo coachSo) {
        Long cityId = coachSo.getCityId();
        CheckEmptyHelper.checkObject("缺少城市id", cityId);
        log.info("cityId:" + cityId);
        List<CoachVo> list = coachDao.listPaginationVoBySo(coachSo);
        int count = coachDao.countBySo(coachSo);
        return new PageList<>(list, count);
    }

    /**
     * 检查必填字段
     */
    private void checkRequired(CoachVo coach) {

    }

    /**
     * 检查字段唯一性（创建）
     */
    private void checkDuplicate4Create(CoachVo coach) {

    }


    /**
     * 检查字段唯一性（更新）
     */
    private void checkDuplicate4Update(CoachVo coach) {

    }


}

package com.xth.service.store.common;

import com.alibaba.fastjson.JSON;
import com.xth.dao.city.CityDao;
import com.xth.dao.client.ClientDao;
import com.xth.dao.coach.CoachDao;
import com.xth.dao.store.StoreDao;
import com.xth.model.base.PageList;
import com.xth.model.bo.city.City;
import com.xth.model.bo.city.DefaultCityLocation;
import com.xth.model.bo.client.Client;
import com.xth.model.bo.coach.Coach;
import com.xth.model.bo.store.Store;
import com.xth.model.so.city.LocationSo;
import com.xth.model.so.coach.CoachSo;
import com.xth.model.so.store.StoreSo;
import com.xth.model.vo.store.ExportStoreVo;
import com.xth.model.vo.store.StoreVo;
import com.xth.service.AbstractTransactionalService;
import com.xth.service.CheckEmptyHelper;
import com.xth.service.ImportExcelUtil;
import com.xth.service.RedisReadHelper;
import com.xth.service.store.helper.StoreServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;


/**
 * 系统生成Service实现类
 *
 * @author admin
 * @date 2018/09/16
 */
@Service
public class StoreServiceImpl extends AbstractTransactionalService implements StoreService {

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private StoreServiceHelper storeServiceHelper;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private CoachDao coachDao;

    /**
     * 新增
     *
     * @param store 新增对象
     * @return 新增对象的id
     */
    @Override
    public Long create(StoreVo store) {
        checkBeforeCreate(store);
        prepareBeforeCreate(store);
        Store storeBo = dozer.convert(store, Store.class);
        return storeDao.insert(storeBo);
    }

    private void prepareBeforeCreate(StoreVo store) {
        if (store.getIsHot() == null) {
            store.setIsHot(Boolean.FALSE);
            store.setHotLevel(0);
        }
    }

    private void checkBeforeCreate(StoreVo store) {
        checkRequired(store);
        checkDuplicate4Create(store);
    }


    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    @Override
    public void delete(Long id) {
        CoachSo coachSo = new CoachSo();
        coachSo.setStoreId(id);
        List<Coach> coachList = coachDao.listBoBySo(coachSo);
        if (!CollectionUtils.isEmpty(coachList)) {
            for (Coach coach : coachList) {
                coach.setStoreId(null);
                coachDao.update(coach);
            }
        }
        storeDao.delete(id);
    }

    /**
     * 修改
     *
     * @param store 修改对象
     */
    @Override
    public void update(StoreVo store) {
        checkBeforeUpdate(store);
        Store storeBo = dozer.convert(store, Store.class);
        storeDao.update(storeBo);
    }

    private void checkBeforeUpdate(StoreVo store) {
        checkRequired(store);
        checkDuplicate4Update(store);
    }

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    @Override
    public StoreVo find(Long id) {
        StoreVo storeVo = storeDao.findVo(id);
        CheckEmptyHelper.checkObject("不存在该id[" + id + "]的场馆", storeVo);
        Long cityId = storeVo.getCityId();
        City city = cityDao.findBo(cityId);
        storeVo.setCityName(city == null ? null : city.getName());
        return storeVo;
    }

    /**
     * 1. 如果没授权也没选择位置，默认返回杭州的场馆列表
     * 2. 如果只授权，有经纬度和城市id
     * 3. 如果没授权，选择了城市，使用城市的默认经纬度
     * 4. 如果授权了，也选择城市，地址使用用户所在地址，城市使用缓存中的选择的城市id
     * <p>
     * 按条件查询
     *
     * @param storeSo 查询条件
     * @return 查询结果
     */
    @Override
    public PageList<StoreVo> listPagination4Weapp(StoreSo storeSo) {

        Long cityId = storeSo.getCityId();
        CheckEmptyHelper.checkLong("缺少城市id参数", cityId);
        log.info("用户选择的城市id：" + cityId);

        Long clientId = storeSo.getClientId();
        if (clientId == null) {
            log.info("查询场馆列表，未onlogin，没有获取到clientId，返回杭州的场馆列表");
            storeSo.setCityId(2L);
            List<StoreVo> list = storeDao.listPaginationVoBySo(storeSo);
            int count = storeDao.countBySo(storeSo);
            return new PageList<>(list, count);
        }

        Client client = clientDao.findBo(clientId);
        String userLatitude = client.getLastLatitude();
        String userLongitude = client.getLastLongitude();

        if (userLatitude == null && userLongitude == null) {
            City city = cityDao.findBo(cityId);
            LocationSo location = new DefaultCityLocation().getDefaultCity(city.getName());
            userLatitude = location.getLatitude();
            userLongitude = location.getLongitude();
            log.info("用户未授权，使用用户所选择的城市【" + city.getName() + "】的默认地址");
        }

        List<StoreVo> list = storeDao.listPaginationVoBySo(storeSo);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(p -> p.setDiscountContentMessage(storeServiceHelper.getDisCountMessage(p)));
        }
        int count = storeDao.countBySo(storeSo);

        for (StoreVo storeVo : list) {
            String latitude = storeVo.getLatitude();
            String longitude = storeVo.getLongitude();
            if (!StringUtils.isEmpty(latitude) && !StringUtils.isEmpty(longitude)) {
                double distance = storeServiceHelper.distance(userLongitude, userLatitude, longitude, latitude);
                storeVo.setDistance(Double.parseDouble(new DecimalFormat("#0.00").format(distance)));
            }
        }

        list.sort((StoreVo v1, StoreVo v2) -> v1.getDistance().compareTo(v2.getDistance()));

        return new PageList<>(list, count);
    }

    @Override
    public void batchImport(MultipartFile file) {
        ExportStoreVo storeVo = new ExportStoreVo();
        List<Map<String, Object>> list = null;
        try {
            list = ImportExcelUtil.importExcel(file, storeVo);
        } catch (Exception e) {
            log.error("导入场馆数据失败", e.getMessage(), e);
            e.printStackTrace();
        }
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (Map<String, Object> vo : list) {
            StoreVo newStore = JSON.parseObject(JSON.toJSONString(vo), StoreVo.class);
            if (!StringUtils.isEmpty(newStore.getStarLevel())) {
                String starLevel;
                if (newStore.getStarLevel().contains(".")) {
                    starLevel = newStore.getStarLevel().substring(0, newStore.getStarLevel().indexOf("."));
                } else {
                    starLevel = newStore.getStarLevel();
                }
                newStore.setStarLevel(starLevel);
            }

            String cityName = newStore.getCityName();
            City city = cityDao.findByName(cityName);
            newStore.setCityId(city.getId());
            newStore.setLockVersion(0);
            storeDao.insert(dozer.convert(newStore, Store.class));
        }
    }

    /**
     * 按条件查询
     *
     * @param storeSo 查询条件
     * @return 查询结果
     */
    @Override
    public PageList<StoreVo> listPagination(StoreSo storeSo) {
        List<StoreVo> list = storeDao.listPaginationVoBySo(storeSo);
        if (!CollectionUtils.isEmpty(list)) {
            for (StoreVo storeVo : list) {
                Long cityId = storeVo.getCityId();
                City city = cityDao.findBo(cityId);
                storeVo.setCityName(city == null ? null : city.getName());
            }
        }
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(p -> p.setDiscountContentMessage(storeServiceHelper.getDisCountMessage(p)));
        }
        int count = storeDao.countBySo(storeSo);
        return new PageList<>(list, count);
    }


    /**
     * 检查必填字段
     */
    private void checkRequired(StoreVo store) {
        CheckEmptyHelper.checkObject("城市不能为空", store.getCityId());
        City city = cityDao.findBo(store.getCityId());
        CheckEmptyHelper.checkObject("未找到id为" + store.getCityId() + "的城市", city);

    }

    /**
     * 检查字段唯一性（创建）
     */
    private void checkDuplicate4Create(StoreVo store) {

    }


    /**
     * 检查字段唯一性（更新）
     */
    private void checkDuplicate4Update(StoreVo store) {

    }


}

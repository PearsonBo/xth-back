package com.xth.service.level.common;

import com.xth.dao.level.LevelDao;
import com.xth.model.base.PageList;
import com.xth.model.bo.level.Level;
import com.xth.model.so.level.LevelSo;
import com.xth.model.vo.level.LevelVo;
import com.xth.service.AbstractTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


/**
 * 系统生成Service实现类
 *
 * @author admin
 * @date 2018/09/24
 */
@Service
public class LevelServiceImpl extends AbstractTransactionalService implements LevelService {

    @Autowired
    private LevelDao levelDao;

    /**
     * 新增
     *
     * @param level 新增对象
     * @return 新增对象的id
     */
    @Override
    public Long create(LevelVo level) {
        checkBeforeCreate(level);
        Level levelBo = dozer.convert(level, Level.class);
        return levelDao.insert(levelBo);
    }

    private void checkBeforeCreate(LevelVo level) {
        checkRequired(level);
        checkDuplicate4Create(level);
    }


    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    @Override
    public void delete(Long id) {
        levelDao.delete(id);
    }

    /**
     * 修改
     *
     * @param level 修改对象
     */
    @Override
    public void update(LevelVo level) {
        checkBeforeUpdate(level);
        Level levelBo = dozer.convert(level, Level.class);
        levelDao.update(levelBo);
    }

    private void checkBeforeUpdate(LevelVo level) {
        checkRequired(level);
        checkDuplicate4Update(level);
    }

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    @Override
    public LevelVo find(Long id) {
        return levelDao.findVo(id);
    }

    /**
     * 按条件查询
     *
     * @param levelSo 查询条件
     * @return 查询结果
     */
    @Override
    public PageList<LevelVo> listPagination(LevelSo levelSo) {
        List<LevelVo> list = levelDao.listPaginationVoBySo(levelSo);
        int count = levelDao.countBySo(levelSo);
        return new PageList<>(list, count);
    }

    /**
     * 检查必填字段
     */
    private void checkRequired(LevelVo level) {

    }

    /**
     * 检查字段唯一性（创建）
     */
    private void checkDuplicate4Create(LevelVo level) {

    }


    /**
     * 检查字段唯一性（更新）
     */
    private void checkDuplicate4Update(LevelVo level) {

    }


}

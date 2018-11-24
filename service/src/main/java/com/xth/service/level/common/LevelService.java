package com.xth.service.level.common;


import com.xth.model.base.PageList;
import com.xth.model.so.level.LevelSo;
import com.xth.model.vo.level.LevelVo;

/**
 * 系统生成Service类
 *
 * @author admin
 * @date 2018/09/24
 */
public interface LevelService {

    /**
     * 新增
     *
     * @param level 新增对象
     * @return 新增对象的id
     */
    Long create(LevelVo level);

    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    void delete(Long id);

    /**
     * 修改
     *
     * @param level 修改对象
     */
    void update(LevelVo level);

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    LevelVo find(Long id);

    /**
     * 按条件查询
     *
     * @param levelSo 查询条件
     * @return 查询结果
     */
    PageList<LevelVo> listPagination(LevelSo levelSo);


}


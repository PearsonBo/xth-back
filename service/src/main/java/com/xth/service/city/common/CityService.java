package com.xth.service.city.common;


import com.xth.model.base.PageList;
import com.xth.model.so.city.CitySo;
import com.xth.model.so.city.LocationSo;
import com.xth.model.vo.city.CityVo;

/**
 * 系统生成Service类
 *
 * @author admin
 * @date 2018/09/26
 */
public interface CityService {

    /**
     * 新增
     *
     * @param city 新增对象
     * @return 新增对象的id
     */
    Long create(CityVo city);

    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    void delete(Long id);

    /**
     * 修改
     *
     * @param city 修改对象
     */
    void update(CityVo city);

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    CityVo find(Long id);

    /**
     * 按条件查询
     *
     * @param citySo 查询条件
     * @return 查询结果
     */
    PageList<CityVo> listPagination(CitySo citySo);


    /**
     * 根据用户详细经纬度寻找城市
     *
     * @param locationSo
     * @return
     */
    CityVo findCity(LocationSo locationSo);
}


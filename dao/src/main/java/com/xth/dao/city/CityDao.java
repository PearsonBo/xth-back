package com.xth.dao.city;


import com.xth.dao.BaseDao;
import com.xth.model.bo.city.City;

/**
 * 系统生成Dao
 *
 * @author admin
 * @date 2017/11/14
 */
public interface CityDao extends BaseDao<City> {


    City findByName(String cityName);
}

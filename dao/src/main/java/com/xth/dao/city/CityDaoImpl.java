package com.xth.dao.city;

import com.xth.dao.BaseNoHistoryDaoImpl;
import com.xth.mapper.Mapper;
import com.xth.mapper.city.CityMapper;
import com.xth.model.bo.city.City;
import com.xth.model.exception.BizException;
import com.xth.model.exception.ExceptionType;
import com.xth.model.so.city.CitySo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * 系统生成Dao实现类
 *
 * @author admin
 * @date 2018/09/26
 */
@Repository
public class CityDaoImpl extends BaseNoHistoryDaoImpl<City> implements CityDao {

    @Autowired
    private CityMapper mapper;


    @Override
    protected Mapper<City> getMapper() {
        return mapper;
    }

    @Override
    public City findByName(String cityName) {
        CitySo citySo = new CitySo();
        citySo.setName(cityName);
        List<City> cityList = mapper.listBoBySo(citySo);
        if (CollectionUtils.isEmpty(cityList) || cityList.size() > 1) {
            throw new BizException("系统中不存在或者存在多条[" + cityName + "]", ExceptionType.DIRTY_DATA);
        }
        return cityList.get(0);
    }
}

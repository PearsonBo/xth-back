package com.xth.service.city.helper;

import com.google.common.collect.Lists;
import com.xth.dao.city.CityDao;
import com.xth.model.bo.city.City;
import com.xth.model.exception.BizException;
import com.xth.model.exception.ExceptionType;
import com.xth.model.so.city.CitySo;
import com.xth.model.vo.city.CityVo;
import com.xth.service.AbstractNoTransactionalService;
import com.xth.service.CheckEmptyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统生成Service帮助类
 *
 * @author admin
 * @date 2018/09/26
 */
@Service
public class CityServiceHelper extends AbstractNoTransactionalService {

    @Autowired
    private CityDao cityDao;


    public CityVo findCityByCityId(Long cityId) {
        List<CityVo> cityVos = cityDao.listVoByIdList(Lists.newArrayList(cityId));
        if (cityVos == null || cityVos.size() > 1) {
            throw new BizException(String.format("城市id【%d】不存在或者存在多条", cityId), ExceptionType.VIOLATE_BIZ_RULE);
        }
        return cityVos.get(0);
    }
}

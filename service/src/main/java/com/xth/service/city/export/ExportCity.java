package com.xth.service.city.export;

import com.xth.dao.city.CityDao;
import com.xth.model.so.city.CitySo;
import com.xth.model.vo.city.CityVo;
import com.xth.service.BaseExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统生成导出类
 *
 * @author admin
 * @date 2018/09/26
 */
@Service
public class ExportCity extends BaseExport<CitySo> {

    private static final String TEMPLATE = "T_CITY.xml";
    private static final String STORE_NAME = "T_CITY";

    @Autowired
    private CityDao cityDao;


}

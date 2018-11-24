package com.xth.service.city.additional;

import com.xth.dao.city.CityDao;
import com.xth.service.AbstractTransactionalService;
import com.xth.service.city.helper.CityServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统生成AdditionalServiceImpl
 *
 * @author admin
 * @date 2018/09/26
 */
@Service
public class CityAdditionalServiceImpl extends AbstractTransactionalService implements CityAdditionalService {

    @Autowired
    private CityDao cityDao;
    @Autowired
    private CityServiceHelper cityServiceHelper;


}

package com.xth.service.level.additional;

import com.xth.dao.level.LevelDao;
import com.xth.service.AbstractTransactionalService;
import com.xth.service.level.helper.LevelServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统生成AdditionalServiceImpl
 *
 * @author admin
 * @date 2018/09/24
 */
@Service
public class LevelAdditionalServiceImpl extends AbstractTransactionalService implements LevelAdditionalService {

    @Autowired
    private LevelDao levelDao;
    @Autowired
    private LevelServiceHelper levelServiceHelper;


}

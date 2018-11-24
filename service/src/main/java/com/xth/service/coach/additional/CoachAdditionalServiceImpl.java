package com.xth.service.coach.additional;

import com.xth.dao.coach.CoachDao;
import com.xth.service.AbstractTransactionalService;
import com.xth.service.coach.helper.CoachServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统生成AdditionalServiceImpl
 *
 * @author admin
 * @date 2018/09/17
 */
@Service
public class CoachAdditionalServiceImpl extends AbstractTransactionalService implements CoachAdditionalService {

    @Autowired
    private CoachDao coachDao;
    @Autowired
    private CoachServiceHelper coachServiceHelper;


}

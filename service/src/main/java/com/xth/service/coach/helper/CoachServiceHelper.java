package com.xth.service.coach.helper;

import com.xth.dao.coach.CoachDao;
import com.xth.service.AbstractNoTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统生成Service帮助类
 *
 * @author admin
 * @date 2018/09/17
 */
@Service
public class CoachServiceHelper extends AbstractNoTransactionalService {

    @Autowired
    private CoachDao coachDao;


}

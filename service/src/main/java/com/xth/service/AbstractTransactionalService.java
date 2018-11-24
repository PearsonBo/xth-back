package com.xth.service;

import com.xth.util.DozerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/11 0011 下午 21:53
 */
@Transactional(rollbackFor = Exception.class)
public abstract class AbstractTransactionalService {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected DozerHelper dozer;

    protected DozerHelper getDozer() {
        return dozer;
    }

}
package com.xth.service;

import com.xth.util.DozerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/11 0011 下午 21:55
 */
public abstract class AbstractNoTransactionalService {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected DozerHelper dozerHelper;

    protected DozerHelper getDozerHelper() {
        return dozerHelper;
    }
}

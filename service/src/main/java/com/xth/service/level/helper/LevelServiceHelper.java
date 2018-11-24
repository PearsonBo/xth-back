package com.xth.service.level.helper;

import com.xth.dao.level.LevelDao;
import com.xth.service.AbstractNoTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统生成Service帮助类
 *
 * @author admin
 * @date 2018/09/24
 */
@Service
public class LevelServiceHelper extends AbstractNoTransactionalService {

    @Autowired
    private LevelDao levelDao;


}

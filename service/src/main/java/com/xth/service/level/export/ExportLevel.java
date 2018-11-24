package com.xth.service.level.export;

import com.xth.dao.level.LevelDao;
import com.xth.model.so.level.LevelSo;
import com.xth.model.vo.level.LevelVo;
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
 * @date 2018/09/24
 */
@Service
public class ExportLevel extends BaseExport<LevelSo> {

    private static final String TEMPLATE = "T_LEVEL.xml";
    private static final String STORE_NAME = "T_LEVEL";

    @Autowired
    private LevelDao levelDao;


}

package com.xth.service.operator.export;

import com.xth.dao.operator.OperatorDao;
import com.xth.model.so.operator.OperatorSo;
import com.xth.model.vo.operator.OperatorVo;
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
 * @date 2018/10/08
 */
@Service
public class ExportOperator extends BaseExport<OperatorSo> {

    private static final String TEMPLATE = "T_OPERATOR.xml";
    private static final String STORE_NAME = "T_OPERATOR";

    @Autowired
    private OperatorDao operatorDao;


}

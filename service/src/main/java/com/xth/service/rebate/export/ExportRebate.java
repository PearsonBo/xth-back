package com.xth.service.rebate.export;

import com.xth.dao.rebate.RebateDao;
import com.xth.model.so.rebate.RebateSo;
import com.xth.model.vo.rebate.RebateVo;
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
 * @date 2018/09/29
 */
@Service
public class ExportRebate extends BaseExport<RebateSo> {

    private static final String TEMPLATE = "T_REBATE.xml";
    private static final String STORE_NAME = "T_REBATE";

    @Autowired
    private RebateDao rebateDao;


}

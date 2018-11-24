package com.xth.service.client.export;

import com.xth.dao.client.ClientDao;
import com.xth.model.so.client.ClientSo;
import com.xth.service.BaseExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统生成导出类
 *
 * @author admin
 * @date 2018/09/18
 */
@Service
public class ExportClient extends BaseExport<ClientSo> {

    private static final String TEMPLATE = "T_CLIENT.xml";
    private static final String STORE_NAME = "T_CLIENT";

    @Autowired
    private ClientDao clientDao;


}

package com.xth.controller.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xth.controller.AjaxBaseController;
import com.xth.model.base.PackVo;
import com.xth.model.so.client.ClientSo;
import com.xth.model.vo.client.ClientVo;
import com.xth.service.client.additional.ClientAdditionalService;
import com.xth.service.client.common.ClientService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统生成Controller
 *
 * @author admin
 * @date 2018/09/18
 */
@RestController
@RequestMapping(ClientController.VIEW_PREFIX)
@Api(tags = {"客户接口"})
public class ClientController extends AjaxBaseController {

    protected static Logger logger = LoggerFactory.getLogger(ClientController.class);

    public static final String VIEW_PREFIX = "/rest/client";

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientAdditionalService clientAdditionalService;


    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    public PackVo create(@RequestBody ClientVo client) {
        clientService.create(client);
        return new PackVo();
    }

    @RequestMapping(value = DELETE, method = RequestMethod.GET)
    public PackVo delete(Long id) {
        clientService.delete(id);
        return new PackVo();
    }

    @RequestMapping(value = UPDATE, method = RequestMethod.POST)
    public PackVo update(@RequestBody ClientVo client) {
        clientService.update(client);
        return new PackVo();
    }

    @RequestMapping(value = FIND, method = RequestMethod.GET)
    @HystrixCommand(commandKey = "Client")
    public PackVo<ClientVo> find(Long id) {
        PackVo<ClientVo> packVo = new PackVo<>();
        ClientVo clientVo = clientService.find(id);
        packVo.setVo(clientVo);
        return packVo;
    }

    @RequestMapping(value = LIST_PAGINATION, method = RequestMethod.POST)
    @HystrixCommand(commandKey = "Client")
    public PackVo<ClientVo> listPagination(@RequestBody ClientSo clientSo) {
        PackVo<ClientVo> packVo = new PackVo<>();
        packVo.setPageList(clientService.listPagination(clientSo));
        return packVo;
    }

}

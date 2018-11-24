package com.xth.controller.store;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xth.controller.AjaxBaseController;
import com.xth.model.base.PackVo;
import com.xth.model.so.store.StoreSo;
import com.xth.model.vo.store.ExportStoreVo;
import com.xth.model.vo.store.StoreVo;
import com.xth.service.store.additional.StoreAdditionalService;
import com.xth.service.store.common.StoreService;
import com.xth.service.store.export.ExportStore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 系统生成Controller
 *
 * @author admin
 * @date 2018/09/16
 */
@RestController
@RequestMapping(StoreController.VIEW_PREFIX)
@Api(tags = {"场馆接口"})
public class StoreController extends AjaxBaseController {

    public static final String VIEW_PREFIX = "/rest/store";

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreAdditionalService storeAdditionalService;

    @Autowired
    private ExportStore exportStore;

    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    public PackVo create(@RequestBody StoreVo store) {
        storeService.create(store);
        return new PackVo();
    }

    @RequestMapping(value = DELETE, method = RequestMethod.GET)
    public PackVo delete(Long id) {
        storeService.delete(id);
        return new PackVo();
    }

    @RequestMapping(value = UPDATE, method = RequestMethod.POST)
    public PackVo update(@RequestBody StoreVo store) {
        storeService.update(store);
        return new PackVo();
    }

    @RequestMapping(value = FIND, method = RequestMethod.GET)
    @HystrixCommand(commandKey = "Store")
    @ApiOperation(value = "获取场馆信息", notes = "根据场馆id查询场馆详细信息")
    public PackVo<StoreVo> find(Long id) {
        PackVo<StoreVo> packVo = new PackVo<>();
        StoreVo storeVo = storeService.find(id);
        packVo.setVo(storeVo);
        return packVo;
    }

    @RequestMapping(value = LIST_PAGINATION, method = RequestMethod.POST)
    @ApiOperation(value = "获取场馆列表", notes = "")
    public PackVo<StoreVo> listPagination(@RequestBody StoreSo storeSo) {
        PackVo<StoreVo> packVo = new PackVo<>();
        packVo.setPageList(storeService.listPagination(storeSo));
        return packVo;
    }

    @RequestMapping(value = EXPORT, method = RequestMethod.POST)
    @ApiOperation(value = "场馆导出", notes = "场馆导出")
    public PackVo<String> export(@RequestBody StoreSo storeSo) throws Exception {
        PackVo<String> packVo = new PackVo<>();
        packVo.setVo(exportStore.export(storeSo));
        return packVo;
    }

    @RequestMapping(value = IMPORT, method = RequestMethod.POST)
    @ApiOperation(value = "场馆导入", notes = "场馆导入")
    public PackVo<ExportStoreVo> batchImport(@RequestParam(value = "file", required = false) MultipartFile file) {
        PackVo<ExportStoreVo> packVo = new PackVo<>();
        storeService.batchImport(file);
        return packVo;
    }

}

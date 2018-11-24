package com.xth.controller.city;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xth.controller.AjaxBaseController;
import com.xth.model.base.PackVo;
import com.xth.model.so.city.CitySo;
import com.xth.model.so.city.LocationSo;
import com.xth.model.vo.city.CityVo;
import com.xth.service.city.additional.CityAdditionalService;
import com.xth.service.city.common.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统生成Controller
 *
 * @author admin
 * @date 2018/09/26
 */
@RestController
@RequestMapping(CityController.VIEW_PREFIX)
@Api(tags = {"城市接口"})
public class CityController extends AjaxBaseController {

    public static final String VIEW_PREFIX = "/rest/city";

    @Autowired
    private CityService cityService;

    @Autowired
    private CityAdditionalService cityAdditionalService;


    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    public PackVo create(@RequestBody CityVo city) {
        cityService.create(city);
        return new PackVo();
    }

    @RequestMapping(value = DELETE, method = RequestMethod.GET)
    public PackVo delete(Long id) {
        cityService.delete(id);
        return new PackVo();
    }

    @RequestMapping(value = UPDATE, method = RequestMethod.POST)
    public PackVo update(@RequestBody CityVo city) {
        cityService.update(city);
        return new PackVo();
    }

    @RequestMapping(value = FIND, method = RequestMethod.GET)
    @HystrixCommand(commandKey = "City")
    public PackVo<CityVo> find(Long id) {
        PackVo<CityVo> packVo = new PackVo<>();
        CityVo cityVo = cityService.find(id);
        packVo.setVo(cityVo);
        return packVo;
    }

    @RequestMapping(value = LIST_PAGINATION, method = RequestMethod.POST)
    @HystrixCommand(commandKey = "City")
    public PackVo<CityVo> listPagination(@RequestBody CitySo citySo) {
        PackVo<CityVo> packVo = new PackVo<>();
        packVo.setPageList(cityService.listPagination(citySo));
        return packVo;
    }

}

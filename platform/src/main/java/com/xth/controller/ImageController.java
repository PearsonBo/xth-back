package com.xth.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xth.model.base.PackVo;
import com.xth.service.rebate.common.RebateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * 系统生成Controller
 *
 * @author admin
 * @date 2018/09/26
 */
@RestController
@RequestMapping(ImageController.VIEW_PREFIX)
@Api(tags = {"上传图片接口"})
public class ImageController extends AjaxBaseController {

    public static final String VIEW_PREFIX = "/rest/common";
    private static final String UPLOAD_IMG = "/uploadImg";

    @Autowired
    private RebateService rebateService;

    @RequestMapping(value = UPLOAD_IMG, method = {RequestMethod.POST, RequestMethod.GET})
    @HystrixCommand(commandKey = "uploadImg")
    @ApiOperation(value = "上传图片", notes = "imgType参数用于辨别图片类型，方便图片分开存储。" +
            "枚举有：store、coach、rebate、level、default，非必填，返回服务端图片路径")
    public PackVo<String> uploadImg(HttpServletRequest request,
                                    @RequestParam(value = "file", required = false) MultipartFile file,
                                    @RequestParam(value = "imgType", required = false) String imgType)
            throws UnsupportedEncodingException {
        PackVo<String> packVo = new PackVo<>();
        String path = rebateService.uploadImage(request, file, imgType);
        packVo.setVo(path);
        return packVo;
    }
}

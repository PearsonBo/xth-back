package com.xth.service.rebate.common;


import com.xth.model.base.PageList;
import com.xth.model.so.rebate.RebateSo;
import com.xth.model.vo.rebate.RebateVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * 系统生成Service类
 *
 * @author admin
 * @date 2018/09/29
 */
public interface RebateService {

    /**
     * 新增
     *
     * @param rebate 新增对象
     * @return 新增对象的id
     */
    Long create(RebateVo rebate);

    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    void delete(Long id);

    /**
     * 修改
     *
     * @param rebate 修改对象
     */
    void update(RebateVo rebate);

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    RebateVo find(Long id);

    /**
     * 按条件查询
     *
     * @param rebateSo 查询条件
     * @return 查询结果
     */
    PageList<RebateVo> listPagination(RebateSo rebateSo);


    /**
     * 计算返利
     *
     * @param rebateVo
     * @return
     */
    String calRebate(RebateVo rebateVo);

    /**
     * 上传图片
     *
     * @param request
     * @param file
     * @param imgType
     */
    String uploadImage(HttpServletRequest request, MultipartFile file, String imgType) throws UnsupportedEncodingException;

    /**
     * 通过返利
     *
     * @param id
     */
    void pass(Long id);

    /**
     * 废弃返利
     *
     * @param id
     */
    void inactive(Long id);
}


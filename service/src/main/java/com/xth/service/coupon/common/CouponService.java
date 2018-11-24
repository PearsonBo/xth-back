package com.xth.service.coupon.common;


import com.xth.model.base.PageList;
import com.xth.model.so.coupon.CouponSo;
import com.xth.model.vo.coupon.CouponVo;

/**
 * 系统生成Service类
 *
 * @author admin
 * @date 2018/09/25
 */
public interface CouponService {

    /**
     * 新增
     *
     * @param coupon 新增对象
     * @return 新增对象的id
     */
    Long create(CouponVo coupon);

    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    void delete(Long id);

    /**
     * 修改
     *
     * @param coupon 修改对象
     */
    void update(CouponVo coupon);

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    CouponVo find(Long id);

    /**
     * 按条件查询
     *
     * @param couponSo 查询条件
     * @return 查询结果
     */
    PageList<CouponVo> listPagination(CouponSo couponSo);


    PageList<CouponVo> listPagination4App(CouponSo couponSo);
}


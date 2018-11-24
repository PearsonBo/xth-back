package com.xth.service.client.common;


import com.xth.model.base.PageList;
import com.xth.model.so.client.ClientCouponSo;
import com.xth.model.vo.client.ClientCouponVo;

/**
 * 系统生成Service类
 *
 * @author admin
 * @date 2018/09/26
 */
public interface ClientCouponService {

    /**
     * 新增
     *
     * @param clientCoupon 新增对象
     * @return 新增对象的id
     */
    Long create(ClientCouponVo clientCoupon);

    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    void delete(Long id);

    /**
     * 修改
     *
     * @param clientCoupon 修改对象
     */
    void update(ClientCouponVo clientCoupon);

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    ClientCouponVo find(Long id);

    /**
     * 按条件查询
     *
     * @param clientCouponSo 查询条件
     * @return 查询结果
     */
    PageList<ClientCouponVo> listPagination(ClientCouponSo clientCouponSo);


    /**
     * 客户领取优惠券
     *
     * @param clientCouponVo
     */
    void receiveCoupon(ClientCouponVo clientCouponVo);
}


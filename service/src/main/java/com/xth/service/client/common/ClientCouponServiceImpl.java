package com.xth.service.client.common;

import com.xth.dao.client.ClientCouponDao;
import com.xth.dao.coupon.CouponDao;
import com.xth.model.base.PageList;
import com.xth.model.bo.client.ClientCoupon;
import com.xth.model.exception.BizException;
import com.xth.model.exception.ExceptionType;
import com.xth.model.so.client.ClientCouponSo;
import com.xth.model.vo.client.ClientCouponVo;
import com.xth.model.vo.coupon.CouponVo;
import com.xth.service.AbstractTransactionalService;
import com.xth.service.CheckEmptyHelper;
import com.xth.service.client.helper.ClientCouponServiceHelper;
import com.xth.service.coupon.helper.CouponServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * 系统生成Service实现类
 *
 * @author admin
 * @date 2018/09/26
 */
@Service
public class ClientCouponServiceImpl extends AbstractTransactionalService implements ClientCouponService {

    @Autowired
    private ClientCouponDao clientCouponDao;

    @Autowired
    private ClientCouponServiceHelper clientCouponServiceHelper;

    @Autowired
    private CouponDao couponDao;

    @Autowired
    private CouponServiceHelper couponServiceHelper;


    /**
     * 新增
     *
     * @param clientCoupon 新增对象
     * @return 新增对象的id
     */
    @Override
    public Long create(ClientCouponVo clientCoupon) {
        checkBeforeCreate(clientCoupon);
        ClientCoupon clientCouponBo = dozer.convert(clientCoupon, ClientCoupon.class);
        return clientCouponDao.insert(clientCouponBo);
    }

    private void checkBeforeCreate(ClientCouponVo clientCoupon) {
        checkRequired(clientCoupon);
        checkDuplicate4Create(clientCoupon);
    }


    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    @Override
    public void delete(Long id) {
        clientCouponDao.delete(id);
    }

    /**
     * 修改
     *
     * @param clientCoupon 修改对象
     */
    @Override
    public void update(ClientCouponVo clientCoupon) {
        checkBeforeUpdate(clientCoupon);
        ClientCoupon clientCouponBo = dozer.convert(clientCoupon, ClientCoupon.class);
        clientCouponDao.update(clientCouponBo);
    }

    private void checkBeforeUpdate(ClientCouponVo clientCoupon) {
        checkRequired(clientCoupon);
        checkDuplicate4Update(clientCoupon);
    }

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    @Override
    public ClientCouponVo find(Long id) {
        return clientCouponDao.findVo(id);
    }

    /**
     * 按条件查询
     *
     * @param clientCouponSo 查询条件
     * @return 查询结果
     */
    @Override
    public PageList<ClientCouponVo> listPagination(ClientCouponSo clientCouponSo) {
        List<ClientCouponVo> list = clientCouponDao.listPaginationVoBySo(clientCouponSo);
        if (!CollectionUtils.isEmpty(list)) {
            for (ClientCouponVo clientCouponVo : list) {
                Long couponId = clientCouponVo.getCouponId();
                CouponVo couponVo = couponDao.findVo(couponId);
                if (couponVo != null) {
                    couponServiceHelper.fillCoupon(couponVo);
                    clientCouponVo.setCouponVo(couponVo);
                }

            }
        }
        int count = clientCouponDao.countBySo(clientCouponSo);
        return new PageList<>(list, count);
    }

    @Override
    public void receiveCoupon(ClientCouponVo clientCouponVo) {
        CheckEmptyHelper.checkObject("客户优惠券对象不能为空", clientCouponVo);
        CheckEmptyHelper.checkLong("优惠券couponId不能为空", clientCouponVo.getCouponId());

        clientCouponVo.setIsUsed(Boolean.FALSE);
        clientCouponVo.setAvailable(Boolean.TRUE);

        checkClientCanReceive(clientCouponVo);
        create(clientCouponVo);
    }

    /**
     * 检测用户是否已领
     *
     * @param clientCouponVo
     */
    private void checkClientCanReceive(ClientCouponVo clientCouponVo) {
        Long clientId = clientCouponVo.getClientId();
        Long couponId = clientCouponVo.getCouponId();
        List<ClientCouponVo> vos = clientCouponServiceHelper.findByClientIdAndCouponId(couponId, clientId);
        if (vos != null && vos.size() > 0) {
            throw new BizException("您已领取该优惠券", ExceptionType.BLANK);
        }
    }

    /**
     * 检查必填字段
     */
    private void checkRequired(ClientCouponVo clientCoupon) {

    }

    /**
     * 检查字段唯一性（创建）
     */
    private void checkDuplicate4Create(ClientCouponVo clientCoupon) {

    }


    /**
     * 检查字段唯一性（更新）
     */
    private void checkDuplicate4Update(ClientCouponVo clientCoupon) {

    }


}

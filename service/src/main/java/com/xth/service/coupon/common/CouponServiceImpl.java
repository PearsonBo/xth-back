package com.xth.service.coupon.common;

import com.xth.dao.client.ClientCouponDao;
import com.xth.dao.coupon.CouponDao;
import com.xth.dao.store.StoreDao;
import com.xth.model.base.AbstractVo;
import com.xth.model.base.PageList;
import com.xth.model.bo.client.ClientCoupon;
import com.xth.model.bo.coupon.Coupon;
import com.xth.model.enums.CouponApplyScope;
import com.xth.model.enums.DiscountType;
import com.xth.model.exception.BizException;
import com.xth.model.exception.ExceptionType;
import com.xth.model.so.client.ClientCouponSo;
import com.xth.model.so.coupon.CouponSo;
import com.xth.model.vo.coupon.CouponVo;
import com.xth.service.AbstractTransactionalService;
import com.xth.service.CheckEmptyHelper;
import com.xth.service.city.helper.CityServiceHelper;
import com.xth.service.coupon.helper.CouponServiceHelper;
import com.xth.service.store.helper.StoreServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


/**
 * 系统生成Service实现类
 *
 * @author admin
 * @date 2018/09/25
 */
@Service
public class CouponServiceImpl extends AbstractTransactionalService implements CouponService {

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private CouponDao couponDao;

    @Autowired
    private CityServiceHelper cityServiceHelper;

    @Autowired
    private StoreServiceHelper storeServiceHelper;

    @Autowired
    private CouponServiceHelper couponServiceHelper;

    @Autowired
    private ClientCouponDao clientCouponDao;

    /**
     * 新增
     *
     * @param coupon 新增对象
     * @return 新增对象的id
     */
    @Override
    public Long create(CouponVo coupon) {
        checkBeforeCreate(coupon);
        prepareBeforeCreate(coupon);
        Coupon couponBo = dozer.convert(coupon, Coupon.class);
        return couponDao.insert(couponBo);
    }

    private void prepareBeforeCreate(CouponVo coupon) {
        fillCityId(coupon);
        coupon.setAvailable(Boolean.TRUE);
        coupon.setEffectTime(new Date());
    }

    private void checkBeforeCreate(CouponVo coupon) {
        checkRequired(coupon);
        checkDuplicate4Create(coupon);
        checkContent(coupon);
    }

    private void checkContent(CouponVo coupon) {
        String content = coupon.getContent();

        DiscountType discountType = coupon.getType();
        switch (discountType) {
            case RATE:
                boolean isInt = isInteger(content);
                if (!isInt) {
                    throw new BizException("折扣类型优惠券必须是数字", ExceptionType.VIOLATE_BIZ_CHECK);
                }
                break;
            case FULL_REDUCTION:
                try {
                    boolean contains = content.contains("|");
                    if (!contains) {
                        throw new BizException("满减类型优惠券请按照200|100格式输入", ExceptionType.VIOLATE_BIZ_CHECK);
                    }
                    String[] contentList = content.split("\\|");
                    for (String s : contentList) {
                        boolean integer = isInteger(s);
                        if (!integer) {
                            throw new BizException("满减类型优惠券请按照200|100格式输入", ExceptionType.VIOLATE_BIZ_CHECK);
                        }
                    }
                } catch (Exception e) {
                    throw new BizException("满减类型优惠券请按照200|100格式输入", ExceptionType.VIOLATE_BIZ_CHECK);
                }

            default:
                ;
                break;
        }
    }

    private static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    @Override
    public void delete(Long id) {
        ClientCouponSo clientCouponSo = new ClientCouponSo();
        clientCouponSo.setCouponId(id);
        List<ClientCoupon> couponList = clientCouponDao.listBoBySo(clientCouponSo);
        if (!CollectionUtils.isEmpty(couponList)) {
            for (ClientCoupon clientCoupon : couponList) {
                clientCoupon.setCouponId(null);
                clientCouponDao.update(clientCoupon);
            }
        }
        couponDao.delete(id);
    }

    /**
     * 修改
     *
     * @param coupon 修改对象
     */
    @Override
    public void update(CouponVo coupon) {
        checkBeforeUpdate(coupon);
        fillCityId(coupon);
        Coupon couponBo = dozer.convert(coupon, Coupon.class);
        couponDao.update(couponBo);
    }

    private void fillCityId(CouponVo coupon) {
        if (coupon.getApplyCityId() != null) {
            coupon.setCouponApplyScope(CouponApplyScope.CITY);
            coupon.setCityId(coupon.getApplyCityId());
        }
        if (coupon.getApplyStoreId() != null) {
            coupon.setCouponApplyScope(CouponApplyScope.STORE);
            Long cityId = storeDao.findBo(coupon.getApplyStoreId()).getCityId();
            coupon.setCityId(cityId);
        }
    }

    private void checkBeforeUpdate(CouponVo coupon) {
        checkRequired(coupon);
        checkDuplicate4Update(coupon);
    }

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    @Override
    public CouponVo find(Long id) {
        return couponDao.findVo(id);
    }

    /**
     * 按条件查询
     *
     * @param couponSo 查询条件
     * @return 查询结果
     */
    @Override
    public PageList<CouponVo> listPagination(CouponSo couponSo) {
        List<CouponVo> list = couponDao.listPaginationVoBySo(couponSo);
        if (!CollectionUtils.isEmpty(list)) {
            for (CouponVo couponVo : list) {
                couponServiceHelper.fillCoupon(couponVo);
            }
        }
        int count = couponDao.countBySo(couponSo);
        return new PageList<>(list, count);
    }

    @Override
    public PageList<CouponVo> listPagination4App(CouponSo couponSo) {
        Long cityId = couponSo.getCityId();
        CheckEmptyHelper.checkObject("缺少城市id", cityId);
        log.info("cityId:" + cityId);

        List<CouponVo> list = couponDao.listPaginationVoBySo(couponSo);
        if (!CollectionUtils.isEmpty(list)) {
            for (CouponVo couponVo : list) {
                couponServiceHelper.fillCoupon(couponVo);
            }
        }
        int count = couponDao.countBySo(couponSo);
        return new PageList<>(list, count);
    }

    /**
     * 检查必填字段
     */
    private void checkRequired(CouponVo coupon) {
        CheckEmptyHelper.checkObject("内容不可为空", coupon.getContent());
        CheckEmptyHelper.checkObject("优惠类型不可为空", coupon.getType());
        if (coupon.getApplyStoreId() == null && coupon.getApplyCityId() == null) {
            throw new BizException("适用范围内容不可为空", ExceptionType.VIOLATE_BIZ_CHECK);
        }
    }

    /**
     * 检查字段唯一性（创建）
     */
    private void checkDuplicate4Create(CouponVo coupon) {

    }


    /**
     * 检查字段唯一性（更新）
     */
    private void checkDuplicate4Update(CouponVo coupon) {

    }


}

package com.xth.service.rebate.common;

import com.xth.dao.client.ClientCouponDao;
import com.xth.dao.coupon.CouponDao;
import com.xth.dao.rebate.RebateDao;
import com.xth.dao.store.StoreDao;
import com.xth.model.base.AbstractVo;
import com.xth.model.base.PageList;
import com.xth.model.bo.client.ClientCoupon;
import com.xth.model.bo.coupon.Coupon;
import com.xth.model.bo.rebate.Rebate;
import com.xth.model.bo.store.Store;
import com.xth.model.enums.DiscountType;
import com.xth.model.enums.RebateStatusEnum;
import com.xth.model.exception.BizException;
import com.xth.model.exception.ExceptionType;
import com.xth.model.so.rebate.RebateSo;
import com.xth.model.vo.client.ClientCouponVo;
import com.xth.model.vo.rebate.RebateVo;
import com.xth.model.vo.store.StoreVo;
import com.xth.service.AbstractTransactionalService;
import com.xth.service.CheckEmptyHelper;
import com.xth.service.client.helper.ClientCouponServiceHelper;
import com.xth.service.store.helper.StoreServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;


/**
 * 系统生成Service实现类
 *
 * @author admin
 * @date 2018/09/29
 */
@Service
public class RebateServiceImpl extends AbstractTransactionalService implements RebateService {

    private static final String URL = "http://xthapi-prod.isagr.com";

    @Value("${file.wechat.upload.path}")
    private String uploadPath;

    @Autowired
    private RebateDao rebateDao;

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private CouponDao couponDao;

    @Autowired
    private ClientCouponServiceHelper clientCouponServiceHelper;

    @Autowired
    private ClientCouponDao clientCouponDao;

    @Autowired
    private StoreServiceHelper storeServiceHelper;

    /**
     * 新增
     *
     * @param rebate 新增对象
     * @return 新增对象的id
     */
    @Override
    public Long create(RebateVo rebate) {
        checkBeforeCreate(rebate);
        rebate.setStatus(RebateStatusEnum.SUBMITED);
        Long couponId = rebate.getCouponId();
        Long clientId = rebate.getClientId();
        Long storeId = rebate.getStoreId();

        RebateSo rebateSo = new RebateSo();
        rebateSo.setStoreId(storeId);
        rebateSo.setStatus(RebateStatusEnum.SUBMITED);
        rebateSo.setClientId(clientId);

        List<RebateVo> clientSubmmitedRebate = rebateDao.listVoBySo(rebateSo);
        if (!CollectionUtils.isEmpty(clientSubmmitedRebate)) {
            throw new BizException("您已提交该场馆的返利，请等待审批通过。", ExceptionType.BLANK);
        }

        List<ClientCouponVo> vos = clientCouponServiceHelper.findByClientIdAndCouponId(couponId, clientId);
        if (!CollectionUtils.isEmpty(vos)) {
            ClientCouponVo clientCouponVo = vos.get(0);
            clientCouponVo.setAvailable(Boolean.FALSE);
            clientCouponVo.setIsUsed(Boolean.TRUE);
            log.info("更新用户领取的优惠券未使用过");
            clientCouponDao.update(dozer.convert(clientCouponVo, ClientCoupon.class));
        }

        Rebate rebateBo = dozer.convert(rebate, Rebate.class);
        return rebateDao.insert(rebateBo);
    }

    private void checkBeforeCreate(RebateVo rebate) {
        checkRequired(rebate);
        checkDuplicate4Create(rebate);
    }


    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    @Override
    public void delete(Long id) {
        rebateDao.delete(id);
    }

    /**
     * 修改
     *
     * @param rebate 修改对象
     */
    @Override
    public void update(RebateVo rebate) {
        checkBeforeUpdate(rebate);
        Rebate rebateBo = dozer.convert(rebate, Rebate.class);
        rebateDao.update(rebateBo);
    }

    private void checkBeforeUpdate(RebateVo rebate) {
        checkRequired(rebate);
        checkDuplicate4Update(rebate);
    }

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    @Override
    public RebateVo find(Long id) {
        return rebateDao.findVo(id);
    }

    /**
     * 按条件查询
     *
     * @param rebateSo 查询条件
     * @return 查询结果
     */
    @Override
    public PageList<RebateVo> listPagination(RebateSo rebateSo) {
        List<RebateVo> list = rebateDao.listPaginationVoBySo(rebateSo);
        if (!CollectionUtils.isEmpty(list)) {
            for (RebateVo rebateVo : list) {
                Long storeId = rebateVo.getStoreId();
                StoreVo storeVo = storeDao.findVo(storeId);
                if (storeVo == null) {
                    continue;
                }
                storeVo.setDiscountContentMessage(storeServiceHelper.getDisCountMessage(storeVo));
                rebateVo.setStoreVo(storeVo);
                rebateVo.setStoreName(storeVo.getName());
            }
        }
        int count = rebateDao.countBySo(rebateSo);
        return new PageList<>(list, count);
    }

    @Override
    public String calRebate(RebateVo rebateVo) {
        BigDecimal consumeMoney = rebateVo.getConsumeMoney();
        CheckEmptyHelper.checkObject("消费金额未传", consumeMoney);
        log.info("消费金额" + consumeMoney);
        BigDecimal returnMoney = new BigDecimal(0);

        Long storeId = rebateVo.getStoreId();
        CheckEmptyHelper.checkLong("场馆id未传", storeId);
        Store store = storeDao.findBo(storeId);
        String discountContent = store.getDiscountContent();
        DiscountType discountType = store.getDiscountType();
        log.info("场馆折扣类型【" + discountType + "】折扣内容【" + discountContent + "】");
        switch (discountType) {
            case RATE:
                returnMoney = consumeMoney.multiply(new BigDecimal(discountContent)).multiply(new BigDecimal(0.01));
                break;
            case FULL_REDUCTION:
                String[] split = discountContent.split("\\|");
                if (consumeMoney.compareTo(new BigDecimal(split[0])) == -1) {
                    returnMoney = consumeMoney;
                } else {
                    returnMoney = new BigDecimal(split[1]);
                }
                break;
            default:
                break;
        }

        Long couponId = rebateVo.getCouponId();
        CheckEmptyHelper.checkLong("优惠券id未传", couponId);
        Coupon coupon = couponDao.findBo(couponId);
        String content = coupon.getContent();
        DiscountType type = coupon.getType();

        log.info("优惠券折扣类型【" + type + "】折扣内容【" + content + "】");
        switch (type) {
            case FULL_REDUCTION:
                String[] split = content.split("\\|");
                if (consumeMoney.compareTo(new BigDecimal(split[0])) == -1) {
                    returnMoney = returnMoney;
                } else {
                    returnMoney = returnMoney.add(new BigDecimal(split[1]));
                }
                break;
            default:
                break;
        }

        String returnMoneyStr = returnMoney.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString();
        log.info("返回金额" + returnMoneyStr);
        return returnMoneyStr;
    }

    @Override
    public String uploadImage(HttpServletRequest request, MultipartFile file, String imgType) {
        log.info("开始执行upload");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String path = null;
            String type = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                    imgType = imgType == null ? "default" : imgType;
                    path = uploadPath + "uploads/" + imgType + "/" + trueFileName;
                    log.info("img path: " + path);
                    try {
                        file.transferTo(new File(path));
                        return URL + path;
                    } catch (IOException e) {
                        log.error("上传图片报错" + e.getMessage(), e);
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void pass(Long id) {
        Rebate rebate = rebateDao.findBo(id);
        CheckEmptyHelper.checkObject("没有找到该id【" + id + "】的返利订单", rebate);
        RebateStatusEnum status = rebate.getStatus();
        if (!RebateStatusEnum.SUBMITED.equals(status)) {
            throw new BizException("只有已提交的返利才可以通过返利", ExceptionType.VIOLATE_BIZ_RULE);
        }
        rebate.setStatus(RebateStatusEnum.DONE);
        rebateDao.update(rebate);
    }

    @Override
    public void inactive(Long id) {
        Rebate rebate = rebateDao.findBo(id);
        CheckEmptyHelper.checkObject("没有找到该id【" + id + "】的返利订单", rebate);
        RebateStatusEnum status = rebate.getStatus();
        if (!RebateStatusEnum.SUBMITED.equals(status)) {
            throw new BizException("只有已提交的返利才可以废弃返利", ExceptionType.VIOLATE_BIZ_RULE);
        }
        rebate.setStatus(RebateStatusEnum.INACTIVE);
        rebateDao.update(rebate);
    }

    /**
     * 检查必填字段
     */
    private void checkRequired(RebateVo rebate) {

    }

    /**
     * 检查字段唯一性（创建）
     */
    private void checkDuplicate4Create(RebateVo rebate) {

    }


    /**
     * 检查字段唯一性（更新）
     */
    private void checkDuplicate4Update(RebateVo rebate) {

    }


}

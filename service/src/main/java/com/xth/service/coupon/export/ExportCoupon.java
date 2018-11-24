package com.xth.service.coupon.export;

import com.xth.dao.coupon.CouponDao;
import com.xth.model.so.coupon.CouponSo;
import com.xth.model.vo.coupon.CouponVo;
import com.xth.service.BaseExport;
import com.xth.service.ExcelData;
import com.xth.service.ExportExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统生成导出类
 *
 * @author admin
 * @date 2018/09/25
 */
@Service
public class ExportCoupon extends BaseExport<CouponSo> {

    private static final String TEMPLATE = "T_COUPON.xml";
    private static final String STORE_NAME = "T_COUPON";

    @Autowired
    private CouponDao couponDao;

    @Autowired
    private ExportExcelUtils exportExcelUtils;

    @Override
    public String export(CouponSo so) throws Exception {
        List<CouponVo> couponVoList = couponDao.listVoBySo(so);


        Map<String, List<?>> dataMap = new HashMap<>();
        dataMap.put("couponVoList", couponVoList);

        ExcelData data = new ExcelData();
        data.setName("hello");
        List<String> titles = new ArrayList();
        titles.add("名称");
        titles.add("优惠类型");
        titles.add("优惠内容");
        titles.add("生效时间");
        titles.add("过期时间");
        titles.add("适用范围");
        titles.add("是否可用");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        List<Object> row = new ArrayList();

        if (!CollectionUtils.isEmpty(couponVoList)) {
            for (CouponVo couponVo : couponVoList) {
                row.add(couponVo.getName());
                row.add(couponVo.getType());
                row.add(couponVo.getContent());
                row.add(couponVo.getEffectTime());
                row.add(couponVo.getExpireTime());
                row.add(couponVo.getCouponApplyScope());
                row.add(couponVo.getAvailable());
                rows.add(row);
            }
        }

        data.setRows(rows);
        String fileUrl = exportExcelUtils.exportExcel("优惠券.xlsx", data);

        return fileUrl;
    }

}

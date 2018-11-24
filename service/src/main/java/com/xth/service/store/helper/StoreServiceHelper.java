package com.xth.service.store.helper;

import com.google.common.collect.Lists;
import com.xth.dao.store.StoreDao;
import com.xth.model.enums.DiscountType;
import com.xth.model.exception.BizException;
import com.xth.model.exception.ExceptionType;
import com.xth.model.vo.city.CityVo;
import com.xth.model.vo.store.StoreVo;
import com.xth.service.AbstractNoTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统生成Service帮助类
 *
 * @author admin
 * @date 2018/09/16
 */
@Service
public class StoreServiceHelper extends AbstractNoTransactionalService {

    @Autowired
    private StoreDao storeDao;

    private static final double EARTH_RADIUS = 6378.137;

    public static void main(String[] args) {
        double distance = new StoreServiceHelper().distance("120.13", "30.21",
                "118.21", "28.11");
        System.out.println(distance);
    }

    public double distance(String nowLng, String nowLat, String longitude, String latitude) {
        double long1;
        double lat1;
        double long2;
        double lat2;
        double a, b, sa2, sb2, d = 0;
        try {
            long1 = Double.valueOf(nowLng);
            lat1 = Double.valueOf(nowLat);
            long2 = Double.valueOf(longitude);
            lat2 = Double.valueOf(latitude);
            lat1 = rad(lat1);
            lat2 = rad(lat2);
            a = lat1 - lat2;
            b = rad(long1 - long2);
            sa2 = Math.sin(a / 2.0);
            sb2 = Math.sin(b / 2.0);
            d = 2 * EARTH_RADIUS
                    * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
                    * Math.cos(lat2) * sb2 * sb2));

        } catch (Exception e) {

            e.printStackTrace();
        }
        return d;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public StoreVo findStoreByStoreId(Long storeId) {
        List<StoreVo> storeVos = storeDao.listVoByIdList(Lists.newArrayList(storeId));
        if (storeVos == null || storeVos.size() > 1) {
            throw new BizException(String.format("场馆id【%d】不存在或者存在多条", storeId), ExceptionType.VIOLATE_BIZ_RULE);
        }
        return storeVos.get(0);
    }

    /**
     * 拼接折扣展示信息
     *
     * @param storeVo
     * @return
     */
    public String getDisCountMessage(StoreVo storeVo) {
        String discountContent = storeVo.getDiscountContent();
        DiscountType discountType = storeVo.getDiscountType();
        if (discountType == null) {
            return "";
        }
        String discountContentMsg = null;
        switch (discountType) {
            case RATE:
                discountContentMsg = "最高返利" + discountContent + "%";
                break;
            case FULL_REDUCTION:
                discountContentMsg = "满" + discountContent.split("|")[0] + "减" + discountContent.split("|")[1] + "元";
                break;
            default:
                break;
        }
        return discountContentMsg;
    }
}

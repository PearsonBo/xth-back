package com.xth.service.city.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xth.dao.city.CityDao;
import com.xth.dao.coach.CoachDao;
import com.xth.dao.coupon.CouponDao;
import com.xth.dao.store.StoreDao;
import com.xth.model.base.AbstractVo;
import com.xth.model.base.PageList;
import com.xth.model.bo.city.City;
import com.xth.model.bo.coach.Coach;
import com.xth.model.bo.coupon.Coupon;
import com.xth.model.bo.store.Store;
import com.xth.model.exception.BizException;
import com.xth.model.exception.ExceptionType;
import com.xth.model.so.city.CitySo;
import com.xth.model.so.city.LocationSo;
import com.xth.model.so.coach.CoachSo;
import com.xth.model.so.coupon.CouponSo;
import com.xth.model.so.store.StoreSo;
import com.xth.model.vo.city.CityVo;
import com.xth.service.AbstractTransactionalService;
import com.xth.util.LocationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 系统生成Service实现类
 *
 * @author admin
 * @date 2018/09/26
 */
@Service
public class CityServiceImpl extends AbstractTransactionalService implements CityService {

    @Autowired
    private CityDao cityDao;

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private CoachDao coachDao;

    @Autowired
    private CouponDao couponDao;

    /**
     * 新增
     *
     * @param city 新增对象
     * @return 新增对象的id
     */
    @Override
    public Long create(CityVo city) {
        checkBeforeCreate(city);
        City cityBo = dozer.convert(city, City.class);
        return cityDao.insert(cityBo);
    }

    private void checkBeforeCreate(CityVo city) {
        checkRequired(city);
        checkDuplicate4Create(city);
    }


    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    @Override
    public void delete(Long id) {
        StoreSo storeSo = new StoreSo();
        storeSo.setCityId(id);
        List<Store> storeList = storeDao.listBoBySo(storeSo);
        if (!CollectionUtils.isEmpty(storeList)) {
            for (Store store : storeList) {
                store.setCityId(null);
                storeDao.update(store);
            }
        }

        CoachSo coachSo = new CoachSo();
        coachSo.setCityId(id);
        List<Coach> coachList = coachDao.listBoBySo(storeSo);
        if (!CollectionUtils.isEmpty(coachList)) {
            for (Coach coach : coachList) {
                coach.setCityId(null);
                coachDao.update(coach);
            }
        }

        CouponSo couponSo = new CouponSo();
        couponSo.setCityId(id);
        List<Coupon> couponList = couponDao.listBoBySo(couponSo);
        if (!CollectionUtils.isEmpty(couponList)) {
            for (Coupon coupon : couponList) {
                coupon.setCityId(null);
                couponDao.update(coupon);
            }
        }
        cityDao.delete(id);
    }

    /**
     * 修改
     *
     * @param city 修改对象
     */
    @Override
    public void update(CityVo city) {
        checkBeforeUpdate(city);
        City cityBo = dozer.convert(city, City.class);
        cityDao.update(cityBo);
    }

    private void checkBeforeUpdate(CityVo city) {
        checkRequired(city);
        checkDuplicate4Update(city);
    }

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    @Override
    public CityVo find(Long id) {
        return cityDao.findVo(id);
    }

    /**
     * 按条件查询
     *
     * @param citySo 查询条件
     * @return 查询结果
     */
    @Override
    public PageList<CityVo> listPagination(CitySo citySo) {
        List<CityVo> list = cityDao.listPaginationVoBySo(citySo);
        int count = cityDao.countBySo(citySo);
        return new PageList<>(list, count);
    }

    @Override
    public CityVo findCity(LocationSo locationSo) {
        String add = LocationUtil.getLocation(locationSo.getLongitude(), locationSo.getLatitude());
        JSONArray jsonArray = (JSONArray) JSONObject.parseObject(add, Map.class).get("addrList");
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        String cityName = jsonObject.getString("admName");
        log.info(String.format("根据经度【%s】纬度【%s】查询出来的城市是【%s】", locationSo.getLatitude(), locationSo.getLongitude(), cityName));

        List<CityVo> cityList = cityDao.listVoBySo(new CitySo());
        Stream<CityVo> cityVoStream = cityList.stream().filter(c -> cityName.indexOf(c.getName()) != -1);
        if (cityVoStream == null) {
            throw new BizException("您当前的城市未开通服务", ExceptionType.VIOLATE_BIZ_CHECK);
        }
        List<CityVo> citys = cityList.stream().filter(c -> cityName.indexOf(c.getName()) != -1).collect(Collectors.toList());
        return citys.get(0);
    }

    /**
     * 检查必填字段
     */
    private void checkRequired(CityVo city) {

    }

    /**
     * 检查字段唯一性（创建）
     */

    private void checkDuplicate4Create(CityVo city) {

    }


    /**
     * 检查字段唯一性（更新）
     */
    private void checkDuplicate4Update(CityVo city) {

    }


}

package com.xth.model.bo.city;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.xth.model.so.city.LocationSo;

import static com.xth.model.constant.CommonConstant.LATITUDE;
import static com.xth.model.constant.CommonConstant.LONGITUDE;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/10/4 0004 下午 17:21
 */
public class DefaultCityLocation {

    private Table<String, String, String> distance;

    public DefaultCityLocation() {
        Table<String, String, String> distance = HashBasedTable.create();
        // 北京
        distance.put("北京", LONGITUDE, "116.38");
        distance.put("北京", LATITUDE, "39.90");

        // 杭州市
        distance.put("杭州", LONGITUDE, "120.13");
        distance.put("杭州", LATITUDE, "30.27");

        // 合肥
        distance.put("合肥", LONGITUDE, "117.25");
        distance.put("合肥", LATITUDE, "31.83");
        this.distance = distance;
    }

    public LocationSo getDefaultCity(String cityName) {
        LocationSo locationSo = new LocationSo();
        locationSo.setLatitude(this.distance.get(cityName, LATITUDE));
        locationSo.setLongitude(this.distance.get(cityName, LONGITUDE));
        return locationSo;
    }

}

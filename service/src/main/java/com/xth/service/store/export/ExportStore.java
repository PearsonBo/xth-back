package com.xth.service.store.export;

import com.xth.dao.city.CityDao;
import com.xth.dao.store.StoreDao;
import com.xth.model.bo.city.City;
import com.xth.model.so.store.StoreSo;
import com.xth.model.vo.store.StoreVo;
import com.xth.service.BaseExport;
import com.xth.service.ExcelData;
import com.xth.service.ExportExcelUtils;
import com.xth.service.store.helper.StoreServiceHelper;
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
 * @date 2018/09/16
 */
@Service
public class ExportStore extends BaseExport<StoreSo> {

    private static final String TEMPLATE = "T_STORE.xml";
    private static final String STORE_NAME = "T_STORE";

    @Autowired
    private CityDao cityDao;

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private StoreServiceHelper storeServiceHelper;

    @Autowired
    private ExportExcelUtils exportExcelUtils;

    @Override
    public String export(StoreSo so) throws Exception {
        log.info("开始导出场馆");
        List<StoreVo> storeVoList = storeDao.listVoBySo(so);

        if (!CollectionUtils.isEmpty(storeVoList)) {
            storeVoList.forEach(p -> p.setDiscountContentMessage(storeServiceHelper.getDisCountMessage(p)));
            for (StoreVo storeVo : storeVoList) {
                storeVo.setDiscountContentMessage(storeServiceHelper.getDisCountMessage(storeVo));
                City city = cityDao.findBo(storeVo.getCityId());
                storeVo.setCityName(city == null ? "未知" : city.getName());
            }
        }

        Map<String, List<?>> dataMap = new HashMap<>();
        dataMap.put("storeVoList", storeVoList);

        ExcelData data = new ExcelData();
        data.setName("store");
        List<String> titles = new ArrayList();
        titles.add("场馆名称");
        titles.add("城市名称");
        titles.add("详细地址");
        titles.add("经度");
        titles.add("纬度");
        titles.add("联系人姓名");
        titles.add("联系人电话");
        titles.add("在馆学员人数");
        titles.add("星级");
        titles.add("场馆折扣");
        titles.add("是否热门");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();

        if (!CollectionUtils.isEmpty(storeVoList)) {
            for (StoreVo storeVo : storeVoList) {
                List<Object> row = new ArrayList();
                row.add(storeVo.getName());
                row.add(storeVo.getCityName());
                row.add(storeVo.getAddress());
                row.add(storeVo.getLongitude());
                row.add(storeVo.getLatitude());
                row.add(storeVo.getContactName());
                row.add(storeVo.getContactPhone());
                row.add(storeVo.getInStuNums());
                row.add(storeVo.getStarLevel());
                row.add(storeVo.getDiscountContent());
                row.add(storeVo.getIsHot());
                rows.add(row);
            }
        }

        data.setRows(rows);
        String fileUrl = exportExcelUtils.exportExcel("场馆.xlsx", data);
        log.info("导出场馆结束,url:" + fileUrl);
        return fileUrl;
    }

}

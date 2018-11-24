package com.xth.service.coach.export;

import com.xth.dao.coach.CoachDao;
import com.xth.model.so.coach.CoachSo;
import com.xth.model.vo.coach.CoachVo;
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
 * @date 2018/09/17
 */
@Service
public class ExportCoach extends BaseExport<CoachSo> {

    private static final String TEMPLATE = "T_COACH.xml";
    private static final String STORE_NAME = "T_COACH";

    @Autowired
    private CoachDao coachDao;

    @Autowired
    private ExportExcelUtils exportExcelUtils;

    @Override
    public String export(CoachSo so) throws Exception {
        log.info("开始导出教练");
        List<CoachVo> coachVoList = coachDao.listVoBySo(so);

        if (!CollectionUtils.isEmpty(coachVoList)) {
            for (CoachVo coachVo : coachVoList) {

            }
        }

        Map<String, List<?>> dataMap = new HashMap<>();
        dataMap.put("coachVoList", coachVoList);

        ExcelData data = new ExcelData();
        data.setName("coach");
        List<String> titles = new ArrayList();
        titles.add("姓名");
        titles.add("性别");
        titles.add("出生年份（到年）");
        titles.add("身份证号");
        titles.add("联系电话");
        titles.add("所属场馆");
        titles.add("特长描述");
        titles.add("星级");
        titles.add("是否是金牌教练");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();

        if (!CollectionUtils.isEmpty(coachVoList)) {
            for (CoachVo coachVo : coachVoList) {
                List<Object> row = new ArrayList();
                row.add(coachVo.getName());
                row.add(coachVo.getGender());
                row.add(coachVo.getBirthYear());
                row.add(coachVo.getIdNumber());
                row.add(coachVo.getPhone());
                row.add(coachVo.getStoreId());
                row.add(coachVo.getSpeciality());
                row.add(coachVo.getStarLevel());
                row.add(coachVo.getIsGold());
                rows.add(row);
            }
        }

        data.setRows(rows);
        String fileUrl = exportExcelUtils.exportExcel("教练.xlsx", data);
        log.info("导出教练结束,url:" + fileUrl);
        return fileUrl;
    }
}

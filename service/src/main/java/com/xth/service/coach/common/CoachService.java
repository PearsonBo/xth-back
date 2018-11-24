package com.xth.service.coach.common;


import com.xth.model.base.PageList;
import com.xth.model.so.coach.CoachSo;
import com.xth.model.vo.coach.CoachVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 系统生成Service类
 *
 * @author admin
 * @date 2018/09/17
 */
public interface CoachService {

    /**
     * 新增
     *
     * @param coach 新增对象
     * @return 新增对象的id
     */
    Long create(CoachVo coach);

    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    void delete(Long id);

    /**
     * 修改
     *
     * @param coach 修改对象
     */
    void update(CoachVo coach);

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    CoachVo find(Long id);

    /**
     * 按条件查询
     *
     * @param coachSo 查询条件
     * @return 查询结果
     */
    PageList<CoachVo> listPagination(CoachSo coachSo);


    /**
     * 批量导入
     *
     * @param file
     */
    void batchImport(MultipartFile file);

    PageList<CoachVo> listPagination4App(CoachSo coachSo);
}


package com.xth.service.store.common;


import com.xth.model.base.PageList;
import com.xth.model.so.store.StoreSo;
import com.xth.model.vo.store.StoreVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 系统生成Service类
 *
 * @author admin
 * @date 2018/09/16
 */
public interface StoreService {

    /**
     * 新增
     *
     * @param store 新增对象
     * @return 新增对象的id
     */
    Long create(StoreVo store);

    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    void delete(Long id);

    /**
     * 修改
     *
     * @param store 修改对象
     */
    void update(StoreVo store);

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    StoreVo find(Long id);

    /**
     * 按条件查询
     *
     * @param storeSo 查询条件
     * @return 查询结果
     */
    PageList<StoreVo> listPagination(StoreSo storeSo);


    /**
     * for wechatapp
     *
     * @param storeSo
     * @return
     */
    PageList<StoreVo> listPagination4Weapp(StoreSo storeSo);

    /**
     * 导入excel
     *
     * @param file
     */
    void batchImport(MultipartFile file);
}


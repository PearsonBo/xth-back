package com.xth.service.client.common;


import com.xth.model.base.PageList;
import com.xth.model.so.city.LocationSo;
import com.xth.model.so.client.ClientSo;
import com.xth.model.vo.city.CityVo;
import com.xth.model.vo.client.ClientVo;

/**
 * 系统生成Service类
 *
 * @author admin
 * @date 2018/09/18
 */
public interface ClientService {

    /**
     * 新增
     *
     * @param client 新增对象
     * @return 新增对象的id
     */
    Long create(ClientVo client);

    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    void delete(Long id);

    /**
     * 修改
     *
     * @param client 修改对象
     */
    void update(ClientVo client);

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    ClientVo find(Long id);

    /**
     * 按条件查询
     *
     * @param clientSo 查询条件
     * @return 查询结果
     */
    PageList<ClientVo> listPagination(ClientSo clientSo);

    /**
     * 填充手机号
     *
     * @param thirdSession
     * @param clientId
     * @param encryptedData
     * @param iv
     */
    void fillMobile(String thirdSession, String clientId, String encryptedData, String iv);

    /**
     * 填充用户最近登录信息
     *
     * @param locationSo
     * @return
     */
    CityVo fillAddressByDetail(LocationSo locationSo);

    /**
     * 填充用户最近登录信息
     *
     * @param locationSo
     * @return
     */
    CityVo fillAddressByCityId(LocationSo locationSo);
}


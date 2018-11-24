package com.xth.mapper;


import com.xth.model.base.AbstractSo;

import java.util.List;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/10 0010 下午 23:37
 */
public interface Mapper<T> {

    /**
     * 批量插入
     *
     * @param insertList 待插入集合
     */
    void batchInsert(List<T> insertList);

    /**
     * 插入
     *
     * @param bo 待插入对象
     * @return 插入是否成功
     */
    int insert(T bo);

    /**
     * 更新
     *
     * @param bo 待更新对象
     * @return 更新是否成功
     */
    int update(T bo);

    /**
     * 删除
     *
     * @param id 待删除对象Id
     * @return 删除是否成功
     */
    int delete(Long id);

    /**
     * 根据id集合删除
     *
     * @param idList 待删除对象Id集合
     * @return 是否删除成功
     */
    int deleteByIdList(List<Long> idList);

    /**
     * 查找
     *
     * @param id 待查找对象Id
     * @return 查找到的结果
     */
    T findBo(Long id);

    /**
     * 查询
     *
     * @param so 查询条件
     * @return 查询结果
     */
    List<T> listBoBySo(AbstractSo so);

    /**
     * 查询id
     *
     * @param so 查询条件
     * @return 查询结果
     */
    List<Long> listIdBySo(AbstractSo so);

    /**
     * 查询数量
     *
     * @param so 查询条件
     * @return 查询结果
     */
    int countBySo(AbstractSo so);

    /**
     * 查找
     *
     * @param id 待查找对象Id
     * @return 查找结果
     */
    <U> U findVo(Long id);

    /**
     * 查询
     *
     * @param so 查询条件
     * @return 查询结果
     */
    <U> List<U> listVoBySo(AbstractSo so);

    /**
     * 根据Id集合查询
     *
     * @param idList 待查询Id集合
     * @return 查询结果
     */
    List<T> listBoByIdList(List<Long> idList);

    /**
     * 根据Id集合查询
     *
     * @param idList 待查询Id集合
     * @return 查询结果
     */
    <U> List<U> listVoByIdList(List<Long> idList);

}

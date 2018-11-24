package com.xth.service;


import com.xth.model.vo.operator.OperatorVo;

import java.util.Set;

/**
 * @author Hu Jianbo
 * @date: 2018/7/24
 */
public interface OperationService {

    Set<String> getRoles(Long id);

    Set<String> getPerms(Long id);
}

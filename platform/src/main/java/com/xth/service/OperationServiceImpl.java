package com.xth.service;

import com.xth.dao.operator.OperatorDao;
import com.xth.model.base.AbstractVo;
import com.xth.model.exception.BizException;
import com.xth.model.exception.ExceptionType;
import com.xth.model.so.operator.OperatorSo;
import com.xth.model.vo.operator.OperatorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Hu Jianbo
 * @date: 2018/7/24
 */
@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperatorDao operatorDao;

    @Override
    public Set<String> getRoles(Long id) {
        return new HashSet<String>(Arrays.asList("role1", "role2", "role3"));
    }

    @Override
    public Set<String> getPerms(Long id) {
        return new HashSet<String>(Arrays.asList("perm1", "perm2", "perm3"));
    }
}

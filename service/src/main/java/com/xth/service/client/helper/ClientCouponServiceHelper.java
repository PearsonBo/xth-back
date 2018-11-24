package com.xth.service.client.helper;

import com.xth.dao.client.ClientCouponDao;
import com.xth.model.so.client.ClientCouponSo;
import com.xth.model.vo.client.ClientCouponVo;
import com.xth.service.AbstractNoTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统生成Service帮助类
 *
 * @author admin
 * @date 2018/09/26
 */
@Service
public class ClientCouponServiceHelper extends AbstractNoTransactionalService {

    @Autowired
    private ClientCouponDao clientCouponDao;


    public List<ClientCouponVo> findByClientIdAndCouponId(Long couponId, Long clientId) {
        ClientCouponSo clientCouponSo = new ClientCouponSo();
        clientCouponSo.setCouponId(couponId);
        clientCouponSo.setClientId(clientId);
        List<ClientCouponVo> vos = clientCouponDao.listVoBySo(clientCouponSo);
        return vos;
    }
}

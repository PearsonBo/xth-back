package com.xth.service.client.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xth.dao.city.CityDao;
import com.xth.dao.client.ClientDao;
import com.xth.model.base.PageList;
import com.xth.model.bo.client.Client;
import com.xth.model.exception.BizException;
import com.xth.model.exception.ExceptionType;
import com.xth.model.so.city.CitySo;
import com.xth.model.so.city.LocationSo;
import com.xth.model.so.client.ClientSo;
import com.xth.model.vo.city.CityVo;
import com.xth.model.vo.client.ClientVo;
import com.xth.service.AbstractTransactionalService;
import com.xth.service.CheckEmptyHelper;
import com.xth.service.RedisReadHelper;
import com.xth.util.LocationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 系统生成Service实现类
 *
 * @author admin
 * @date 2018/09/18
 */
@Service
public class ClientServiceImpl extends AbstractTransactionalService implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private RedisReadHelper redisReadHelper;

    /**
     * 新增
     *
     * @param client 新增对象
     * @return 新增对象的id
     */
    @Override
    public Long create(ClientVo client) {
        checkBeforeCreate(client);
        Client clientBo = dozer.convert(client, Client.class);
        return clientDao.insert(clientBo);
    }

    private void checkBeforeCreate(ClientVo client) {
        checkRequired(client);
        checkDuplicate4Create(client);
    }


    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    @Override
    public void delete(Long id) {
        clientDao.delete(id);
    }

    /**
     * 修改
     *
     * @param client 修改对象
     */
    @Override
    public void update(ClientVo client) {
        checkBeforeUpdate(client);
        Client clientBo = dozer.convert(client, Client.class);
        clientDao.update(clientBo);
    }

    private void checkBeforeUpdate(ClientVo client) {
        checkRequired(client);
        checkDuplicate4Update(client);
    }

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    @Override
    public ClientVo find(Long id) {
        return clientDao.findVo(id);
    }

    /**
     * 按条件查询
     *
     * @param clientSo 查询条件
     * @return 查询结果
     */
    @Override
    public PageList<ClientVo> listPagination(ClientSo clientSo) {
        List<ClientVo> list = clientDao.listPaginationVoBySo(clientSo);
        int count = clientDao.countBySo(clientSo);
        return new PageList<>(list, count);
    }

    @Override
    public void fillMobile(String thirdSession, String clientId, String encryptedData, String iv) {

        log.info("thirdSession:" + thirdSession);
        log.info("encryptedData:" + encryptedData);
        log.info("iv:" + iv);
        String res = Aes.wxDecrypt(encryptedData, thirdSession, iv);
        log.info("mobile res: " + res);
        CheckEmptyHelper.checkObject("未拿到手机号", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        String mobile = jsonObject.getString("phoneNumber");

        log.info("手机号获取result：" + mobile);
        Client client = clientDao.findBo(Long.parseLong(clientId));
        client.setMobile(mobile);
        clientDao.update(client);

    }

    @Override
    public CityVo fillAddressByDetail(LocationSo locationSo) {
        String add = LocationUtil.getLocation(locationSo.getLongitude(), locationSo.getLatitude());
        JSONArray jsonArray = (JSONArray) JSONObject.parseObject(add, Map.class).get("addrList");
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        String cityName = jsonObject.getString("admName");
        log.info(String.format("根据经度【%s】纬度【%s】查询出来的城市是【%s】", locationSo.getLatitude(), locationSo.getLongitude(), cityName));

        List<CityVo> cityList = cityDao.listVoBySo(new CitySo());
        List<CityVo> citys = cityList.stream().filter(c -> cityName.indexOf(c.getName()) != -1).collect(Collectors.toList());

        if (citys == null || citys.size() == 0) {
            throw new BizException("您当前的城市【" + cityName + "】未开通服务", ExceptionType.VIOLATE_BIZ_CHECK);
        }
        Client client = clientDao.findBo(locationSo.getClientId());
        log.info("更新用户【" + client.getId() + "】最近登录地址");
        client.setLastLatitude(locationSo.getLatitude());
        client.setLastLongitude(locationSo.getLongitude());
        clientDao.update(client);

        return citys.get(0);
    }

    @Override
    public CityVo fillAddressByCityId(LocationSo locationSo) {
        CheckEmptyHelper.checkLong("缺少选择的城市id参数", locationSo.getCityId());
        CityVo cityVo = cityDao.findVo(locationSo.getCityId());
        log.info("用户选择的城市：" + cityVo.getName());
        return cityVo;
    }

    /**
     * 检查必填字段
     */
    private void checkRequired(ClientVo client) {

    }

    /**
     * 检查字段唯一性（创建）
     */
    private void checkDuplicate4Create(ClientVo client) {

    }


    /**
     * 检查字段唯一性（更新）
     */
    private void checkDuplicate4Update(ClientVo client) {

    }


}

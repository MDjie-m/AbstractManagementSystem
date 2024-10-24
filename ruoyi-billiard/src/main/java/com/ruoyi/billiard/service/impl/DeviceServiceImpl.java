package com.ruoyi.billiard.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.billiard.domain.DeskDeviceRelation;
import com.ruoyi.billiard.domain.Store;
import com.ruoyi.billiard.domain.dto.LightDeviceExtendData;
import com.ruoyi.billiard.domain.vo.Device2DeviceStoreVo;
import com.ruoyi.billiard.domain.vo.YingShiYunVo;
import com.ruoyi.billiard.enums.DeviceStatus;
import com.ruoyi.billiard.enums.DeviceType;
import com.ruoyi.billiard.enums.LightMQTTMsgType;
import com.ruoyi.billiard.mapper.DeskDeviceRelationMapper;
import com.ruoyi.billiard.service.IMQTTService;
import com.ruoyi.billiard.service.IStoreService;
import com.ruoyi.common.config.YingShiYunConfig;
import com.ruoyi.common.config.YingShiYunEntity;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.DeviceMapper;
import com.ruoyi.billiard.domain.Device;
import com.ruoyi.billiard.service.IDeviceService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 设备信息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-07
 */
@Service
@Slf4j
public class DeviceServiceImpl implements IDeviceService, MQTTServiceImpl.DeviceCallbackEvent {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeskDeviceRelationMapper deskDeviceRelationMapper;

    @Resource
    private IMQTTService mqttService;

    @Autowired
    private YingShiYunConfig yingShiYunConfig;

    @Autowired
    private IStoreService storeService;


    /**
     * 查询设备信息
     *
     * @param deviceId 设备信息主键
     * @return 设备信息
     */
    @Override
    public Device selectDeviceByDeviceId(Long deviceId) {
        return deviceMapper.selectById(deviceId);
    }

    /**
     * 查询设备信息列表
     *
     * @param device 设备信息
     * @return 设备信息
     */
    @Override
    public List<Device> selectDeviceList(Device device) {
        return deviceMapper.selectDeviceList(device);
    }

    /**
     * 新增设备信息
     *
     * @param device 设备信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertDevice(Device device) {
        if (StringUtils.isNotEmpty(device.getDeviceSerialNum())) {
            AssertUtil.isTrue(!deviceMapper.exists(Device::getDeviceSerialNum, device.getDeviceSerialNum()), "设备编码重复");
        }
        SecurityUtils.fillCreateUser(device);
        device.setDeviceId(IdUtils.singleNextId());
        int res = deviceMapper.insertDevice(device);
        if (res > 0) {
            AssertUtil.isTrue(subLightMQTT(device), "订阅主题填写错误");
        }
        return res;
    }

    /**
     * 修改设备信息
     *
     * @param device 设备信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateDevice(Device device) {
        if (StringUtils.isNotEmpty(device.getDeviceSerialNum())) {
            AssertUtil.isTrue(!deviceMapper.existsExcludeId(Device::getDeviceSerialNum, device.getDeviceSerialNum(),
                    Device::getDeviceId, device.getDeviceId()), "设备编码重复");
        }
        SecurityUtils.fillUpdateUser(device);
        int res = deviceMapper.updateDevice(device);
        subLightMQTT(device);
        return res;
    }

    /**
     * 批量删除设备信息
     *
     * @param deviceIds 需要删除的设备信息主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteDeviceByDeviceIds(Long[] deviceIds) {
        AssertUtil.isTrue(!deskDeviceRelationMapper.existsIn(DeskDeviceRelation::getDeviceId, Arrays.asList(deviceIds)), "设备已绑定，不能删除.");

        return deviceMapper.deleteDeviceByDeviceIds(deviceIds);
    }

    /**
     * 删除设备信息信息
     *
     * @param deviceId 设备信息主键
     * @return 结果
     */
    @Override
    public int deleteDeviceByDeviceId(Long deviceId) {

        return deviceMapper.deleteDeviceByDeviceId(deviceId);
    }

    @Override
    public Integer lightSubMsg(Long deviceId) {
        Device device = deviceMapper.selectById(deviceId);
        AssertUtil.notNullOrEmpty(device, "设备不存在");
        AssertUtil.isTrue(!Objects.equals(device.getDeviceType(), DeviceType.LIGHT.getValue()), "不是灯光设备");
        return subLightMQTT(device) ? 1 : 0;
    }

    @Override
    @SneakyThrows
    public Boolean switchLight(Long deviceId, Boolean isOpen) {
        Device device = deviceMapper.selectById(deviceId);
        AssertUtil.notNullOrEmpty(device, "设备不存在");
        AssertUtil.isTrue(Objects.equals(device.getDeviceType(), DeviceType.LIGHT.getValue()), "不是灯光设备");
        boolean exceptState = Boolean.TRUE.equals(isOpen);
        Integer val = exceptState ? 1 : 0;
        sendSwitchLightMsg(deviceId, exceptState);
        Integer queryStatus = null;
        for (int i = 0; i < 18; i++) {
            queryStatus = deviceMapper.selectCustomStatus(deviceId);
            if (Objects.equals(queryStatus, val)) {
                break;
            }
            Thread.sleep(60);
        }
        AssertUtil.isTrue(Objects.equals(val, queryStatus), "操作失败");
        return true;
    }

    @Override
    @SneakyThrows
    public void lightStatusCheckJob(Long sleepTime) {
        List<Device> lights = this.deviceMapper.selectDeviceList(Device.builder().deviceType(DeviceType.LIGHT.getValue()).build());
        lights.forEach(p -> {
            LightDeviceExtendData lightDeviceExtendData = p.toCustomExtendData(LightDeviceExtendData.class);
            if (Objects.isNull(lightDeviceExtendData) || StringUtils.isEmpty(lightDeviceExtendData.getPubTopic())) {
                return;
            }
            mqttService.sendMsg(lightDeviceExtendData.getPubTopic(), LightMQTTMsgType.INFO.getValue(), "{  \"type\":\"info\"}");
        });

        Thread.sleep(sleepTime);
        Date now = new Date();
        lights = this.deviceMapper.selectList(deviceMapper.query().in(Device::getDeviceId, lights.stream().map(Device::getDeviceId).collect(Collectors.toList())));
        for (Device light : lights) {
            if (Objects.isNull(light.getLastReportTime())) {
                light.setStatus(DeviceStatus.UNKNOWN.getValue());
            } else if (light.getLastReportTime().getTime() > now.getTime() - sleepTime * 1000L) {
                light.setStatus(DeviceStatus.ONLINE.getValue());
            } else {
                light.setStatus(DeviceStatus.OFFLINE.getValue());
            }
        }
        deviceMapper.updateBatch(lights);


    }

    @Override
    public YingShiYunVo selectDeviceStoreList() {
        // 获取设备
        List<Device> devices = Optional.ofNullable(deviceMapper.selectList(deviceMapper.query().eq(Device::getDeviceType, DeviceType.CAMERA.getValue()))).orElse(Collections.emptyList());
        // 获取设备关联的店铺信息
        List<Device2DeviceStoreVo> device2DeviceStoreVos = devices.stream().map(p -> {
            Device2DeviceStoreVo device2DeviceStoreVo = new Device2DeviceStoreVo();
            BeanUtils.copyBeanProp(device2DeviceStoreVo, p);
            Store store = storeService.selectStoreByStoreId(p.getStoreId());
            if (Objects.nonNull(store)) {
                device2DeviceStoreVo.setStoreName(store.getStoreName());
                device2DeviceStoreVo.setStoreAddress(store.getStoreAddress());
                device2DeviceStoreVo.setStoreImg(store.getStoreImg());
                device2DeviceStoreVo.setStoreStatus(store.getStatus());
                device2DeviceStoreVo.setDeviceImg(yingShiYunConfig.getDeviceCaptureImg(p.getDeviceSerialNum()));
            }
            return device2DeviceStoreVo;
        }).collect(Collectors.toList());

        // 获取萤石云token数据
        YingShiYunEntity yingShiYunEntity = yingShiYunConfig.returnAppTokenData();

        return YingShiYunVo.builder()
                .appKey(yingShiYunEntity.getAppKey())
                .accessToken(yingShiYunEntity.getAccessToken())
                .device2DeviceStoreVos(device2DeviceStoreVos).build();
    }

    @PostConstruct
    public void initMQTT() {
        mqttService.connect();
        List<Device> lights = this.deviceMapper.selectDeviceList(Device.builder().deviceType(DeviceType.LIGHT.getValue()).build());
        lights.forEach(this::subLightMQTT);
    }

    private boolean subLightMQTT(Device p) {
        try {
            LightDeviceExtendData extendData = p.toLightData();
            if (Objects.isNull(extendData)) {
                return true;
            }
            if (StringUtils.isNotEmpty(extendData.getSubTopic())) {
                mqttService.subDevice(p.getStoreId(), p.getDeviceId(), extendData.getSubTopic(), this);
            }
//            if (StringUtils.isNotEmpty(extendData.getPubTopic())) {
//                setLightReportSetting(extendData.getPubTopic());
//            }
        } catch (Exception e) {
            log.error("设备订阅消息失败,id:{}", p.getDeviceId(), e);
            return true;
        }
        return true;
    }

    @Override
    public void lightMQTTCallback(int msgId, Long storeId, Long deviceId, String msg) {
        deviceMapper.updateLastReportTime(deviceId, DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, new Date()));

        JSONObject resJson = JSON.parseObject(msg);
        if (Objects.nonNull(resJson) && resJson.containsKey("key")) {
            deviceMapper.update(null, deviceMapper.updateWrapper().set(Device::getCustomStatus, resJson.getInteger("key"))
                    .eq(Device::getDeviceId, deviceId));
        }
    }

    @SneakyThrows
    public boolean sendSwitchLightMsg(Long deviceId, Boolean open) {
        String msg = String.format("{\"type\":\"event\",\"key\":%s}", open ? 1 : 0);
        sendLightMsg(deviceId, LightMQTTMsgType.SWITCH, msg);
        sendLightMsg(deviceId, LightMQTTMsgType.INFO, "{  \"type\":\"info\"}");
        return true;
    }

    @SneakyThrows
    public boolean setLightReportSetting(String topic) {
        //设置60秒上报一次
        String msg = "{  \"type\":\"setting\",  \"timerEnable\":1,  \"timerInterval\":60 }";
        mqttService.sendMsg(topic, LightMQTTMsgType.SETTING.getValue(), msg);
        return true;
    }

    private boolean sendLightMsg(Long deviceId, LightMQTTMsgType type, String msg) {
        Device device = deviceMapper.selectById(deviceId);
        AssertUtil.notNullOrEmpty(device, "设备不存在");
        AssertUtil.isTrue(Objects.equals(DeviceType.LIGHT.getValue(), device.getDeviceType()), "不是灯光设备");
        LightDeviceExtendData lightDeviceExtendData = device.toCustomExtendData(LightDeviceExtendData.class);
        if (Objects.isNull(lightDeviceExtendData) || StringUtils.isEmpty(lightDeviceExtendData.getPubTopic())) {
            throw new ServiceException("灯光自定义配置不争取");
        }
        mqttService.sendMsg(lightDeviceExtendData.getPubTopic(), type.getValue(), msg);

        return true;
    }
}

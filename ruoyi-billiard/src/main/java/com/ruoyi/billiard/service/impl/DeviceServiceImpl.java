package com.ruoyi.billiard.service.impl;

import java.util.Arrays;
import java.util.List;

import com.ruoyi.billiard.domain.DeskDeviceRelation;
import com.ruoyi.billiard.domain.StoreUser;
import com.ruoyi.billiard.mapper.DeskDeviceRelationMapper;
import com.ruoyi.billiard.service.IDeskDeviceRelationService;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.DeviceMapper;
import com.ruoyi.billiard.domain.Device;
import com.ruoyi.billiard.service.IDeviceService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 设备信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-07
 */
@Service
public class DeviceServiceImpl implements IDeviceService 
{
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeskDeviceRelationMapper  deskDeviceRelationMapper;

    /**
     * 查询设备信息
     * 
     * @param deviceId 设备信息主键
     * @return 设备信息
     */
    @Override
    public Device selectDeviceByDeviceId(Long deviceId)
    {
        return deviceMapper.selectById(deviceId);
    }

    /**
     * 查询设备信息列表
     * 
     * @param device 设备信息
     * @return 设备信息
     */
    @Override
    public List<Device> selectDeviceList(Device device)
    {
        return deviceMapper.selectDeviceList(device);
    }

    /**
     * 新增设备信息
     * 
     * @param device 设备信息
     * @return 结果
     */
    @Override
    public int insertDevice(Device device)
    {
        if(StringUtils.isNotEmpty(device.getDeviceSerialNum())){
           AssertUtil.isTrue(! deviceMapper.exists(Device::getDeviceSerialNum,device.getDeviceSerialNum()),"设备编码重复");
        }
        device.setDeviceId(IdUtils.singleNextId());
        device.setCreateTime(DateUtils.getNowDate());
        device.setCreateBy(SecurityUtils.getUsername());
        device.setUpdateBy(SecurityUtils.getUsername());
        return deviceMapper.insertDevice(device);
    }

    /**
     * 修改设备信息
     * 
     * @param device 设备信息
     * @return 结果
     */
    @Override
    public int updateDevice(Device device)
    {
        if(StringUtils.isNotEmpty(device.getDeviceSerialNum())){
            AssertUtil.isTrue(!  deviceMapper.existsExcludeId(Device::getDeviceSerialNum,  device.getDeviceSerialNum(),
                    Device::getDeviceId, device.getDeviceId()),"设备编码重复");
        }
        device.setCreateBy(SecurityUtils.getUsername());
        device.setUpdateTime(DateUtils.getNowDate());
        return deviceMapper.updateDevice(device);
    }

    /**
     * 批量删除设备信息
     * 
     * @param deviceIds 需要删除的设备信息主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteDeviceByDeviceIds(Long[] deviceIds)
    {
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
    public int deleteDeviceByDeviceId(Long deviceId)
    {

        return deviceMapper.deleteDeviceByDeviceId(deviceId);
    }
}

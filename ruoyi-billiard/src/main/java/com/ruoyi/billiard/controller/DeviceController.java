package com.ruoyi.billiard.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.ResultVo;
import com.ruoyi.common.core.page.PageResVo;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.billiard.domain.Device;
import com.ruoyi.billiard.service.IDeviceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备信息Controller
 * 
 * @author ruoyi
 * @date 2024-09-07
 */
@RestController
@RequestMapping("/billiard/device")
public class DeviceController extends BaseController
{
    @Autowired
    private IDeviceService deviceService;

    /**
     * 查询设备信息列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:device:list')")
    @GetMapping("/list")
    public PageResVo<Device> list(Device device)
    {
        startPage();
        List<Device> list = deviceService.selectDeviceList(device);
        return PageResVo.success(list);
    }
    @GetMapping("/list/all")
    public ResultVo< List<Device>> listAll(Device device)
    {
        List<Device> list = deviceService.selectDeviceList(device);
        return ResultVo.success(list);
    }
    /**
     * 导出设备信息列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:device:export')")
    @Log(title = "设备信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Device device)
    {
        List<Device> list = deviceService.selectDeviceList(device);
        ExcelUtil<Device> util = new ExcelUtil<Device>(Device.class);
        util.exportExcel(response, list, "设备信息数据");
    }

    /**
     * 获取设备信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:device:query')")
    @GetMapping(value = "/{deviceId}")
    public ResultVo<Device> getInfo(@PathVariable("deviceId") Long deviceId)
    {
        return ResultVo.success(deviceService.selectDeviceByDeviceId(deviceId));
    }

    /**
     * 新增设备信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:device:add')")
    @Log(title = "设备信息", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody Device device)
    {
        return ResultVo.success(deviceService.insertDevice(device));
    }

    /**
     * 修改设备信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:device:edit')")
    @Log(title = "设备信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public  ResultVo<Integer> edit(@RequestBody Device device)
    {
        return ResultVo.success(deviceService.updateDevice(device));
    }
    /**
     * 修改设备信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:device:edit')")
    @PutMapping   (value = "/{deviceId}/light/sub")
    public  ResultVo<Integer> lightSub( @PathVariable("deviceId") Long deviceId)
    {
        return ResultVo.success(deviceService.lightSubMsg(deviceId));
    }
    /**
     * 开关灯
     */
    @PreAuthorize("@ss.hasPermi('billiard:device:edit')")
    @PutMapping   (value = "/{deviceId}/light/switch")
    @SneakyThrows
    public  ResultVo<Boolean> lightSub( @PathVariable("deviceId") Long deviceId,Boolean open)
    {
        ResultVo<Boolean>  res= ResultVo.success(deviceService.switchLight(deviceId,open));

        return  res;
    }
    /**
     * 删除设备信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:device:remove')")
    @Log(title = "设备信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deviceIds}")
    public  ResultVo<Integer> remove(@PathVariable Long[] deviceIds)
    {
        return  ResultVo.success(deviceService.deleteDeviceByDeviceIds(deviceIds));
    }
}

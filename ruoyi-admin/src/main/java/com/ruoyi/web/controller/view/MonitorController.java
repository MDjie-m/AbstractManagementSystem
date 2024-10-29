package com.ruoyi.web.controller.view;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.web.controller.data.DataApiController;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api("监测点管理")
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    /**
     * 查询监测点历史/实时数据
     * @return
     */
    @ApiOperation("查询监测点历史/实时数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "queryType", value = "查询类型（history/realTime）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间（yyyy-MM-dd HH:mm:ss）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间（yyyy-MM-dd HH:mm:ss）", required = true, dataType = "String")
    })
    public R<List<DataApiController.DeviceUploadData>> queryMonitorData(String queryType, String startTime, String endTime){

        List<DataApiController.DeviceUploadData> list = new ArrayList<>();
        return R.ok(list);
    }

    /**
     * 查询过去24小时数据
     * @return
     */
    @ApiOperation("查询过去24小时数据")
    public R<List<DataApiController.DeviceUploadData>> queryPast24HoursData(@ApiParam(value = "设备ID") String deviceId){
        List<DataApiController.DeviceUploadData> list = new ArrayList<>();
        return R.ok(list);
    }

    /**
     * 查询监测点告警
     * @return
     */
    @ApiOperation("查询监测点告警")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parkId", value = "园区ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "factor", value = "监测因子", required = true, dataType = "String"),
            @ApiImplicitParam(name = "emiId", value = "排点ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间（yyyy-MM-dd HH:mm:ss）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间（yyyy-MM-dd HH:mm:ss）", required = true, dataType = "String")
    })
    public R<String> monitorWarning(String parkId, String factor, String emiId, String startTime, String endTime){
        return R.ok("success");
    }

    /**
     * 监控告警阈值设置
     * @return
     */
    @ApiOperation("监控告警阈值设置")
    @PutMapping("/warning/set")
    public R<String> monitorWarningSet(@RequestBody WarningSet warningSet){
        return R.ok("success");
    }

}

@ApiModel("监控告警阈值设置")
class WarningSet{
    @ApiModelProperty("告警ID")
    String warningId;
    @ApiModelProperty("告警名称")
    String warningName;
    @ApiModelProperty("监测因子")
    String factor;
    @ApiModelProperty("告警阈值")
    String threshold;
    @ApiModelProperty("设备ID")
    String deviceId;
    @ApiModelProperty("备注")
    String comment;
}

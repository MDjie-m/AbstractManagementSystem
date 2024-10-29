package com.ruoyi.web.controller.view;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.web.controller.data.DataApiController;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "监测点管理")
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
            @ApiImplicitParam(name = "startTime", value = "开始时间（yyyy-MM-dd HH:mm:ss）", required = false, dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间（yyyy-MM-dd HH:mm:ss）", required = false, dataType = "String")
    })
    @GetMapping("/queryMonitorData")
    public R<List<DataApiController.DeviceUploadData>> queryMonitorData(String queryType, String startTime, String endTime){

        List<DataApiController.DeviceUploadData> list = new ArrayList<>();
        return R.ok(list);
    }

    /**
     * 查询过去24小时数据
     * @return
     */
    @ApiOperation("查询过去24小时数据")
    @GetMapping("/queryPast24HoursData")
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
    @GetMapping("/warning/get")
    public R<WarningSet> monitorWarning(String parkId, String factor, String emiId, String startTime, String endTime){
        WarningSet warningSet = new WarningSet("", "", "", "", "");
        return R.ok(warningSet);
    }

    /**
     * 监控告警阈值设置
     * @return
     */
    @ApiOperation("监控告警阈值设置")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "warningName", value = "告警名称", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "factor", value = "监测因子", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "threshold", value = "告警阈值", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "deviceId", value = "设备ID", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "comment", value = "备注", required = false, dataType = "String")
//    })
    @PutMapping("/warning/set")
    public R<String> monitorWarningSet(@RequestBody WarningSet warningSet){
        return R.ok("success");
    }

}

@ApiModel( value="WarningSet", description = "监控告警阈值设置")
class WarningSet{

    @ApiModelProperty("告警ID")
    private String warningId;
    @ApiModelProperty("告警名称")
    private String warningName;
    @ApiModelProperty("监测因子")
    private  String factor;
    @ApiModelProperty("告警阈值")
    private String threshold;
    @ApiModelProperty("设备ID")
    private String deviceId;
    @ApiModelProperty("备注")
    private String comment;

    public WarningSet(String warningName, String factor, String threshold, String deviceId, String comment){
        this.warningName = warningName;
        this.factor = factor;
        this.threshold = threshold;
        this.deviceId = deviceId;
        this.comment = comment;
    }

    public String getWarningName() {
        return warningName;
    }

    public String getFactor() {
        return factor;
    }

    public String getThreshold() {
        return threshold;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getComment() {
        return comment;
    }
}

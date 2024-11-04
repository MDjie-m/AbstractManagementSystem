package com.tianyi.web.controller.terminal;

import com.tianyi.common.core.domain.R;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "终端管理")
@RestController
@RequestMapping("/terminal")
public class TerminalController {

    @ApiOperation("基站信息查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rateType", value = "基站类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "String")
    })
    @GetMapping("/queryBSInfo")
    public R<String> queryBSInfo(String rateType, String page){

        return R.ok("查询成功");
    }

    @ApiOperation("查询IMEI信息")
    @GetMapping("/queryImeiInfo")
    public R<String> queryImeiInfo(@ApiParam(value = "IMEI", required = true) String imei){

        return R.ok("查询成功");
    }

    @ApiOperation("查询IMEI轨迹")
    @GetMapping("/trackIMEI")
    public R<String> trackIMEI(String imei){
        return R.ok("查询成功");
    }

    @ApiOperation("终端统计")
    @GetMapping("/terminalStat")
    public R<String> terminalStat(){
        return R.ok("查询成功");
    }

    @ApiOperation("省内流量漫入查询")
    @GetMapping("/provFlowIn")
    public R<String> provFlowIn(){
        return R.ok("查询成功");
    }

    @ApiOperation("外省流量漫入查询")
    public R<String> otherProvFlowIn(){
        return R.ok("查询成功");
    }
}

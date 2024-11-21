package com.tianyi.web.controller.terminal;

import com.tianyi.common.core.domain.R;
import com.tianyi.terminal.service.ITerminalService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "终端管理")
@RestController
@RequestMapping("/terminal")
public class TerminalController {

    @Autowired
    private ITerminalService terminalService;

    @ApiOperation("基站终端统计信息查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rateType", value = "基站类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "coordinates", value = "坐标点", required = true, dataType = "String")
    })
    @GetMapping("/queryBSStatInfo")
    public R<String> queryBSInfo(String rateType, String coordinates){

        return R.ok("查询成功");
    }

    @ApiOperation("查询基站终端信息")
    @GetMapping("/queryBSTerminalInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bsId", value = "基站ID", required = true, dataType = "String")
    })
    public R<String> queryBSTerminalInfo(String bsId){
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

}

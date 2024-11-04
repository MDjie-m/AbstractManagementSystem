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

    @ApiOperation("查询基站信息")
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



}

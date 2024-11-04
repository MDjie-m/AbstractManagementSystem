package com.tianyi.web.controller.fault;

import com.tianyi.common.core.domain.R;
import com.tianyi.fault.service.IFaultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "故障诊断")
@RestController
@RequestMapping("/fault")
public class FaultController {

    @Autowired
    private IFaultService faultService;

    @ApiOperation("终端状态查询")
    @GetMapping("/termStatQuery")
    public R<String> termStatQuery(){
        return R.ok("ok");
    }

    @ApiOperation("业务状态查询")
    @GetMapping("/buzStatQuery")
    public R<String> buzStatQuery(){
        return R.ok("ok");
    }

    @ApiOperation("异常断网状态查询")
    @GetMapping("/abNetDisconQuery")
    public R<String> abNetDisconQuery(){
        return R.ok("ok");
    }

    @ApiOperation("网络状态查询")
    @GetMapping("/netStatQuery")
    public R<String> netStatQuery(){
        return R.ok("ok");
    }

    @ApiOperation("平台状态查询")
    @GetMapping("/platStatQuery")
    public R<String> platStatQuery(){
        return R.ok("ok");
    }
}

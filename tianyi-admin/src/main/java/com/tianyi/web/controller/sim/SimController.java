package com.tianyi.web.controller.sim;

import com.tianyi.common.core.domain.R;
import com.tianyi.sim.service.ISimService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "SIM卡管理", description = "SIM卡管理模块描述")
@RestController
@RequestMapping("/sim")
public class SimController {

    @Autowired
    private ISimService simService;

    @ApiOperation(value = "sim卡停机清单查询")
    @GetMapping("/shutdownQuery")
    public R<String> shutdownQuery() {
        return R.ok("停机查询");
    }

    @ApiOperation(value = "sim卡断网清单查询")
    @GetMapping("/disconQuery")
    public R<String> disconQuery() {
        return R.ok("断网查询");
    }

    @ApiOperation(value = "sim卡NB异常清单查询")
    @GetMapping("/NBAbnormalQuery")
    public R<String> NBAbnormalQuery() {
        return R.ok("NB异常查询");
    }

    @ApiOperation(value = "套餐到期预警")
    @GetMapping("/packExpWarning")
    public R<String> packExpWarning() {
        return R.ok("套餐到期预警");
    }

    @ApiOperation(value = "长期未使用预警")
    @GetMapping("/longTermNoUseWarning")
    public R<String> longTermNoUseWarning() {
        return R.ok("长期未使用预警");
    }

    @ApiOperation(value = "套餐欠费预警")
    @GetMapping("/upPaidWarning")
    public R<String> upPaidWarning() {
        return R.ok("套餐欠费预警");
    }
}

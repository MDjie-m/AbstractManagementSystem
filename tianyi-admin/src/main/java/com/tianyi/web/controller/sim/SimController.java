package com.tianyi.web.controller.sim;

import com.tianyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "SIM卡管理", value = "SIM卡管理模块", description = "SIM卡管理模块描述")
@RestController
@RequestMapping("/sim")
public class SimController {

    @ApiOperation(tags = "SIM卡停机", value = "sim卡停机分析")
    public R<String> stopAnalysis(){
        return R.ok("停止分析");
    }

}

package com.ruoyi.web.controller.view;

import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("浓度检测")
@RestController
@RequestMapping("/gas")
public class GasController {

    @ApiOperation("查询气体浓度")
    public R<String> queryGasConcentration() {
        // TODO: query gas concentration from database
        return R.ok("100");
    }
}

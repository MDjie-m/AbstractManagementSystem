package com.ruoyi.web.controller.view;

import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 溯源
 */
@Api(tags = "溯源管理")
@RestController
@RequestMapping("/trace")
public class TraceController {

    /**
     * 溯源点溯源分析结果
     * @return
     */
    @ApiOperation("溯源分析")
    @GetMapping("/trace")
    public R<String> trace(@ApiParam(value="溯源点", required=true) String tracePos){
        return R.ok("trace");
    }
}

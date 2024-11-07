package com.tianyi.web.controller.common;

import com.tianyi.common.core.domain.R;
import com.tianyi.sim.domain.Dept;
import com.tianyi.sim.service.IAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 查询子地市信息
 */
@Api(tags = "公共接口")
@RestController
@RequestMapping("/common/sale")
public class AreaController {

    @Autowired
    private IAreaService areaService;

    @ApiOperation(value = "查询子地市信息", notes = "查询子地市信息")
    @GetMapping("/querySubArea")
    public R<List<Dept>> querySubArea(@ApiParam("地市ID") String areaId) {
        // 根据城市查询销售单位
        return R.ok(areaService.querySubArea(areaId));
    }
}

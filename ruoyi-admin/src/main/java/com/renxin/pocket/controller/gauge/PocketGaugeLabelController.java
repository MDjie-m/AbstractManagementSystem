package com.renxin.pocket.controller.gauge;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.gauge.dto.GaugeLabelQueryDTO;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.gauge.domain.PsyGauge;
import com.renxin.gauge.service.IPsyGaugeLabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @User hogan
 * @Time 2022/10/17 16:50
 * @e-mail hkcugwh@163.com
 **/
@RestController
@RequestMapping("/pocket/home/gauge/label")
@Api(value = "pocketGaugeLabelController" ,tags = {"测评标签api"})
public class PocketGaugeLabelController extends BaseController {

    @Autowired
    private IPsyGaugeLabelService psyGaugeLabelService;

    @GetMapping("/list")
    @ApiOperation("查询测评标签列表")
    @RateLimiter
    public TableDataInfo list(GaugeLabelQueryDTO gaugeLabelQueryDTO)
    {
        startPage();
        List<PsyGauge> list = psyGaugeLabelService.queryGaugeByLabel(gaugeLabelQueryDTO.getLabelCode());
        return getDataTable(list);
    }

}

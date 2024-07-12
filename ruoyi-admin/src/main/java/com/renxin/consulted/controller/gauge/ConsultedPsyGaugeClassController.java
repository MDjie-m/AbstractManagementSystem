package com.renxin.consulted.controller.gauge;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.gauge.domain.PsyGaugeClass;
import com.renxin.gauge.service.IPsyGaugeClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测评分类Controller
 * 
 * @author renxin
 * @date 2022-08-30
 */
@RestController
@RequestMapping("/consulted/evaluation/class")
@Api(value = "consultedPsyGaugeClassController" ,tags = {"测评分类api"})
public class ConsultedPsyGaugeClassController extends BaseController
{
    @Autowired
    private IPsyGaugeClassService psyGaugeClassService;

    /**
     * 查询测评分类列表
     */
//    @PreAuthorize("@ss.hasPermi('psychology:gaugeClass:list')")
    @PostMapping("/list")
    @ApiOperation("查询测评分类列表")
    @RateLimiter
    public TableDataInfo list(@RequestBody PsyGaugeClass psyGaugeClass)
    {
        startPage();
        List<PsyGaugeClass> list = psyGaugeClassService.selectPsyGaugeClassList(psyGaugeClass);
        return getDataTable(list);
    }


}

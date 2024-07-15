package com.renxin.pocket.controller.gauge;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.gauge.domain.PsyGaugeBannerConfig;
import com.renxin.gauge.service.IPsyGaugeBannerConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测评banner配置Controller
 * 
 * @author renxin
 * @date 2022-10-18
 */
@RestController
@RequestMapping("/pocket/home/banner/config")
@Api(value = "pocketGaugeBannerConfigController" ,tags = {"测评banner页配置控制器"})
public class PocketGaugeBannerConfigController extends BaseController
{
    @Resource
    private IPsyGaugeBannerConfigService psyGaugeBannerConfigService;

    /**
     * 查询测评banner配置列表
     */
    @GetMapping("/list")
    @ApiOperation("查询测评banner配置列表")
    @RateLimiter
    public TableDataInfo list(PsyGaugeBannerConfig psyGaugeBannerConfig)
    {
        startPage();
        List<PsyGaugeBannerConfig> list = psyGaugeBannerConfigService.selectPsyGaugeBannerConfigList(psyGaugeBannerConfig);
        return getDataTable(list);
    }
}

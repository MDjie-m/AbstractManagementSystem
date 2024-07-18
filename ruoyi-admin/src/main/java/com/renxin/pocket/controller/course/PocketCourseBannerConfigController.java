package com.renxin.pocket.controller.course;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.course.domain.CourCourseBannerConfig;
import com.renxin.course.service.ICourCourseBannerConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/pocket/course/banner/config")
@Api(value = "consultedCourseBannerConfigController" ,tags = {"课程banner页配置控制器"})
public class PocketCourseBannerConfigController extends BaseController
{
    @Resource
    private ICourCourseBannerConfigService courCourseBannerConfigService;

    /**
     * 查询测评banner配置列表
     */
    @PostMapping("/list")
    @ApiOperation("查询课程banner配置列表")
    @RateLimiter
    public TableDataInfo list(CourCourseBannerConfig courCourseBannerConfig)
    {
        startPage();
        List<CourCourseBannerConfig> list = courCourseBannerConfigService.selectCourCourseBannerConfigList(courCourseBannerConfig);
        return getDataTable(list);
    }
}

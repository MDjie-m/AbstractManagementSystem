package com.renxin.consulted.controller.course;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.course.domain.CourSection;
import com.renxin.course.service.ICourCourseService;
import com.renxin.course.service.ICourSectionService;
import com.renxin.framework.web.service.ConsultedTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/consulted/course/section")
@Api(value = "consultedCourSectionController" ,tags = {"课程章节API"})
public class ConsultedCourSectionController extends BaseController
{
    @Autowired
    private ICourSectionService courSectionService;

    @Autowired
    private ICourCourseService courCourseService;

    @Resource
    private ConsultedTokenService consultedTokenService;

    /**
     * 查询课程章节列表
     */
//    @PreAuthorize("@ss.hasPermi('course:section:list')")
    @PostMapping("/list")
    @ApiOperation("查询课程章节列表")
    @RateLimiter
    public TableDataInfo list(@RequestBody CourSection courSection, HttpServletRequest request)
    {
        startPage();
        List<CourSection> list = courSectionService.selectCourSectionList(courSection);
        Integer userId = consultedTokenService.getUserId(request);

        // 判断课程是否已经下单支付
        if (courCourseService.getPaidCourseCount(userId, courSection.getCourseId()) == 0) { // 未下单支付
            list.forEach(item -> {
                if (item.getType() == 0) { // 普通课程禁止学习
                    item.setContentUrl(null);
                }
            });
        };

        return getDataTable(list);
    }

    /**
     * 查询章节详情
     */
//    @PreAuthorize("@ss.hasPermi('course:section:query')")
    @PostMapping(value = "/getInfo")
    @ApiOperation("查询章节详情")
    @RateLimiter
    public AjaxResult getInfo(@RequestParam(value = "id") Integer id)
    {
        return AjaxResult.success(courSectionService.selectCourSectionById(id));
    }
}

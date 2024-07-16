package com.renxin.pocket.controller.course;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.course.domain.CourCourse;
import com.renxin.course.domain.CourCourseLabel;
import com.renxin.course.service.ICourCourseLabelService;
import com.renxin.course.service.ICourCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程标签Controller
 *
 * @author luzijun
 * @date 2023-03-16
 */
@RestController
@RequestMapping("/pocket/course/label")
@Api(value = "pocketCourseLabelController" ,tags = {"课程标签API"})
public class PocketCourseLabelController extends BaseController
{
    @Autowired
    private ICourCourseLabelService courCourseLabelService;

    @Autowired
    private ICourCourseService courCourseService;

    /**
     * 根据标签查询课程列表
     */
//    @PreAuthorize("@ss.hasPermi('course:label:list')")
    @PostMapping("/list")
    @ApiOperation("根据标签查询课程列表")
    @RateLimiter
    public TableDataInfo list(@RequestBody CourCourseLabel courCourseLabel)
    {
        startPage();
        List<CourCourseLabel> list = courCourseLabelService.selectCourCourseLabelList(courCourseLabel);
        List<CourCourse> courCourseList = new ArrayList<>();
        list.forEach(item -> {
            courCourseList.add(courCourseService.selectCourCourseById(item.getId()));
        });

        return getDataTable(courCourseList);
    }

}

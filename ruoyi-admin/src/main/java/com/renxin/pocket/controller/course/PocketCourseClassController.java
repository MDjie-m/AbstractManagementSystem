package com.renxin.pocket.controller.course;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.course.domain.CourCourseClass;
import com.renxin.course.service.ICourCourseClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pocket/course/class")
@Api(value = "pocketCourseClassController" ,tags = {"课程类型API"})
public class PocketCourseClassController extends BaseController
{
    @Autowired
    private ICourCourseClassService courCourseClassService;

    /**
     * 查询课程类型列表
     */
    @PostMapping("/list")
    @ApiOperation("查询课程类型列表")
    @RateLimiter
    public TableDataInfo list(@RequestBody CourCourseClass courCourseClass)
    {
        startPage();
        courCourseClass.setServiceTo(1);//来访者
        List<CourCourseClass> list = courCourseClassService.selectCourCourseClassList(courCourseClass);
        return getDataTable(list);
    }
}

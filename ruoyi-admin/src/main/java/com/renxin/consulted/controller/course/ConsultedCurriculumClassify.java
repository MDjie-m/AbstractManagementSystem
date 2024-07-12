package com.renxin.consulted.controller.course;


import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.course.domain.CourCourseClass;
import com.renxin.course.service.ICourCourseClassService;
import com.renxin.web.controller.common.CommonCosController;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/consulted/course")
public class ConsultedCurriculumClassify extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(CommonCosController.class);

    @Autowired
    private ICourCourseClassService courCourseClassService;


    @GetMapping("/list")
    @ApiOperation("查询课程分类列表")
    @RateLimiter
    public TableDataInfo list(CourCourseClass courCourseType)
    {
        startPage();
        List<CourCourseClass> list = courCourseClassService.selectCourCourseClassList(courCourseType);
        return getDataTable(list);
    }

}

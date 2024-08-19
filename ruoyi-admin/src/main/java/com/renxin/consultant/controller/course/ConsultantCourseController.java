package com.renxin.consultant.controller.course;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.course.domain.CourCourseClass;
import com.renxin.course.service.ICourCourseClassService;
import com.renxin.psychology.domain.PsyConsultClass;
import com.renxin.psychology.service.IPsyConsultClassService;
import com.renxin.psychology.vo.PsyConsultClassVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/consultant/course")
@Api(value = "ConsultantCourseController" ,tags = {"咨询师课程Api"})
public class ConsultantCourseController extends BaseController {

    @Resource
    private ICourCourseClassService courCourseClassService;

    @ApiOperation(value = "查询class列表")
    @PostMapping("/class/list")
    @RateLimiter
    public AjaxResult list(CourCourseClass courCourseClass)
    {
        //startPage();
        List<CourCourseClass> list = courCourseClassService.selectCourCourseClassList(courCourseClass);
        return AjaxResult.success(list);
    }

}

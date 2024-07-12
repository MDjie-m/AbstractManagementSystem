package com.renxin.consulted.controller.course;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.course.constant.CourConstant;
import com.renxin.course.domain.CourUserCourseSection;
import com.renxin.course.service.ICourUserCourseSectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/consulted/course/userSection")
@Api(value = "consultedCourUserSectionController" ,tags = {"用户课程章节API"})
public class ConsultedCourUserSectionController extends BaseController {

    @Autowired
    private ICourUserCourseSectionService courUserCourseSectionService;

    /**
     * 查询用户的课程列表
     */
//    @PreAuthorize("@ss.hasPermi('course:userSection:list')")
    @PostMapping("/list")
    @ApiOperation("查询课程章节列表")
    @RateLimiter
    public TableDataInfo list(@RequestBody CourUserCourseSection courUserCourseSection)
    {
        startPage();
        List<CourUserCourseSection> list = courUserCourseSectionService.selectCourUserCourseSectionList(courUserCourseSection);
        return getDataTable(list);
    }

    /**
     * 新增或更新用户-课程-章节关系
     */
    @PostMapping("/saveUserSectionInfo")
    @ApiOperation("记录课程章节完成情况，新增或更新用户-课程-章节关系")
    @RateLimiter
    public AjaxResult saveUserSectionInfo(@RequestBody CourUserCourseSection userCourseSection)
    {
        if (userCourseSection.getFinishStatus() == null) {
            userCourseSection.setFinishStatus(CourConstant.SECTION_UNFINISHED);
        }
        // 根据用户、课程、章节查询是否已有学习完成记录
        CourUserCourseSection queryParams = new CourUserCourseSection();
        queryParams.setUserId(userCourseSection.getUserId());
        queryParams.setCourseId(userCourseSection.getCourseId());
        queryParams.setSectionId(userCourseSection.getSectionId());
        List<CourUserCourseSection> userCourseSectionList =  courUserCourseSectionService.selectCourUserCourseSectionList(queryParams);
        if (userCourseSectionList.size() == 0) {
            // 新增
            return AjaxResult.success(courUserCourseSectionService.insertCourUserCourseSection(userCourseSection));
        } else if (userCourseSectionList.size() == 1){
            // 更新
            userCourseSection.setId(userCourseSectionList.get(0).getId());
            return AjaxResult.success(courUserCourseSectionService.recordEndTime(userCourseSection));
        }
        return AjaxResult.error("记录课程章节异常");
    }
}

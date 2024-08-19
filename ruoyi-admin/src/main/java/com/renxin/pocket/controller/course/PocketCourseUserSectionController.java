package com.renxin.pocket.controller.course;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.course.constant.CourConstant;
import com.renxin.course.domain.CourUserCourseSection;
import com.renxin.course.service.ICourUserCourseSectionService;
import com.renxin.framework.web.service.PocketTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/pocket/course/userSection")
@Api(value = "pocketCourseSectionController" ,tags = {"用户课程章节API"})
public class PocketCourseUserSectionController extends BaseController {

    @Autowired
    private ICourUserCourseSectionService courUserCourseSectionService;

    @Resource
    private PocketTokenService pocketTokenService;

    @Autowired
    private ICourUserCourseSectionService userCourseSectionService;
    
    /**
     * 查询用户的课程列表
     */
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


    /**
     * 修改课程笔记
     */
    @PostMapping("/updateSectionNote")
    @ApiOperation("修改课程章节笔记")
    @RateLimiter
    public AjaxResult updateSectionNote(@RequestBody CourUserCourseSection req, HttpServletRequest request)
    {
        Long userId = pocketTokenService.getUserId(request);
        req.setUserType(1);//来访者
        req.setUserId(userId);
        Integer i = userCourseSectionService.updateSectionNote(req);
        return AjaxResult.success(i);
    }

    /**
     * 查询笔记
     */
//    @PreAuthorize("@ss.hasPermi('course:section:list')")
    @PostMapping("/sectionNoteList")
    @ApiOperation("查询笔记清单")
    @RateLimiter
    public TableDataInfo sectionNoteList(@RequestBody CourUserCourseSection req, HttpServletRequest request)
    {
        Long userId = pocketTokenService.getUserId(request);
        req.setUserType(1);//来访者
        req.setUserId(userId);
        req.setIsQueryNote(true);
        List<CourUserCourseSection> list = userCourseSectionService.selectCourUserCourseSectionList(req);
        return getDataTable(list);
    }
}

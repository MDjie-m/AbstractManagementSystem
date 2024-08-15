package com.renxin.pocket.controller.course;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.course.constant.CourConstant;
import com.renxin.course.domain.CourCourse;
import com.renxin.course.domain.CourOrder;
import com.renxin.course.domain.CourSection;
import com.renxin.course.domain.CourUserCourseSection;
import com.renxin.course.service.ICourCourseService;
import com.renxin.course.service.ICourOrderService;
import com.renxin.course.service.ICourSectionService;
import com.renxin.course.service.ICourUserCourseSectionService;
import com.renxin.course.vo.CourseListVO;
import com.renxin.course.vo.CourseVO;
import com.renxin.course.vo.SectionVO;
import com.renxin.framework.web.service.PocketTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 课程Controller
 *
 * @author luzijun
 * @date 2023-03-15
 */
@RestController
@RequestMapping("/pocket/course")
@Api(value = "pocketCourseController" ,tags = {"课程API"})
public class PocketCourseController extends BaseController
{
    @Autowired
    private ICourCourseService courCourseService;

    @Autowired
    private ICourUserCourseSectionService courUserCourseSectionService;

    @Autowired
    private ICourOrderService courOrderService;

    @Autowired
    private ICourSectionService courSectionService;

    @Autowired
    private PocketTokenService pocketTokenService;




    /**
     * 根据课程类型查询课程列表
     */
//    @PreAuthorize("@ss.hasPermi('course:course:list')")
    @PostMapping("/list")
    @ApiOperation("查询课程列表")
    @RateLimiter
    public TableDataInfo list(@RequestBody CourCourse courCourse)
    {
        startPage();
        courCourse.setOnSale(CourConstant.COUR_COURSE_ON_SALE);
        courCourse.setServiceTo(1);
        List<CourseListVO> list = courCourseService.getCourseListByClassId(courCourse);
        return getDataTable(list);
    }

    /**
     * 根据搜素条件查询课程列表
     */
    @PostMapping("/search")
    @ApiOperation("查询课程列表")
    @RateLimiter
    public AjaxResult getList(@RequestParam String searchValue)    {
        // 只查询已上架的课程
        CourCourse course = new CourCourse();
        course.setOnSale(CourConstant.COUR_COURSE_ON_SALE);
        List<CourCourse> list = courCourseService.selectCourCourseList(course);

        list = list.stream()
                .filter(item -> item.getName().contains(searchValue) || item.getAuthor().contains(searchValue))
                .collect(Collectors.toList());
        return AjaxResult.success(list);
    }

    /**
     * 查询用户的课程列表
     */
//    @PreAuthorize("@ss.hasPermi('course:userSection:list')")
    @PostMapping("/getCourseListByUserId")
    @ApiOperation("根据用户ID查询课程列表")
    @RateLimiter
    public TableDataInfo getCourseListByUserId(@RequestParam Long userId)
    {
        startPage();
        List<CourCourse> list = courCourseService.getCourseListByUserId(userId);
        List<CourseVO> courseVOList = new ArrayList<>();
        List<CourseVO> courseFinishList = new ArrayList<>();
        list.forEach(courCourse -> {
            CourseVO courseVO = new CourseVO();

            // 计算课程完成情况
            boolean hasUnFinished = courCourseService.calCourCourseList(userId, courCourse.getId());
            BeanUtils.copyProperties(courCourse, courseVO);
            courseVO.setFinishStatus(hasUnFinished ? 0 : 1);

            // 增加章节列表
            CourSection courSection = CourSection.builder()
                    .courseId(courCourse.getId())
                    .build();
            List<CourSection> sectionList = courSectionService.selectCourSectionList(courSection);
            courseVO.setSectionList(sectionList);

            // 计算课程学习时长
            Integer studyDuration = courCourseService.calCourCourseStudyDuration(userId, courCourse.getId());
            courseVO.setStudyDuration(studyDuration);
            if (hasUnFinished) {
                courseVOList.add(courseVO);
                courseVOList.stream()
                        .sorted(Comparator.comparingInt(CourseVO::getStudyDuration))
                        .forEach(System.out::println);
            }
            else {
                courseFinishList.add(courseVO);
            }

        });
        courseVOList.addAll(courseFinishList);
        return getDataTable(courseVOList);
    }

    /**
     * 查询课程信息，与用户无关的信息
     */
//    @PreAuthorize("@ss.hasPermi('course:course:query')")
    @PostMapping(value = "/getInfo")
    @ApiOperation("查询课程信息")
    @RateLimiter
    public AjaxResult getInfo(@RequestParam(value = "id") Long courseId)
    {
        CourCourse course = courCourseService.selectCourCourseById(courseId);
        if (course == null) {
            return AjaxResult.error("查询课程详情失败");
        }
        // 查询课程的学习人数
        CourUserCourseSection courUserCourseSection = new CourUserCourseSection();
        courUserCourseSection.setCourseId(courseId);
        List<CourUserCourseSection> courUserCourseSectionList = courUserCourseSectionService.selectCourUserCourseSectionList(courUserCourseSection);

        CourseVO courseVO = new CourseVO();
        BeanUtils.copyProperties(course, courseVO);
        courseVO.setStudyNum(courUserCourseSectionList.size());

        // 增加章节列表
        CourSection courSection = CourSection.builder()
                .courseId(courseId)
                .build();
        List<CourSection> sectionList = courSectionService.selectCourSectionList(courSection);
        // 查询章节的学习情况
        List<SectionVO> sectionVOList = new ArrayList<>();
        for (CourSection section: sectionList) {
            SectionVO sectionVO = new SectionVO();
            BeanUtils.copyProperties(section, sectionVO);
            sectionVOList.add(sectionVO);
        }
        courseVO.setSectionList(sectionVOList);

        return AjaxResult.success(courseVO);
    }

    /**
     * 根据课程主键查询课程详情
     */
//    @PreAuthorize("@ss.hasPermi('course:course:query')")
    @PostMapping(value = "/detail/{courseId}")
    @ApiOperation("查询课程详情")
    @RateLimiter
    public AjaxResult detail(@PathVariable("courseId") Long courseId, HttpServletRequest request)
    {
        LoginDTO loginUser = pocketTokenService.getLoginUser(request);
        Long userId = loginUser.getUserId();

        CourCourse course = courCourseService.selectCourCourseById(courseId);
        if (course == null) {
            return AjaxResult.error("查询课程详情失败");
        }

        // 查询课程的学习人数
        CourUserCourseSection courUserCourseSection = new CourUserCourseSection();
        courUserCourseSection.setCourseId(courseId);
        List<CourUserCourseSection> courUserCourseSectionList = courUserCourseSectionService.selectCourUserCourseSectionList(courUserCourseSection);

        CourseVO courseVO = new CourseVO();
        BeanUtils.copyProperties(course, courseVO);
        courseVO.setStudyNum(courUserCourseSectionList.size());

        // 增加章节列表
        CourSection courSection = CourSection.builder()
                .courseId(courseId)
                .build();
        List<CourSection> sectionList = courSectionService.selectCourSectionList(courSection);
        // 查询章节的学习情况
        List<SectionVO> sectionVOList = new ArrayList<>();


        for (CourSection section: sectionList) {
            SectionVO sectionVO = new SectionVO();
            CourUserCourseSection userCourseSection = new CourUserCourseSection();
            userCourseSection.setUserId(userId.longValue());
            userCourseSection.setCourseId(courseId);
            BeanUtils.copyProperties(section, sectionVO);
            userCourseSection.setSectionId(section.getId());
            sectionVO.setEndTime(courUserCourseSectionService.findEndTime(userCourseSection));

            // 未购买课程的普通章节不返回内容链接
            if (courCourseService.getPaidCourseCount(userId.longValue(), courseId) == 0 && sectionVO.getType() == CourConstant.SECTION_NORMAL) {
                sectionVO.setContentUrl(null);
            }

            sectionVOList.add(sectionVO);
        }
        courseVO.setSectionList(sectionVOList);

        // 查询用户有没有购买该订单
        if (userId == 0 || courseVO.getPayType() == CourConstant.COURSE_FREE) { // 没有给出用户标识
            return AjaxResult.success(courseVO);
        }
        List<CourOrder> courOrderList = courOrderService.selectCourOrderByUser(userId.longValue(), courseId);
        courseVO.setIsBuy(courOrderList.size() > 0 ? CourConstant.COURSE_BUY : CourConstant.COURSE_NOT_BUY);

        return AjaxResult.success(courseVO);
    }

}

package com.renxin.consultant.controller.course;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.course.constant.CourConstant;
import com.renxin.course.domain.*;
import com.renxin.course.service.*;
import com.renxin.course.vo.CourseListVO;
import com.renxin.course.vo.CourseVO;
import com.renxin.course.vo.SectionVO;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.framework.web.service.PocketTokenService;
import com.renxin.psychology.domain.PsyConsultClass;
import com.renxin.psychology.service.IPsyConsultClassService;
import com.renxin.psychology.vo.PsyConsultClassVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/consultant/course")
@Api(value = "ConsultantCourseController" ,tags = {"咨询师课程Api"})
public class ConsultantCourseController extends BaseController {

    @Resource
    private IPsyConsultClassService PsyConsultClassService;

    @Autowired
    private ICourCourseService courCourseService;

    @Autowired
    private ICourUserCourseSectionService courUserCourseSectionService;

    @Autowired
    private ICourOrderService courOrderService;

    @Autowired
    private ICourSectionService courSectionService;

    @Resource
    ConsultantTokenService consultantTokenService;

    @Autowired
    private ICourCourseClassService courCourseClassService;
    
    @Autowired
    private ICourUserCourseSectionService userCourseSectionService;
    

    @ApiOperation(value = "查询class列表")
    @PostMapping("/class/list")
    @RateLimiter
    public TableDataInfo list(@RequestBody CourCourseClass courCourseClass)
    {
        startPage();
        courCourseClass.setServiceTo(2);//咨询师
        List<CourCourseClass> list = courCourseClassService.selectCourCourseClassList(courCourseClass);
        return getDataTable(list);
    }
    
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
        courCourse.setServiceTo(2);//面向咨询师
        List<CourseListVO> list = courCourseService.getCourseListByClassId(courCourse);
        return getDataTable(list);
    }

    /**
     * 根据搜素条件查询课程列表
     */
    @PostMapping("/search")
    @ApiOperation("查询课程列表")
    @RateLimiter
    public AjaxResult getList(@RequestParam String searchValue) {
        // 只查询已上架的课程
        CourCourse course = new CourCourse();
        course.setOnSale(CourConstant.COUR_COURSE_ON_SALE);
        course.setServiceTo(2);//面向咨询师
        List<CourCourse> list = courCourseService.selectCourCourseList(course);

        list = list.stream()
                .filter(item -> item.getName().contains(searchValue) || item.getAuthor().contains(searchValue))
                .collect(Collectors.toList());
        return AjaxResult.success(list);
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
    public AjaxResult detail(@PathVariable("courseId") Long courseId,HttpServletRequest request)
    {
        Long cUserId = consultantTokenService.getConsultId(request);

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
            userCourseSection.setUserId(cUserId);
            userCourseSection.setCourseId(courseId);
            BeanUtils.copyProperties(section, sectionVO);
            userCourseSection.setSectionId(section.getId());
            sectionVO.setEndTime(courUserCourseSectionService.findEndTime(userCourseSection));

            // 未购买课程的普通章节不返回内容链接
            if (courCourseService.getPaidCourseCount(cUserId, courseId) == 0 && sectionVO.getType() == CourConstant.SECTION_NORMAL) {
                sectionVO.setContentUrl(null);
            }

            sectionVOList.add(sectionVO);
        }
        courseVO.setSectionList(sectionVOList);

        // 查询用户有没有购买该订单
        if (cUserId == 0 || courseVO.getPayType() == CourConstant.COURSE_FREE) { // 没有给出用户标识
            return AjaxResult.success(courseVO);
        }
        List<CourOrder> courOrderList = courOrderService.selectCourOrderByUser(cUserId, courseId);
        courseVO.setIsBuy(courOrderList.size() > 0 ? CourConstant.COURSE_BUY : CourConstant.COURSE_NOT_BUY);

        return AjaxResult.success(courseVO);
    }

    
    /**
     * 修改课程笔记
     */
    @PostMapping("/updateSectionNote")
    @ApiOperation("修改课程章节笔记")
    @RateLimiter
    public AjaxResult updateSectionNote(@RequestBody CourUserCourseSection req, HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        req.setUserType(2);//咨询师
        req.setUserId(consultId);
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
        Long consultId = consultantTokenService.getConsultId(request);
        req.setIsQueryNote(true);
        req.setUserId(consultId);
        req.setUserType(2);//咨询师
        List<CourUserCourseSection> list = userCourseSectionService.selectCourUserCourseSectionList(req);
        
        return getDataTable(list);
    }
    
    
    /**
     * 查询课程章节列表
     */
//    @PreAuthorize("@ss.hasPermi('course:section:list')")
    @PostMapping("/sectionList")
    @ApiOperation("查询课程章节列表")
    @RateLimiter
    public TableDataInfo sectionList(@RequestBody CourSection req, HttpServletRequest request)
    {
        List<CourSection> list = courSectionService.selectCourSectionList(req);
        Long consultId = consultantTokenService.getConsultId(request);
        // 判断课程是否已经下单支付
        if (courCourseService.getPaidCourseCount(consultId, req.getCourseId()) == 0) { // 未下单支付
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
    @PostMapping(value = "/getSectionInfo")
    @ApiOperation("查询章节详情")
    @RateLimiter
    public AjaxResult getSectionInfo(@RequestParam(value = "id") Long id)
    {
        return AjaxResult.success(courSectionService.selectCourSectionById(id));
    }


    /**
     * 新增或更新用户-课程-章节关系
     */
    @PostMapping("/saveUserSectionInfo")
    @ApiOperation("记录课程章节完成情况，新增或更新用户-课程-章节关系")
    @RateLimiter
    public AjaxResult saveUserSectionInfo(@RequestBody CourUserCourseSection userCourseSection, HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        if (userCourseSection.getFinishStatus() == null) {
            userCourseSection.setFinishStatus(CourConstant.SECTION_UNFINISHED);
        }
        // 根据用户、课程、章节查询是否已有学习完成记录
        CourUserCourseSection queryParams = new CourUserCourseSection();
        queryParams.setUserId(consultId);
        queryParams.setUserType(2);//咨询师
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

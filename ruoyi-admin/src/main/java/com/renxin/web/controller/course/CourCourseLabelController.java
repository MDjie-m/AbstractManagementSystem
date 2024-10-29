package com.renxin.web.controller.course;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.renxin.course.mapper.CourCourseMapper;
import com.renxin.course.service.ICourCourseService;
import com.renxin.course.vo.LabelVO;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.enums.BusinessType;
import com.renxin.course.domain.CourCourseLabel;
import com.renxin.course.service.ICourCourseLabelService;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.common.core.page.TableDataInfo;

/**
 * labelController
 * 
 * @author renxin
 * @date 2023-03-16
 */
@RestController
@RequestMapping("/course/label")
public class CourCourseLabelController extends BaseController
{
    @Autowired
    private ICourCourseLabelService courCourseLabelService;

    @Autowired
    private ICourCourseService courCourseService;

    @Autowired
    private CourCourseMapper courCourseMapper;
    /**
     * 查询label列表
     */
    @PreAuthorize("@ss.hasPermi('course:label:list')")
    @GetMapping("/list")
    public TableDataInfo list(CourCourseLabel courCourseLabel)
    {
        startPage();
        List<CourCourseLabel> list = courCourseLabelService.selectCourCourseLabelList(courCourseLabel);
        List<LabelVO> labelVOList = transCourCourseLabelsToLabelVOs(list);

        return getDataTable(labelVOList);
    }

    private List<LabelVO> transCourCourseLabelsToLabelVOs(List<CourCourseLabel> list) {
        List<LabelVO> labelVOList = new ArrayList<>();
        for (CourCourseLabel label : list) {
            LabelVO labelVO = transCourCourseLabelToLabelVO(label);
            labelVOList.add(labelVO);
        }
        return labelVOList;
    }

    private LabelVO transCourCourseLabelToLabelVO(CourCourseLabel label) {
        LabelVO labelVO = new LabelVO();
        BeanUtils.copyProperties(label, labelVO);
        // 根据课程ID查询课程名称
        String courseName = courCourseMapper.selectCourCourseById(label.getCourseId()).getName();

        labelVO.setCourseName(courseName);
        return labelVO;
    }
    /**
     * 导出label列表
     */
    @PreAuthorize("@ss.hasPermi('course:label:export')")
    @Log(title = "label", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CourCourseLabel courCourseLabel)
    {
        List<CourCourseLabel> list = courCourseLabelService.selectCourCourseLabelList(courCourseLabel);
        List<LabelVO> labelVOList = transCourCourseLabelsToLabelVOs(list);

        ExcelUtil<LabelVO> util = new ExcelUtil<LabelVO>(LabelVO.class);
        util.exportExcel(response, labelVOList, "label数据");
    }

    /**
     * 获取label详细信息
     */
    @PreAuthorize("@ss.hasPermi('course:label:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(transCourCourseLabelToLabelVO(courCourseLabelService.selectCourCourseLabelById(id)));
    }

    /**
     * 新增label
     */
    @PreAuthorize("@ss.hasPermi('course:label:add')")
    @Log(title = "label", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CourCourseLabel courCourseLabel)
    {
        try {
            int res = courCourseLabelService.insertCourCourseLabel(courCourseLabel);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "新增课程标签失败");
        }
    }

    /**
     * 修改label
     */
    @PreAuthorize("@ss.hasPermi('course:label:edit')")
    @Log(title = "label", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CourCourseLabel courCourseLabel)
    {
        try {
            int res = courCourseLabelService.updateCourCourseLabel(courCourseLabel);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "修改课程标签失败");
        }
    }

    /**
     * 删除label
     */
    @PreAuthorize("@ss.hasPermi('course:label:remove')")
    @Log(title = "label", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        try {
            int res = courCourseLabelService.deleteCourCourseLabelByIds(ids);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "删除课程标签失败");
        }
    }
}

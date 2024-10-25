package com.ruoyi.system.service;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.system.domain.bo.CoursesBo;
import com.ruoyi.system.domain.vo.CoursesVo;

import java.util.Collection;
import java.util.List;

/**
 * 课程Service接口
 *
 * @author nbacheng
 * @date 2024-10-25
 */
public interface ICoursesService {

    /**
     * 查询课程
     */
    CoursesVo queryById(Long id);

    /**
     * 查询课程列表
     */
    TableDataInfo<CoursesVo> queryPageList(CoursesBo bo, PageQuery pageQuery);

    /**
     * 查询课程列表
     */
    List<CoursesVo> queryList(CoursesBo bo);

    /**
     * 新增课程
     */
    Boolean insertByBo(CoursesBo bo);

    /**
     * 修改课程
     */
    Boolean updateByBo(CoursesBo bo);

    /**
     * 校验并批量删除课程信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}

package com.ruoyi.system.service;


import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.system.domain.bo.CoursesMenuBo;
import com.ruoyi.system.domain.vo.CoursesMenuVo;

import java.util.Collection;
import java.util.List;

/**
 * 课程目录Service接口
 *
 * @author nbacheng
 * @date 2024-10-25
 */
public interface ICoursesMenuService {

    /**
     * 查询课程目录
     */
    CoursesMenuVo queryById(Long id);

    /**
     * 查询课程目录列表
     */
    TableDataInfo<CoursesMenuVo> queryPageList(CoursesMenuBo bo, PageQuery pageQuery);

    /**
     * 查询课程目录列表
     */
    List<CoursesMenuVo> queryList(CoursesMenuBo bo);

    /**
     * 新增课程目录
     */
    Boolean insertByBo(CoursesMenuBo bo);

    /**
     * 修改课程目录
     */
    Boolean updateByBo(CoursesMenuBo bo);

    /**
     * 校验并批量删除课程目录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}

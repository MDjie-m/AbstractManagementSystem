package com.ruoyi.system.service;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.system.domain.bo.CoursesFileBo;
import com.ruoyi.system.domain.vo.CoursesFileVo;

import java.util.Collection;
import java.util.List;

/**
 * 课程相关文件Service接口
 *
 * @author nbacheng
 * @date 2024-10-25
 */
public interface ICoursesFileService {

    /**
     * 查询课程相关文件
     */
    CoursesFileVo queryById(Long fileId);

    /**
     * 查询课程相关文件列表
     */
    TableDataInfo<CoursesFileVo> queryPageList(CoursesFileBo bo, PageQuery pageQuery);

    /**
     * 查询课程相关文件列表
     */
    List<CoursesFileVo> queryList(CoursesFileBo bo);

    /**
     * 新增课程相关文件
     */
    Boolean insertByBo(CoursesFileBo bo);

    /**
     * 修改课程相关文件
     */
    Boolean updateByBo(CoursesFileBo bo);

    /**
     * 校验并批量删除课程相关文件信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}

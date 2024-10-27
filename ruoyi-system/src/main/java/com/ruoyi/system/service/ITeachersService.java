package com.ruoyi.system.service;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.system.domain.bo.TeachersBo;
import com.ruoyi.system.domain.vo.TeachersVo;

import java.util.Collection;
import java.util.List;

/**
 * 教师Service接口
 *
 * @author nbacheng
 * @date 2024-10-25
 */
public interface ITeachersService {

    /**
     * 查询教师
     */
    TeachersVo queryById(Long id);

    /**
     * 查询教师列表
     */
    TableDataInfo<TeachersVo> queryPageList(TeachersBo bo, PageQuery pageQuery);

    /**
     * 查询教师列表
     */
    List<TeachersVo> queryList(TeachersBo bo);

    /**
     * 新增教师
     */
    Boolean insertByBo(TeachersBo bo);

    /**
     * 修改教师
     */
    Boolean updateByBo(TeachersBo bo);

    /**
     * 校验并批量删除教师信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}

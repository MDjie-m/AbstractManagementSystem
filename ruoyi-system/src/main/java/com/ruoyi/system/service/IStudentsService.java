package com.ruoyi.system.service;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.system.domain.bo.StudentsBo;
import com.ruoyi.system.domain.vo.StudentsVo;

import java.util.Collection;
import java.util.List;

/**
 * 学生Service接口
 *
 * @author nbacheng
 * @date 2024-10-25
 */
public interface IStudentsService {

    /**
     * 查询学生
     */
    StudentsVo queryById(Long id);

    /**
     * 查询学生列表
     */
    TableDataInfo<StudentsVo> queryPageList(StudentsBo bo, PageQuery pageQuery);

    /**
     * 查询学生列表
     */
    List<StudentsVo> queryList(StudentsBo bo);

    /**
     * 新增学生
     */
    Boolean insertByBo(StudentsBo bo);

    /**
     * 修改学生
     */
    Boolean updateByBo(StudentsBo bo);

    /**
     * 校验并批量删除学生信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}

package com.ruoyi.system.service;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.system.domain.bo.ClassesBo;
import com.ruoyi.system.domain.vo.ClassesVo;

import java.util.Collection;
import java.util.List;

/**
 * 班级Service接口
 *
 * @author nbacheng
 * @date 2024-10-25
 */
public interface IClassesService {

    /**
     * 查询班级
     */
    ClassesVo queryById(Long id);

    /**
     * 查询班级列表
     */
    TableDataInfo<ClassesVo> queryPageList(ClassesBo bo, PageQuery pageQuery);

    /**
     * 查询班级列表
     */
    List<ClassesVo> queryList(ClassesBo bo);

    /**
     * 新增班级
     */
    Boolean insertByBo(ClassesBo bo);

    /**
     * 修改班级
     */
    Boolean updateByBo(ClassesBo bo);

    /**
     * 校验并批量删除班级信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}

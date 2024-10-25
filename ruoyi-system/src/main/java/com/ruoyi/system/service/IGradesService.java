package com.ruoyi.system.service;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.system.domain.bo.GradesBo;
import com.ruoyi.system.domain.vo.GradesVo;

import java.util.Collection;
import java.util.List;

/**
 * 年级Service接口
 *
 * @author nbacheng
 * @date 2024-10-25
 */
public interface IGradesService {

    /**
     * 查询年级
     */
    GradesVo queryById(Long id);

    /**
     * 查询年级列表
     */
    TableDataInfo<GradesVo> queryPageList(GradesBo bo, PageQuery pageQuery);

    /**
     * 查询年级列表
     */
    List<GradesVo> queryList(GradesBo bo);

    /**
     * 新增年级
     */
    Boolean insertByBo(GradesBo bo);

    /**
     * 修改年级
     */
    Boolean updateByBo(GradesBo bo);

    /**
     * 校验并批量删除年级信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}

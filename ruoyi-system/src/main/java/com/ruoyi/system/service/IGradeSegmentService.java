package com.ruoyi.system.service;


import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.system.domain.bo.GradeSegmentBo;
import com.ruoyi.system.domain.vo.GradeSegmentVo;

import java.util.Collection;
import java.util.List;

/**
 * 年段Service接口
 *
 * @author nbacheng
 * @date 2024-10-25
 */
public interface IGradeSegmentService {

    /**
     * 查询年段
     */
    GradeSegmentVo queryById(Long id);

    /**
     * 查询年段列表
     */
    TableDataInfo<GradeSegmentVo> queryPageList(GradeSegmentBo bo, PageQuery pageQuery);

    /**
     * 查询年段列表
     */
    List<GradeSegmentVo> queryList(GradeSegmentBo bo);

    /**
     * 新增年段
     */
    Boolean insertByBo(GradeSegmentBo bo);

    /**
     * 修改年段
     */
    Boolean updateByBo(GradeSegmentBo bo);

    /**
     * 校验并批量删除年段信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}

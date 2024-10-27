package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.domain.GradeSegment;
import com.ruoyi.system.domain.bo.GradeSegmentBo;
import com.ruoyi.system.domain.vo.GradeSegmentVo;
import com.ruoyi.system.mapper.GradeSegmentMapper;
import com.ruoyi.system.service.IGradeSegmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 年段Service业务层处理
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@RequiredArgsConstructor
@Service
public class GradeSegmentServiceImpl implements IGradeSegmentService {

    private final GradeSegmentMapper baseMapper;

    /**
     * 查询年段
     */
    @Override
    public GradeSegmentVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询年段列表
     */
    @Override
    public TableDataInfo<GradeSegmentVo> queryPageList(GradeSegmentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<GradeSegment> lqw = buildQueryWrapper(bo);
        Page<GradeSegmentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询年段列表
     */
    @Override
    public List<GradeSegmentVo> queryList(GradeSegmentBo bo) {
        LambdaQueryWrapper<GradeSegment> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<GradeSegment> buildQueryWrapper(GradeSegmentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<GradeSegment> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getSchoolId() != null, GradeSegment::getSchoolId, bo.getSchoolId());
        lqw.like(StringUtils.isNotBlank(bo.getSegmentName()), GradeSegment::getSegmentName, bo.getSegmentName());
        lqw.eq(bo.getCreateDate() != null, GradeSegment::getCreateDate, bo.getCreateDate());
        lqw.eq(bo.getUpdateDate() != null, GradeSegment::getUpdateDate, bo.getUpdateDate());
        return lqw;
    }

    /**
     * 新增年段
     */
    @Override
    public Boolean insertByBo(GradeSegmentBo bo) {
        GradeSegment add = BeanUtil.toBean(bo, GradeSegment.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改年段
     */
    @Override
    public Boolean updateByBo(GradeSegmentBo bo) {
        GradeSegment update = BeanUtil.toBean(bo, GradeSegment.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(GradeSegment entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除年段
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

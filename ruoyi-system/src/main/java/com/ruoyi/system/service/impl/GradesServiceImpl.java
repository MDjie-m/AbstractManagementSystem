package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.domain.Grades;
import com.ruoyi.system.domain.bo.GradesBo;
import com.ruoyi.system.domain.vo.GradesVo;
import com.ruoyi.system.mapper.GradesMapper;
import com.ruoyi.system.service.IGradesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 年级Service业务层处理
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@RequiredArgsConstructor
@Service
public class GradesServiceImpl implements IGradesService {

    private final GradesMapper baseMapper;

    /**
     * 查询年级
     */
    @Override
    public GradesVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询年级列表
     */
    @Override
    public TableDataInfo<GradesVo> queryPageList(GradesBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Grades> lqw = buildQueryWrapper(bo);
        Page<GradesVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询年级列表
     */
    @Override
    public List<GradesVo> queryList(GradesBo bo) {
        LambdaQueryWrapper<Grades> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Grades> buildQueryWrapper(GradesBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Grades> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getGradeName()), Grades::getGradeName, bo.getGradeName());
        lqw.eq(bo.getGradeSegmentId() != null, Grades::getGradeSegmentId, bo.getGradeSegmentId());
        lqw.eq(bo.getSchoolId() != null, Grades::getSchoolId, bo.getSchoolId());
        lqw.eq(bo.getCreateDate() != null, Grades::getCreateDate, bo.getCreateDate());
        lqw.eq(bo.getUpdateDate() != null, Grades::getUpdateDate, bo.getUpdateDate());
        return lqw;
    }

    /**
     * 新增年级
     */
    @Override
    public Boolean insertByBo(GradesBo bo) {
        Grades add = BeanUtil.toBean(bo, Grades.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改年级
     */
    @Override
    public Boolean updateByBo(GradesBo bo) {
        Grades update = BeanUtil.toBean(bo, Grades.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Grades entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除年级
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

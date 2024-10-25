package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.domain.Classes;
import com.ruoyi.system.domain.bo.ClassesBo;
import com.ruoyi.system.domain.vo.ClassesVo;
import com.ruoyi.system.mapper.ClassesMapper;
import com.ruoyi.system.service.IClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 班级Service业务层处理
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@RequiredArgsConstructor
@Service
public class ClassesServiceImpl implements IClassesService {

    private final ClassesMapper baseMapper;

    /**
     * 查询班级
     */
    @Override
    public ClassesVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询班级列表
     */
    @Override
    public TableDataInfo<ClassesVo> queryPageList(ClassesBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Classes> lqw = buildQueryWrapper(bo);
        Page<ClassesVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询班级列表
     */
    @Override
    public List<ClassesVo> queryList(ClassesBo bo) {
        LambdaQueryWrapper<Classes> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Classes> buildQueryWrapper(ClassesBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Classes> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getGradeId() != null, Classes::getGradeId, bo.getGradeId());
        lqw.like(StringUtils.isNotBlank(bo.getClassName()), Classes::getClassName, bo.getClassName());
        lqw.eq(bo.getHeadTeacherId() != null, Classes::getHeadTeacherId, bo.getHeadTeacherId());
        lqw.eq(bo.getGradeSegmentId() != null, Classes::getGradeSegmentId, bo.getGradeSegmentId());
        lqw.eq(bo.getSchoolId() != null, Classes::getSchoolId, bo.getSchoolId());
        lqw.eq(bo.getCreateDate() != null, Classes::getCreateDate, bo.getCreateDate());
        lqw.eq(bo.getUpdateDate() != null, Classes::getUpdateDate, bo.getUpdateDate());
        return lqw;
    }

    /**
     * 新增班级
     */
    @Override
    public Boolean insertByBo(ClassesBo bo) {
        Classes add = BeanUtil.toBean(bo, Classes.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改班级
     */
    @Override
    public Boolean updateByBo(ClassesBo bo) {
        Classes update = BeanUtil.toBean(bo, Classes.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Classes entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除班级
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

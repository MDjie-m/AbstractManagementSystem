package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.domain.Courses;
import com.ruoyi.system.domain.bo.CoursesBo;
import com.ruoyi.system.domain.vo.CoursesVo;
import com.ruoyi.system.mapper.CoursesMapper;
import com.ruoyi.system.service.ICoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 课程Service业务层处理
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@RequiredArgsConstructor
@Service
public class CoursesServiceImpl implements ICoursesService {

    private final CoursesMapper baseMapper;

    /**
     * 查询课程
     */
    @Override
    public CoursesVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询课程列表
     */
    @Override
    public TableDataInfo<CoursesVo> queryPageList(CoursesBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Courses> lqw = buildQueryWrapper(bo);
        Page<CoursesVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询课程列表
     */
    @Override
    public List<CoursesVo> queryList(CoursesBo bo) {
        LambdaQueryWrapper<Courses> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Courses> buildQueryWrapper(CoursesBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Courses> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getCourseName()), Courses::getCourseName, bo.getCourseName());
        lqw.eq(StringUtils.isNotBlank(bo.getCourseDescription()), Courses::getCourseDescription, bo.getCourseDescription());
        lqw.eq(StringUtils.isNotBlank(bo.getCourseCover()), Courses::getCourseCover, bo.getCourseCover());
        lqw.eq(bo.getCreateDate() != null, Courses::getCreateDate, bo.getCreateDate());
        lqw.eq(bo.getUpdateDate() != null, Courses::getUpdateDate, bo.getUpdateDate());
        return lqw;
    }

    /**
     * 新增课程
     */
    @Override
    public Boolean insertByBo(CoursesBo bo) {
        Courses add = BeanUtil.toBean(bo, Courses.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改课程
     */
    @Override
    public Boolean updateByBo(CoursesBo bo) {
        Courses update = BeanUtil.toBean(bo, Courses.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Courses entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除课程
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.domain.Students;
import com.ruoyi.system.domain.bo.StudentsBo;
import com.ruoyi.system.domain.vo.StudentsVo;
import com.ruoyi.system.mapper.StudentsMapper;
import com.ruoyi.system.service.IStudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 学生Service业务层处理
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@RequiredArgsConstructor
@Service
public class StudentsServiceImpl implements IStudentsService {

    private final StudentsMapper baseMapper;

    /**
     * 查询学生
     */
    @Override
    public StudentsVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询学生列表
     */
    @Override
    public TableDataInfo<StudentsVo> queryPageList(StudentsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Students> lqw = buildQueryWrapper(bo);
        Page<StudentsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询学生列表
     */
    @Override
    public List<StudentsVo> queryList(StudentsBo bo) {
        LambdaQueryWrapper<Students> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Students> buildQueryWrapper(StudentsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Students> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getClassId() != null, Students::getClassId, bo.getClassId());
        lqw.like(StringUtils.isNotBlank(bo.getStudentName()), Students::getStudentName, bo.getStudentName());
        lqw.eq(StringUtils.isNotBlank(bo.getStudentNo()), Students::getStudentNo, bo.getStudentNo());
        lqw.eq(bo.getEnrolledTime() != null, Students::getEnrolledTime, bo.getEnrolledTime());
        lqw.eq(StringUtils.isNotBlank(bo.getSex()), Students::getSex, bo.getSex());
        lqw.eq(bo.getSchoolId() != null, Students::getSchoolId, bo.getSchoolId());
        lqw.eq(bo.getCreateDate() != null, Students::getCreateDate, bo.getCreateDate());
        lqw.eq(bo.getUpdateDate() != null, Students::getUpdateDate, bo.getUpdateDate());
        return lqw;
    }

    /**
     * 新增学生
     */
    @Override
    public Boolean insertByBo(StudentsBo bo) {
        Students add = BeanUtil.toBean(bo, Students.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改学生
     */
    @Override
    public Boolean updateByBo(StudentsBo bo) {
        Students update = BeanUtil.toBean(bo, Students.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Students entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除学生
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.domain.Teachers;
import com.ruoyi.system.domain.bo.TeachersBo;
import com.ruoyi.system.domain.vo.TeachersVo;
import com.ruoyi.system.mapper.TeachersMapper;
import com.ruoyi.system.service.ITeachersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 教师Service业务层处理
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@RequiredArgsConstructor
@Service
public class TeachersServiceImpl implements ITeachersService {

    private final TeachersMapper baseMapper;

    /**
     * 查询教师
     */
    @Override
    public TeachersVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询教师列表
     */
    @Override
    public TableDataInfo<TeachersVo> queryPageList(TeachersBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Teachers> lqw = buildQueryWrapper(bo);
        Page<TeachersVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询教师列表
     */
    @Override
    public List<TeachersVo> queryList(TeachersBo bo) {
        LambdaQueryWrapper<Teachers> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Teachers> buildQueryWrapper(TeachersBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Teachers> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getSchoolId() != null, Teachers::getSchoolId, bo.getSchoolId());
        lqw.like(StringUtils.isNotBlank(bo.getTeacherName()), Teachers::getTeacherName, bo.getTeacherName());
        lqw.eq(bo.getCreateDate() != null, Teachers::getCreateDate, bo.getCreateDate());
        lqw.eq(bo.getUpdateDate() != null, Teachers::getUpdateDate, bo.getUpdateDate());
        return lqw;
    }

    /**
     * 新增教师
     */
    @Override
    public Boolean insertByBo(TeachersBo bo) {
        Teachers add = BeanUtil.toBean(bo, Teachers.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改教师
     */
    @Override
    public Boolean updateByBo(TeachersBo bo) {
        Teachers update = BeanUtil.toBean(bo, Teachers.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Teachers entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除教师
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.domain.CoursesMenu;
import com.ruoyi.system.domain.bo.CoursesMenuBo;
import com.ruoyi.system.domain.vo.CoursesMenuVo;
import com.ruoyi.system.mapper.CoursesMenuMapper;
import com.ruoyi.system.service.ICoursesMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 课程目录Service业务层处理
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@RequiredArgsConstructor
@Service
public class CoursesMenuServiceImpl implements ICoursesMenuService {

    private final CoursesMenuMapper baseMapper;

    /**
     * 查询课程目录
     */
    @Override
    public CoursesMenuVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询课程目录列表
     */
    @Override
    public TableDataInfo<CoursesMenuVo> queryPageList(CoursesMenuBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CoursesMenu> lqw = buildQueryWrapper(bo);
        Page<CoursesMenuVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询课程目录列表
     */
    @Override
    public List<CoursesMenuVo> queryList(CoursesMenuBo bo) {
        LambdaQueryWrapper<CoursesMenu> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CoursesMenu> buildQueryWrapper(CoursesMenuBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CoursesMenu> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getMemuName()), CoursesMenu::getMemuName, bo.getMemuName());
        lqw.eq(bo.getCourseId() != null, CoursesMenu::getCourseId, bo.getCourseId());
        lqw.eq(bo.getSort() != null, CoursesMenu::getSort, bo.getSort());
        lqw.eq(bo.getCreateTime() != null, CoursesMenu::getCreateTime, bo.getCreateTime());
        lqw.eq(bo.getUpdateTime() != null, CoursesMenu::getUpdateTime, bo.getUpdateTime());
        return lqw;
    }

    /**
     * 新增课程目录
     */
    @Override
    public Boolean insertByBo(CoursesMenuBo bo) {
        CoursesMenu add = BeanUtil.toBean(bo, CoursesMenu.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改课程目录
     */
    @Override
    public Boolean updateByBo(CoursesMenuBo bo) {
        CoursesMenu update = BeanUtil.toBean(bo, CoursesMenu.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(CoursesMenu entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除课程目录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

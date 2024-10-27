package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.domain.Schools;
import com.ruoyi.system.domain.bo.SchoolsBo;
import com.ruoyi.system.domain.vo.SchoolsVo;
import com.ruoyi.system.mapper.SchoolsMapper;
import com.ruoyi.system.service.ISchoolsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 学校Service业务层处理
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@RequiredArgsConstructor
@Service
public class SchoolsServiceImpl implements ISchoolsService {

    private final SchoolsMapper baseMapper;

    /**
     * 查询学校
     */
    @Override
    public SchoolsVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询学校列表
     */
    @Override
    public TableDataInfo<SchoolsVo> queryPageList(SchoolsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Schools> lqw = buildQueryWrapper(bo);
        Page<SchoolsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询学校列表
     */
    @Override
    public List<SchoolsVo> queryList(SchoolsBo bo) {
        LambdaQueryWrapper<Schools> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Schools> buildQueryWrapper(SchoolsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Schools> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getSchoolName()), Schools::getSchoolName, bo.getSchoolName());
        lqw.eq(StringUtils.isNotBlank(bo.getAddress()), Schools::getAddress, bo.getAddress());
        lqw.eq(bo.getCreateDate() != null, Schools::getCreateDate, bo.getCreateDate());
        lqw.eq(bo.getUpdateDate() != null, Schools::getUpdateDate, bo.getUpdateDate());
        return lqw;
    }

    /**
     * 新增学校
     */
    @Override
    public Boolean insertByBo(SchoolsBo bo) {
        Schools add = BeanUtil.toBean(bo, Schools.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改学校
     */
    @Override
    public Boolean updateByBo(SchoolsBo bo) {
        Schools update = BeanUtil.toBean(bo, Schools.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Schools entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除学校
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

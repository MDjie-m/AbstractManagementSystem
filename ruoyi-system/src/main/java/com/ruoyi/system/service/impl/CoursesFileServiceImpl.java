package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.domain.CoursesFile;
import com.ruoyi.system.domain.bo.CoursesFileBo;
import com.ruoyi.system.domain.vo.CoursesFileVo;
import com.ruoyi.system.mapper.CoursesFileMapper;
import com.ruoyi.system.service.ICoursesFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 课程相关文件Service业务层处理
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@RequiredArgsConstructor
@Service
public class CoursesFileServiceImpl implements ICoursesFileService {

    private final CoursesFileMapper baseMapper;

    /**
     * 查询课程相关文件
     */
    @Override
    public CoursesFileVo queryById(Long fileId){
        return baseMapper.selectVoById(fileId);
    }

    /**
     * 查询课程相关文件列表
     */
    @Override
    public TableDataInfo<CoursesFileVo> queryPageList(CoursesFileBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CoursesFile> lqw = buildQueryWrapper(bo);
        Page<CoursesFileVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询课程相关文件列表
     */
    @Override
    public List<CoursesFileVo> queryList(CoursesFileBo bo) {
        LambdaQueryWrapper<CoursesFile> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CoursesFile> buildQueryWrapper(CoursesFileBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CoursesFile> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getFileName()), CoursesFile::getFileName, bo.getFileName());
        lqw.eq(StringUtils.isNotBlank(bo.getFileUrl()), CoursesFile::getFileUrl, bo.getFileUrl());
        lqw.eq(StringUtils.isNotBlank(bo.getFileSize()), CoursesFile::getFileSize, bo.getFileSize());
        lqw.eq(StringUtils.isNotBlank(bo.getFileType()), CoursesFile::getFileType, bo.getFileType());
        lqw.eq(bo.getUseType() != null, CoursesFile::getUseType, bo.getUseType());
        lqw.like(StringUtils.isNotBlank(bo.getTypeName()), CoursesFile::getTypeName, bo.getTypeName());
        lqw.eq(bo.getSort() != null, CoursesFile::getSort, bo.getSort());
        lqw.eq(bo.getCourseId() != null, CoursesFile::getCourseId, bo.getCourseId());
        lqw.eq(bo.getMenuId() != null, CoursesFile::getMenuId, bo.getMenuId());
        lqw.eq(bo.getCreateDate() != null, CoursesFile::getCreateDate, bo.getCreateDate());
        lqw.eq(bo.getUpdateDate() != null, CoursesFile::getUpdateDate, bo.getUpdateDate());
        return lqw;
    }

    /**
     * 新增课程相关文件
     */
    @Override
    public Boolean insertByBo(CoursesFileBo bo) {
        CoursesFile add = BeanUtil.toBean(bo, CoursesFile.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setFileId(add.getFileId());
        }
        return flag;
    }

    /**
     * 修改课程相关文件
     */
    @Override
    public Boolean updateByBo(CoursesFileBo bo) {
        CoursesFile update = BeanUtil.toBean(bo, CoursesFile.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(CoursesFile entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除课程相关文件
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}

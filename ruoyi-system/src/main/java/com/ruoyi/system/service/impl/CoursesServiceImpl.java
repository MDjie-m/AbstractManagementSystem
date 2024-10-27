package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.system.domain.Courses;
import com.ruoyi.system.domain.CoursesFile;
import com.ruoyi.system.domain.CoursesMenu;
import com.ruoyi.system.domain.bo.CoursesBo;
import com.ruoyi.system.domain.bo.CoursesFileBo;
import com.ruoyi.system.domain.vo.CoursesVo;
import com.ruoyi.system.domain.vo.SysOssVo;
import com.ruoyi.system.mapper.CoursesFileMapper;
import com.ruoyi.system.mapper.CoursesMapper;
import com.ruoyi.system.mapper.CoursesMenuMapper;
import com.ruoyi.system.service.ICoursesService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.framework.config.ServerConfig;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 课程Service业务层处理
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@RequiredArgsConstructor
@Service
public class CoursesServiceImpl implements ICoursesService {

    private static final Logger log = LoggerFactory.getLogger(CoursesServiceImpl.class);

    private final CoursesMapper baseMapper;

    private final CoursesFileMapper coursesFileMapper;

    private final CoursesMenuMapper coursesMenuMapper;

    @Autowired
    private ServerConfig serverConfig;

    private static final String FILE_DELIMETER = ",";

    /**
     * 查询课程
     */
    @Override
    public CoursesVo queryById(Long id){
        CoursesVo vo= new CoursesVo();
        vo = baseMapper.selectVoById(id);

        //获取图片信息
        List<CoursesFile> systemFiles = new ArrayList<>();
        List<CoursesFile> effectFiles = new ArrayList<>();
        List<CoursesFile> packageFiles = new ArrayList<>();
        systemFiles = coursesFileMapper.selectList(new QueryWrapper<CoursesFile>()
            .eq("course_id", vo.getId())
            .eq("use_type", 0)
            .eq("del_flag", 0)
        );
        effectFiles = coursesFileMapper.selectList(new QueryWrapper<CoursesFile>()
            .eq("course_id", vo.getId())
            .eq("use_type", 1)
            .eq("del_flag", 0)
        );
        packageFiles = coursesFileMapper.selectList(new QueryWrapper<CoursesFile>()
            .eq("course_id", vo.getId())
            .eq("use_type", 2)
            .eq("del_flag", 0)
        );
        vo.setSystemFiles(systemFiles);
        vo.setEffectFiles(effectFiles);
        vo.setPackageFiles(packageFiles);
        //获取课程目录
        vo.setMenuList(coursesMenuMapper.selectList(new QueryWrapper<CoursesMenu>().eq("course_id", id).orderByAsc("sort")));

        return vo;


    }

    /**
     * 查询课程列表
     */
    @Override
    public TableDataInfo<CoursesVo> queryPageList(CoursesBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Courses> lqw = buildQueryWrapper(bo);
        Page<CoursesVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
/*        //获取需要展示的图片
        for(CoursesVo vo : result.getRecords()) {
            vo.setSystemFiles(coursesFileMapper.selectList(new QueryWrapper<CoursesFile>()
                .eq("course_id", vo.getId())
                .eq("use_type", 0)
                .eq("del_flag", 0)
            ));
            vo.setEffectFiles(coursesFileMapper.selectList(new QueryWrapper<CoursesFile>()
                .eq("course_id", vo.getId())
                .eq("use_type", 1)
                .eq("del_flag", 0)
            ));
            vo.setPackageFiles(coursesFileMapper.selectList(new QueryWrapper<CoursesFile>()
                .eq("course_id", vo.getId())
                .eq("use_type", 2)
                .eq("del_flag", 0)
            ));
        }*/
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
        lqw.eq(bo.getCreateTime() != null, Courses::getCreateTime, bo.getCreateTime());
        lqw.eq(bo.getCreateTime() != null, Courses::getCreateTime, bo.getCreateTime());
        return lqw;
    }

    /**
     * 新增课程
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertByBo(CoursesBo bo, List<MultipartFile> coverfiles, List<MultipartFile> systemFiles,
                              List<MultipartFile> effectFiles, List<MultipartFile> packageFiles) {
        Courses add = BeanUtil.toBean(bo, Courses.class);
        validEntityBeforeSave(add);
        boolean flag = false;
        try
        {
            //上传图片封面并保存
            if(coverfiles != null && coverfiles.size() > 0) {
                for (MultipartFile file: coverfiles) {
                    Map<String, String> map = uploadFile(file);
                    add.setCourseCover(map.get("url"));
                }
            }
            flag = baseMapper.insert(add) > 0;
            if (flag) {
                bo.setId(add.getId());
            }
            //获取不需要删除的图片
            if(bo.getSystemFiles().size() >0 || bo.getEffectFiles().size()>0 || bo.getPackageFiles().size()>0) {
                List<String> noDeletedImgList =  Stream.of(bo.getSystemFiles(),
                        bo.getEffectFiles(),
                        bo.getPackageFiles())
                    .flatMap(List::stream)
                    .map(CoursesFileBo::getFileName).collect(Collectors.toList());
                coursesFileMapper.delete(new QueryWrapper<CoursesFile>()
                    .eq("course_id",add.getId())
                    .notIn("file_name", noDeletedImgList));
            } else {
                coursesFileMapper.delete(new QueryWrapper<CoursesFile>().eq("course_id",add.getId()));
            }
            //保存新增图片
            for (MultipartFile file: systemFiles) {
                Map<String, String> map = uploadFile(file);
                storeCourseFile(map.get("newFileName"),map.get("url"),0,"课程体系",add.getId());
            }
            for (MultipartFile file: effectFiles) {
                Map<String, String> map = uploadFile(file);
                storeCourseFile(map.get("newFileName"),map.get("url"),1,"课程效果",add.getId());
            }
            for (MultipartFile file: packageFiles) {
                Map<String, String> map = uploadFile(file);
                storeCourseFile(map.get("newFileName"),map.get("url"),2,"材料包展示",add.getId());
            }
            //保存课程目录
            for(CoursesMenu menu : bo.getMenuList()) {
                menu.setCourseId(add.getId());
                coursesMenuMapper.insert(menu);
            }

        } catch (Exception e) {
            log.error("课程保存失败 => {}", e.getMessage());
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
        //保存课程目录
        coursesMenuMapper.updateBatchById(bo.getMenuList());
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


    private Map<String, String> uploadFile(MultipartFile file) throws Exception
    {
        Map<String, String> map = new HashMap<>(4);
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;

            map.put("url", url);
            map.put("fileName", fileName);
            map.put("newFileName", FileUtils.getName(fileName));
            map.put("originalFilename", file.getOriginalFilename());


        } catch (Exception e)
        {
            log.error("验证码短信发送异常 => {}", e.getMessage());
            throw e;
        }
        return map;
    }

    private void storeCourseFile(String fileName, String url, Integer type, String typeName, Long courseId){

        CoursesFile file = new CoursesFile();
        file.setFileName(fileName);
        file.setFileUrl(url);
        file.setUseType(type);
        file.setTypeName(typeName);
        file.setCourseId(courseId);
        coursesFileMapper.insert(file);
    }
}

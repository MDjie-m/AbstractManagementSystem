package com.renxin.psychology.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.renxin.common.constant.CacheConstants;
import com.renxin.common.core.redis.RedisCache;
import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.DateUtils;
import com.renxin.common.utils.SecurityUtils;
import com.renxin.course.domain.CourCourse;
import com.renxin.course.mapper.CourCourseMapper;
import com.renxin.course.service.ICourCourseService;
import com.renxin.psychology.domain.PsyCouponTemplate;
import com.renxin.psychology.service.IPsyCouponTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.renxin.psychology.mapper.PsyConsultantPackageMapper;
import com.renxin.psychology.domain.PsyConsultantPackage;
import com.renxin.psychology.service.IPsyConsultantPackageService;

import javax.annotation.Resource;

/**
 * 咨询师成长套餐Service业务层处理
 * 
 * @author renxin
 * @date 2024-06-26
 */
@Service
@Slf4j
public class PsyConsultantPackageServiceImpl extends ServiceImpl<PsyConsultantPackageMapper, PsyConsultantPackage> 
        implements IPsyConsultantPackageService 
{

    @Autowired
    private IPsyConsultantPackageService self; // 注入自身

    @Resource
    private RedisCache redisCache;
    
    @Autowired
    private PsyConsultantPackageMapper psyConsultantPackageMapper;
    
    @Autowired
    private IPsyCouponTemplateService couponTemplateService;

    /**
     * 查询咨询师成长套餐
     * 
     * @param packageId 咨询师成长套餐主键
     * @return 咨询师成长套餐
     */
    @Override
    @Cacheable(value = CacheConstants.PACKAGE_BY_ID_KEY, key = "#packageId", unless = "#result == null")
    public PsyConsultantPackage selectPsyConsultantPackageByPackageId(Long packageId)
    {
        log.info( java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + "--------------------------------连接MySQL查询套餐:" + packageId);
        PsyConsultantPackage pack = psyConsultantPackageMapper.selectPsyConsultantPackageByPackageId(packageId);
        return pack;
    }

    /**
     * 查询咨询师成长套餐列表
     * 
     * @param psyConsultantPackage 咨询师成长套餐
     * @return 咨询师成长套餐
     */
    @Override
    public List<PsyConsultantPackage> selectPsyConsultantPackageList(PsyConsultantPackage psyConsultantPackage)
    {
        return psyConsultantPackageMapper.selectPsyConsultantPackageList(psyConsultantPackage);
    }

    /**
     * 新增咨询师成长套餐
     * 
     * @param psyConsultantPackage 咨询师成长套餐
     * @return 结果
     */
    @Override
    public int insertPsyConsultantPackage(PsyConsultantPackage req)
    {
        Long userId = SecurityUtils.getLoginUser().getUserId();
        req.setCreateBy(userId+"");
        req.setUpdateBy(userId+"");
        req.setCreateTime(DateUtils.getNowDate());
        req.setUpdateTime(DateUtils.getNowDate());
        checkAndCleanTemId(req);//校验并清除关联券模版
        int i = psyConsultantPackageMapper.insertPsyConsultantPackage(req);

        refreshIdList();
        return i;
    }

    /**
     * 修改咨询师成长套餐
     * 
     * @param psyConsultantPackage 咨询师成长套餐
     * @return 结果
     */
    @Override
    @CacheEvict(cacheNames = CacheConstants.PACKAGE_BY_ID_KEY, key = "#req.packageId")
    public int updatePsyConsultantPackage(PsyConsultantPackage req)
    {
        req.setUpdateTime(DateUtils.getNowDate());
        checkAndCleanTemId(req);//校验并清除关联券模版
        int i = psyConsultantPackageMapper.updatePsyConsultantPackage(req);

        refreshIdList();
        return i;
    }

    /**
     * 批量删除咨询师成长套餐
     * 
     * @param packageIds 需要删除的咨询师成长套餐主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantPackageByPackageIds(Long[] packageIds)
    {
        int i = psyConsultantPackageMapper.deletePsyConsultantPackageByPackageIds(packageIds);
        
        //批量删除缓存
        redisCache.deleteMultiCache(CacheConstants.PACKAGE_BY_ID_KEY,Arrays.asList(packageIds));
        refreshIdList();
        return i;
    }

    /**
     * 删除咨询师成长套餐信息
     * 
     * @param packageId 咨询师成长套餐主键
     * @return 结果
     */
    @Override
    @CacheEvict(cacheNames = CacheConstants.PACKAGE_BY_ID_KEY, key = "#packageId")
    public int deletePsyConsultantPackageByPackageId(Long packageId)
    {
        int i = psyConsultantPackageMapper.deletePsyConsultantPackageByPackageId(packageId);

        refreshIdList();
        return i;
    }


    //校验套餐是否支持购买
    @Override
    public void checkConsultantPackageOrder(PsyConsultantPackage pack){
        Integer teamSupNum = pack.getTeamSupNum();//团督券数量
        Long teamSupCouponTemplateId = pack.getTeamSupCouponTemplateId();//团督券模版id
        Integer personSupNum = pack.getPersonSupNum();
        Long personSupCouponTemplateId = pack.getPersonSupCouponTemplateId();
        Integer personExpNum = pack.getPersonExpNum();
        Long personExpCouponTemplateId = pack.getPersonExpCouponTemplateId();
        Integer courseNum = pack.getCourseNum();
        Long courseCouponTemplateId = pack.getCourseCouponTemplateId();

        if (teamSupNum > 0 && ObjectUtils.isNotEmpty(teamSupCouponTemplateId)){
            PsyCouponTemplate template = couponTemplateService.selectPsyCouponTemplateById(teamSupCouponTemplateId);
            if (template.getTotalNum() - template.getUsedNum() < teamSupNum){
                throw new ServiceException("该套餐包含的团督券已达到发行上限, 无法继续发行.");
            }
        }

        if (personSupNum > 0 && ObjectUtils.isNotEmpty(personSupCouponTemplateId)){
            PsyCouponTemplate template = couponTemplateService.selectPsyCouponTemplateById(personSupCouponTemplateId);
            if (template.getTotalNum() - template.getUsedNum() < personSupNum){
                throw new ServiceException("该套餐包含的个督券已达到发行上限, 无法继续发行.");
            }
        }

        if (personExpNum > 0 && ObjectUtils.isNotEmpty(personExpCouponTemplateId)){
            PsyCouponTemplate template = couponTemplateService.selectPsyCouponTemplateById(personExpCouponTemplateId);
            if (template.getTotalNum() - template.getUsedNum() < personExpNum){
                throw new ServiceException("该套餐包含的体验券已达到发行上限, 无法继续发行.");
            }
        }

        if (courseNum > 0 && ObjectUtils.isNotEmpty(courseCouponTemplateId)){
            PsyCouponTemplate template = couponTemplateService.selectPsyCouponTemplateById(courseCouponTemplateId);
            if (template.getTotalNum() - template.getUsedNum() < courseNum){
                throw new ServiceException("该套餐包含的课程券已达到发行上限, 无法继续发行.");
            }
        }
    }


    //刷新缓存
    @Override
    public void refreshCacheByIdList(List<Long> idList){
        redisCache.deleteMultiCache(CacheConstants.PACKAGE_BY_ID_KEY,idList);
        for (Long id : idList) {
            self.selectPsyConsultantPackageByPackageId(id);
        }
        refreshIdList();
    }

    @Override
    public void refreshCacheById(Long id){
        refreshCacheByIdList(Arrays.asList(id));
    }

    @Override
    public void refreshCacheAll(){
        //获取完整id清单
        List<Long> courseIdList = psyConsultantPackageMapper.selectList(new LambdaQueryWrapper<PsyConsultantPackage>()
                .select(PsyConsultantPackage::getPackageId)
                .orderByDesc(PsyConsultantPackage::getCreateTime)).stream().map(p -> p.getPackageId()).collect(Collectors.toList());

        //刷新缓存
        refreshCacheByIdList(courseIdList);
        refreshIdList();
    }

    //刷新该对象 各种类型下的id清单
    @Override
    public void refreshIdList(){
        //完整对象清单
        List<PsyConsultantPackage> allCourseList = psyConsultantPackageMapper.selectList(new LambdaQueryWrapper<PsyConsultantPackage>()
                .select(PsyConsultantPackage::getPackageId)
                .eq(PsyConsultantPackage::getStatus,"0")//上架状态
                .orderByDesc(PsyConsultantPackage::getCreateTime));

        //删除原先的所有idList
        //redisCache.deleteStartWith(CacheConstants.PACKAGE_ID_LIST);
        
        //id清单放入缓存
        ////完整id清单
        List<Long> allIdList = allCourseList.stream().map(p -> p.getPackageId()).collect(Collectors.toList());
        redisCache.setCacheList(CacheConstants.PACKAGE_ID_LIST + "::" + "all",allIdList);
    }


    //校验并清除关联券模版
    private void checkAndCleanTemId(PsyConsultantPackage pack){
        if (pack.getTeamSupNum() == 0){ pack.setTeamSupCouponTemplateId(null);};
        if (pack.getPersonSupNum() == 0){ pack.setPersonSupCouponTemplateId(null);};
        if (pack.getPersonExpNum() == 0){ pack.setPersonExpCouponTemplateId(null);};
        if (pack.getCourseNum() == 0){ pack.setCourseCouponTemplateId(null);};
    }
}

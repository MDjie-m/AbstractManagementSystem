package com.renxin.consultant.controller;

import com.renxin.common.constant.CacheConstants;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.PageDomain;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.core.page.TableSupport;
import com.renxin.common.core.redis.RedisCache;
import com.renxin.common.domain.RelateInfo;
import com.renxin.common.utils.PageUtils;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.psychology.domain.PsyConsultantTeamSupervision;
import com.renxin.psychology.request.QueryListByTypeReq;
import com.renxin.psychology.service.IPsyConsultantTeamSupervisionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 团队督导(组织)Controller
 * 
 * @author renxin
 * @date 2024-06-26
 */
@RestController
@RequestMapping("/consultant/supervision-team")
@Api(value = "ConsultantTeamSupervisionController" ,tags = {"咨询师端-团队督导(组织)Api"})
public class ConsultantTeamSupervisionController extends BaseController
{
    @Autowired
    private IPsyConsultantTeamSupervisionService psyConsultantTeamSupervisionService;

    @Resource
    private ConsultantTokenService consultantTokenService;
    
    @Resource
    private RedisCache redisCache;
    
    /**
     * 查询团队督导(组织)列表
     */
    @ApiOperation(value = "查询团队督导(组织)列表")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PsyConsultantTeamSupervision req)
    {
        startPage();
        List<Long> idList = req.getIdList();
        if (ObjectUtils.isNotEmpty(idList)){
            List<PsyConsultantTeamSupervision> cacheList = redisCache.getMultiCacheMapValue(CacheConstants.TEAM_SUP_BY_ID_KEY, idList);
            //  if (idList.size() == cacheList.size()){
            return getDataTable(cacheList);
        }
        
        List<PsyConsultantTeamSupervision> list = psyConsultantTeamSupervisionService.selectPsyConsultantTeamSupervisionList(req);
        return getDataTable(list);
    }

    /**
     * 根据类型  查询团队督导(组织)列表
     */
    @ApiOperation(value = "查询团队督导(组织)列表")
    @PostMapping("/listByType")
    public TableDataInfo listByType(@RequestBody QueryListByTypeReq req)
    {
        String listType = req.getListType();
        List<Long> idList = redisCache.getCacheList(CacheConstants.TEAM_SUP_ID_LIST + "::" + listType);
        List<PsyConsultantTeamSupervision> cacheList = redisCache.getMultiCacheMapValue(CacheConstants.TEAM_SUP_BY_ID_KEY , PageUtils.paginate(idList));
        
        return getDataTable(cacheList, idList.size());
    }

    /**
     * 获取团队督导(组织)详细信息
     */
    @ApiOperation("获取团队督导(组织)详细信息")
    @PostMapping(value = "/detail")
    public AjaxResult getInfo(@RequestBody PsyConsultantTeamSupervision req)
    {
        PsyConsultantTeamSupervision teamSupervision = psyConsultantTeamSupervisionService.selectPsyConsultantTeamSupervisionById(req.getId());
        return AjaxResult.success(teamSupervision);
    }

    
    /**
     * 获取团队督导(组织)与本用户关联信息
     */
    @ApiOperation("获取团队督导(组织)详细信息")
    @PostMapping(value = "/getTeamRelateInfo")
    public AjaxResult getTeamRelateInfo(@RequestBody PsyConsultantTeamSupervision req, HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        req.setConsultantId(consultId+"");
        RelateInfo teamRelateInfo = psyConsultantTeamSupervisionService.getTeamRelateInfo(req);
        return AjaxResult.success(teamRelateInfo);
    }

}

package com.renxin.consultant.controller;

import com.renxin.common.annotation.Log;
import com.renxin.common.constant.CacheConstants;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.core.redis.RedisCache;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.PageUtils;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyConsultantPackage;
import com.renxin.psychology.domain.PsyConsultantTeamSupervision;
import com.renxin.psychology.request.QueryListByTypeReq;
import com.renxin.psychology.service.IPsyConsultantPackageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 咨询师成长套餐Controller
 * 
 * @author renxin
 * @date 2024-06-26
 */
@RestController
@RequestMapping("/consultant/package")
public class ConsultantPackageController extends BaseController
{
    @Autowired
    private IPsyConsultantPackageService psyConsultantPackageService;

    @Resource
    private RedisCache redisCache;

    /**
     * 查询咨询师成长套餐列表
     */
    //@PreAuthorize("@ss.hasPermi('system:package:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PsyConsultantPackage psyConsultantPackage)
    {
        startPage();
        List<PsyConsultantPackage> list = psyConsultantPackageService.selectPsyConsultantPackageList(psyConsultantPackage);
        return getDataTable(list);
    }

    /**
     * 根据类型  查询套餐列表
     */
    @ApiOperation(value = "根据类型  查询套餐列表")
    @PostMapping("/cache")
    public TableDataInfo listByType(@RequestBody QueryListByTypeReq req)
    {
        String listType = req.getListType();
        List<Long> idList = redisCache.getCacheList(CacheConstants.PACKAGE_ID_LIST + "::" + listType);
        List<PsyConsultantPackage> cacheList = redisCache.getMultiCacheMapValue(CacheConstants.PACKAGE_BY_ID_KEY , PageUtils.paginate(idList));

        return getDataTable(cacheList, idList.size());
    }
    
    /**
     * 获取咨询师成长套餐详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:package:query')")
    @PostMapping(value = "/queryById")
    public AjaxResult getInfo(@RequestBody PsyConsultantPackage psyConsultantPackage)
    {
        return AjaxResult.success(psyConsultantPackageService.selectPsyConsultantPackageByPackageId(psyConsultantPackage.getPackageId()));
    }

   
}

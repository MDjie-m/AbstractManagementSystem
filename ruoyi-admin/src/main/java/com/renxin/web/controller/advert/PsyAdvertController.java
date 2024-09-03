package com.renxin.web.controller.advert;


import com.renxin.advert.domain.PsyAdvert;
import com.renxin.advert.domain.PsyAdvertItem;
import com.renxin.advert.service.IPsyAdvertService;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.course.service.ICourCourseService;
import com.renxin.gauge.service.IPsyGaugeQuestionsService;
import com.renxin.gauge.service.IPsyGaugeService;
import com.renxin.psychology.domain.PsyConsultantTeamSupervision;
import com.renxin.psychology.service.IPsyConsultService;
import com.renxin.psychology.service.IPsyConsultantPackageService;
import com.renxin.psychology.service.IPsyConsultantTeamSupervisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 页面广告Controller
 * 
 * @author renxin
 * @date 2024-08-16
 */
@RestController
@RequestMapping("/system/advert")
public class PsyAdvertController extends BaseController
{
    @Autowired
    private IPsyAdvertService psyAdvertService;

    
    //刷新全部业务数据缓存
    @GetMapping(value = "/refreshAll")
    public AjaxResult refreshAllBusinessCache()
    {
        psyAdvertService.refreshAllBusinessCache();
        return AjaxResult.success();
    }
    
    /**
     * 查询各类型的对象清单
     */
    //@PreAuthorize("@ss.hasPermi('system:item:list')")
    @PostMapping("/queryObjectByIds")
    public AjaxResult queryObjectByIds(PsyAdvert req)
    {
        return psyAdvertService.queryObjectByIds(req);
    }
    
    
    /**
     * 查询页面广告列表
     */
    //@PreAuthorize("@ss.hasPermi('system:advert:list')")
    @PostMapping("/list")
    public TableDataInfo list(PsyAdvert psyAdvert)
    {
        startPage();
        List<PsyAdvert> list = psyAdvertService.selectPsyAdvertList(psyAdvert);
        return getDataTable(list);
    }

    /**
     * 导出页面广告列表
     */
    //@PreAuthorize("@ss.hasPermi('system:advert:export')")
    /*@PostMapping("/export")
    public void export(HttpServletResponse response, PsyAdvert psyAdvert)
    {
        List<PsyAdvert> list = psyAdvertService.selectPsyAdvertList(psyAdvert);
        ExcelUtil<PsyAdvert> util = new ExcelUtil<PsyAdvert>(PsyAdvert.class);
        util.exportExcel(response, list, "页面广告数据");
    }*/

    /**
     * 获取页面广告详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:advert:query')")
    @PostMapping(value = "/queryByAdvertNo")
    public AjaxResult queryByAdvertNo(@RequestBody PsyAdvert psyAdvert)
    {
        return AjaxResult.success(psyAdvertService.selectPsyAdvertByAdvertNo(psyAdvert.getAdvertNo()));
    }

    /**
     * 新增页面广告
     */
    //@PreAuthorize("@ss.hasPermi('system:advert:add')")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PsyAdvert psyAdvert)
    {
        return toAjax(psyAdvertService.insertPsyAdvert(psyAdvert));
    }

    /**
     * 修改页面广告
     */
    //@PreAuthorize("@ss.hasPermi('system:advert:edit')")
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody PsyAdvert psyAdvert)
    {
        return toAjax(psyAdvertService.updatePsyAdvert(psyAdvert));
    }

    /**
     * 删除页面广告
     */
    //@PreAuthorize("@ss.hasPermi('system:advert:remove')")
	@PostMapping("/delete")
    public AjaxResult remove(@RequestBody PsyAdvert psyAdvert)
    {
        return toAjax(psyAdvertService.deletePsyAdvertByAdvertNos(new String[] {psyAdvert.getAdvertNo()}));
    }
    
    
}

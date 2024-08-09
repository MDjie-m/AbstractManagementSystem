package com.renxin.consultant.controller;


import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyCouponTemplate;
import com.renxin.psychology.service.IPsyCouponTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 优惠券模版Controller
 * 
 * @author renxin
 * @date 2024-08-02
 */
@RestController
@RequestMapping("/consultant/couponTemplate")
public class ConsultantCouponTemplateController extends BaseController
{
    @Autowired
    private IPsyCouponTemplateService psyCouponTemplateService;

    /**
     * 查询优惠券模版列表
     */
    //@PreAuthorize("@ss.hasPermi('marketing:couponTemplate:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PsyCouponTemplate psyCouponTemplate)
    {
        startPage();
        List<PsyCouponTemplate> list = psyCouponTemplateService.selectPsyCouponTemplateList(psyCouponTemplate);
        return getDataTable(list);
    }
    


    /**
     * 导出优惠券模版列表
     */
    /*//@PreAuthorize("@ss.hasPermi('marketing:couponTemplate:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyCouponTemplate psyCouponTemplate)
    {
        List<PsyCouponTemplate> list = psyCouponTemplateService.selectPsyCouponTemplateList(psyCouponTemplate);
        ExcelUtil<PsyCouponTemplate> util = new ExcelUtil<PsyCouponTemplate>(PsyCouponTemplate.class);
        util.exportExcel(response, list, "优惠券模版数据");
    }*/

    /**
     * 获取优惠券模版详细信息
     */
    //@PreAuthorize("@ss.hasPermi('marketing:couponTemplate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyCouponTemplateService.selectPsyCouponTemplateById(id));
    }

    /**
     * 新增优惠券模版
     */
//    //@PreAuthorize("@ss.hasPermi('marketing:couponTemplate:add')")
//    @PostMapping
//    public AjaxResult add(@RequestBody PsyCouponTemplate psyCouponTemplate)
//    {
//        return toAjax(psyCouponTemplateService.insertPsyCouponTemplate(psyCouponTemplate));
//    }
//
//    /**
//     * 修改优惠券模版
//     */
//    //@PreAuthorize("@ss.hasPermi('marketing:couponTemplate:edit')")
//    @PutMapping
//    public AjaxResult edit(@RequestBody PsyCouponTemplate psyCouponTemplate)
//    {
//        return toAjax(psyCouponTemplateService.updatePsyCouponTemplate(psyCouponTemplate));
//    }
//
//    /**
//     * 删除优惠券模版
//     */
//    //@PreAuthorize("@ss.hasPermi('marketing:couponTemplate:remove')")
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(psyCouponTemplateService.deletePsyCouponTemplateByIds(ids));
//    }
//
//    /**
//     * 切换优惠券模版状态
//     */
//    //@PreAuthorize("@ss.hasPermi('marketing:couponTemplate:switchTemplateStatus')")
//    @GetMapping(value = "/switchTemplateStatus/{id}")
//    public AjaxResult switchTemplateStatus(@PathVariable("id") Long id)
//    {
//        psyCouponTemplateService.switchTemplateStatus(id);
//        return AjaxResult.success();
//    }
    
}

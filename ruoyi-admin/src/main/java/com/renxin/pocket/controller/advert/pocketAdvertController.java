package com.renxin.pocket.controller.advert;


import com.renxin.advert.domain.PsyAdvert;
import com.renxin.advert.service.IPsyAdvertService;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 页面广告Controller
 * 
 * @author renxin
 * @date 2024-08-16
 */
@RestController
@RequestMapping("/pocket/advert")
public class pocketAdvertController extends BaseController
{
    @Autowired
    private IPsyAdvertService psyAdvertService;

    /**
     * 查询各类型的对象清单
     */
    //@PreAuthorize("@ss.hasPermi('system:item:list')")
    @PostMapping("/queryObjectByIds")
    public AjaxResult queryObjectByIds(@RequestBody PsyAdvert req)
    {
        return psyAdvertService.queryObjectByIds(req);
    }
    
    /**
     * 查询页面广告列表
     */
    //@PreAuthorize("@ss.hasPermi('system:advert:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PsyAdvert psyAdvert)
    {
        startPage();
        psyAdvert.setServiceTo(1);//面向来访者
        List<PsyAdvert> list = psyAdvertService.selectPsyAdvertList(psyAdvert);
        return getDataTable(list);
    }

    /**
     * 获取页面广告详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:advert:query')")
    @PostMapping(value = "/queryByAdvertNo")
    public AjaxResult queryByAdvertNo(@RequestBody PsyAdvert psyAdvert)
    {
        return AjaxResult.success(psyAdvertService.selectPsyAdvertByAdvertNo(psyAdvert.getAdvertNo()));
    }

   
    
    
}

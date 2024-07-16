package com.renxin.pocket.controller.consult;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.psychology.service.IPsyConsultConfigService;
import com.renxin.psychology.service.IPsyConsultTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 心理咨询师Controller
 * 
 * @author renxin
 * @date 2022-08-26
 */
@RestController
@RequestMapping("/pocket/consult/config")
public class PocketConsultConfigController extends BaseController
{
    @Resource
    private IPsyConsultConfigService psyConsultConfigService;

    @Resource
    private IPsyConsultTypeService psyConsultTypeService;

    @PostMapping("/getDateNum/{num}")
    @RateLimiter
    public AjaxResult getConfigByType(@PathVariable("num") Integer num)
    {
        return AjaxResult.success(psyConsultConfigService.getDateNum(num));
    }

    /**
     * 根据类型查询字典
     */
    @PostMapping("/getConfigByType/{dictType}")
    @RateLimiter
    public AjaxResult getConfigByType(@PathVariable("dictType") String dictType)
    {
        return AjaxResult.success(psyConsultConfigService.getConfigByType(dictType));
    }

    @PostMapping("/getConfigByTypes/{dictTypes}")
    @RateLimiter
    public AjaxResult getConfigByTypes(@PathVariable(value = "dictTypes") String[] dictTypes)
    {
        return AjaxResult.success(psyConsultConfigService.getConfigByTypes(dictTypes));
    }

    @PostMapping("/getTrees")
    @RateLimiter
    public AjaxResult getTrees()
    {
        return AjaxResult.success(psyConsultTypeService.getTrees());
    }

    @PostMapping("/getNotices")
    @RateLimiter
    public AjaxResult getNotices()
    {
        return AjaxResult.success(psyConsultConfigService.getNotices());
    }


}

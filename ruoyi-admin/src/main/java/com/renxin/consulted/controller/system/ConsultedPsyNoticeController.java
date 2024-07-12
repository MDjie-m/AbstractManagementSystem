package com.renxin.consulted.controller.system;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.system.service.ISysNoticeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 公告 信息操作处理
 * 
 * @author renxin
 */
@RestController
@RequestMapping("/consulted/system/notice")
public class ConsultedPsyNoticeController extends BaseController
{
    @Resource
    private ISysNoticeService noticeService;

    /**
     * 根据通知公告编号获取详细信息
     */
    @GetMapping(value = "/{noticeId}")
    @RateLimiter
    public AjaxResult getInfo(@PathVariable Long noticeId)
    {
        return AjaxResult.success(noticeService.selectNoticeById(noticeId));
    }


}

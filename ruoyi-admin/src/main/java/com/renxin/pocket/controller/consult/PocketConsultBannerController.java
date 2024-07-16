package com.renxin.pocket.controller.consult;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.psychology.domain.PsyConsultBannerConfig;
import com.renxin.psychology.service.IPsyConsultBannerConfigService;
import com.renxin.psychology.vo.PsyConsultBannerConfigVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 心理咨询师Controller
 * 
 * @author renxin
 * @date 2022-08-26
 */
@RestController
@RequestMapping("/pocket/consult/banner")
public class PocketConsultBannerController extends BaseController
{
    @Resource
    private IPsyConsultBannerConfigService PsyConsultBannerConfigService;

    /**
     * 查询banner列表
     */
    @GetMapping("/list") 
    @RateLimiter
    public TableDataInfo list(PsyConsultBannerConfigVO req)
    {
        startPage();
        List<PsyConsultBannerConfig> list = PsyConsultBannerConfigService.getList(req);
        return getDataTable(list);
    }

}

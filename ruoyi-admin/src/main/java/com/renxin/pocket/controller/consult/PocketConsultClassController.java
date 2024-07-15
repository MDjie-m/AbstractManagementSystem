package com.renxin.pocket.controller.consult;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.psychology.domain.PsyConsultClass;
import com.renxin.psychology.service.IPsyConsultClassService;
import com.renxin.psychology.vo.PsyConsultClassVO;
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
@RequestMapping("/pocket/consult/class")
public class PocketConsultClassController extends BaseController
{
    @Resource
    private IPsyConsultClassService PsyConsultClassService;

    /**
     * 查询class列表
     */
    @GetMapping("/list")
    @RateLimiter
    public TableDataInfo list(PsyConsultClassVO req)
    {
        startPage();
        List<PsyConsultClass> list = PsyConsultClassService.getList(req);
        return getDataTable(list);
    }


}

package com.renxin.pocket.controller.marketing;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.user.request.IntegralSearchReq;
import com.renxin.user.service.IPsyUserIntegralRecordService;
import com.renxin.user.vo.PsyUserIntegralRecordVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/consulted/user/integral")
public class ConsultedPsyUserIntegralController extends BaseController {

    @Resource
    private IPsyUserIntegralRecordService integralRecordService;

    /**
     * 积分分页列表
     * @param req 搜索条件
     */
    @GetMapping("/list")
    @RateLimiter
    public TableDataInfo getList(IntegralSearchReq req) {
        startPage();
//        req.setUid(getUserId().intValue());
        List<PsyUserIntegralRecordVO> list = integralRecordService.getList(req);
        return getDataTable(list);
    }

    @GetMapping("/getUserIntegral")
    @RateLimiter
    public AjaxResult getUserIntegral(HttpServletRequest request) {
        return AjaxResult.success(integralRecordService.getUserIntegral(request));
    }


}

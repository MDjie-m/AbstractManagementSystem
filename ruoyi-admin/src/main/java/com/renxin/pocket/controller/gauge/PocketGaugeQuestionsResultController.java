package com.renxin.pocket.controller.gauge;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.constant.RespMessageConstants;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.GaugeCommitResultDTO;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.framework.web.service.PocketTokenService;
import com.renxin.gauge.domain.PsyGaugeQuestionsResult;
import com.renxin.gauge.domain.PsyGaugeQuestionsResultAll;
import com.renxin.gauge.service.IPsyGaugeQuestionsResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 心理测评问题结果Controller
 * 
 * @author renxin
 * @date 2022-09-10
 */
@RestController
@RequestMapping("/pocket/gauge/result")
@Api(value = "pocketGaugeQuestionsResultController" ,tags = {"测评结果提交"})
public class PocketGaugeQuestionsResultController extends BaseController
{

    @Autowired
    private IPsyGaugeQuestionsResultService psyGaugeQuestionsResultService;

    @Autowired
    private PocketTokenService pocketTokenService;

    /**
     * 新增心理测评问题结果
     */
    @PostMapping
    @ApiOperation("答题")
    @RateLimiter
    public AjaxResult add(@RequestBody @Validated PsyGaugeQuestionsResult psyGaugeQuestionsResult, HttpServletRequest request)
    {
        LoginDTO loginUser = pocketTokenService.getLoginUser(request);
        System.out.println(loginUser);
        Long userId = loginUser.getUserId();
        return toAjax(psyGaugeQuestionsResultService.answer(psyGaugeQuestionsResult ,userId));
    }


    /**
     * 新增心理测评问题结果
     */
    @PostMapping("/commit")
    @ApiOperation("普通计算提交测评并生成结果")
    @RateLimiter
    public AjaxResult commitResult(@RequestBody @Validated GaugeCommitResultDTO gaugeCommitResultDTO, HttpServletRequest request)
    {
        LoginDTO loginUser = pocketTokenService.getLoginUser(request);
        Long userId = loginUser.getUserId();
        String result = psyGaugeQuestionsResultService.commitResult(gaugeCommitResultDTO, userId);

        return AjaxResult.success(RespMessageConstants.OPERATION_SUCCESS, result);
    }

    /**
     * 测评结果保存
     */
    @PostMapping("/addList")
    @ApiOperation("测评结果保存")
    @RateLimiter
    public AjaxResult addList(@RequestBody @Validated List<PsyGaugeQuestionsResultAll> psyGaugeQuestionsResultAlls)
    {
        return toAjax(psyGaugeQuestionsResultService.addList(psyGaugeQuestionsResultAlls));
    }


    @PostMapping(value = "/getReport/{orderId}")
    @RateLimiter
    public AjaxResult getReport(@PathVariable("orderId") String orderId) {
        return AjaxResult.success(psyGaugeQuestionsResultService.getReport(orderId));
    }
}

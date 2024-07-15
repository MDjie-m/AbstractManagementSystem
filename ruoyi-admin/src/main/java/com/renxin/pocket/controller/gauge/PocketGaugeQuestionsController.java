package com.renxin.pocket.controller.gauge;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.framework.web.service.PocketTokenService;
import com.renxin.gauge.domain.PsyGaugeMultiSetting;
import com.renxin.gauge.domain.PsyGaugeQuestions;
import com.renxin.gauge.service.IPsyGaugeMultiSettingService;
import com.renxin.gauge.service.IPsyGaugeQuestionsService;
import com.renxin.gauge.vo.PsyQuestionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 心理测评问题Controller
 * 
 * @author renxin
 * @date 2022-09-06
 */
@RestController
@RequestMapping("/pocket/gauge/questions")
@Api(value = "pocketGaugeQuestionsController" ,tags = {"测评问题api"})
public class PocketGaugeQuestionsController extends BaseController
{
    @Autowired
    private IPsyGaugeQuestionsService psyGaugeQuestionsService;

    @Autowired
    private IPsyGaugeMultiSettingService psyGaugeMultiSettingService;

    @Autowired
    private PocketTokenService pocketTokenService;

    /**
     * 查询心理测评问题列表
     */
    @GetMapping("/list")
    @ApiOperation("查询测评问题列表")
    @RateLimiter
    public AjaxResult list(PsyGaugeQuestions psyGaugeQuestions ,HttpServletRequest request)
    {
        LoginDTO loginUser = pocketTokenService.getLoginUser(request);
        Integer userId = loginUser.getUserId();
        List<PsyQuestionVO> list = psyGaugeQuestionsService.appQueryQuesList(psyGaugeQuestions ,userId);
        return AjaxResult.success(list);
    }

    @GetMapping("/lats")
    @ApiOperation("查询测评问题列表") // Integer gaugeId
    @RateLimiter
    public AjaxResult lats(PsyGaugeMultiSetting psyGaugeMultiSetting)
    {
        return AjaxResult.success(psyGaugeMultiSettingService.selectPsyGaugeMultiSettingList(psyGaugeMultiSetting));
    }

    @GetMapping("/wrongs/{orderId}/{gaugeId}")
    @ApiOperation("查询测评问题列表")
    @RateLimiter
    public AjaxResult wrongs(@PathVariable("orderId") Integer orderId, @PathVariable("gaugeId") Integer gaugeId)
    {
        return AjaxResult.success(psyGaugeQuestionsService.wrongs(orderId, gaugeId));
    }

}

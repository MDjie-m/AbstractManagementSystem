package com.renxin.pocket.controller.gauge;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.constant.CacheConstants;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.core.redis.RedisCache;
import com.renxin.common.utils.PageUtils;
import com.renxin.framework.web.service.PocketTokenService;
import com.renxin.gauge.domain.PsyGaugeMultiSetting;
import com.renxin.gauge.domain.PsyGaugeQuestions;
import com.renxin.gauge.service.IPsyGaugeMultiSettingService;
import com.renxin.gauge.service.IPsyGaugeQuestionsService;
import com.renxin.gauge.vo.PsyQuestionVO;
import com.renxin.psychology.domain.PsyConsultantTeamSupervision;
import com.renxin.psychology.request.QueryListByTypeReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Resource
    private RedisCache redisCache;
    
    @Autowired
    private IPsyGaugeQuestionsService psyGaugeQuestionsService;

    @Autowired
    private IPsyGaugeMultiSettingService psyGaugeMultiSettingService;

    @Autowired
    private PocketTokenService pocketTokenService;

    /**
     * 查询心理测评问题列表
     */
    @PostMapping("/list")
    @ApiOperation("查询测评问题列表")
    @RateLimiter
    public AjaxResult list(@RequestBody PsyGaugeQuestions psyGaugeQuestions ,HttpServletRequest request)
    {
        LoginDTO loginUser = pocketTokenService.getLoginUser(request);
        Long userId = loginUser.getUserId();
        psyGaugeQuestions.setUserId(userId);
        List<PsyQuestionVO> list = psyGaugeQuestionsService.appQueryQuesList(psyGaugeQuestions);
        return AjaxResult.success(list);
    }

    /**
     * 根据类型  查询测评列表
     */
    @ApiOperation(value = "根据类型  查询测评列表")
    @PostMapping("/cache")
    public TableDataInfo listByType(@RequestBody QueryListByTypeReq req)
    {
        String listType = req.getListType();
        List<Long> idList = redisCache.getCacheList(CacheConstants.QUESTION_ID_LIST + "::" + listType);
        List<PsyConsultantTeamSupervision> cacheList = redisCache.getMultiCacheMapValue(CacheConstants.QUESTION_BY_ID_KEY , PageUtils.paginate(idList));

        return getDataTable(cacheList, idList.size());
    }

    @PostMapping("/lats")
    @ApiOperation("查询测评问题列表") // Long gaugeId
    @RateLimiter
    public AjaxResult lats(@RequestBody PsyGaugeMultiSetting psyGaugeMultiSetting)
    {
        return AjaxResult.success(psyGaugeMultiSettingService.selectPsyGaugeMultiSettingList(psyGaugeMultiSetting));
    }

    @PostMapping("/wrongs/{orderId}/{gaugeId}")
    @ApiOperation("查询测评问题列表")
    @RateLimiter
    public AjaxResult wrongs(@PathVariable("orderId") Long orderId, @PathVariable("gaugeId") Long gaugeId)
    {
        return AjaxResult.success(psyGaugeQuestionsService.wrongs(orderId, gaugeId));
    }

}

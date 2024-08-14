package com.renxin.consultant.controller;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.ConsultDTO;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.psychology.dto.OrderItemDTO;
import com.renxin.psychology.dto.RecentWorkDTO;
import com.renxin.psychology.request.PsyWorkReq;
import com.renxin.psychology.service.IPsyConsultService;
import com.renxin.psychology.service.IPsyConsultWorkService;
import com.renxin.psychology.vo.PsyConsultWorkVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/consultant/work")
@Api(value = "ConsultantWorkController" ,tags = {" 排班api"})
public class ConsultantWorkController extends BaseController {

    @Resource
    private ConsultantTokenService consultantTokenService;

    @Resource
    private IPsyConsultWorkService psyConsultWorkService;

    @Resource
    private IPsyConsultService psyConsultService;

    @ApiOperation(value = "咨询师排版计划表")
    @PostMapping("/liveHour")
    @RateLimiter
    public TableDataInfo list(@RequestBody PsyWorkReq req, HttpServletRequest request)
    {
        ConsultDTO loginUser = consultantTokenService.getLoginUser(request);
        if(loginUser != null){
            req.setIds(Collections.singletonList(loginUser.getConsultId()));
        }
        startPage();
        List<HashMap<String, String>>  list = psyConsultWorkService.getWorks(req);
        return getDataTable(list);
    }
    
    /**
     * 指定咨询师的可约时间(最近七天)
     */
    @PostMapping(value = "/getConsultWorksById/{id}")
    @RateLimiter
    public AjaxResult getConsultWorksById(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultService.getConsultWorksById(id));
    }

    @ApiOperation(value = "本咨询师今日任务")
    @PostMapping("/todoList")
    @RateLimiter
    public AjaxResult todoList(@RequestBody PsyWorkReq req, HttpServletRequest request)
    {
        ConsultDTO loginUser = consultantTokenService.getLoginUser(request);
        if(loginUser != null){
            //req.setIds(Collections.singletonList(loginUser.getConsultId()));
            req.setConsultId(loginUser.getConsultId());
        }
        List<OrderItemDTO> todoList = psyConsultWorkService.getTodoList(req);
        return AjaxResult.success(todoList);
    }
    

    @ApiOperation(value = "咨询师排班详情")
    @PostMapping("/detail")
    public AjaxResult detail(@RequestBody PsyWorkReq req,HttpServletRequest request)
    {
        ConsultDTO loginUser = consultantTokenService.getLoginUser(request);
        if(loginUser != null){
            req.setIds(Collections.singletonList(loginUser.getConsultId()));
        }
        HashMap<String, String> list = psyConsultWorkService.getWorkDetail(req);
        if(list != null && !list.isEmpty()){
            return AjaxResult.successData(list);
        }else{
            return AjaxResult.success();
        }
    }

    @ApiOperation(value = "咨询师排班新增")
    @PostMapping("/create")
    public AjaxResult create(@RequestBody PsyConsultWorkVO req,HttpServletRequest request)
    {
        ConsultDTO loginUser = consultantTokenService.getLoginUser(request);
        if(loginUser != null){
            req.setConsultId(loginUser.getConsultId());
        }
        return AjaxResult.success(psyConsultWorkService.add(req));
    }

    @ApiOperation(value = "本咨询师近期安排")
    @PostMapping("/recentWorkList")
    @RateLimiter
    public AjaxResult recentWorkList(@RequestBody PsyWorkReq req, HttpServletRequest request)
    {
        if (ObjectUtils.isEmpty(req.getConsultId())){
            Long consultId = consultantTokenService.getConsultId(request);
            req.setConsultId(consultId);
        }
        
        List<RecentWorkDTO> recentWorkList = psyConsultWorkService.recentWorkList(req);
        return AjaxResult.success(recentWorkList);
    }
    
}

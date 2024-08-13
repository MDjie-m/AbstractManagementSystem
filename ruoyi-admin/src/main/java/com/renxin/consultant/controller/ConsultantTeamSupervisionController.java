package com.renxin.consultant.controller;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.psychology.domain.PsyConsultantTeamSupervision;
import com.renxin.psychology.service.IPsyConsultantTeamSupervisionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 团队督导(组织)Controller
 * 
 * @author renxin
 * @date 2024-06-26
 */
@RestController
@RequestMapping("/consultant/supervision-team")
@Api(value = "ConsultantTeamSupervisionController" ,tags = {"咨询师端-团队督导(组织)Api"})
public class ConsultantTeamSupervisionController extends BaseController
{
    @Autowired
    private IPsyConsultantTeamSupervisionService psyConsultantTeamSupervisionService;

    @Resource
    private ConsultantTokenService consultantTokenService;
    
    /**
     * 查询团队督导(组织)列表
     */
    @ApiOperation(value = "查询团队督导(组织)列表")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PsyConsultantTeamSupervision req)
    {
        startPage();
        List<PsyConsultantTeamSupervision> list = psyConsultantTeamSupervisionService.selectPsyConsultantTeamSupervisionList(req);
        return getDataTable(list);
    }

    /**
     * 获取团队督导(组织)详细信息
     */
    @ApiOperation("获取团队督导(组织)详细信息")
    @PostMapping(value = "/detail")
    public AjaxResult getInfo(@RequestBody PsyConsultantTeamSupervision req)
    {
        return AjaxResult.success(psyConsultantTeamSupervisionService.selectPsyConsultantTeamSupervisionById(req.getId()));
    }
    
}

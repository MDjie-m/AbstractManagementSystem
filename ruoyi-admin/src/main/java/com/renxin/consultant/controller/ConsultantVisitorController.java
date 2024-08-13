package com.renxin.consultant.controller;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.psychology.domain.PsyConsult;
import com.renxin.psychology.domain.PsyConsultOrder;
import com.renxin.psychology.domain.PsyUser;
import com.renxin.psychology.domain.PsyUserLabel;
import com.renxin.psychology.dto.OrderListDTO;
import com.renxin.psychology.request.VisitorDetailReq;
import com.renxin.psychology.service.IPsyConsultOrderService;
import com.renxin.psychology.service.IPsyConsultService;
import com.renxin.psychology.service.IPsyUserLabelService;
import com.renxin.psychology.service.IPsyUserService;
import com.renxin.psychology.vo.PsyConsultOrderVO;
import com.renxin.psychology.vo.PsyConsultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户标签Controller
 * 
 * @author renxin
 * @date 2024-08-09
 */
@RestController
@RequestMapping("/consultant/visitor")
public class ConsultantVisitorController extends BaseController
{
    @Autowired
    private IPsyUserLabelService psyUserLabelService;

    @Autowired
    private IPsyUserService psyUserService;
    
    @Autowired
    private IPsyConsultOrderService consultOrderService;

    @Resource
    ConsultantTokenService consultantTokenService;
    
    @Resource
    IPsyConsultService consultService;

    /**
     * 来访者端顾客清单
     */
    @PostMapping("/queryUserList")
    public TableDataInfo queryUserList(@RequestBody PsyConsultOrderVO req, HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
            req.setConsultId(consultId);
        startPage();
        List<PsyConsultOrderVO> userList = consultOrderService.queryUserList(req);
        
        return getDataTable(userList);
    }

    /**
     * 咨询师端顾客清单
     */
    @PostMapping("/queryConsultantList")
    public TableDataInfo queryConsultantList(@RequestBody PsyConsultOrderVO req, HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        req.setConsultId(consultId);
        startPage();
        List<PsyConsult> psyConsultList = consultService.queryConsultantList(req);

        return getDataTable(psyConsultList);
    }

    /**
     * 来访者顾客详情
     */
    //@PreAuthorize("@ss.hasPermi('system:label:query')")
    @PostMapping(value = "/queryUserDetail")
    public AjaxResult queryUserDetail(@RequestBody VisitorDetailReq req, HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        req.setChargeConsultantId(consultId);
        PsyUser psyUser = psyUserService.queryUserDetail(req);
        return AjaxResult.success(psyUser);
    }
    
    /**
     * 咨询师顾客详情
     */
    //@PreAuthorize("@ss.hasPermi('system:label:query')")
    @PostMapping(value = "/queryConsultantDetail")
    public AjaxResult queryConsultantDetail(@RequestBody VisitorDetailReq req, HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        req.setChargeConsultantId(consultId);
        PsyConsultVO psyConsultVO = consultService.queryConsultantDetail(req);
        return AjaxResult.success(psyConsultVO);
    }
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 获取用户标签详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:label:query')")
    @PostMapping(value = "/queryById")
    public AjaxResult getInfo(@RequestBody PsyUserLabel psyUserLabel)
    {
        return AjaxResult.success(psyUserLabelService.selectPsyUserLabelById(psyUserLabel.getId()));
    }

    /**
     * 新增用户标签
     */
    //@PreAuthorize("@ss.hasPermi('system:label:add')")
    @Log(title = "用户标签", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PsyUserLabel psyUserLabel)
    {
        psyUserLabel.setUserFillLabel(null);
        psyUserLabel.setAdminFillLabel(null);
        return toAjax(psyUserLabelService.insertPsyUserLabel(psyUserLabel));
    }

    /**
     * 修改用户标签
     */
    //@PreAuthorize("@ss.hasPermi('system:label:edit')")
    @Log(title = "用户标签", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody PsyUserLabel psyUserLabel)
    {
        psyUserLabel.setUserFillLabel(null);
        psyUserLabel.setAdminFillLabel(null);
        return toAjax(psyUserLabelService.updatePsyUserLabel(psyUserLabel));
    }

    /**
     * 删除用户标签
     */
    //@PreAuthorize("@ss.hasPermi('system:label:remove')")
//    @Log(title = "用户标签", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(psyUserLabelService.deletePsyUserLabelByIds(ids));
//    }
}

package com.renxin.pocket.controller.user;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.framework.web.service.PocketTokenService;
import com.renxin.psychology.domain.PsyUserLabel;
import com.renxin.psychology.service.IPsyUserLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户标签Controller
 * 
 * @author renxin
 * @date 2024-08-09
 */
@RestController
@RequestMapping("/pocket/label")
public class PocketUserLabelController extends BaseController
{
    @Autowired
    private IPsyUserLabelService psyUserLabelService;

    @Autowired
    private PocketTokenService pocketTokenService;

    /**
     * 查询用户标签列表
     */
    //@PreAuthorize("@ss.hasPermi('system:label:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PsyUserLabel psyUserLabel, HttpServletRequest request)
    {
        Integer userId = pocketTokenService.getUserId(request);
        psyUserLabel.setUserId(Long.valueOf(userId));
        List<PsyUserLabel> list = psyUserLabelService.selectPsyUserLabelList(psyUserLabel);
        return getDataTable(list);
    }

    /**
     * 获取用户标签详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:label:query')")
//    @PostMapping(value = "/queryById")
//    public AjaxResult getInfo(@RequestBody PsyUserLabel psyUserLabel)
//    {
//        return AjaxResult.success(psyUserLabelService.selectPsyUserLabelById(psyUserLabel.getId()));
//    }

    /**
     * 新增用户标签
     */
    //@PreAuthorize("@ss.hasPermi('system:label:add')")
    @Log(title = "用户标签", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PsyUserLabel psyUserLabel, HttpServletRequest request)
    {
        Integer userId = pocketTokenService.getUserId(request);
        psyUserLabel.setUserId(Long.valueOf(userId));
        psyUserLabel.setConsultantFillLabel(null);
        psyUserLabel.setAdminFillLabel(null);
        return toAjax(psyUserLabelService.insertPsyUserLabel(psyUserLabel));
    }

    /**
     * 修改用户标签
     */
    //@PreAuthorize("@ss.hasPermi('system:label:edit')")
    @Log(title = "用户标签", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody PsyUserLabel psyUserLabel, HttpServletRequest request)
    {
        Integer userId = pocketTokenService.getUserId(request);
        psyUserLabel.setUserId(Long.valueOf(userId));
        psyUserLabel.setConsultantFillLabel(null);
        psyUserLabel.setAdminFillLabel(null);
        return toAjax(psyUserLabelService.updatePsyUserLabel(psyUserLabel));
    }
    
}

package com.renxin.consultant.controller;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyConsultantPackage;
import com.renxin.psychology.service.IPsyConsultantPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 咨询师成长套餐Controller
 * 
 * @author renxin
 * @date 2024-06-26
 */
@RestController
@RequestMapping("/consultant/package")
public class ConsultantPackageController extends BaseController
{
    @Autowired
    private IPsyConsultantPackageService psyConsultantPackageService;

    /**
     * 查询咨询师成长套餐列表
     */
    //@PreAuthorize("@ss.hasPermi('system:package:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PsyConsultantPackage psyConsultantPackage)
    {
        startPage();
        List<PsyConsultantPackage> list = psyConsultantPackageService.selectPsyConsultantPackageList(psyConsultantPackage);
        return getDataTable(list);
    }
    
    /**
     * 获取咨询师成长套餐详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:package:query')")
    @PostMapping(value = "/queryById")
    public AjaxResult getInfo(@RequestBody PsyConsultantPackage psyConsultantPackage)
    {
        return AjaxResult.success(psyConsultantPackageService.selectPsyConsultantPackageByPackageId(psyConsultantPackage.getPackageId()));
    }

   
}

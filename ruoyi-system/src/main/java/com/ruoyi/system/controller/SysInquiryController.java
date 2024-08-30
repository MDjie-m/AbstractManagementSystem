package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.dto.SysProDuctDTO;
import com.ruoyi.system.domain.vo.SysProductVO;
import com.ruoyi.system.service.ISysProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysInquiry;
import com.ruoyi.system.service.ISysInquiryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 
 * @author tyc
 * @date 2024-08-26
 */
@RestController
@RequestMapping("/system/inquiry")
public class SysInquiryController extends BaseController
{
    @Autowired
    private ISysInquiryService sysInquiryService;
    @Autowired
    private ISysProductService sysProductService;

    /**
     *这里查的是询价界面的询价和询价清单的列表，除了正常的筛选，还包括可报价不可报价，返回的数据包括询价次数。
     */
    @PreAuthorize("@ss.hasPermi('system:inquiry:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody SysProDuctDTO sysProDuctDTO)
    {
        //从上下文中获取角色id
        Long roleId = SecurityUtils.getLoginUser().getUser().getRoleId();
        //然后判断roleid是管理员还是供应商还是采购员，三个都不一样的，不能根据名称判断，因为名称可能被修改
        List<SysProductVO> list = null;
        sysProDuctDTO.setFlag(false);
        PageHelper.startPage(sysProDuctDTO.getPageNum()==null?1:sysProDuctDTO.getPageNum(),
                sysProDuctDTO.getPageSize()==null?1:sysProDuctDTO.getPageSize());
        if(roleId==1){
            //说明是管理员，则查所有产品信息。如果供应商id为null且buyerid为null说明查所有
            list = sysProductService.selectSysProductList(sysProDuctDTO);
        }else if (roleId==2){
            //说明是采购员，如果供应商id为null且buyerid有具体值说明查采购员自己管理的产品
            sysProDuctDTO.setBuyerId(SecurityUtils.getUserId());
            list = sysProductService.selectSysProductList(sysProDuctDTO);
        }
        //多了个询价次数，因此要再查一下询价次数
        assert list != null;
        for (SysProductVO sysProductVO : list) {
            sysProductVO.setInquiryTimes(sysInquiryService.selectInquiryTimes(sysProductVO.getProductId()));
        }
        return getDataTable(list);
    }

    /**
     * 批量询价，如果今天已经询价怎么弄？
     */
    @PreAuthorize("@ss.hasPermi('system:inquiry:update')")
    @PostMapping("/batchInquiry")
    public AjaxResult batchInquiry(@RequestBody List<SysProDuctDTO> sysProDuctDTOList)
    {
        return success(sysInquiryService.batchInquiry(sysProDuctDTOList));
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:inquiry:query')")
    @GetMapping(value = "/{inquiryId}")
    public AjaxResult getInfo(@PathVariable("inquiryId") String inquiryId)
    {
        return success(sysInquiryService.selectSysInquiryByInquiryId(inquiryId));
    }

    /**
     * 新增
     */
    @PreAuthorize("@ss.hasPermi('system:inquiry:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysInquiry sysInquiry)
    {
        return toAjax(sysInquiryService.insertSysInquiry(sysInquiry));
    }

    /**
     * 修改
     */
    @PreAuthorize("@ss.hasPermi('system:inquiry:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysInquiry sysInquiry)
    {
        return toAjax(sysInquiryService.updateSysInquiry(sysInquiry));
    }

    /**
     * 删除
     */
    @PreAuthorize("@ss.hasPermi('system:inquiry:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{inquiryIds}")
    public AjaxResult remove(@PathVariable String[] inquiryIds)
    {
        return toAjax(sysInquiryService.deleteSysInquiryByInquiryIds(inquiryIds));
    }

    /**
     * 切换询价清单状态
     */
    @PreAuthorize("@ss.hasPermi('system:inquiry:edit')")
    @PostMapping("/updateInquiryListStatus")
    public AjaxResult updateInquiryListStatus(@RequestBody SysProDuctDTO sysProDuctDTO)
    {
        return toAjax(sysProductService.updateInquiryListStatus(sysProDuctDTO));
    }

    /**
     * 批量拒绝报价
     */
    @PreAuthorize("@ss.hasPermi('system:inquiry:edit')")
    @PostMapping("/refuseQuotation")
    public AjaxResult refuseQuotation(@RequestBody List<Long> inquiryIds)
    {
        return toAjax(sysInquiryService.refuseQuotation(inquiryIds));
    }
}

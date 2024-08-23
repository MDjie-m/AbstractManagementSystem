package com.ruoyi.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.SysProductType;
import com.ruoyi.system.domain.dto.SysProDuctDTO;
import com.ruoyi.system.domain.vo.SysProductVO;
import com.ruoyi.system.service.ISysProductTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysProduct;
import com.ruoyi.system.service.ISysProductService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 产品Controller
 * 
 * @author xgg
 * @date 2024-07-23
 */
@RestController
@RequestMapping("/system/product")
public class SysProductController extends BaseController
{
    @Autowired
    private ISysProductService sysProductService;
    @Autowired
    private ISysProductTypeService sysProductTypeService;
    @Value("${staticFile.rootPath}")
    private String rootPath;
    /**
     * 查询产品列表
     */
    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody SysProDuctDTO sysProDuctDTO)
    {
        //从上下文中获取角色id
        Long roleId = SecurityUtils.getLoginUser().getUser().getRoleId();
        //然后判断roleid是管理员还是供应商还是采购员，三个都不一样的，不能根据名称判断，因为名称可能被修改
        List<SysProductVO> list = null;
        sysProDuctDTO.setFlag(false);
        if(roleId==1){
            //说明是管理员，则查所有产品信息。如果供应商id为null且buyerid为null说明查所有
            startPage();
            list = sysProductService.selectSysProductList(sysProDuctDTO);
        }else if (roleId==2){
            //说明是采购员，如果供应商id为null且buyerid有具体值说明查采购员自己管理的产品
            sysProDuctDTO.setBuyerId(SecurityUtils.getUserId());
            startPage();
            list = sysProductService.selectSysProductList(sysProDuctDTO);
        }else if (roleId==6){
            sysProDuctDTO.setFlag(true);
            //说明是供应商，根据供应商的id查他自己的产品信息,供应商Id为他自己的供应商id，采购员id为null说明是供应商查他自己的产品。
            sysProDuctDTO.setSupplierId(SecurityUtils.getLoginUser().getUser().getSupplierId());
            startPage();
            list = sysProductService.selectSysProductList(sysProDuctDTO);
        }
        return getDataTable(list);
    }

    /**
     * 下载模板地址返回
     * @return AjaxResult
     */
    @PostMapping("/getUrl")
    public  AjaxResult getUrl(){
        // todo 对应文件名称
        String url = rootPath+"/template/"+"productExport.xlsx";
        return success().put("data",url);
    }
    /**
     * 导入产品
     */
    @Log(title = "产品", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:product:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysProduct> util = new ExcelUtil<SysProduct>(SysProduct.class);
        List<SysProduct> userList = util.importExcel(file.getInputStream());
        String message = sysProductService.importProduct(userList, updateSupport);
        return success(message);
    }

    /**
     * 导出产品列表
     */
    @PreAuthorize("@ss.hasPermi('system:product:export')")
    @Log(title = "产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysProduct sysProduct)
    {
//        List<SysProduct> list = sysProductService.selectSysProductList(sysProduct);
//        ExcelUtil<SysProduct> util = new ExcelUtil<SysProduct>(SysProduct.class);
//        util.exportExcel(response, list, "产品数据");
    }

    /**
     * 获取产品详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:product:query')")
    @GetMapping("/getInfo")
    public AjaxResult getInfo(@RequestParam String productId)
    {
        //从上下文中获取角色id
        Long roleId = SecurityUtils.getLoginUser().getUser().getRoleId();
        //然后判断roleid是管理员还是供应商还是采购员，三个都不一样的，不能根据名称判断，因为名称可能被修改
        SysProDuctDTO sysProDuctDTO = new SysProDuctDTO();
        sysProDuctDTO.setProductId(productId);
        SysProductVO sysProductVO = null;
        //设置为false，代表要顺便查供应商名称
        sysProDuctDTO.setFlag(false);
        if(roleId==1){
            //说明是管理员，则查所有产品信息。如果供应商id为null且buyerid为null说明要查名字
            sysProductVO = sysProductService.selectSysProductByProductId(sysProDuctDTO);
        }else if (roleId==2){
            //说明是采购员，如果供应商id为null且userid有具体值说明查采购员自己管理的产品
            sysProDuctDTO.setBuyerId(SecurityUtils.getUserId());
            sysProductVO = sysProductService.selectSysProductByProductId(sysProDuctDTO);
        }else if (roleId==6){
            sysProDuctDTO.setFlag(true);
            //说明是供应商，根据供应商的id查他自己的产品信息,供应商Id为他自己的供应商id，采购员id为null说明是供应商查他自己的产品。
            sysProDuctDTO.setSupplierId(SecurityUtils.getLoginUser().getUser().getSupplierId());
            sysProductVO = sysProductService.selectSysProductByProductId(sysProDuctDTO);
        }
        return success().put("data",sysProductVO);
    }

    /**
     * 新增产品，产品分类要同时存进口和国产，进口没有的就不存。
     */
    @PreAuthorize("@ss.hasPermi('system:product:add')")
    @Log(title = "产品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysProDuctDTO sysProDuctDTO)
    {
        return toAjax(sysProductService.insertSysProduct(sysProDuctDTO));
    }

    /**
     * 修改产品
     */
    @PreAuthorize("@ss.hasPermi('system:product:edit')")
    @Log(title = "产品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody SysProduct sysProduct)
    {
        return toAjax(sysProductService.updateSysProduct(sysProduct));
    }

    /**
     * 删除产品,根据产品id列表进行逻辑删除
     */
    @PreAuthorize("@ss.hasPermi('system:product:remove')")
    @Log(title = "产品", businessType = BusinessType.DELETE)
	@PostMapping("/deleteByIds")
    public AjaxResult remove(@RequestBody List<String> productIds)
    {
        return toAjax(sysProductService.deleteSysProductByProductIds(productIds));
    }

    /**
     * 修改产品报价状态
     */
    @PreAuthorize("@ss.hasPermi('system:product:updateStatus')")
    @GetMapping("/updateStatus")
    public AjaxResult updateStatus(String productId,String status)
    {
        return toAjax(sysProductService.updateStatus(productId,status));
    }

    /**
     * 切换产品的报价清单状态
     */
    @PreAuthorize("@ss.hasPermi('system:product:updateStatus')")
    @GetMapping("/updateQuoteListStatus")
    public AjaxResult updateQuoteListStatus(@RequestParam String productId,@RequestParam String status)
    {
        // 跟上面切换是否可报价状态的逻辑一致
        return toAjax(sysProductService.updateQuoteListStatus(productId,status));
    }
}

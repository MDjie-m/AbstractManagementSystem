package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.dto.SysProDuctDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${staticFile.rootPath}")
    private String rootPath;
    /**
     * 查询产品列表
     */
    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysProduct sysProduct)
    {
        startPage();
        List<SysProduct> list = sysProductService.selectSysProductList(sysProduct);
        return getDataTable(list);
    }
    /**
     * 下载模板地址返回
     * @return
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
        List<SysProduct> list = sysProductService.selectSysProductList(sysProduct);
        ExcelUtil<SysProduct> util = new ExcelUtil<SysProduct>(SysProduct.class);
        util.exportExcel(response, list, "产品数据");
    }

    /**
     * 获取产品详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:product:query')")
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") String productId)
    {
        return success(sysProductService.selectSysProductByProductId(productId));
    }

    /**
     * 新增产品
     */
    @PreAuthorize("@ss.hasPermi('system:product:add')")
    @Log(title = "产品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysProDuctDTO sysProDuctDTO)
    {
        String[] names = sysProDuctDTO.getNames().split("/");
        String[] codes = sysProDuctDTO.getCodes().split("/");
        System.out.println(names);
        // 根据数组长度设置相应的分类名称
        for (int i = 0; i < names.length; i++) {
            switch (i) {
                case 0:
                    sysProDuctDTO.setPrimaryCategoryName(names[i]);
                    sysProDuctDTO.setPrimaryCategory(codes[i]);
                    break;
                case 1:
                    sysProDuctDTO.setSecondaryCategoryName(names[i]);
                    sysProDuctDTO.setSecondaryCategory(codes[i]);
                    break;
                case 2:
                    sysProDuctDTO.setTertiaryCategoryName(names[i]);
                    sysProDuctDTO.setTertiaryCategory(codes[i]);
                    break;
                case 3:
                    sysProDuctDTO.setQuaternaryCategoryName(names[i]);
                    sysProDuctDTO.setQuaternaryCategory(codes[i]);
                    break;
                case 4:
                    sysProDuctDTO.setFifthCategoryName(names[i]);
                    sysProDuctDTO.setFifthCategory(codes[i]);
                    break;
            }
        }

        return toAjax(sysProductService.insertSysProduct(sysProDuctDTO));
    }

    /**
     * 修改产品
     */
    @PreAuthorize("@ss.hasPermi('system:product:edit')")
    @Log(title = "产品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysProduct sysProduct)
    {
        return toAjax(sysProductService.updateSysProduct(sysProduct));
    }

    /**
     * 删除产品
     */
    @PreAuthorize("@ss.hasPermi('system:product:remove')")
    @Log(title = "产品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productIds}")
    public AjaxResult remove(@PathVariable String[] productIds)
    {
        return toAjax(sysProductService.deleteSysProductByProductIds(productIds));
    }
    /**
     * 修改产品报价状态
     */
    @PreAuthorize("@ss.hasPermi('system:product:updateStatus')")
    @PostMapping("/updateStatus")
    public AjaxResult updateStatus(String productId,String status)
    {
        return toAjax(sysProductService.updateStatus(productId,status));
    }
}

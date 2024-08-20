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
        //从上下文中获取角色id,暂时假定一个用户一个角色哈。
        Long roleId = SecurityUtils.getLoginUser().getUser().getRoleId();
        //然后判断roleid是管理员的id还是供应商的id还是采购员的id，三个都不一样的，不能根据名称判断，因为名称可能被修改
        List<SysProductVO> list = null;
        sysProDuctDTO.setFlag(false);
        if(roleId==1){
            //说明是管理员，则查所有产品信息。如果供应商id为null且userid为0说明查所有
            startPage();
            list = sysProductService.selectSysProductList(sysProDuctDTO);
        }else if (roleId==2){
            //说明是采购员，如果供应商id为null且userid有具体值说明查采购员自己管理的产品
            sysProDuctDTO.setBuyerId(SecurityUtils.getUserId());
            startPage();
            list = sysProductService.selectSysProductList(sysProDuctDTO);
        }else if (roleId==6){
            sysProDuctDTO.setFlag(true);
            //说明是供应商，根据供应商的id查他自己的产品信息,供应商Id为他自己的供应商id，采购员id为0说明是供应商查他自己的产品。
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
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") String productId)
    {
        return success(sysProductService.selectSysProductByProductId(productId));
    }

    /**
     * 新增产品，产品分类要同时存进口和国产，进口没有的就不存。
     */
    @PreAuthorize("@ss.hasPermi('system:product:add')")
    @Log(title = "产品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysProDuctDTO sysProDuctDTO)
    {
        String[] names = sysProDuctDTO.getNames().split("/");
        String[] codes = sysProDuctDTO.getCodes().split("/");
        String[] newCodes = new String[codes.length];
        // 根据选的国产还是进口，然后把names和codes赋值到对应的字段，国产有的分类海关没有的话，那就产品表相关字段为空。
        if(sysProDuctDTO.getDomesticImportedType()==0){
            //说明新增或修改的产品列表数据传来的是国产的分类，那就去掉cn前缀查找对应进口分类名称。
            for (int i = 0; i < codes.length; i++) {
                newCodes[i] = codes[i].substring(2);
            }
            //去掉了前缀cn就去查询每一级国产的分类名称对应的进口的名称
            List<SysProductType> sysProductTypeList = sysProductTypeService.selectType(newCodes);
            //如果传来的类别参数是国产开头，这里赋值
            for (int i = 0; i < names.length; i++) {
                switch (i) {
                    case 0:
                        sysProDuctDTO.setCnPrimaryCategoryName(names[i]);
                        sysProDuctDTO.setCnPrimaryCategory(codes[i]);
                        if(!sysProductTypeList.isEmpty()){
                            sysProDuctDTO.setPrimaryCategoryName(sysProductTypeList.get(i).getProductName());
                            sysProDuctDTO.setPrimaryCategory(sysProductTypeList.get(i).getProductCode());
                        }
                        break;
                    case 1:
                        sysProDuctDTO.setCnSecondaryCategoryName(names[i]);
                        sysProDuctDTO.setCnSecondaryCategory(codes[i]);
                        if(!sysProductTypeList.isEmpty()){
                            sysProDuctDTO.setSecondaryCategoryName(sysProductTypeList.get(i).getProductName());
                            sysProDuctDTO.setSecondaryCategory(sysProductTypeList.get(i).getProductCode());
                        }
                        break;
                    case 2:
                        sysProDuctDTO.setCnTertiaryCategoryName(names[i]);
                        sysProDuctDTO.setCnTertiaryCategory(codes[i]);
                        if(!sysProductTypeList.isEmpty()){
                            sysProDuctDTO.setTertiaryCategoryName(sysProductTypeList.get(i).getProductName());
                            sysProDuctDTO.setTertiaryCategory(sysProductTypeList.get(i).getProductCode());
                        }
                        break;
                    case 3:
                        sysProDuctDTO.setCnQuaternaryCategoryName(names[i]);
                        sysProDuctDTO.setCnQuaternaryCategory(codes[i]);
                        if(!sysProductTypeList.isEmpty()){
                            sysProDuctDTO.setQuaternaryCategoryName(sysProductTypeList.get(i).getProductName());
                            sysProDuctDTO.setQuaternaryCategory(sysProductTypeList.get(i).getProductCode());
                        }
                        break;
                    case 4:
                        sysProDuctDTO.setCnFifthCategoryName(names[i]);
                        sysProDuctDTO.setCnFifthCategory(codes[i]);
                        if(!sysProductTypeList.isEmpty()){
                            sysProDuctDTO.setFifthCategoryName(sysProductTypeList.get(i).getProductName());
                            sysProDuctDTO.setFifthCategory(sysProductTypeList.get(i).getProductCode());
                        }
                        break;
                }
            }
            //这里赋值标签id和name,标签的id和name在list的最后一个数据里才有,因为国产有的进口不一定有，
            // 所以要判断sysProductTypeList是否有数据
            if(!sysProductTypeList.isEmpty()){//如果有就直接赋值
                sysProDuctDTO.setTagId((sysProductTypeList.get(sysProductTypeList.size()-1)).getTagId());
                sysProDuctDTO.setTagName((sysProductTypeList.get(sysProductTypeList.size()-1)).getTagName());
            }else {
                //如果没有要再查一次最后一级code对应的标签
                SysProductType sysProductType = sysProductTypeService.selectTag(codes[codes.length-1]);
                //然后赋值给这条新增的数据
                sysProDuctDTO.setTagId(sysProductType.getTagId());
                sysProDuctDTO.setTagName(sysProductType.getTagName());
            }
        }else{
            //说明不是国产是进口，就加上cn前缀，去查找对应国产分类名称。
            for (int i = 0; i < codes.length; i++) {
                newCodes[i] = "cn" + codes[i];
            }
            //去掉了前缀cn就去查询每一级国产的分类名称对应的进口的名称
            List<SysProductType> sysProductTypeList = sysProductTypeService.selectType(newCodes);
            //如果传来的类别参数不是是国产开头，这里赋值
            for (int i = 0; i < names.length; i++) {
                switch (i) {
                    case 0:
                        sysProDuctDTO.setPrimaryCategoryName(names[i]);
                        sysProDuctDTO.setPrimaryCategory(codes[i]);
                        sysProDuctDTO.setCnPrimaryCategoryName(sysProductTypeList.get(i).getProductName());
                        sysProDuctDTO.setCnPrimaryCategory(sysProductTypeList.get(i).getProductCode());
                        break;
                    case 1:
                        sysProDuctDTO.setSecondaryCategoryName(names[i]);
                        sysProDuctDTO.setSecondaryCategory(codes[i]);
                        sysProDuctDTO.setCnSecondaryCategoryName(sysProductTypeList.get(i).getProductName());
                        sysProDuctDTO.setCnSecondaryCategory(sysProductTypeList.get(i).getProductCode());
                        break;
                    case 2:
                        sysProDuctDTO.setTertiaryCategoryName(names[i]);
                        sysProDuctDTO.setTertiaryCategory(codes[i]);
                        sysProDuctDTO.setCnTertiaryCategoryName(sysProductTypeList.get(i).getProductName());
                        sysProDuctDTO.setCnTertiaryCategory(sysProductTypeList.get(i).getProductCode());
                        break;
                    case 3:
                        sysProDuctDTO.setQuaternaryCategoryName(names[i]);
                        sysProDuctDTO.setQuaternaryCategory(codes[i]);
                        sysProDuctDTO.setCnQuaternaryCategoryName(sysProductTypeList.get(i).getProductName());
                        sysProDuctDTO.setCnQuaternaryCategory(sysProductTypeList.get(i).getProductCode());
                        break;
                    case 4:
                        sysProDuctDTO.setFifthCategoryName(names[i]);
                        sysProDuctDTO.setFifthCategory(codes[i]);
                        sysProDuctDTO.setCnFifthCategoryName(sysProductTypeList.get(i).getProductName());
                        sysProDuctDTO.setCnFifthCategory(sysProductTypeList.get(i).getProductCode());
                        break;
                }
            }
            // 这里赋值标签id和name,标签的id和name在list的最后一个数据里才有
            sysProDuctDTO.setTagId((sysProductTypeList.get(sysProductTypeList.size()-1)).getTagName());
            sysProDuctDTO.setTagId((sysProductTypeList.get(sysProductTypeList.size()-1)).getTagName());
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

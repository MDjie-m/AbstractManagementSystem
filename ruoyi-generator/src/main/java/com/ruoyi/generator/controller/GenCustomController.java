package com.ruoyi.generator.controller;


import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.generator.service.GenCustomService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @Description TODO
 * @Author nuoqin
 * @DATA 2024/8/15 16:29
 */
@RestController
@RequestMapping("/custom/data")
public class GenCustomController {

    private final GenCustomService genCustomService;

    public GenCustomController(GenCustomService genCustomService){
        this.genCustomService=genCustomService;
    }

    @GetMapping("/{tableId}/columns")
    public AjaxResult columns(@PathVariable Long tableId){

        return AjaxResult.success(genCustomService.columns(tableId));
    }



    @GetMapping("/{tableId}/list")
    @PreAuthorize("@ss.hasPermi('custom:#tableId:list')")
    public TableDataInfo list(@PathVariable Long tableId, @RequestParam Map<String,Object> data) throws Exception {

        return genCustomService.findList(tableId,data);
    }


    @GetMapping("/{tableId}/findById/{id}")
    @PreAuthorize("@ss.hasPermi('custom:#tableId:findById')")
    public AjaxResult findById(@PathVariable Long tableId,@PathVariable Long id) throws Exception {

        return AjaxResult.success(genCustomService.findById(tableId,id));
    }

    @PostMapping("/{tableId}/add")
    @PreAuthorize("@ss.hasPermi('custom:#tableId:add')")
    public AjaxResult add(@PathVariable Long tableId,@RequestBody Map<String,Object> data){
        genCustomService.add(tableId,data);
        return AjaxResult.success();
    }


    @PutMapping("/{tableId}/update")
    @PreAuthorize("@ss.hasPermi('custom:#tableId:update')")
    public AjaxResult update(@PathVariable Long tableId,@RequestBody Map<String,Object> data){
        genCustomService.update(tableId,data);
        return AjaxResult.success(genCustomService.columns(tableId));
    }


    @DeleteMapping("/{tableId}/delete/{ids}")
    @PreAuthorize("@ss.hasPermi('custom:#tableId:delete')")
    public AjaxResult delete(@PathVariable Long tableId,@PathVariable Long[] ids){
        genCustomService.delete(tableId, Arrays.asList(ids));
        return AjaxResult.success();
    }
}

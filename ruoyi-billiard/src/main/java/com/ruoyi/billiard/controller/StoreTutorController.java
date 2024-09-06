package com.ruoyi.billiard.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.ResultVo;
import com.ruoyi.common.core.page.PageResVo;
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
import com.ruoyi.billiard.domain.StoreTutor;
import com.ruoyi.billiard.service.IStoreTutorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 门店助教Controller
 * 
 * @author ruoyi
 * @date 2024-09-06
 */
@RestController
@RequestMapping("/billiard/tutor")
public class StoreTutorController extends BaseController
{
    @Autowired
    private IStoreTutorService storeTutorService;

    /**
     * 查询门店助教列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:tutor:list')")
    @GetMapping("/list")
    public PageResVo<StoreTutor> list(StoreTutor storeTutor)
    {
        startPage();
        List<StoreTutor> list = storeTutorService.selectStoreTutorList(storeTutor);
        return PageResVo.success(list);
    }

    /**
     * 导出门店助教列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:tutor:export')")
    @Log(title = "门店助教", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StoreTutor storeTutor)
    {
        List<StoreTutor> list = storeTutorService.selectStoreTutorList(storeTutor);
        ExcelUtil<StoreTutor> util = new ExcelUtil<StoreTutor>(StoreTutor.class);
        util.exportExcel(response, list, "门店助教数据");
    }

    /**
     * 获取门店助教详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:tutor:query')")
    @GetMapping(value = "/{storeTutorId}")
    public ResultVo<StoreTutor> getInfo(@PathVariable("storeTutorId") Long storeTutorId)
    {
        return ResultVo.success(storeTutorService.selectStoreTutorByStoreTutorId(storeTutorId));
    }

    /**
     * 新增门店助教
     */
    @PreAuthorize("@ss.hasPermi('billiard:tutor:add')")
    @Log(title = "门店助教", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody StoreTutor storeTutor)
    {
        return ResultVo.success(storeTutorService.insertStoreTutor(storeTutor));
    }

    /**
     * 修改门店助教
     */
    @PreAuthorize("@ss.hasPermi('billiard:tutor:edit')")
    @Log(title = "门店助教", businessType = BusinessType.UPDATE)
    @PutMapping
    public  ResultVo<Integer> edit(@RequestBody StoreTutor storeTutor)
    {
        return ResultVo.success(storeTutorService.updateStoreTutor(storeTutor));
    }

    /**
     * 删除门店助教
     */
    @PreAuthorize("@ss.hasPermi('billiard:tutor:remove')")
    @Log(title = "门店助教", businessType = BusinessType.DELETE)
	@DeleteMapping("/{storeTutorIds}")
    public  ResultVo<Integer> remove(@PathVariable Long[] storeTutorIds)
    {
        return  ResultVo.success(storeTutorService.deleteStoreTutorByStoreTutorIds(storeTutorIds));
    }
}

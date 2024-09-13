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
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.billiard.domain.TutorPrice;
import com.ruoyi.billiard.service.ITutorPriceService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 教练价格Controller
 *
 * @author zhoukeu
 * @date 2024-09-14
 */
@RestController
@RequestMapping("/billiard/tutorPrice")
public class TutorPriceController extends BaseController
{
    @Autowired
    private ITutorPriceService tutorPriceService;

    /**
     * 查询教练价格列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:tutorPrice:list')")
    @GetMapping("/list")
    public PageResVo<TutorPrice> list(TutorPrice tutorPrice)
    {
        startPage();
        List<TutorPrice> list = tutorPriceService.selectTutorPriceList(tutorPrice);
        return PageResVo.success(list);
    }

    /**
     * 导出教练价格列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:tutorPrice:export')")
    @Log(title = "教练价格", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TutorPrice tutorPrice)
    {
        List<TutorPrice> list = tutorPriceService.selectTutorPriceList(tutorPrice);
        ExcelUtil<TutorPrice> util = new ExcelUtil<TutorPrice>(TutorPrice.class);
        util.exportExcel(response, list, "教练价格数据");
    }

    /**
     * 获取教练价格详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:tutorPrice:query')")
    @GetMapping(value = "/{tutorPriceId}")
    public ResultVo<TutorPrice> getInfo(@PathVariable("tutorPriceId") Long tutorPriceId)
    {
        return ResultVo.success(tutorPriceService.selectTutorPriceByTutorPriceId(tutorPriceId));
    }

    /**
     * 新增教练价格
     */
    @PreAuthorize("@ss.hasPermi('billiard:tutorPrice:add')")
    @Log(title = "教练价格", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody TutorPrice tutorPrice)
    {
        return ResultVo.success(tutorPriceService.insertTutorPrice(tutorPrice));
    }

    /**
     * 修改教练价格
     */
    @PreAuthorize("@ss.hasPermi('billiard:tutorPrice:edit')")
    @Log(title = "教练价格", businessType = BusinessType.UPDATE)
    @PutMapping
    public  ResultVo<Integer> edit(@RequestBody TutorPrice tutorPrice)
    {
        return ResultVo.success(tutorPriceService.updateTutorPrice(tutorPrice));
    }

    /**
     * 删除教练价格
     */
    @PreAuthorize("@ss.hasPermi('billiard:tutorPrice:remove')")
    @Log(title = "教练价格", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tutorPriceIds}")
    public  ResultVo<Integer> remove(@PathVariable Long[] tutorPriceIds)
    {
        return  ResultVo.success(tutorPriceService.deleteTutorPriceByTutorPriceIds(tutorPriceIds));
    }
}

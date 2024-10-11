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
import com.ruoyi.billiard.domain.StoreSwapRecord;
import com.ruoyi.billiard.service.IStoreSwapRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 交班记录Controller
 *
 * @author ruoyi
 * @date 2024-10-10
 */
@RestController
@RequestMapping("/billiard/store/swap-record")
public class StoreSwapRecordController extends BaseController {
    @Autowired
    private IStoreSwapRecordService storeSwapRecordService;

    /**
     * 查询交班记录列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:swap-record:list')")
    @GetMapping("/list")
    public PageResVo<StoreSwapRecord> list(StoreSwapRecord storeSwapRecord) {
        startPage();
        List<StoreSwapRecord> list = storeSwapRecordService.selectStoreSwapRecordList(storeSwapRecord);
        return PageResVo.success(list);
    }

    /**
     * 导出交班记录列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:swap-record:export')")
    @Log(title = "交班记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StoreSwapRecord storeSwapRecord) {
        List<StoreSwapRecord> list = storeSwapRecordService.selectStoreSwapRecordList(storeSwapRecord);
        ExcelUtil<StoreSwapRecord> util = new ExcelUtil<StoreSwapRecord>(StoreSwapRecord.class);
        util.exportExcel(response, list, "交班记录数据");
    }

    /**
     * 获取交班记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:swap-record:query')")
    @GetMapping(value = "/{swapRecordId}")
    public ResultVo<StoreSwapRecord> getInfo(@PathVariable("swapRecordId") Long swapRecordId) {
        return ResultVo.success(storeSwapRecordService.selectStoreSwapRecordBySwapRecordId(swapRecordId));
    }

    /**
     * 新增交班记录
     */
    @PreAuthorize("@ss.hasPermi('billiard:swap-record:add')")
    @Log(title = "交班记录", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultVo<Integer> add(@RequestBody StoreSwapRecord storeSwapRecord) {
        storeSwapRecord.setStoreId(getStoreIdWithThrow());
        return ResultVo.success(storeSwapRecordService.insertStoreSwapRecord(storeSwapRecord));
    }

    /**
     * 修改交班记录
     */
    @PreAuthorize("@ss.hasPermi('billiard:swap-record:edit')")
    @Log(title = "交班记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResultVo<Integer> edit(@RequestBody StoreSwapRecord storeSwapRecord) {
        return ResultVo.success(storeSwapRecordService.updateStoreSwapRecord(storeSwapRecord));
    }

    /**
     * 删除交班记录
     */
    @PreAuthorize("@ss.hasPermi('billiard:swap-record:remove')")
    @Log(title = "交班记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{swapRecordIds}")
    public ResultVo<Integer> remove(@PathVariable Long[] swapRecordIds) {
        return ResultVo.success(storeSwapRecordService.deleteStoreSwapRecordBySwapRecordIds(swapRecordIds));
    }

}

package com.ruoyi.billiard.controller.wxapp;

import com.ruoyi.billiard.domain.dto.HomeReportDto;
import com.ruoyi.billiard.domain.vo.HomeReportVo;
import com.ruoyi.billiard.service.IOrderService;
import com.ruoyi.common.core.domain.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author: zhoukeu
 * @dateCreated: Created in 2024-09-20:42
 * @className: MiniAppWorkbenchController 小程序工作台接口
 */
@RestController
@RequestMapping("api/mini-app/workbench")
public class MiniAppWorkbenchController {

    @Autowired
    private IOrderService orderService;


    /**
     * 获取报表流水数据
     *
     * @param
     * @return
     */
    @PreAuthorize("@ss.hasPermi('miniapp:index:query')")
    @PostMapping("/homeReport")
    public ResultVo<HomeReportVo> getReportData(@RequestBody HomeReportDto dto) {
        return ResultVo.success(orderService.selectOrderData2Report(dto));
    }
}

package com.ruoyi.billiard.controller.wxapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhoukeu
 * @dateCreated: Created in 2024-09-20:42
 * @className: MiniAppWorkbenchController 小程序工作台接口
 */
@RestController
@RequestMapping("api/mini-app/workbench")
public class MiniAppWorkbenchController {

    /**
     * 获取报表流水数据
     *
     * @param storeId
     * @return
     */
    @GetMapping("/reportData/{storeId}")
    public Object getReportData(@PathVariable("storeId") Integer storeId) {
        return null;
    }
}

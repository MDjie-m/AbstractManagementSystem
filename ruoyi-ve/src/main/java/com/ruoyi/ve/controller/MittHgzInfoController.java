package com.ruoyi.ve.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.ve.domain.MittHgzInfo;
import com.ruoyi.ve.service.IMittHgzInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门信息
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/ve/mitthgzinfo")
public class MittHgzInfoController extends BaseController
{
    @Autowired
    private IMittHgzInfoService mittHgzInfoService;

    /**
     * 获取部门列表
     */
    @PreAuthorize("@ss.hasPermi('ve:mitthgzinfo:list')")
    @GetMapping("/list")
    public AjaxResult list(MittHgzInfo mittHgzInfo)
    {
        List<MittHgzInfo> depts = mittHgzInfoService.selectMittHgzInfoList(mittHgzInfo);
        return success(depts);
    }

}

package com.tianyi.web.controller.sim;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
import com.tianyi.common.annotation.Log;
import com.tianyi.common.core.controller.BaseController;
import com.tianyi.common.core.domain.AjaxResult;
import com.tianyi.common.enums.BusinessType;
import com.tianyi.sim.domain.PackExpList;
import com.tianyi.sim.service.IPackExpListService;
import com.tianyi.common.utils.poi.ExcelUtil;
import com.tianyi.common.core.page.TableDataInfo;

/**
 * 套餐到期预警Controller
 * 
 * @author tianyi
 * @date 2024-11-06
 */
@Api(tags = "SIM卡管理")
@RestController
@RequestMapping("/sim/pack_exp")
public class PackExpListController extends BaseController
{
    @Autowired
    private IPackExpListService packExpListService;

    /**
     * 查询套餐到期预警列表
     */
    @ApiOperation("查询套餐到期预警列表")
    @PreAuthorize("@ss.hasPermi('sim:pack_exp:list')")
    @GetMapping("/list")
    public TableDataInfo list(SimEntity simEntity)
    {
        startPage();
        PackExpList packExpList = new PackExpList();
        BeanUtils.copyProperties(simEntity, packExpList);
        List<PackExpList> list = packExpListService.selectPackExpListList(packExpList);
        return getDataTable(list);
    }
}

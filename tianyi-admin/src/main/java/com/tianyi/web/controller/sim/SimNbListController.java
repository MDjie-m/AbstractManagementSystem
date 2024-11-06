package com.tianyi.web.controller.sim;

import java.util.List;
import io.swagger.annotations.Api;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.ApiModelProperty;
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
import com.tianyi.sim.domain.SimNbList;
import com.tianyi.sim.service.ISimNbListService;
import com.tianyi.common.utils.poi.ExcelUtil;
import com.tianyi.common.core.page.TableDataInfo;

/**
 * NB异常清单Controller
 * 
 * @author tianyi
 * @date 2024-11-06
 */
@Api(tags = "SIM卡管理")
@RestController
@RequestMapping("/sim/nb")
public class SimNbListController extends BaseController
{
    @Autowired
    private ISimNbListService simNbListService;

    /**
     * 查询NB异常清单列表
     */
    @ApiOperation("NB异常清单查询")
    @PreAuthorize("@ss.hasPermi('sim:nb:list')")
    @GetMapping("/list")
    public TableDataInfo list(SimEntity simEntity)
    {
        startPage();
        SimNbList simNbList = new SimNbList();
        BeanUtils.copyProperties(simEntity, simNbList);
        List<SimNbList> list = simNbListService.selectSimNbListList(simNbList);
        return getDataTable(list);
    }

    /**
     * 导出NB异常清单列表
     */
    @PreAuthorize("@ss.hasPermi('sim:nb:export')")
    @Log(title = "NB异常清单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SimNbList simNbList)
    {
        List<SimNbList> list = simNbListService.selectSimNbListList(simNbList);
        ExcelUtil<SimNbList> util = new ExcelUtil<SimNbList>(SimNbList.class);
        util.exportExcel(response, list, "NB异常清单数据");
    }

    /**
     * 获取NB异常清单详细信息
     */
    @PreAuthorize("@ss.hasPermi('sim:nb:query')")
    @GetMapping(value = "/{provId}")
    public AjaxResult getInfo(@PathVariable("provId") Long provId)
    {
        return success(simNbListService.selectSimNbListByProvId(provId));
    }

}

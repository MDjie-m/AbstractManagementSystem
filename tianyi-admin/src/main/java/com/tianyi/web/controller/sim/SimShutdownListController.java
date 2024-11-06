package com.tianyi.web.controller.sim;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.tianyi.common.annotation.Anonymous;
import com.tianyi.common.annotation.Excel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
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
import com.tianyi.sim.domain.SimShutdownList;
import com.tianyi.sim.service.ISimShutdownListService;
import com.tianyi.common.utils.poi.ExcelUtil;
import com.tianyi.common.core.page.TableDataInfo;

/**
 * 停机清单Controller
 *
 * @author tianyi
 * @date 2024-11-05
 */
@Api(tags = "SIM卡管理")
@RestController
@RequestMapping("/sim/list")
public class SimShutdownListController extends BaseController {
    @Autowired
    private ISimShutdownListService simShutdownListService;

    /**
     * 查询停机清单列表
     */
    @ApiOperation("停机清单查询")
    @PreAuthorize("@ss.hasPermi('sim:list:list')")
    @GetMapping("/list")
    public TableDataInfo list(SimEntity simEntity) {
        startPage();
        SimShutdownList simShutdownList = new SimShutdownList();
        BeanUtils.copyProperties(simEntity, simShutdownList);
        List<SimShutdownList> list = simShutdownListService.selectSimShutdownListList(simShutdownList);
        return getDataTable(list);
    }

}

class SimEntity {

    @ApiModelProperty(value = "地市名称")
    private String areaName;

    @ApiModelProperty(value = "客户名称")
    private String custName;

    @ApiModelProperty(value = "用户号码")
    private Long accNbr;

    @ApiModelProperty(value = "停机原因")
    private String netStyle;


    @ApiModelProperty(value = "数据日期")
    private String yyyymmdd;

    public SimEntity() {

    }

    public SimEntity(String areaName, String custName, Long accNbr, String netStyle, String yyyymmdd) {
        this.areaName = areaName;
        this.custName = custName;
        this.accNbr = accNbr;
        this.netStyle = netStyle;
        this.yyyymmdd = yyyymmdd;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Long getAccNbr() {
        return accNbr;
    }

    public void setAccNbr(Long accNbr) {
        this.accNbr = accNbr;
    }

    public String getNetStyle() {
        return netStyle;
    }

    public void setNetStyle(String netStyle) {
        this.netStyle = netStyle;
    }

    public String getYyyymmdd() {
        return yyyymmdd;
    }

    public void setYyyymmdd(String yyyymmdd) {
        this.yyyymmdd = yyyymmdd;
    }
}

package com.tianyi.web.controller.sim;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.tianyi.common.annotation.Excel;
import io.swagger.annotations.Api;
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
import com.tianyi.sim.domain.NonuseWarningList;
import com.tianyi.sim.service.INonuseWarningListService;
import com.tianyi.common.utils.poi.ExcelUtil;
import com.tianyi.common.core.page.TableDataInfo;

/**
 * 套餐长期不使用预警Controller
 * 
 * @author tianyi
 * @date 2024-11-06
 */
@Api(tags = "SIM卡管理")
@RestController
@RequestMapping("/sim/nonuse")
public class NonuseWarningListController extends BaseController
{
    @Autowired
    private INonuseWarningListService nonuseWarningListService;

    /**
     * 查询套餐长期不使用预警列表
     */
    @ApiOperation("套餐长期不使用预警")
    @PreAuthorize("@ss.hasPermi('sim:nonuse:list')")
    @GetMapping("/list")
    public TableDataInfo list(SimNonuseEntity simNonuseEntity)
    {
        startPage();
        NonuseWarningList nonuseWarningList=new NonuseWarningList();
        BeanUtils.copyProperties(simNonuseEntity, nonuseWarningList);
        List<NonuseWarningList> list = nonuseWarningListService.selectNonuseWarningListList(nonuseWarningList);
        return getDataTable(list);
    }


}

class SimNonuseEntity {

    @ApiModelProperty(value = "地市名称")
    private String areaName;

    @ApiModelProperty(value = "客户名称")
    private String custName;

    @ApiModelProperty(value = "用户号码")
    private Long accNbr;

    @ApiModelProperty(value = "网络制式")
    private String netStyle;


    @ApiModelProperty(value = "不使用时长")
    private String noUseDur;

    @ApiModelProperty(value = "数据日期")
    private String yyyymmdd;

    public SimNonuseEntity() {

    }

    public SimNonuseEntity(String areaName, String custName, Long accNbr, String netStyle, String noUseDur,String yyyymmdd) {
        this.areaName = areaName;
        this.custName = custName;
        this.accNbr = accNbr;
        this.netStyle = netStyle;
        this.noUseDur=noUseDur;
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

    public String getNoUseDur() {
        return noUseDur;
    }

    public void setNoUseDur(String noUseDur) {
        this.noUseDur = noUseDur;
    }
}

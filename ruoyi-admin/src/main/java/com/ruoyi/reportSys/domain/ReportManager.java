package com.ruoyi.reportSys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.windSys.domain.BladePart;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 *
 * @author GG
 * @date 2024-07-15
 */
public class ReportManager extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 风机ID */
    private Long wId;

    /** 风机编号 */
    @Excel(name = "风机编号")
    private String turbineCode;

    /** 风机编号照片 */
    @Excel(name = "风机编号照片")
    private String turbineCodePhoto;

    /** 整机厂家 */
    @Excel(name = "整机厂家")
    private String manufacturer;

    /** 叶片厂家 */
    @Excel(name = "叶片厂家")
    private String bladeManufacturer;

    /** 叶片型号 */
    @Excel(name = "叶片型号")
    private String bladeModel;

    /** 巡检时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "巡检时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspectionDate;

    /** 检修单位 */
    @Excel(name = "检修单位")
    private String maintenanceUnit;

    /** 巡检人员 */
    @Excel(name = "巡检人员")
    private String inspectionStaff;

    /** 录入人员 */
    @Excel(name = "录入人员")
    private String entryStaff;

    /** 公司 */
    @Excel(name = "公司")
    private String company;

    /** 风场 */
    @Excel(name = "风场")
    private String windFarm;

    /** 叶片1_编号（唯一） */
    @Excel(name = "叶片1_编号", readConverterExp = "唯=一")
    private String blade1Code;

    /** 叶片1_图片url */
    @Excel(name = "叶片1_图片url")
    private String blade1PhotoUrl;

    /** 叶片2_编号（唯一） */
    @Excel(name = "叶片2_编号", readConverterExp = "唯=一")
    private String blade2Code;

    /** 叶片2_图片url */
    @Excel(name = "叶片2_图片url")
    private String blade2PhotoUrl;

    /** 叶片3_编号（唯一） */
    @Excel(name = "叶片3_编号", readConverterExp = "唯=一")
    private String blade3Code;

    /** 叶片3_图片url */
    @Excel(name = "叶片3_图片url")
    private String blade3PhotoUrl;

    /** 备用1 */
    private Long wReserve1;

    /** 备用2 */
    private Long wReserve2;

    /** 备用3 */
    private String wReserve3;

    /** 备用4 */
    private String wReserve4;

    /** 备用5 */
    private String wReserve5;

    /** 备用6 */
    private Date wReserve6;

    /** 备用7 */
    private Date wReserve7;

    /** 叶片管理信息 */
    private List<BladePart> bladePartList;

    public void setwId(Long wId)
    {
        this.wId = wId;
    }

    public Long getwId()
    {
        return wId;
    }
    public void setTurbineCode(String turbineCode)
    {
        this.turbineCode = turbineCode;
    }

    public String getTurbineCode()
    {
        return turbineCode;
    }
    public void setTurbineCodePhoto(String turbineCodePhoto)
    {
        this.turbineCodePhoto = turbineCodePhoto;
    }

    public String getTurbineCodePhoto()
    {
        return turbineCodePhoto;
    }
    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }
    public void setBladeManufacturer(String bladeManufacturer)
    {
        this.bladeManufacturer = bladeManufacturer;
    }

    public String getBladeManufacturer()
    {
        return bladeManufacturer;
    }
    public void setBladeModel(String bladeModel)
    {
        this.bladeModel = bladeModel;
    }

    public String getBladeModel()
    {
        return bladeModel;
    }
    public void setInspectionDate(Date inspectionDate)
    {
        this.inspectionDate = inspectionDate;
    }

    public Date getInspectionDate()
    {
        return inspectionDate;
    }
    public void setMaintenanceUnit(String maintenanceUnit)
    {
        this.maintenanceUnit = maintenanceUnit;
    }

    public String getMaintenanceUnit()
    {
        return maintenanceUnit;
    }
    public void setInspectionStaff(String inspectionStaff)
    {
        this.inspectionStaff = inspectionStaff;
    }

    public String getInspectionStaff()
    {
        return inspectionStaff;
    }
    public void setEntryStaff(String entryStaff)
    {
        this.entryStaff = entryStaff;
    }

    public String getEntryStaff()
    {
        return entryStaff;
    }
    public void setCompany(String company)
    {
        this.company = company;
    }

    public String getCompany()
    {
        return company;
    }
    public void setWindFarm(String windFarm)
    {
        this.windFarm = windFarm;
    }

    public String getWindFarm()
    {
        return windFarm;
    }
    public void setBlade1Code(String blade1Code)
    {
        this.blade1Code = blade1Code;
    }

    public String getBlade1Code()
    {
        return blade1Code;
    }
    public void setBlade1PhotoUrl(String blade1PhotoUrl)
    {
        this.blade1PhotoUrl = blade1PhotoUrl;
    }

    public String getBlade1PhotoUrl()
    {
        return blade1PhotoUrl;
    }
    public void setBlade2Code(String blade2Code)
    {
        this.blade2Code = blade2Code;
    }

    public String getBlade2Code()
    {
        return blade2Code;
    }
    public void setBlade2PhotoUrl(String blade2PhotoUrl)
    {
        this.blade2PhotoUrl = blade2PhotoUrl;
    }

    public String getBlade2PhotoUrl()
    {
        return blade2PhotoUrl;
    }
    public void setBlade3Code(String blade3Code)
    {
        this.blade3Code = blade3Code;
    }

    public String getBlade3Code()
    {
        return blade3Code;
    }
    public void setBlade3PhotoUrl(String blade3PhotoUrl)
    {
        this.blade3PhotoUrl = blade3PhotoUrl;
    }

    public String getBlade3PhotoUrl()
    {
        return blade3PhotoUrl;
    }
    public void setwReserve1(Long wReserve1)
    {
        this.wReserve1 = wReserve1;
    }

    public Long getwReserve1()
    {
        return wReserve1;
    }
    public void setwReserve2(Long wReserve2)
    {
        this.wReserve2 = wReserve2;
    }

    public Long getwReserve2()
    {
        return wReserve2;
    }
    public void setwReserve3(String wReserve3)
    {
        this.wReserve3 = wReserve3;
    }

    public String getwReserve3()
    {
        return wReserve3;
    }
    public void setwReserve4(String wReserve4)
    {
        this.wReserve4 = wReserve4;
    }

    public String getwReserve4()
    {
        return wReserve4;
    }
    public void setwReserve5(String wReserve5)
    {
        this.wReserve5 = wReserve5;
    }

    public String getwReserve5()
    {
        return wReserve5;
    }
    public void setwReserve6(Date wReserve6)
    {
        this.wReserve6 = wReserve6;
    }

    public Date getwReserve6()
    {
        return wReserve6;
    }
    public void setwReserve7(Date wReserve7)
    {
        this.wReserve7 = wReserve7;
    }

    public Date getwReserve7()
    {
        return wReserve7;
    }

    public List<BladePart> getBladePartList()
    {
        return bladePartList;
    }

    public void setBladePartList(List<BladePart> bladePartList)
    {
        this.bladePartList = bladePartList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("wId", getwId())
                .append("turbineCode", getTurbineCode())
                .append("turbineCodePhoto", getTurbineCodePhoto())
                .append("manufacturer", getManufacturer())
                .append("bladeManufacturer", getBladeManufacturer())
                .append("bladeModel", getBladeModel())
                .append("inspectionDate", getInspectionDate())
                .append("maintenanceUnit", getMaintenanceUnit())
                .append("inspectionStaff", getInspectionStaff())
                .append("entryStaff", getEntryStaff())
                .append("company", getCompany())
                .append("windFarm", getWindFarm())
                .append("blade1Code", getBlade1Code())
                .append("blade1PhotoUrl", getBlade1PhotoUrl())
                .append("blade2Code", getBlade2Code())
                .append("blade2PhotoUrl", getBlade2PhotoUrl())
                .append("blade3Code", getBlade3Code())
                .append("blade3PhotoUrl", getBlade3PhotoUrl())
                .append("wReserve1", getwReserve1())
                .append("wReserve2", getwReserve2())
                .append("wReserve3", getwReserve3())
                .append("wReserve4", getwReserve4())
                .append("wReserve5", getwReserve5())
                .append("wReserve6", getwReserve6())
                .append("wReserve7", getwReserve7())
                .append("bladePartList", getBladePartList())
                .toString();
    }

}

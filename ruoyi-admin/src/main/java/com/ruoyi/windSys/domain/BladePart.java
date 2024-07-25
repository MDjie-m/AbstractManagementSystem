package com.ruoyi.windSys.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 叶片管理对象 blade_part
 * 
 * @author GG
 * @date 2024-07-13
 */
public class BladePart extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long bpId;

    /** 叶片部位编号 */
    @Excel(name = "叶片部位编号")
    private String bladePartCode;

    /** 所属风机id */
    @Excel(name = "所属风机id")
    private Long turbineCode;

    /** 叶片_编号（唯一） */
    @Excel(name = "叶片_编号", readConverterExp = "唯=一")
    private String bladeCode;

    /** 叶片部位名称 */
    @Excel(name = "叶片部位名称")
    private String bladePartName;

    /** 叶片部位图片url */
    @Excel(name = "叶片部位图片url")
    private String bladePartPhotoUrl;

    /** 叶片部位检测结果 */
    @Excel(name = "叶片部位检测结果")
    private String bladePartInspectionResult;

    /** 施工区域 */
    @Excel(name = "施工区域")
    private String constructionArea;

    /** 施工内容 */
    @Excel(name = "施工内容")
    private String constructionContent;

    /** 叶片内外 */
    @Excel(name = "叶片内外")
    private String bladeInsideOutside;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /** 备用1 */
    private Long bpReserve1;

    /** 备用2 */
    private String bpReserve2;

    /** 备用3 */
    private String bpReserve3;

    /** 备用4 */
    private Date bpReserve4;

    public void setBpId(Long bpId) 
    {
        this.bpId = bpId;
    }

    public Long getBpId() 
    {
        return bpId;
    }
    public void setBladePartCode(String bladePartCode) 
    {
        this.bladePartCode = bladePartCode;
    }

    public String getBladePartCode() 
    {
        return bladePartCode;
    }
    public void setTurbineCode(Long turbineCode) 
    {
        this.turbineCode = turbineCode;
    }

    public Long getTurbineCode() 
    {
        return turbineCode;
    }
    public void setBladeCode(String bladeCode) 
    {
        this.bladeCode = bladeCode;
    }

    public String getBladeCode() 
    {
        return bladeCode;
    }
    public void setBladePartName(String bladePartName) 
    {
        this.bladePartName = bladePartName;
    }

    public String getBladePartName() 
    {
        return bladePartName;
    }
    public void setBladePartPhotoUrl(String bladePartPhotoUrl) 
    {
        this.bladePartPhotoUrl = bladePartPhotoUrl;
    }

    public String getBladePartPhotoUrl() 
    {
        return bladePartPhotoUrl;
    }
    public void setBladePartInspectionResult(String bladePartInspectionResult) 
    {
        this.bladePartInspectionResult = bladePartInspectionResult;
    }

    public String getBladePartInspectionResult() 
    {
        return bladePartInspectionResult;
    }
    public void setConstructionArea(String constructionArea) 
    {
        this.constructionArea = constructionArea;
    }

    public String getConstructionArea() 
    {
        return constructionArea;
    }
    public void setConstructionContent(String constructionContent) 
    {
        this.constructionContent = constructionContent;
    }

    public String getConstructionContent() 
    {
        return constructionContent;
    }
    public void setBladeInsideOutside(String bladeInsideOutside) 
    {
        this.bladeInsideOutside = bladeInsideOutside;
    }

    public String getBladeInsideOutside() 
    {
        return bladeInsideOutside;
    }
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }
    public void setBpReserve1(Long bpReserve1) 
    {
        this.bpReserve1 = bpReserve1;
    }

    public Long getBpReserve1() 
    {
        return bpReserve1;
    }
    public void setBpReserve2(String bpReserve2) 
    {
        this.bpReserve2 = bpReserve2;
    }

    public String getBpReserve2() 
    {
        return bpReserve2;
    }
    public void setBpReserve3(String bpReserve3) 
    {
        this.bpReserve3 = bpReserve3;
    }

    public String getBpReserve3() 
    {
        return bpReserve3;
    }
    public void setBpReserve4(Date bpReserve4) 
    {
        this.bpReserve4 = bpReserve4;
    }

    public Date getBpReserve4() 
    {
        return bpReserve4;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bpId", getBpId())
            .append("bladePartCode", getBladePartCode())
            .append("turbineCode", getTurbineCode())
            .append("bladeCode", getBladeCode())
            .append("bladePartName", getBladePartName())
            .append("bladePartPhotoUrl", getBladePartPhotoUrl())
            .append("bladePartInspectionResult", getBladePartInspectionResult())
            .append("constructionArea", getConstructionArea())
            .append("constructionContent", getConstructionContent())
            .append("bladeInsideOutside", getBladeInsideOutside())
            .append("uploadTime", getUploadTime())
            .append("bpReserve1", getBpReserve1())
            .append("bpReserve2", getBpReserve2())
            .append("bpReserve3", getBpReserve3())
            .append("bpReserve4", getBpReserve4())
            .toString();
    }
}

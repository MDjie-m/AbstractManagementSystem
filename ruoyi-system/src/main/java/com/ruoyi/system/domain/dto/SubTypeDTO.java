package com.ruoyi.system.domain.dto;

public class SubTypeDTO {
    private String parentCode;
    private Integer depth;
    private Integer classification;

    public SubTypeDTO() {
    }

    public SubTypeDTO(String parentCode, Integer depth, Integer classification) {
        this.parentCode = parentCode;
        this.depth = depth;
        this.classification = classification;
    }

    /**
     * 获取
     * @return parentCode
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * 设置
     * @param parentCode
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    /**
     * 获取
     * @return depth
     */
    public Integer getDepth() {
        return depth;
    }

    /**
     * 设置
     * @param depth
     */
    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    /**
     * 获取
     * @return classification
     */
    public Integer getClassification() {
        return classification;
    }

    /**
     * 设置
     * @param classification
     */
    public void setClassification(Integer classification) {
        this.classification = classification;
    }

    public String toString() {
        return "SubTypeDTO{parentCode = " + parentCode + ", depth = " + depth + ", classification = " + classification + "}";
    }
}
package com.ruoyi.generator.enums;

import java.util.Map;

/**
 *
 *  <el-option label="=" value="EQ" />
 *  <el-option label="!=" value="NE" />
 *  <el-option label=">" value="GT" />
 *  <el-option label=">=" value="GTE" />
 *  <el-option label="<" value="LT" />
 *  <el-option label="<=" value="LTE" />
 *  <el-option label="LIKE" value="LIKE" />
 *  <el-option label="BETWEEN" value="BETWEEN" />
 *
 */

public enum GenQueryEnum {
    LIKE("LIKE","like","包含"),
    NO_LIKE("NO","not like","不包含"),
    EQ("EQ","=","等于"),
    NE("NE","!=","不等于"),
    GT("EQ",">","等于"),
    LT("EQ","<","等于"),
    GTE("EQ",">=","等于"),
    LTE("EQ","<=","等于"),
    BETWEEN("BETWEEN","between","区间");


    private String code;

    private String way;

    private String remark;

    GenQueryEnum(String code, String way, String remark) {
        this.code = code;
        this.way = way;
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public String getWay() {
        return way;
    }

    public static String getWay(String code, String data) {
        StringBuilder andStr= new StringBuilder();
        for (GenQueryEnum value : GenQueryEnum.values()) {
             if (value.getCode().equals(code)){
                 if (code.equals(BETWEEN.getCode())){
                     break;
                 }else if (code.equals(LIKE.getCode())){
                     andStr.append(" like ")
                             .append("concat('%','")
                             .append(data)
                             .append("','%')");
                 }else if (code.equals(NO_LIKE.getCode())){
                     andStr.append(" not like ")
                             .append("concat('%','")
                             .append(data)
                             .append("','%') ");
                 }else{
                     andStr.append(value.getWay())
                             .append("'")
                             .append(data)
                             .append("'");
                 }
                 break;
            }
        }
        return andStr.toString();
    }
}

package com.ruoyi.generator.domain.bo;


import com.ruoyi.common.constant.GenConstants;
import com.ruoyi.generator.domain.GenTableColumn;
import com.ruoyi.generator.enums.GenQueryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author nuoqin
 * @DATA 2024/8/16 10:21
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenSqlBo {

    //字段
    private String preSql;

    //查询条件
    private String afterSql;

    //排序
    private String sortSql;

    public GenSqlBo(String preSql, String afterSql) {
        this.preSql = preSql;
        this.afterSql = afterSql;
    }

    public static GenSqlBo builderSelectList(List<GenTableColumn> tableColumns, Map<String, Object> data){
        StringBuilder sql= new StringBuilder();
        StringBuilder values=new StringBuilder(" 1=1 ");
        //StringBuilder values=new StringBuilder(" flag=0 ");
        StringBuilder sort= new StringBuilder();
        for (GenTableColumn tableColumn : tableColumns) {
            if (tableColumn.isList()) {
                sql.append(tableColumn.getColumnName())
                        .append(" ")
                        .append(tableColumn.getJavaField()).append(",");
            }
            if (tableColumn.isQuery() && ObjectUtils.isNotEmpty(data)) {
                Object obj = data.get(tableColumn.getJavaField() + "[0]");
                if (tableColumn.getQueryType().equals("BETWEEN") && (ObjectUtils.isNotEmpty(obj) && obj.toString().equals("'null'"))){
                    values.append(" and ")
                            .append(tableColumn.getColumnName())
                            .append(" >= '")
                            .append(obj)
                            .append("' ")
                            .append(" and ")
                            .append(tableColumn.getColumnName())
                            .append(" <= '")
                            .append(data.get(tableColumn.getJavaField()+"[1]"))
                            .append("' ");
                }else {
                    String val = MapUtils.getString(data, tableColumn.getJavaField());
                    if (StringUtils.isNotEmpty(val)){
                        values.append(" and ")
                                .append(tableColumn.getColumnName());
                        String way = GenQueryEnum.getWay(tableColumn.getQueryType(),val);
                        values.append(way)
                                .append(" ");
                    }
                }
            }
            if (ObjectUtils.isNotEmpty(tableColumn.getIsSort()) && tableColumn.getIsSort().equals(GenConstants.REQUIRE)){
                if (sort.toString().contains("order by")){
                    sort.append(" , ");
                }
                sort.append(" order by ").append(tableColumn
                        .getColumnName())
                        .append(" ")
                        .append(tableColumn.getSortType())
                        .append(" ");
            }
        }
        String preSql = sql.toString();
        preSql=preSql.substring(0,preSql.length()-1);
        String afterSql = values.toString();
        return new GenSqlBo(preSql,afterSql,sort.toString());
    }

    public static GenSqlBo builderSelectById(List<GenTableColumn> tableColumns){
        StringBuilder sql= new StringBuilder();
        StringBuilder values=new StringBuilder();
        for (GenTableColumn tableColumn : tableColumns) {
            sql.append(tableColumn.getColumnName())
                    .append(" ")
                    .append(tableColumn.getJavaField());
            sql.append(",");
            if (tableColumn.isPk()) {
                values.append(tableColumn.getColumnName());
            }
        }
        String preSql = sql.toString();
        preSql=preSql.substring(0,preSql.length()-1);
        String afterSql = values.toString();
        return new GenSqlBo(preSql,afterSql);
    }


    public static GenSqlBo builderInsert(List<GenTableColumn> tableColumns, Map<String, Object> data){
        StringBuilder sql= new StringBuilder();
        StringBuilder values=new StringBuilder();
        for (GenTableColumn column : tableColumns) {
            if (column.isInsert()){
                sql.append(column.getColumnName())
                        .append(",");
                values.append("'")
                        .append(MapUtils.getString(data,column.getJavaField()))
                        .append("'")
                        .append(",");
            }
        }
        String preSql = sql.toString();
        String afterSql = values.toString();
        return new GenSqlBo(preSql,afterSql);
    }

    public static GenSqlBo builderUpdate(List<GenTableColumn> tableColumns, Map<String, Object> data){
        StringBuilder sql= new StringBuilder();
        StringBuilder values=new StringBuilder();
        for (GenTableColumn column : tableColumns) {
            if (column.isInsert()){
                sql.append(column.getColumnName())
                        .append("='")
                        .append(data.get(column.getJavaField()))
                        .append("'")
                        .append(",");
            }
            if (column.isPk()){
                values.append(column.getColumnName())
                        .append("=")
                        .append(data.get(column.getJavaField()));
            }
        }
        String s = sql.toString();
        String preSql = s.substring(0,sql.length()-1);
        String afterSql = values.toString();
        return new GenSqlBo(preSql,afterSql);
    }
    public static GenSqlBo builderDelete(List<GenTableColumn> tableColumns,List<Long> ids){
        StringBuilder values=new StringBuilder();
        List<GenTableColumn> idColumn = tableColumns.stream().filter(column -> column.isPk()).collect(Collectors.toList());
        GenTableColumn tableColumn = idColumn.get(0);
        values.append(tableColumn.getColumnName())
                .append(" in ( ");
        for (Long id : ids) {
            values.append(id)
                  .append(",");
        }
        String afterSql = values.toString();
        afterSql=afterSql.substring(0,afterSql.length()-1);
        afterSql+=(" )");
        String preSql = " flag=1 ";

        return new GenSqlBo(preSql,afterSql);
    }


}

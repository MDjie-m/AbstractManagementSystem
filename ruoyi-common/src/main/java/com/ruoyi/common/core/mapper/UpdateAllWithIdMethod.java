package com.ruoyi.common.core.mapper;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

@Slf4j
public class UpdateAllWithIdMethod extends AbstractMethod {
    public UpdateAllWithIdMethod() {
        super("updateAllWithId");
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        //具体的字段逻辑在这里处理,实际上就是在这里构造一个新的statement
        SqlMethod sqlMethod = SqlMethod.UPDATE;

        final List<TableFieldInfo> fieldList = tableInfo.getFieldList();

        for (final TableFieldInfo tableFieldInfo : fieldList) {
            final Class<? extends TableFieldInfo> aClass = tableFieldInfo.getClass();

            try {

                final Field fieldFill = aClass.getDeclaredField("fieldFill");
                fieldFill.setAccessible(true);
                fieldFill.set(tableFieldInfo, FieldFill.UPDATE);

                final Field withUpdateFill = aClass.getDeclaredField("withUpdateFill");
                withUpdateFill.setAccessible(true);
                withUpdateFill.set(tableFieldInfo, true);
            } catch (final NoSuchFieldException e) {
                log.error("获取fieldFill失败", e);
            } catch (final IllegalAccessException e) {
                log.error("设置fieldFill失败", e);
            }
        }
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(),
                sqlSet(true, false, tableInfo, false, "", ""),
                 String.format("\r\n where %s=#{%s}",tableInfo.getKeyColumn(), tableInfo.getKeyProperty()),"");
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return addUpdateMappedStatement(mapperClass, modelClass, sqlMethod.getMethod(), sqlSource);

    }


}

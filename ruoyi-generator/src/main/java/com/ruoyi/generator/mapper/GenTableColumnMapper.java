package com.ruoyi.generator.mapper;

import java.util.List;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.generator.domain.GenTableColumn;

/**
 * 业务字段 数据层
 *
 * @author ruoyi
 */
@InterceptorIgnore(dataPermission = "true")
public interface GenTableColumnMapper extends BaseMapperPlus<GenTableColumnMapper, GenTableColumn, GenTableColumn> {
    /**
     * 根据表名称查询列信息
     *
     * @param tableName 表名称
     * @return 列信息
     */
    List<GenTableColumn> selectDbTableColumnsByName(String tableName);

}

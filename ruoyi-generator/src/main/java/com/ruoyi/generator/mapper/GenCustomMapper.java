package com.ruoyi.generator.mapper;

import java.util.List;
import java.util.Map;

public interface GenCustomMapper {

    List<Map<String,Object>> selectTableList(String sql);

    Map<String,Object> selectTableMap(String sql);

    void  insertTable(String sql);

    void updateGenTable(String sql);

    void deleteTableByIds(String sql);
}

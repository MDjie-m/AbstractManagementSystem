package com.tianyi.sim.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SimMapper {
    List<Map<String, Object>> shutdownQuery(@Param("req")Map<String, Object> req);

    int shutdownQueryCnt(@Param("req")Map<String, Object> req);
}

package com.tianyi.sim.mapper;

import com.tianyi.sim.domain.Dept;

import java.util.List;

public interface AreaMapper {

    public List<Dept> selectSubArea(String areaId);
}

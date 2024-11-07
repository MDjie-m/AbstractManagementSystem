package com.tianyi.sim.service;

import com.tianyi.sim.domain.Dept;

import java.util.List;

/**
 * 销售单位查询服务
 */
public interface IAreaService {

    public List<Dept> querySubArea(String areaId);
}

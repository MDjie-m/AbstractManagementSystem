package com.tianyi.sim.service;

import com.tianyi.sim.domain.vo.DataQueryRes;

import java.util.Map;

public interface ISimService {
    DataQueryRes shutdownQuery(Map<String, Object> req);
}

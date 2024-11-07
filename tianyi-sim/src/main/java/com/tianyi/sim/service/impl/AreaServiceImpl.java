package com.tianyi.sim.service.impl;

import com.tianyi.common.core.domain.R;
import com.tianyi.sim.domain.Dept;
import com.tianyi.sim.mapper.AreaMapper;
import com.tianyi.sim.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 销售单位查询服务
 */
@Service
public class AreaServiceImpl implements IAreaService {

    @Autowired
    private AreaMapper areaMapper;

    public List<Dept> querySubArea(String areaId){

        return areaMapper.selectSubArea(areaId);
    }
}

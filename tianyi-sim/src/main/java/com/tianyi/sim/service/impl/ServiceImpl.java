package com.tianyi.sim.service.impl;

import com.tianyi.sim.domain.vo.DataQueryRes;
import com.tianyi.sim.mapper.SimMapper;
import com.tianyi.sim.service.ISimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ServiceImpl implements ISimService {

    @Autowired
    SimMapper simMapper;

    @Override
    public DataQueryRes shutdownQuery(Map<String, Object> req) {
        DataQueryRes data=new DataQueryRes();
        int pageIndex = (int) req.get("pageIndex");
        int pageSize = (int) req.get("pageSize");
        req.put("startPage", pageIndex * pageSize);
        String beginDate=(String)req.get("beginDate");
        String endDate=(String)req.get("endDate");
        req.put("beginDatePre", beginDate.replace("-",""));
        req.put("endDatePre", endDate.replace("-",""));
        List<Map<String, Object>> dataRows= simMapper.shutdownQuery(req);
        int total=simMapper.shutdownQueryCnt(req);
        data.setDataRows(dataRows);
        data.setTotal(total);
        return data;
    }
}

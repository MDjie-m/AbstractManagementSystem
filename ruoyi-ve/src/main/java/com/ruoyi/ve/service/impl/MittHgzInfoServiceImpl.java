package com.ruoyi.ve.service.impl;

import com.ruoyi.ve.domain.MittHgzInfo;
import com.ruoyi.ve.mapper.MittHgzInfoMapper;
import com.ruoyi.ve.service.IMittHgzInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 定时任务调度日志信息 服务层
 * 
 * @author ruoyi
 */
@Service
public class MittHgzInfoServiceImpl implements IMittHgzInfoService
{
    @Autowired
    private MittHgzInfoMapper mittHgzInfoMapper;


    @Override
    public List<MittHgzInfo> selectMittHgzInfoList(MittHgzInfo mittHgzInfo) {
        return mittHgzInfoMapper.selectMittHgzInfoList(mittHgzInfo);
    }

    @Override
    public int updateMittHgzInfo(MittHgzInfo md1) {
        return mittHgzInfoMapper.updateMittHgzInfo(md1);
    }
}

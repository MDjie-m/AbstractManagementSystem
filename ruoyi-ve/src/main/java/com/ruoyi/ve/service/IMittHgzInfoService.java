package com.ruoyi.ve.service;

import com.ruoyi.ve.domain.MittHgzInfo;

import java.util.List;

public interface IMittHgzInfoService
{
    /**
     * 获取待查询的MittHgzInfo记录
     *
     * @return 待查询的MittHgzInfo
     */
    public List<MittHgzInfo> selectMittHgzInfoList(MittHgzInfo mittHgzInfo);


    int updateMittHgzInfo(MittHgzInfo md1);
}

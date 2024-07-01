package com.ruoyi.ve.mapper;

import java.util.List;

import com.ruoyi.ve.domain.MittHgzInfo;

public interface MittHgzInfoMapper
{
    List<MittHgzInfo> selectMittHgzInfoList(MittHgzInfo mittHgzInfo);

    int updateMittHgzInfo(MittHgzInfo md1);
}

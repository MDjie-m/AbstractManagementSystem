package com.tianyi.terminal.service;

import com.tianyi.terminal.domain.*;

import java.util.List;

public interface ITerminalService {


    List<QueryBSStatInfo> queryBSInfo(Double lngBegin, Double lngEnd, Double latBegin, Double latEnd, int netType);

    QueryBSTerminalInfo queryBSTerminalInfo(String bsId);


    QueryImeiInfoRes queryImeiInfo(String bsId, int index, int pageSize);

    Record queryByImeiInfo(String imei);


    TrackImeiRes trackIMEI(String imei, int index, int pageSize);
}

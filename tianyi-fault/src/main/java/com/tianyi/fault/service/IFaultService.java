package com.tianyi.fault.service;

import com.tianyi.fault.domain.CardNetStateQuery;

public interface IFaultService {
    CardNetStateQuery cardNetStateQuery(String imei);
}

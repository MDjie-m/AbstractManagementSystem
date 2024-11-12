package com.tianyi.terminal.service.impl;

import com.tianyi.terminal.mapper.es.EsMapper;
import com.tianyi.terminal.service.ITerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerminalServiceImpl implements ITerminalService {

    @Autowired
    private EsMapper esMapper;

    public void queryTerminalByBsId(){

        esMapper.createIndex();

    }
}

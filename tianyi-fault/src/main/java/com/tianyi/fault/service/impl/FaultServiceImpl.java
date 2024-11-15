package com.tianyi.fault.service.impl;

import com.tianyi.common.utils.DateUtils;
import com.tianyi.fault.domain.CardNetStateQuery;
import com.tianyi.fault.domain.FaultRecord;
import com.tianyi.fault.mapper.FaultMapper;

import com.tianyi.fault.mapper.es.EsFaultMapper;
import com.tianyi.fault.service.IFaultService;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class FaultServiceImpl implements IFaultService {
    private static final Logger logger = LoggerFactory.getLogger("FaultServiceImpl");

    @Autowired
    EsFaultMapper esFaultMapper;
    @Autowired
    private FaultMapper faultMapper;

    @Override
    public CardNetStateQuery cardNetStateQuery(String imei) {
        CardNetStateQuery cardNetStateQuery=new CardNetStateQuery();
        List<String> indexs = DateUtils.getIndexDateList("record_list_", 3);
        LambdaEsQueryWrapper<FaultRecord> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.eq(FaultRecord::getImei,imei).orderByDesc(FaultRecord::getStartTime).limit(1);
        for(String index:indexs){
            if(esFaultMapper.existsIndex(index)){
                wrapper.index(index);
            }
        }
        FaultRecord faultRecord =new FaultRecord();
        faultRecord = esFaultMapper.selectOne(wrapper);
        logger.info("返回结果faultRecord："+faultRecord.toString());
        if(faultRecord.getBillingNbr()==null){
            cardNetStateQuery.setCodeStat("702");
            cardNetStateQuery.setCodeInfo("网络故障");
        }else{
            String billingNbr=faultRecord.getBillingNbr();
            String stopReason=faultMapper.cardStopStateQuery(billingNbr);
            if(stopReason != null){
                cardNetStateQuery.setCodeStat("700");
                cardNetStateQuery.setCodeInfo(stopReason);
            }else {
                String cutReason=faultMapper.cardCutStateQuery(billingNbr);
                if(cutReason !=null){
                    cardNetStateQuery.setCodeStat("700");
                    cardNetStateQuery.setCodeInfo(cutReason);
                }
            }
        }
        return cardNetStateQuery;
    }
}

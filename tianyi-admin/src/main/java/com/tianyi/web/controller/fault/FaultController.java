package com.tianyi.web.controller.fault;

import com.tianyi.common.annotation.Anonymous;
import com.tianyi.common.core.domain.R;
import com.tianyi.fault.domain.CardNetStateQuery;
import com.tianyi.fault.service.IFaultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "故障诊断")
@RestController
@RequestMapping("/fault")
public class FaultController {

    @Autowired
    private IFaultService faultService;

    @ApiOperation("卡停机状态查询")
    @GetMapping("/cardNetStopStateQuery")
    public R<CardNetStateQuery> cardNetStateQuery(@ApiParam("imei") String imei){
        CardNetStateQuery cardNetStateQuery=new CardNetStateQuery();
        cardNetStateQuery=faultService.cardNetStateQuery(imei);
        return R.ok(cardNetStateQuery,"ok");
    }
}

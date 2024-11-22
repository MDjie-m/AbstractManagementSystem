package com.tianyi.web.controller.terminal;

import com.tianyi.common.annotation.Anonymous;
import com.tianyi.common.core.domain.R;
import com.tianyi.terminal.domain.*;
import com.tianyi.terminal.service.ITerminalService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import java.util.List;

@Api(tags = "终端管理")
@RestController
@RequestMapping("/terminal")
public class TerminalController {

    @Autowired
    private ITerminalService terminalService;

    @ApiOperation("基站终端统计信息查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lngBegin", value = "开始经度", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "lngEnd", value = "结束经度", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "latBegin", value = "开始纬度", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "latEnd", value = "结束纬度", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "netType", value = "网络制式", required = true, dataType = "String")
    })
    @GetMapping("/queryBSStatInfo")
    public R<List<QueryBSStatInfo>> queryBSInfo(Double lngBegin, Double lngEnd,Double latBegin,Double latEnd,int netType){
        List<QueryBSStatInfo> queryBSStatInfoList=new ArrayList<>();
        queryBSStatInfoList=terminalService.queryBSInfo(lngBegin,lngEnd,latBegin,latEnd,netType);
        return R.ok(queryBSStatInfoList,"查询成功");
    }

    @ApiOperation("查询基站终端信息")
    @GetMapping("/queryBSTerminalInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bsId", value = "基站ID", required = true, dataType = "String")
    })
    public R<QueryBSTerminalInfo> queryBSTerminalInfo(String bsId){
        QueryBSTerminalInfo queryBSTerminalInfo=new QueryBSTerminalInfo();
        queryBSTerminalInfo=terminalService.queryBSTerminalInfo(bsId);
        return R.ok(queryBSTerminalInfo,"查询成功");
    }

    @ApiOperation("查询IMEI信息")
    @GetMapping("/queryImeiInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bsId", value = "基站ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "index", value = "页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", required = true, dataType = "int")
    })
    public R<QueryImeiInfoRes> queryImeiInfo(String bsId, int index, int pageSize){
        QueryImeiInfoRes queryImeiInfoRes=new QueryImeiInfoRes();
        queryImeiInfoRes=terminalService.queryImeiInfo(bsId,index,pageSize);
        return R.ok(queryImeiInfoRes,"查询成功");
    }

    @ApiOperation("查询IMEI信息")
    @GetMapping("/queryByImeiInfo")
    public R<Record> queryByImeiInfo(@ApiParam(value = "imei", required = true) String imei){
        Record record=new Record();
        record=terminalService.queryByImeiInfo(imei);
        return R.ok(record,"查询成功");
    }

    @ApiOperation("查询IMEI轨迹")
    @GetMapping("/trackIMEI")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imei", value = "imei", required = true, dataType = "String"),
            @ApiImplicitParam(name = "index", value = "页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", required = true, dataType = "int")
    })
    public R<TrackImeiRes> trackIMEI(String imei, int index, int pageSize){
        TrackImeiRes trackImeiRes=new TrackImeiRes();
        trackImeiRes=terminalService.trackIMEI(imei,index,pageSize);
        return R.ok(trackImeiRes,"查询成功");
    }
}

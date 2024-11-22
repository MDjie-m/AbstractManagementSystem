package com.tianyi.terminal.service.impl;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.common.utils.DateUtils;
import com.tianyi.terminal.domain.*;


import com.tianyi.terminal.mapper.es.EsMapper;
import com.tianyi.terminal.service.ITerminalService;


import org.dromara.easyes.core.biz.EsPageInfo;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryWrapper;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedTopHits;
import org.elasticsearch.search.aggregations.metrics.Sum;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class TerminalServiceImpl implements ITerminalService {
    private static final Logger logger = LoggerFactory.getLogger("TerminalServiceImpl");

    private static final String[] TYPE_NAME_FOR_FAIL_SUPP = new String[]{"doc", "_doc"};
    @Autowired
    EsMapper esMapper;

    @Override
    public List<QueryBSStatInfo> queryBSInfo(Double lngBegin, Double lngEnd, Double latBegin, Double latEnd, int netType) {
        List<QueryBSStatInfo> resList=new ArrayList<>();
        String type="";
        if(netType !=0) {
            switch (netType) {
                case 1:
                    type = "NB";
                    break;
                case 2:
                    type = "CAT1";
                    break;
                case 3:
                    type = "4G";
                    break;
                case 4:
                    type = "5G";
                    break;
            }
        }
        String index="record_list_"+DateUtils.getIndexDayStr(1);
        String lastIndex="record_list_"+DateUtils.getIndexDayStr(2);
        List<String> indexNames = new ArrayList<String>();
        if(esMapper.existsIndex(index)){
            indexNames.add(index);
        }else {
            indexNames.add(lastIndex);
        }
        QueryBuilder lngQueryBuilder = QueryBuilders.rangeQuery("bs_longitude").from(lngBegin).to(lngEnd).includeLower(true).includeUpper(true);
        QueryBuilder latQueryBuilder = QueryBuilders.rangeQuery("bs_latitude").from(latBegin).to(latEnd).includeLower(true).includeUpper(true);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(lngQueryBuilder);
        boolQueryBuilder.must(latQueryBuilder);
        if(type.length()>0){
            boolQueryBuilder.must(QueryBuilders.termQuery("net_type", type));
        }
        CardinalityAggregationBuilder imeiAggres = AggregationBuilders.cardinality("imeiAggres").field("imei");
        AggregationBuilder bsIdAgg = AggregationBuilders
                .terms("bsIdAgg")
                .field("bs_id")
                .size(10);
        AggregationBuilder lngAggre = AggregationBuilders
                .terms("lngAggre")
                .field("bs_longitude");
        AggregationBuilder latAggre = AggregationBuilders
                .terms("latAggre")
                .field("bs_latitude");
        bsIdAgg.subAggregation(lngAggre);
        lngAggre.subAggregation(latAggre);
        latAggre.subAggregation(imeiAggres);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(0);
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.aggregation(bsIdAgg);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices((String[])indexNames.toArray(new String[indexNames.size()]));
        searchRequest.types(TYPE_NAME_FOR_FAIL_SUPP);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = esMapper.search(searchRequest, RequestOptions.DEFAULT);
            RestStatus restStatus = searchResponse.status();
            if (restStatus == RestStatus.OK) {
                Terms aggTerms = (Terms)searchResponse.getAggregations().get("bsIdAgg");
                for (Terms.Bucket buck : aggTerms.getBuckets()) {
                    String bsId = buck.getKeyAsString();
                    Aggregations aggregationsCity = buck.getAggregations();
                    Terms aggLngTerms = (Terms) aggregationsCity.get("lngAggre");
                    for (Terms.Bucket buckLng : aggLngTerms.getBuckets()) {
                        String lng = buckLng.getKeyAsString();
                        Aggregations aggregationsRegion = buckLng.getAggregations();
                        Terms aggLatTerms = (Terms)aggregationsRegion.get("latAggre");
                        for(Terms.Bucket buckLat : aggLatTerms.getBuckets()) {
                            String lat = buckLat.getKeyAsString();
                            Aggregations aggregationImer = buckLat.getAggregations();
                            Aggregation subjectCount = aggregationImer.get("imeiAggres");
                            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(subjectCount));
                            String cardinalityValue = jsonObject.getString("value");
                            QueryBSStatInfo queryBSStatInfo=new QueryBSStatInfo();
                            queryBSStatInfo.setBsId(bsId);
                            queryBSStatInfo.setLng(Double.valueOf(lng));
                            queryBSStatInfo.setLat(Double.valueOf(lat));
                            queryBSStatInfo.setTerminalCnt(Integer.valueOf(cardinalityValue));
                            resList.add(queryBSStatInfo);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(resList);
        List<QueryBSStatInfo> resList100=new ArrayList<>();
        if(resList.size()>100){
            for(int i=0;i<100;i++){
                resList100.add(resList.get(i));
            }
            return resList100;
        }else{
            return resList;
        }
    }

    @Override
    public QueryBSTerminalInfo queryBSTerminalInfo(String bsId) {
        QueryBSTerminalInfo queryBSTerminalInfo=new QueryBSTerminalInfo();
        String indexName="";
        String lastIndex="record_list_"+DateUtils.getIndexDayStr(1);
        String lastTwoIndex="record_list_"+DateUtils.getIndexDayStr(2);
        List<String> indexNames = new ArrayList<String>();
        if(esMapper.existsIndex(lastIndex)){
            indexName=lastIndex;
            indexNames.add(lastIndex);
        }else {
            indexName=lastTwoIndex;
            indexNames.add(lastTwoIndex);
        }
        LambdaEsQueryWrapper<Record> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.eq(Record::getBsId,bsId).limit(1).index(indexName);
        Record record = esMapper.selectOne(wrapper);
        logger.info("返回record："+record.toString());
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("bs_id", bsId));
        CardinalityAggregationBuilder imeiAggres = AggregationBuilders.cardinality("imeiAggres").field("imei");
        SumAggregationBuilder sumVol = AggregationBuilders
                .sum("sumVol")
                .field("vol");
        AggregationBuilder bsIdAgg = AggregationBuilders
                .terms("bsIdAgg")
                .field("bs_id")
                .size(1);
        bsIdAgg.subAggregation(imeiAggres);
        bsIdAgg.subAggregation(sumVol);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(0);
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.aggregation(bsIdAgg);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices((String[])indexNames.toArray(new String[indexNames.size()]));
        searchRequest.types(TYPE_NAME_FOR_FAIL_SUPP);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = esMapper.search(searchRequest, RequestOptions.DEFAULT);
            RestStatus restStatus = searchResponse.status();
            if (restStatus == RestStatus.OK) {
                Terms aggTerms = (Terms)searchResponse.getAggregations().get("bsIdAgg");
                for (Terms.Bucket buck : aggTerms.getBuckets()) {
                    Sum vol = buck.getAggregations().get("sumVol");
                    double volValue = vol.getValue();
                    double volValueNew = volValue/1024.0/1024.0;
                    Double volD=new BigDecimal(volValueNew).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    Aggregations aggregationImer = buck.getAggregations();
                    Aggregation subjectCount = aggregationImer.get("imeiAggres");
                    JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(subjectCount));
                    String cardinalityValue = jsonObject.getString("value");
                    queryBSTerminalInfo.setBsId(bsId);
                    queryBSTerminalInfo.setBsName(record.getBsName());
                    queryBSTerminalInfo.setLng(record.getLng());
                    queryBSTerminalInfo.setLat(record.getLat());
                    queryBSTerminalInfo.setTerminalCnt(cardinalityValue);
                    queryBSTerminalInfo.setVol(String.valueOf(volD));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queryBSTerminalInfo;
    }

    @Override
    public QueryImeiInfoRes queryImeiInfo(String bsId, int index, int pageSize) {
        QueryImeiInfoRes res=new QueryImeiInfoRes();
        List<RecordImei> dataRows = new ArrayList<>();
        List<RecordImei> totalDataRows = new ArrayList<>();
        String lastIndex="record_list_"+DateUtils.getIndexDayStr(1);
        String lastTwoIndex="record_list_"+DateUtils.getIndexDayStr(2);
        List<String> indexNames = new ArrayList<String>();
        if(esMapper.existsIndex(lastIndex)){
            indexNames.add(lastIndex);
        }else {
            indexNames.add(lastTwoIndex);
        }
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("bs_id", bsId));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(0);
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.aggregation(AggregationBuilders
                .terms("imeiAgg")
                .field("imei")
                .size(30000)
                .subAggregation(AggregationBuilders.topHits("top_starttime")
                        .sort("start_time", SortOrder.DESC)
                        .size(1)));
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices((String[])indexNames.toArray(new String[indexNames.size()]));
        searchRequest.types(TYPE_NAME_FOR_FAIL_SUPP);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = esMapper.search(searchRequest, RequestOptions.DEFAULT);
            RestStatus restStatus = searchResponse.status();
            if (restStatus == RestStatus.OK) {
                Terms aggTerms = (Terms) searchResponse.getAggregations().get("imeiAgg");
                for (Terms.Bucket buck : aggTerms.getBuckets()) {
                    ParsedTopHits topDetail = buck.getAggregations().get("top_starttime");
                    SearchHit[] hits = topDetail.getHits().getHits();
                    Map<String, Object> latestDocument = hits[0].getSourceAsMap();
                    RecordImei recordImei=new RecordImei();
                    recordImei.setImei((String)latestDocument.get("imei"));
                    recordImei.setImsi((String)latestDocument.get("imsi"));
                    recordImei.setBillingNbr((String)latestDocument.get("billing_nbr"));
                    recordImei.setBsId(bsId);
                    recordImei.setVol((String)latestDocument.get("vol"));
                    recordImei.setVolDur((String)latestDocument.get("vol_dur"));
                    recordImei.setProvName((String)latestDocument.get("prov_name"));
                    recordImei.setAreaName((String)latestDocument.get("area_name"));
                    recordImei.setCustName((String)latestDocument.get("cust_name"));
                    recordImei.setModelName((String)latestDocument.get("model_name"));
                    recordImei.setManufacturer((String)latestDocument.get("manufacturer"));
                    totalDataRows.add(recordImei);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        int fromIndex = (index - 1) * pageSize;
        if (fromIndex >= totalDataRows.size()) {

        }else {
            int toIndex = Math.min(fromIndex + pageSize, totalDataRows.size());
            dataRows=totalDataRows.subList(fromIndex, toIndex);
        }
        res.setDataRows(dataRows);
        res.setTotal(totalDataRows.size());
        return res;
    }

    @Override
    public Record queryByImeiInfo(String imei) {
        String indexName="";
        String lastIndex="record_list_"+DateUtils.getIndexDayStr(1);
        String lastTwoIndex="record_list_"+DateUtils.getIndexDayStr(2);
        if(esMapper.existsIndex(lastIndex)){
            indexName=lastIndex;
        }else {
            indexName=lastTwoIndex;
        }
        LambdaEsQueryWrapper<Record> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.eq(Record::getImei,imei)
               .orderByDesc(Record::getStartTime)
                .limit(1)
                .index(indexName);
        Record record = esMapper.selectOne(wrapper);
        logger.info("返回结果："+record.toString());
        return record;
    }

    @Override
    public TrackImeiRes trackIMEI(String imei, int index, int pageSize) {
        String indexName="";
        String lastIndex="record_list_"+DateUtils.getIndexDayStr(1);
        String lastTwoIndex="record_list_"+DateUtils.getIndexDayStr(2);
        if(esMapper.existsIndex(lastIndex)){
            indexName=lastIndex;
        }else {
            indexName=lastTwoIndex;
        }
        TrackImeiRes trackImeiRes =new TrackImeiRes();
        LambdaEsQueryWrapper<Record> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.eq(Record::getImei,imei).index(indexName);
        EsPageInfo<Record> recordEsPageInfo = esMapper.pageQuery(wrapper, index, pageSize);
        long total = recordEsPageInfo.getTotal();
        List<Record> list = recordEsPageInfo.getList();
        trackImeiRes.setTotal(total);
        trackImeiRes.setDataRows(list);
        return trackImeiRes;
    }


}

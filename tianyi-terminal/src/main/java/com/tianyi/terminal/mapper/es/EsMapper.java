package com.tianyi.terminal.mapper.es;


import com.tianyi.terminal.domain.Record;
import org.dromara.easyes.core.kernel.BaseEsMapper;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public interface EsMapper extends BaseEsMapper<Record> {


    default public Record queryByBsID(String bsId){
        return null;
    }
}

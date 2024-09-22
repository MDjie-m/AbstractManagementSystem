package com.ruoyi.billiard.mapper;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.billiard.domain.StoreTutor;
import com.ruoyi.common.core.domain.model.KeyValueVo;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 门店助教Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-06
 */
@Mapper
public interface StoreTutorMapper extends MyBaseMapper<StoreTutor>
{
    /**
     * 查询门店助教
     * 
     * @param storeTutorId 门店助教主键
     * @return 门店助教
     */
    public StoreTutor selectStoreTutorByStoreTutorId(Long storeTutorId);

    /**
     * 查询门店助教列表
     * 
     * @param storeTutor 门店助教
     * @return 门店助教集合
     */
    public List<StoreTutor> selectStoreTutorList(StoreTutor storeTutor);


    List<KeyValueVo<Integer, Long>> queryCountGroupByWorkStatus(Long storeId);

    BigDecimal queryPrice(Long tutorId);
}

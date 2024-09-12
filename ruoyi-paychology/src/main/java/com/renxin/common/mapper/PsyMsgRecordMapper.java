package com.renxin.common.mapper;


import com.renxin.common.domain.PsyMsgRecord;

import java.util.List;

/**
 * 消息记录Mapper接口
 * 
 * @author renxin
 * @date 2024-09-11
 */
public interface PsyMsgRecordMapper 
{
    /**
     * 查询消息记录
     * 
     * @param id 消息记录主键
     * @return 消息记录
     */
    public PsyMsgRecord selectPsyMsgRecordById(Long id);

    /**
     * 查询消息记录列表
     * 
     * @param psyMsgRecord 消息记录
     * @return 消息记录集合
     */
    public List<PsyMsgRecord> selectPsyMsgRecordList(PsyMsgRecord psyMsgRecord);

    /**
     * 新增消息记录
     * 
     * @param psyMsgRecord 消息记录
     * @return 结果
     */
    public int insertPsyMsgRecord(PsyMsgRecord psyMsgRecord);

    /**
     * 修改消息记录
     * 
     * @param psyMsgRecord 消息记录
     * @return 结果
     */
    public int updatePsyMsgRecord(PsyMsgRecord psyMsgRecord);

    /**
     * 删除消息记录
     * 
     * @param id 消息记录主键
     * @return 结果
     */
    public int deletePsyMsgRecordById(Long id);

    /**
     * 批量删除消息记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyMsgRecordByIds(Long[] ids);
}

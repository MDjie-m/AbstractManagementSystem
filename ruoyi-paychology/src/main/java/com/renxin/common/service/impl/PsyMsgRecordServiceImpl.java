package com.renxin.common.service.impl;


import com.renxin.common.domain.PsyMsgRecord;
import com.renxin.common.mapper.PsyMsgRecordMapper;
import com.renxin.common.service.IPsyMsgRecordService;
import com.renxin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 消息记录Service业务层处理
 * 
 * @author renxin
 * @date 2024-09-11
 */
@Service
public class PsyMsgRecordServiceImpl implements IPsyMsgRecordService 
{
    @Autowired
    private PsyMsgRecordMapper psyMsgRecordMapper;
    
  /*  @Autowired
    private SimpMessagingTemplate messagingTemplate;
*/
    /**
     * 查询消息记录
     * 
     * @param id 消息记录主键
     * @return 消息记录
     */
    @Override
    public PsyMsgRecord selectPsyMsgRecordById(Long id)
    {
        return psyMsgRecordMapper.selectPsyMsgRecordById(id);
    }

    /**
     * 查询消息记录列表
     * 
     * @param psyMsgRecord 消息记录
     * @return 消息记录
     */
    @Override
    public List<PsyMsgRecord> selectPsyMsgRecordList(PsyMsgRecord psyMsgRecord)
    {
        return psyMsgRecordMapper.selectPsyMsgRecordList(psyMsgRecord);
    }

    /**
     * 新增消息记录
     * 
     * @param psyMsgRecord 消息记录
     * @return 结果
     */
    @Override
    public int insertPsyMsgRecord(PsyMsgRecord req)
    {
        req.setCreateTime(DateUtils.getNowDate());
        req.setUpdateTime(DateUtils.getNowDate());
        req.setSendTime(DateUtils.getNowDate());
        
        //消息发送
       /* if (req.getReceiveUserId() != null) {
            messagingTemplate.convertAndSendToUser(req.getReceiveUserType() + "-" + req.getReceiveUserId(), 
                    "/queue/messages", req);
        }*/
        
        //消息记录
        int i = psyMsgRecordMapper.insertPsyMsgRecord(req);
        return i;
    }

    /**
     * 修改消息记录
     * 
     * @param psyMsgRecord 消息记录
     * @return 结果
     */
    @Override
    public int updatePsyMsgRecord(PsyMsgRecord psyMsgRecord)
    {
        psyMsgRecord.setUpdateTime(DateUtils.getNowDate());
        return psyMsgRecordMapper.updatePsyMsgRecord(psyMsgRecord);
    }

    /**
     * 批量删除消息记录
     * 
     * @param ids 需要删除的消息记录主键
     * @return 结果
     */
    @Override
    public int deletePsyMsgRecordByIds(Long[] ids)
    {
        return psyMsgRecordMapper.deletePsyMsgRecordByIds(ids);
    }

    /**
     * 删除消息记录信息
     * 
     * @param id 消息记录主键
     * @return 结果
     */
    @Override
    public int deletePsyMsgRecordById(Long id)
    {
        return psyMsgRecordMapper.deletePsyMsgRecordById(id);
    }
}

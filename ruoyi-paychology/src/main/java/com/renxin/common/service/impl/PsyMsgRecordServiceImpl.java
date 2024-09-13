package com.renxin.common.service.impl;


import com.renxin.common.core.domain.dto.ConsultDTO;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.common.domain.PersonInfo;
import com.renxin.common.domain.PsyMsgRecord;
import com.renxin.common.exception.ServiceException;
import com.renxin.common.mapper.PsyMsgRecordMapper;
import com.renxin.common.service.IPsyMsgRecordService;
import com.renxin.common.utils.DateUtils;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.framework.web.service.PocketTokenService;
import com.renxin.psychology.domain.PsyUser;
import com.renxin.psychology.service.IPsyConsultService;
import com.renxin.psychology.service.IPsyUserService;
import com.renxin.psychology.vo.PsyConsultVO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private ConsultantTokenService consultantTokenService;
    @Autowired
    private PocketTokenService pocketTokenService;
    @Autowired
    private IPsyConsultService consultService;
    @Autowired
    private IPsyUserService userService;
    
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
    public List<PsyMsgRecord> selectPsyMsgRecordList(PsyMsgRecord req)
    {
        List<PsyMsgRecord> recordList = psyMsgRecordMapper.selectPsyMsgRecordList(req);
        //查询对话双方的perosonInfo
        Long talkUserId1 = req.getTalkUserId1();
        Long talkUserId2 = req.getTalkUserId2();
        Integer talkUserType1 = req.getTalkUserType1();
        Integer talkUserType2 = req.getTalkUserType2();
        PersonInfo personInfo1 = getPersonInfo(talkUserId1, talkUserType1);
        PersonInfo personInfo2 = getPersonInfo(talkUserId2, talkUserType2);

        //填充sendBy
        for (PsyMsgRecord msgRecord : recordList) {
            if (msgRecord.getSendUserId().equals(talkUserId1)){
                msgRecord.setSendBy(personInfo1);
            }else{
                msgRecord.setSendBy(personInfo2);
            }
        }
        
        return recordList;
    }
    
    //获取用户基本信息
    private PersonInfo getPersonInfo(Long userId, Integer userType){
        PersonInfo personInfo = new PersonInfo();
        if (1 == userType) {//来访者
            PsyUser psyUser = userService.selectPsyUserById(userId);
            BeanUtils.copyProperties(psyUser,personInfo);
            personInfo.setUserType(1);
        }else if(2 == userType) {//咨询师
            PsyConsultVO consultant = consultService.getOne(userId);
            personInfo.setId(consultant.getId());
            personInfo.setAvatar(consultant.getAvatar());
            personInfo.setName(consultant.getNickName());
            personInfo.setUserType(2);
        }
        return personInfo;
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

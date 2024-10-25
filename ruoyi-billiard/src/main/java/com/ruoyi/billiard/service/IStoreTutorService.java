package com.ruoyi.billiard.service;

import java.time.LocalDateTime;
import java.util.List;
import com.ruoyi.billiard.domain.StoreTutor;
import com.ruoyi.billiard.domain.vo.miniappdomain.TutorResVo;

/**
 * 门店助教Service接口
 * 
 * @author ruoyi
 * @date 2024-09-06
 */
public interface IStoreTutorService 
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

    /**
     * 新增门店助教
     * 
     * @param storeTutor 门店助教
     * @return 结果
     */
    public int insertStoreTutor(StoreTutor storeTutor);

    /**
     * 修改门店助教
     * 
     * @param storeTutor 门店助教
     * @return 结果
     */
    public int updateStoreTutor(StoreTutor storeTutor);

    /**
     * 批量删除门店助教
     * 
     * @param storeTutorIds 需要删除的门店助教主键集合
     * @return 结果
     */
    public int deleteStoreTutorByStoreTutorIds(Long[] storeTutorIds);

    /**
     * 删除门店助教信息
     * 
     * @param storeTutorId 门店助教主键
     * @return 结果
     */
    public int deleteStoreTutorByStoreTutorId(Long storeTutorId);


    void swapToNewDesk(Long tutorId, Long storeId,  Long newDeskId);

    void tutorPause(Long tutorId, Long storeId);

    void tutorResume(Long tutorId, Long storeId);

    void tutorStop(Long tutorId, Long storeId);

    List<TutorResVo>  queryByStoreId(Long storeId);
}

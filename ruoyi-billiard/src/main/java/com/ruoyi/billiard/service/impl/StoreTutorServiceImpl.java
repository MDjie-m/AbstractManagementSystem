package com.ruoyi.billiard.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.StoreTutorMapper;
import com.ruoyi.billiard.domain.StoreTutor;
import com.ruoyi.billiard.service.IStoreTutorService;

/**
 * 门店助教Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-06
 */
@Service
public class StoreTutorServiceImpl implements IStoreTutorService 
{
    @Autowired
    private StoreTutorMapper storeTutorMapper;

    /**
     * 查询门店助教
     * 
     * @param storeTutorId 门店助教主键
     * @return 门店助教
     */
    @Override
    public StoreTutor selectStoreTutorByStoreTutorId(Long storeTutorId)
    {
        return storeTutorMapper.selectById(storeTutorId);
    }

    /**
     * 查询门店助教列表
     * 
     * @param storeTutor 门店助教
     * @return 门店助教
     */
    @Override
    public List<StoreTutor> selectStoreTutorList(StoreTutor storeTutor)
    {
        return storeTutorMapper.selectStoreTutorList(storeTutor);
    }

    /**
     * 新增门店助教
     * 
     * @param storeTutor 门店助教
     * @return 结果
     */
    @Override
    public int insertStoreTutor(StoreTutor storeTutor)
    {
        storeTutor.setCreateTime(DateUtils.getNowDate());
        return storeTutorMapper.insertStoreTutor(storeTutor);
    }

    /**
     * 修改门店助教
     * 
     * @param storeTutor 门店助教
     * @return 结果
     */
    @Override
    public int updateStoreTutor(StoreTutor storeTutor)
    {
        storeTutor.setUpdateTime(DateUtils.getNowDate());
        return storeTutorMapper.updateStoreTutor(storeTutor);
    }

    /**
     * 批量删除门店助教
     * 
     * @param storeTutorIds 需要删除的门店助教主键
     * @return 结果
     */
    @Override
    public int deleteStoreTutorByStoreTutorIds(Long[] storeTutorIds)
    {
        return storeTutorMapper.deleteStoreTutorByStoreTutorIds(storeTutorIds);
    }

    /**
     * 删除门店助教信息
     * 
     * @param storeTutorId 门店助教主键
     * @return 结果
     */
    @Override
    public int deleteStoreTutorByStoreTutorId(Long storeTutorId)
    {
        return storeTutorMapper.deleteStoreTutorByStoreTutorId(storeTutorId);
    }
}

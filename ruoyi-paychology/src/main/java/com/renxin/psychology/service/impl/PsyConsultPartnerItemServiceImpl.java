package com.renxin.psychology.service.impl;

import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.renxin.psychology.mapper.PsyConsultPartnerItemMapper;
import com.renxin.psychology.domain.PsyConsultPartnerItem;
import com.renxin.psychology.service.IPsyConsultPartnerItemService;

/**
 * 咨询师入驻申请子Service业务层处理
 * 
 * @author renxin
 * @date 2023-11-07
 */
@Service
public class PsyConsultPartnerItemServiceImpl extends ServiceImpl<PsyConsultPartnerItemMapper, PsyConsultPartnerItem> implements IPsyConsultPartnerItemService
{
    @Autowired
    private PsyConsultPartnerItemMapper psyConsultPartnerItemMapper;

    /**
     * 查询咨询师入驻申请子
     * 
     * @param id 咨询师入驻申请子主键
     * @return 咨询师入驻申请子
     */
    @Override
    public PsyConsultPartnerItem getOne(Long id)
    {
        return psyConsultPartnerItemMapper.selectById(id);
    }

    @Override
    public List<PsyConsultPartnerItem> getListById(Long id)
    {
        LambdaQueryWrapper<PsyConsultPartnerItem> wp = new LambdaQueryWrapper<>();
        wp.eq(PsyConsultPartnerItem::getPId, id);
        return psyConsultPartnerItemMapper.selectList(wp);
    }

    /**
     * 查询咨询师入驻申请子列表
     * 
     * @param psyConsultPartnerItem 咨询师入驻申请子
     * @return 咨询师入驻申请子
     */
    @Override
    public List<PsyConsultPartnerItem> getList(PsyConsultPartnerItem psyConsultPartnerItem)
    {
        return psyConsultPartnerItemMapper.selectList(new QueryWrapper<>(psyConsultPartnerItem));
    }

    /**
     * 新增咨询师入驻申请子
     * 
     * @param psyConsultPartnerItem 咨询师入驻申请子
     * @return 结果
     */
    @Override
    public int add(PsyConsultPartnerItem psyConsultPartnerItem)
    {
        return psyConsultPartnerItemMapper.insert(psyConsultPartnerItem);
    }

    /**
     * 修改咨询师入驻申请子
     * 
     * @param psyConsultPartnerItem 咨询师入驻申请子
     * @return 结果
     */
    @Override
    public int edit(PsyConsultPartnerItem psyConsultPartnerItem)
    {
        return psyConsultPartnerItemMapper.updateById(psyConsultPartnerItem);
    }

    /**
     * 批量删除咨询师入驻申请子
     * 
     * @param ids 需要删除的咨询师入驻申请子主键
     * @return 结果
     */
    @Override
    public int deleteAll(Long[] ids)
    {
        return psyConsultPartnerItemMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除咨询师入驻申请子信息
     * 
     * @param id 咨询师入驻申请子主键
     * @return 结果
     */
    @Override
    public int delete(Long id)
    {
        return psyConsultPartnerItemMapper.deleteById(id);
    }

    /**
     * 统计经历时长
     */
    @Override
    public int countTime(PsyConsultPartnerItem req)
    {
        return psyConsultPartnerItemMapper.countTime(req);
    }
}

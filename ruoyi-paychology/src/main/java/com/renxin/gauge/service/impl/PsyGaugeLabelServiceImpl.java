package com.renxin.gauge.service.impl;

import com.renxin.gauge.domain.PsyGauge;
import com.renxin.gauge.domain.PsyGaugeLabel;
import com.renxin.gauge.mapper.PsyGaugeLabelMapper;
import com.renxin.gauge.service.IPsyGaugeLabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @User hogan
 * @Time 2022/10/17 17:11
 * @e-mail hkcugwh@163.com
 **/
@Service
public class PsyGaugeLabelServiceImpl implements IPsyGaugeLabelService {

    @Resource
    private PsyGaugeLabelMapper psyGaugeLabelMapper;


    /**
     * 查询测评标签
     *
     * @param id 测评标签主键
     * @return 测评标签
     */
    @Override
    public PsyGaugeLabel selectPsyGaugeLabelById(Long id)
    {
        return psyGaugeLabelMapper.selectPsyGaugeLabelById(id);
    }

    /**
     * 查询测评标签列表
     *
     * @param psyGaugeLabel 测评标签
     * @return 测评标签
     */
    @Override
    public List<PsyGaugeLabel> selectPsyGaugeLabelList(PsyGaugeLabel psyGaugeLabel)
    {
        return psyGaugeLabelMapper.queryLabelPage(psyGaugeLabel);
    }

    /**
     * 新增测评标签
     *
     * @param psyGaugeLabel 测评标签
     * @return 结果
     */
    @Override
    public int insertPsyGaugeLabel(PsyGaugeLabel psyGaugeLabel)
    {
        return psyGaugeLabelMapper.insertPsyGaugeLabel(psyGaugeLabel);
    }

    /**
     * 修改测评标签
     *
     * @param psyGaugeLabel 测评标签
     * @return 结果
     */
    @Override
    public int updatePsyGaugeLabel(PsyGaugeLabel psyGaugeLabel)
    {
        return psyGaugeLabelMapper.updatePsyGaugeLabel(psyGaugeLabel);
    }

    /**
     * 批量删除测评标签
     *
     * @param ids 需要删除的测评标签主键
     * @return 结果
     */
    @Override
    public int deletePsyGaugeLabelByIds(Long[] ids)
    {
        return psyGaugeLabelMapper.deletePsyGaugeLabelByIds(ids);
    }

    /**
     * 删除测评标签信息
     *
     * @param id 测评标签主键
     * @return 结果
     */
    @Override
    public int deletePsyGaugeLabelById(Long id)
    {
        return psyGaugeLabelMapper.deletePsyGaugeLabelById(id);
    }

    @Override
    public List<PsyGauge> queryGaugeByLabel(Integer labelCode) {
        return psyGaugeLabelMapper.queryGaugeByLabel(labelCode);
    }
}

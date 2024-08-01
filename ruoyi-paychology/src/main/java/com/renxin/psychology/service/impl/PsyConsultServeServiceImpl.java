package com.renxin.psychology.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.renxin.psychology.domain.PsyConsultServe;
import com.renxin.psychology.domain.PsyConsultServeConfig;
import com.renxin.psychology.mapper.PsyConsultServeMapper;
import com.renxin.psychology.request.PsyRefConsultServeReq;
import com.renxin.psychology.service.IPsyConsultServeService;
import com.renxin.psychology.vo.PsyConsultServeVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PsyConsultServeServiceImpl implements IPsyConsultServeService {

    @Resource
    private PsyConsultServeMapper psyConsultServeMapper;

    @Override
    public List<PsyConsultServe> getList(PsyConsultServe req) {
        LambdaQueryWrapper<PsyConsultServe> wp = new LambdaQueryWrapper<>();
        wp.eq((req.getConsultId() != null && req.getConsultId() > 0), PsyConsultServe::getConsultId, req.getConsultId());
        wp.eq((req.getServeId() != null && req.getServeId() > 0), PsyConsultServe::getServeId, req.getServeId());
        return psyConsultServeMapper.selectList(wp);
    }

    @Override
    public PsyConsultServe getOne(PsyConsultServe req) {
        List<PsyConsultServe> serves = getList(req);
        return CollectionUtils.isNotEmpty(serves) ? serves.get(0) : null;
    }

    @Override
    public int getRefCountByConsultId(Long id){
        PsyConsultServe req = new PsyConsultServe();
        req.setConsultId(id);
        List<PsyConsultServe> serves = getList(req);
        return serves.size();
    }

    @Override
    public List<PsyConsultServeVO> getServeRef(PsyConsultServe req) {
        return psyConsultServeMapper.getServeRef(req);
    }

    @Override
    public List<PsyConsultServeVO> getConsultServeRef(PsyConsultServe req) {
        // 咨询师过滤
        return psyConsultServeMapper.getConsultServeRef(req);
    }

    @Override
    public int batchServeRef(PsyRefConsultServeReq req) {
        List<PsyConsultServe> refs = new ArrayList<>();
        req.getIds().forEach(a -> {
            PsyConsultServe ref = new PsyConsultServe();
            ref.setConsultId(req.getConsultId());
            ref.setServeId(a);
            refs.add(ref);
        });

        return psyConsultServeMapper.batchServeRef(refs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(PsyConsultServe serve) {
        LambdaQueryWrapper<PsyConsultServe> wp = new LambdaQueryWrapper<>();
        wp.eq(PsyConsultServe::getServeId, serve.getServeId());
        wp.eq(PsyConsultServe::getConsultId, serve.getConsultId());
        return psyConsultServeMapper.delete(wp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAll() {
        return psyConsultServeMapper.deleteAll();
    }


    /**
     * 根据relationId查询服务详情
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PsyConsultServeConfig getServerDetailByRelationId(String id){
        return psyConsultServeMapper.getServerDetailByRelationId(id);
    }
    
    
}

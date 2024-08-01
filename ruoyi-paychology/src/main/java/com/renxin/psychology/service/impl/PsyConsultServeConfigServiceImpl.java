package com.renxin.psychology.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.renxin.common.constant.PsyConstants;
import com.renxin.common.enums.ServiceObjectEnum;
import com.renxin.common.exception.ServiceException;
import com.renxin.common.exception.UtilException;
import com.renxin.common.utils.ComUtil;
import com.renxin.common.utils.NewDateUtil;
import com.renxin.common.vo.DateLimitUtilVO;
import com.renxin.psychology.constant.ConsultConstant;
import com.renxin.psychology.domain.PsyConsultServe;
import com.renxin.psychology.domain.PsyConsultServeConfig;
import com.renxin.psychology.mapper.PsyConsultServeConfigMapper;
import com.renxin.psychology.request.PsyConsultServeConfigReq;
import com.renxin.psychology.request.PsyRefConsultServeReq;
import com.renxin.psychology.service.IPsyConsultServeConfigService;
import com.renxin.psychology.service.IPsyConsultServeService;
import com.renxin.psychology.vo.PsyConsultServeConfigVO;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 咨询服务配置Service业务层处理
 * 
 * @author renxin
 * @date 2023-07-14
 */
@Service
public class PsyConsultServeConfigServiceImpl extends ServiceImpl<PsyConsultServeConfigMapper, PsyConsultServeConfig> implements IPsyConsultServeConfigService
{
    @Resource
    private IPsyConsultServeService psyConsultServeService;

    @Resource
    private PsyConsultServeConfigMapper psyConsultServeConfigMapper;

    @Override
    public PsyConsultServeConfigVO getOne(Long id) {
        PsyConsultServeConfigVO server = BeanUtil.toBean(psyConsultServeConfigMapper.selectById(id), PsyConsultServeConfigVO.class);
        /*String serviceObjectStr = server.getServiceObject();
        if (ObjectUtils.isNotEmpty(serviceObjectStr)){
            server.setServiceObjectList(ComUtil.stringToArrayStr(serviceObjectStr));
        }else{
            server.setServiceObjectList(new ArrayList<String>());
        }*/
        return server;
    }

    @Override
    public List<PsyConsultServeConfig> getList(PsyConsultServeConfigReq req) {
        req.setDelFlag("0");
        if (StringUtils.isNotBlank(req.getDateLimit())) {
            DateLimitUtilVO dateLimit = NewDateUtil.getDateLimit(req.getDateLimit());
            req.setStartTime(dateLimit.getStartTime());
            req.setEndTime(dateLimit.getEndTime());
        }

        List<PsyConsultServeConfig> list = psyConsultServeConfigMapper.getList(req);
        list.forEach(this::setNames);
        return list;
    }

    private void setNames(PsyConsultServeConfig entity) {
        String modeName = "";
        String typeName = ConsultConstant.CONSULT_TYPE_ONCE.equals(entity.getType()) ? "单次咨询" : "套餐咨询";
        if (ConsultConstant.CONSULT_MODE_SOUND.equals(entity.getMode())) {
            modeName = "语音咨询";
        } else if (ConsultConstant.CONSULT_MODE_VOICE.equals(entity.getMode())) {
            modeName = "视频咨询";
        } else if (ConsultConstant.CONSULT_MODE_FACE.equals(entity.getMode())) {
            modeName = "当面咨询";
        }

        entity.setModeName(modeName);
        entity.setTypeName(typeName);
    }

    private int checkName(String name, BigDecimal price, Long id) {
        LambdaQueryWrapper<PsyConsultServeConfig> wp = new LambdaQueryWrapper<>();
        wp.eq(PsyConsultServeConfig::getName, name);
        wp.eq(PsyConsultServeConfig::getPrice, price);
        wp.ne(id != null, PsyConsultServeConfig::getId, id);
        return psyConsultServeConfigMapper.selectCount(wp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(List<PsyConsultServeConfig> entities) {
        /*for (PsyConsultServeConfig entity : entities) {
            entity.setServiceObject(ComUtil.listToString(entity.getServiceObjectList()));
        }*/
        return this.saveBatch(entities);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBatch(List<PsyConsultServeConfig> entities) {
        return this.updateBatchById(entities);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(PsyConsultServeConfigVO req) {
        if (checkName(req.getName(), req.getPrice(), null) > 0) {
            throw new UtilException("服务已存在");
        }
        //若为单次[个人督导/个人体验], 查询是否存在该级别的相同服务
        String serviceObject = req.getServiceObject();
        Integer type = req.getType();
        if(type == 1 && (ServiceObjectEnum.PERSON_SUP.getKey().equals(serviceObject) || ServiceObjectEnum.PERSON_EXP.getKey().equals(serviceObject))){
            PsyConsultServeConfigReq newReq = new PsyConsultServeConfigReq();
            newReq.setLevel(req.getLevel());
            newReq.setType(1);
            newReq.setServiceObject(serviceObject);
            List<PsyConsultServeConfig> list = psyConsultServeConfigMapper.getList(newReq);
            if (ObjectUtils.isNotEmpty(list)){
                PsyConsultServeConfig old = list.get(0);
                throw new ServiceException("该级别已有服务对象为" + ServiceObjectEnum.fromKey(old.getServiceObject()).getValue() + "的单次服务条目, 不可重复添加");
            }
        }
        
        return psyConsultServeConfigMapper.insert(BeanUtil.toBean(req, PsyConsultServeConfig.class));
    }

    
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(PsyConsultServeConfigVO req) {
        if (checkName(req.getName(), req.getPrice(), req.getId()) > 0) {
            throw new UtilException("服务已存在");
        }
        
        //若为单次[个人督导/个人体验], 查询是否存在该级别的相同服务
        String serviceObject = req.getServiceObject();
        Integer type = req.getType();
        if(type == 1 && (ServiceObjectEnum.PERSON_SUP.getKey().equals(serviceObject) || ServiceObjectEnum.PERSON_EXP.getKey().equals(serviceObject))){
            PsyConsultServeConfigReq newReq = new PsyConsultServeConfigReq();
            newReq.setLevel(req.getLevel());
            newReq.setType(1);
            newReq.setServiceObject(serviceObject);
            List<PsyConsultServeConfig> list = psyConsultServeConfigMapper.getList(newReq);
            if (ObjectUtils.isNotEmpty(list) && !Objects.equals(list.get(0).getId(), req.getId())){
                PsyConsultServeConfig old = list.get(0);
                throw new ServiceException("[该级别]已有服务对象为[" + ServiceObjectEnum.fromKey(old.getServiceObject()).getValue() + "]的[单次服务]条目, 不可重复添加");
            }
        }
        
        //req.setServiceObject(ComUtil.listToString(req.getServiceObjectList()));
        return psyConsultServeConfigMapper.updateById(BeanUtil.toBean(req, PsyConsultServeConfig.class));
    }

    @Override
    public List<PsyConsultServeConfig> getListByIds(List<Long> ids) {
        LambdaQueryWrapper<PsyConsultServeConfig> wp = new LambdaQueryWrapper<>();
        wp.eq(PsyConsultServeConfig::getDelFlag, "0");
        wp.eq(PsyConsultServeConfig::getStatus, "0");
        wp.in(PsyConsultServeConfig::getId, ids);
        return psyConsultServeConfigMapper.selectList(wp);
    }

    @Override
    public int refConsultServe(PsyRefConsultServeReq req) {
        List<PsyConsultServeConfig> ids = getListByIds(req.getIds());
        if (CollectionUtils.isEmpty(ids) || ids.size() != req.getIds().size()) {
            return 0;
        }

        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean setRefNum(PsyRefConsultServeReq req) {
        // 找出咨询师相关服务,更新服务关联咨询师数量
        PsyConsultServe serve = new PsyConsultServe();
        serve.setConsultId(req.getConsultId());
        List<PsyConsultServe> serveList = psyConsultServeService.getList(serve);
        List<Long> collect = serveList.stream().map(PsyConsultServe::getServeId).collect(Collectors.toList());
        List<PsyConsultServeConfig> ids = getListByIds(collect);

        ids.forEach(a -> {
            PsyConsultServe pcs = new PsyConsultServe();
            pcs.setServeId(a.getId());
            List<PsyConsultServe> list = psyConsultServeService.getList(pcs);
            a.setRef(CollectionUtils.isEmpty(list) ? 0 : list.size());
        });

        return this.updateBatch(ids);
    }

    @Override
    public void updateNum(Long id) {
        PsyConsultServeConfig one = psyConsultServeConfigMapper.selectById(id);
        int i = one.getSales() + 1;
        one.setSales(Math.max(i, 0));
        psyConsultServeConfigMapper.updateById(one);
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAll(Long[] ids) {
        return psyConsultServeConfigMapper.tombstonedByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        return psyConsultServeConfigMapper.deleteById(id);
    }
}

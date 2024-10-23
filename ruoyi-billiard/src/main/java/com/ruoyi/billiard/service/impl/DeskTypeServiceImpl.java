package com.ruoyi.billiard.service.impl;

import java.util.List;

import com.ruoyi.billiard.domain.DeskPlace;
import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.billiard.mapper.StoreDeskMapper;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.domain.DeskType;
import com.ruoyi.billiard.service.IDeskTypeService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.mapper.DeskTypeMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 台桌类型Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-23
 */
@Service
public class DeskTypeServiceImpl extends ServiceImpl<DeskTypeMapper, DeskType> implements IDeskTypeService {


    @Resource
    private StoreDeskMapper storeDeskMapper;

    /**
     * 查询台桌类型
     *
     * @param deskTypeId 台桌类型主键
     * @return 台桌类型
     */
    @Override
    public DeskType selectDeskTypeByDeskTypeId(Long deskTypeId) {
        return baseMapper.selectById(deskTypeId);
    }

    /**
     * 查询台桌类型列表
     *
     * @param deskType 台桌类型
     * @return 台桌类型
     */
    @Override
    public List<DeskType> selectDeskTypeList(DeskType deskType) {
        return baseMapper.selectDeskTypeList(deskType);
    }

    /**
     * 新增台桌类型
     *
     * @param deskType 台桌类型
     * @return 结果
     */
    @Override
    public int insertDeskType(DeskType deskType) {
        AssertUtil.isTrue(!baseMapper.exists(baseMapper.query().eq(DeskType::getName, deskType.getName())
                .eq(DeskType::getStoreId, deskType.getStoreId())), "台桌类型重复");
        SecurityUtils.fillCreateUser(deskType);
        deskType.setDeskTypeId(IdUtils.singleNextId());
        return baseMapper.insert(deskType);
    }

    /**
     * 修改台桌类型
     *
     * @param deskType 台桌类型
     * @return 结果
     */
    @Override
    public int updateDeskType(DeskType deskType) {
        AssertUtil.isTrue(!baseMapper.exists(baseMapper.query().eq(DeskType::getName, deskType.getName())
                .eq(DeskType::getStoreId, deskType.getStoreId()).ne(DeskType::getDeskTypeId, deskType.getDeskTypeId())), "台桌类型重复");
        SecurityUtils.fillUpdateUser(deskType);

        return baseMapper.updateById(deskType);
    }

    /**
     * 批量删除台桌类型
     *
     * @param deskTypeIds 需要删除的台桌类型主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteDeskTypeByDeskTypeIds(Long[] deskTypeIds) {
        for (Long deskTypeId : deskTypeIds) {
            deleteDeskTypeByDeskTypeId(deskTypeId);
        }
        return deskTypeIds.length;
    }

    /**
     * 删除台桌类型信息
     *
     * @param deskTypeId 台桌类型主键
     * @return 结果
     */
    @Override
    public int deleteDeskTypeByDeskTypeId(Long deskTypeId) {
        AssertUtil.isTrue(!storeDeskMapper.exists(storeDeskMapper.query().eq(StoreDesk::getDeskType, deskTypeId)), "区域名称已被使用，无法删除");

        return baseMapper.deleteDeskTypeByDeskTypeId(deskTypeId);
    }
}

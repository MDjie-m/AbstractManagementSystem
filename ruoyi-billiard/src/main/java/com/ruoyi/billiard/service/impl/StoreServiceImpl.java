package com.ruoyi.billiard.service.impl;

import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.billiard.domain.StoreTutor;
import com.ruoyi.billiard.domain.StoreUser;
import com.ruoyi.billiard.mapper.StoreTutorMapper;
import com.ruoyi.billiard.mapper.StoreUserMapper;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.StoreMapper;
import com.ruoyi.billiard.domain.Store;
import com.ruoyi.billiard.service.IStoreService;

import javax.annotation.Resource;

/**
 * 门店Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-05
 */
@Service
public class StoreServiceImpl implements IStoreService 
{
    @Resource
    private StoreMapper storeMapper;

    @Resource
    private StoreUserMapper storeUserMapper;

    @Resource
    private StoreTutorMapper storeTutorMapper;



    /**
     * 查询门店
     * 
     * @param storeId 门店主键
     * @return 门店
     */
    @Override
    public Store selectStoreByStoreId(Long storeId)
    {
        return storeMapper.selectById(storeId);
    }

    /**
     * 查询门店列表
     * 
     * @param store 门店
     * @return 门店
     */
    @Override
    public List<Store> selectStoreList(Store store)
    {
        return storeMapper.selectStoreList(store);
    }

    /**
     * 新增门店
     * 
     * @param store 门店
     * @return 结果
     */
    @Override
    public int insertStore(Store store)
    {
        store.setStoreId(IdUtils.singleNextId());
        store.setCreateTime(DateUtils.getNowDate());
        return storeMapper.insert(store);
    }

    /**
     * 修改门店
     * 
     * @param store 门店
     * @return 结果
     */
    @Override
    public int updateStore(Store store)
    {
        store.setUpdateTime(DateUtils.getNowDate());
        return storeMapper.updateStore(store);
    }

    /**
     * 批量删除门店
     * 
     * @param storeIds 需要删除的门店主键
     * @return 结果
     */
    @Override
    public int deleteStoreByStoreIds(Long[] storeIds) {

        AssertUtil.isTrue(!storeUserMapper.existsIn(StoreUser::getStoreId, Arrays.asList(storeIds)), "门店还有员工，不能删除.");
        AssertUtil.isTrue(!storeTutorMapper.existsIn(StoreTutor::getStoreId, Arrays.asList(storeIds)), "门店还有教练，不能删除.");
        return storeMapper.deleteStoreByStoreIds(storeIds);
    }

    /**
     * 删除门店信息
     * 
     * @param storeId 门店主键
     * @return 结果
     */
    @Override
    public int deleteStoreByStoreId(Long storeId)
    {
        return storeMapper.deleteStoreByStoreId(storeId);
    }

    @Override
    public List<Store> selectAll() {
        return storeMapper.selectList(new LambdaQueryWrapper<>());
    }
}

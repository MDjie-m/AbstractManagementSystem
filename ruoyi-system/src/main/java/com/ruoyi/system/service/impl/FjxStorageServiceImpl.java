package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FjxStorageMapper;
import com.ruoyi.system.domain.FjxStorage;
import com.ruoyi.system.service.IFjxStorageService;

/**
 * 存放购物车Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
@Service
public class FjxStorageServiceImpl implements IFjxStorageService 
{
    @Autowired
    private FjxStorageMapper fjxStorageMapper;

    /**
     * 查询存放购物车
     * 
     * @param id 存放购物车主键
     * @return 存放购物车
     */
    @Override
    public FjxStorage selectFjxStorageById(String id)
    {
        return fjxStorageMapper.selectFjxStorageById(id);
    }

    /**
     * 查询存放购物车列表
     * 
     * @param fjxStorage 存放购物车
     * @return 存放购物车
     */
    @Override
    public List<FjxStorage> selectFjxStorageList(FjxStorage fjxStorage)
    {
        return fjxStorageMapper.selectFjxStorageList(fjxStorage);
    }

    /**
     * 新增存放购物车
     * 
     * @param fjxStorage 存放购物车
     * @return 结果
     */
    @Override
    public int insertFjxStorage(FjxStorage fjxStorage)
    {
        return fjxStorageMapper.insertFjxStorage(fjxStorage);
    }

    /**
     * 修改存放购物车
     * 
     * @param fjxStorage 存放购物车
     * @return 结果
     */
    @Override
    public int updateFjxStorage(FjxStorage fjxStorage)
    {
        return fjxStorageMapper.updateFjxStorage(fjxStorage);
    }

    /**
     * 批量删除存放购物车
     * 
     * @param ids 需要删除的存放购物车主键
     * @return 结果
     */
    @Override
    public int deleteFjxStorageByIds(String[] ids)
    {
        return fjxStorageMapper.deleteFjxStorageByIds(ids);
    }

    /**
     * 删除存放购物车信息
     * 
     * @param id 存放购物车主键
     * @return 结果
     */
    @Override
    public int deleteFjxStorageById(String id)
    {
        return fjxStorageMapper.deleteFjxStorageById(id);
    }
}

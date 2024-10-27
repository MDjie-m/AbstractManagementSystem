package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FjxCollectionMapper;
import com.ruoyi.system.domain.FjxCollection;
import com.ruoyi.system.service.IFjxCollectionService;

/**
 * 收藏Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
@Service
public class FjxCollectionServiceImpl implements IFjxCollectionService 
{
    @Autowired
    private FjxCollectionMapper fjxCollectionMapper;

    /**
     * 查询收藏
     * 
     * @param id 收藏主键
     * @return 收藏
     */
    @Override
    public FjxCollection selectFjxCollectionById(String id)
    {
        return fjxCollectionMapper.selectFjxCollectionById(id);
    }

    /**
     * 查询收藏列表
     * 
     * @param fjxCollection 收藏
     * @return 收藏
     */
    @Override
    public List<FjxCollection> selectFjxCollectionList(FjxCollection fjxCollection)
    {
        return fjxCollectionMapper.selectFjxCollectionList(fjxCollection);
    }

    /**
     * 新增收藏
     * 
     * @param fjxCollection 收藏
     * @return 结果
     */
    @Override
    public int insertFjxCollection(FjxCollection fjxCollection)
    {
        return fjxCollectionMapper.insertFjxCollection(fjxCollection);
    }

    /**
     * 修改收藏
     * 
     * @param fjxCollection 收藏
     * @return 结果
     */
    @Override
    public int updateFjxCollection(FjxCollection fjxCollection)
    {
        return fjxCollectionMapper.updateFjxCollection(fjxCollection);
    }

    /**
     * 批量删除收藏
     * 
     * @param ids 需要删除的收藏主键
     * @return 结果
     */
    @Override
    public int deleteFjxCollectionByIds(String[] ids)
    {
        return fjxCollectionMapper.deleteFjxCollectionByIds(ids);
    }

    /**
     * 删除收藏信息
     * 
     * @param id 收藏主键
     * @return 结果
     */
    @Override
    public int deleteFjxCollectionById(String id)
    {
        return fjxCollectionMapper.deleteFjxCollectionById(id);
    }
}

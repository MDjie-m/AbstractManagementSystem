package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FjxCollection;

/**
 * 收藏Service接口
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
public interface IFjxCollectionService 
{
    /**
     * 查询收藏
     * 
     * @param id 收藏主键
     * @return 收藏
     */
    public FjxCollection selectFjxCollectionById(String id);

    /**
     * 查询收藏列表
     * 
     * @param fjxCollection 收藏
     * @return 收藏集合
     */
    public List<FjxCollection> selectFjxCollectionList(FjxCollection fjxCollection);

    /**
     * 新增收藏
     * 
     * @param fjxCollection 收藏
     * @return 结果
     */
    public int insertFjxCollection(FjxCollection fjxCollection);

    /**
     * 修改收藏
     * 
     * @param fjxCollection 收藏
     * @return 结果
     */
    public int updateFjxCollection(FjxCollection fjxCollection);

    /**
     * 批量删除收藏
     * 
     * @param ids 需要删除的收藏主键集合
     * @return 结果
     */
    public int deleteFjxCollectionByIds(String[] ids);

    /**
     * 删除收藏信息
     * 
     * @param id 收藏主键
     * @return 结果
     */
    public int deleteFjxCollectionById(String id);
}

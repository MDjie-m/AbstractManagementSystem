package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FjxStorage;

/**
 * 存放购物车Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
public interface FjxStorageMapper 
{
    /**
     * 查询存放购物车
     * 
     * @param id 存放购物车主键
     * @return 存放购物车
     */
    public FjxStorage selectFjxStorageById(String id);

    /**
     * 查询存放购物车列表
     * 
     * @param fjxStorage 存放购物车
     * @return 存放购物车集合
     */
    public List<FjxStorage> selectFjxStorageList(FjxStorage fjxStorage);

    /**
     * 新增存放购物车
     * 
     * @param fjxStorage 存放购物车
     * @return 结果
     */
    public int insertFjxStorage(FjxStorage fjxStorage);

    /**
     * 修改存放购物车
     * 
     * @param fjxStorage 存放购物车
     * @return 结果
     */
    public int updateFjxStorage(FjxStorage fjxStorage);

    /**
     * 删除存放购物车
     * 
     * @param id 存放购物车主键
     * @return 结果
     */
    public int deleteFjxStorageById(String id);

    /**
     * 批量删除存放购物车
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFjxStorageByIds(String[] ids);
}

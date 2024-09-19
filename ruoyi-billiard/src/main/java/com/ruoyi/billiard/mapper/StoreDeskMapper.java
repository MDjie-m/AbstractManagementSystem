package com.ruoyi.billiard.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ruoyi.billiard.domain.Store;
import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.billiard.domain.vo.CashierDeskDashboardResVo;
import com.ruoyi.common.core.domain.model.KeyValueVo;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 球桌Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-07
 */
@Mapper
public interface StoreDeskMapper extends MyBaseMapper<StoreDesk>
{
    /**
     * 查询球桌
     * 
     * @param deskId 球桌主键
     * @return 球桌
     */
    public StoreDesk selectStoreDeskByDeskId(Long deskId);

    /**
     * 查询球桌列表
     * 
     * @param storeDesk 球桌
     * @return 球桌集合
     */
    public List<StoreDesk> selectStoreDeskList(@Param(Constants.WRAPPER) QueryWrapper<StoreDesk> queryWrapper);


    /**
     * 删除球桌
     * 
     * @param deskId 球桌主键
     * @return 结果
     */
    public int deleteStoreDeskByDeskId(Long deskId);

    /**
     * 批量删除球桌
     * 
     * @param deskIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStoreDeskByDeskIds(Long[] deskIds);

    List<Store> selectStoreByLoginUserId(Long loginUserId);

    Integer checkDeviceBind( @Param("deviceId")Long deviceId,@Param("deskId") Long deskId);

    List<KeyValueVo<Integer, Long>> queryDeskCountGroupByStatus(Long storeId);

    Integer deskInUse(@Param("deskId") Long deskId);

    List<StoreDesk> queryBusyDeskByOrderId(@Param("orderId") Long orderId);
}

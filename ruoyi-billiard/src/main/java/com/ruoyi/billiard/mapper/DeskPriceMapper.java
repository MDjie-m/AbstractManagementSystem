package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.DeskPrice;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 球桌价格Mapper接口
 * 
 * @author zhoukeu
 * @date 2024-09-14
 */
@Repository
public interface DeskPriceMapper extends MyBaseMapper<DeskPrice>
{
    /**
     * 查询球桌价格
     * 
     * @param deskPriceId 球桌价格主键
     * @return 球桌价格
     */
    public DeskPrice selectDeskPriceByDeskPriceId(Long deskPriceId);

    /**
     * 查询球桌价格列表
     * 
     * @param deskPrice 球桌价格
     * @return 球桌价格集合
     */
    public List<DeskPrice> selectDeskPriceList(DeskPrice deskPrice);


    /**
     * 删除球桌价格
     * 
     * @param deskPriceId 球桌价格主键
     * @return 结果
     */
    public int deleteDeskPriceByDeskPriceId(Long deskPriceId);

    /**
     * 批量删除球桌价格
     * 
     * @param deskPriceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeskPriceByDeskPriceIds(Long[] deskPriceIds);
}

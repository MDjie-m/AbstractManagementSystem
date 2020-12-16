package com.ruoyi.hotel.mapper;

import java.util.List;
import com.ruoyi.hotel.domain.HtlDisplayTheme;

/**
 * 主题配置Mapper接口
 * 
 * @author sucheng
 * @date 2020-11-22
 */
public interface HtlDisplayThemeMapper 
{
    /**
     * 查询主题配置
     * 
     * @param hotelId 主题配置ID
     * @return 主题配置
     */
    public HtlDisplayTheme selectHtlDisplayThemeById(Long hotelId);

    /**
     * 查询主题配置列表
     * 
     * @param htlDisplayTheme 主题配置
     * @return 主题配置集合
     */
    public List<HtlDisplayTheme> selectHtlDisplayThemeList(HtlDisplayTheme htlDisplayTheme);

    /**
     * 新增主题配置
     * 
     * @param htlDisplayTheme 主题配置
     * @return 结果
     */
    public int insertHtlDisplayTheme(HtlDisplayTheme htlDisplayTheme);

    /**
     * 修改主题配置
     * 
     * @param htlDisplayTheme 主题配置
     * @return 结果
     */
    public int updateHtlDisplayTheme(HtlDisplayTheme htlDisplayTheme);

    /**
     * 删除主题配置
     * 
     * @param hotelId 主题配置ID
     * @return 结果
     */
    public int deleteHtlDisplayThemeById(Long hotelId);

    /**
     * 批量删除主题配置
     * 
     * @param hotelIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteHtlDisplayThemeByIds(Long[] hotelIds);
}

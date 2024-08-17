package com.renxin.advert.mapper;


import com.renxin.advert.domain.PsyAdvert;

import java.util.List;

/**
 * 页面广告Mapper接口
 * 
 * @author renxin
 * @date 2024-08-16
 */
public interface PsyAdvertMapper 
{
    /**
     * 查询页面广告
     * 
     * @param advertNo 页面广告主键
     * @return 页面广告
     */
    public PsyAdvert selectPsyAdvertByAdvertNo(String advertNo);

    /**
     * 查询页面广告列表
     * 
     * @param psyAdvert 页面广告
     * @return 页面广告集合
     */
    public List<PsyAdvert> selectPsyAdvertList(PsyAdvert psyAdvert);

    /**
     * 新增页面广告
     * 
     * @param psyAdvert 页面广告
     * @return 结果
     */
    public int insertPsyAdvert(PsyAdvert psyAdvert);

    /**
     * 修改页面广告
     * 
     * @param psyAdvert 页面广告
     * @return 结果
     */
    public int updatePsyAdvert(PsyAdvert psyAdvert);

    /**
     * 删除页面广告
     * 
     * @param advertNo 页面广告主键
     * @return 结果
     */
    public int deletePsyAdvertByAdvertNo(String advertNo);

    /**
     * 批量删除页面广告
     * 
     * @param advertNos 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyAdvertByAdvertNos(String[] advertNos);
}

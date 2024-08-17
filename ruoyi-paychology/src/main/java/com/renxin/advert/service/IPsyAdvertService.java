package com.renxin.advert.service;


import com.renxin.advert.domain.PsyAdvert;
import com.renxin.common.core.domain.AjaxResult;

import java.util.List;

/**
 * 页面广告Service接口
 * 
 * @author renxin
 * @date 2024-08-16
 */
public interface IPsyAdvertService 
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
     * 批量删除页面广告
     * 
     * @param advertNos 需要删除的页面广告主键集合
     * @return 结果
     */
    public int deletePsyAdvertByAdvertNos(String[] advertNos);

    /**
     * 删除页面广告信息
     * 
     * @param advertNo 页面广告主键
     * @return 结果
     */
    public int deletePsyAdvertByAdvertNo(String advertNo);

    /**
     * 查询各类型的对象清单
     */
    public AjaxResult queryObjectByIds(PsyAdvert req);
}

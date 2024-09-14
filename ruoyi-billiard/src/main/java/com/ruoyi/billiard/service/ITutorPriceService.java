package com.ruoyi.billiard.service;

import java.util.List;
import com.ruoyi.billiard.domain.TutorPrice;

/**
 * 教练价格Service接口
 * 
 * @author zhoukeu
 * @date 2024-09-14
 */
public interface ITutorPriceService 
{
    /**
     * 查询教练价格
     * 
     * @param tutorPriceId 教练价格主键
     * @return 教练价格
     */
    public TutorPrice selectTutorPriceByTutorPriceId(Long tutorPriceId);

    /**
     * 查询教练价格列表
     * 
     * @param tutorPrice 教练价格
     * @return 教练价格集合
     */
    public List<TutorPrice> selectTutorPriceList(TutorPrice tutorPrice);

    /**
     * 新增教练价格
     * 
     * @param tutorPrice 教练价格
     * @return 结果
     */
    public int insertTutorPrice(TutorPrice tutorPrice);

    /**
     * 修改教练价格
     * 
     * @param tutorPrice 教练价格
     * @return 结果
     */
    public int updateTutorPrice(TutorPrice tutorPrice);

    /**
     * 批量删除教练价格
     * 
     * @param tutorPriceIds 需要删除的教练价格主键集合
     * @return 结果
     */
    public int deleteTutorPriceByTutorPriceIds(Long[] tutorPriceIds);

    /**
     * 删除教练价格信息
     * 
     * @param tutorPriceId 教练价格主键
     * @return 结果
     */
    public int deleteTutorPriceByTutorPriceId(Long tutorPriceId);
}

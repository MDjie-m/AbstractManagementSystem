package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.TutorPrice;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 教练价格Mapper接口
 * 
 * @author zhoukeu
 * @date 2024-09-14
 */
@Mapper
public interface TutorPriceMapper extends MyBaseMapper<TutorPrice>
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
     * 删除教练价格
     * 
     * @param tutorPriceId 教练价格主键
     * @return 结果
     */
    public int deleteTutorPriceByTutorPriceId(Long tutorPriceId);

    /**
     * 批量删除教练价格
     * 
     * @param tutorPriceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTutorPriceByTutorPriceIds(Long[] tutorPriceIds);
}

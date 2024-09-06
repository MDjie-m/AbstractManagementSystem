package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.StoreTutor;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 门店助教Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-06
 */
@Mapper
public interface StoreTutorMapper extends MyBaseMapper<StoreTutor>
{
    /**
     * 查询门店助教
     * 
     * @param storeTutorId 门店助教主键
     * @return 门店助教
     */
    public StoreTutor selectStoreTutorByStoreTutorId(Long storeTutorId);

    /**
     * 查询门店助教列表
     * 
     * @param storeTutor 门店助教
     * @return 门店助教集合
     */
    public List<StoreTutor> selectStoreTutorList(StoreTutor storeTutor);

    /**
     * 新增门店助教
     * 
     * @param storeTutor 门店助教
     * @return 结果
     */
    public int insertStoreTutor(StoreTutor storeTutor);

    /**
     * 修改门店助教
     * 
     * @param storeTutor 门店助教
     * @return 结果
     */
    public int updateStoreTutor(StoreTutor storeTutor);

    /**
     * 删除门店助教
     * 
     * @param storeTutorId 门店助教主键
     * @return 结果
     */
    public int deleteStoreTutorByStoreTutorId(Long storeTutorId);

    /**
     * 批量删除门店助教
     * 
     * @param storeTutorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStoreTutorByStoreTutorIds(Long[] storeTutorIds);
}

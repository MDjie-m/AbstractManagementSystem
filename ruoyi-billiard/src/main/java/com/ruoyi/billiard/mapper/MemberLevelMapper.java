package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.MemberLevel;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 门店会员等级Mapper接口
 * 
 * @author zhoukeu
 * @date 2024-09-14
 */
@Mapper
public interface MemberLevelMapper extends MyBaseMapper<MemberLevel>
{
    /**
     * 查询门店会员等级
     * 
     * @param memberLevelId 门店会员等级主键
     * @return 门店会员等级
     */
    public MemberLevel selectMemberLevelByMemberLevelId(Long memberLevelId);

    /**
     * 查询门店会员等级列表
     * 
     * @param memberLevel 门店会员等级
     * @return 门店会员等级集合
     */
    public List<MemberLevel> selectMemberLevelList(MemberLevel memberLevel);

    /**
     * 新增门店会员等级
     * 
     * @param memberLevel 门店会员等级
     * @return 结果
     */
    public int insertMemberLevel(MemberLevel memberLevel);

    /**
     * 修改门店会员等级
     * 
     * @param memberLevel 门店会员等级
     * @return 结果
     */
    public int updateMemberLevel(MemberLevel memberLevel);

    /**
     * 删除门店会员等级
     * 
     * @param memberLevelId 门店会员等级主键
     * @return 结果
     */
    public int deleteMemberLevelByMemberLevelId(Long memberLevelId);

    /**
     * 批量删除门店会员等级
     * 
     * @param memberLevelIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMemberLevelByMemberLevelIds(Long[] memberLevelIds);
}

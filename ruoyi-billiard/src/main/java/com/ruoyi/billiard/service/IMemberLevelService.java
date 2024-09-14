package com.ruoyi.billiard.service;

import java.util.List;
import com.ruoyi.billiard.domain.MemberLevel;

/**
 * 门店会员等级Service接口
 * 
 * @author zhoukeu
 * @date 2024-09-14
 */
public interface IMemberLevelService 
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
     * 批量删除门店会员等级
     * 
     * @param memberLevelIds 需要删除的门店会员等级主键集合
     * @return 结果
     */
    public int deleteMemberLevelByMemberLevelIds(Long[] memberLevelIds);

    /**
     * 删除门店会员等级信息
     * 
     * @param memberLevelId 门店会员等级主键
     * @return 结果
     */
    public int deleteMemberLevelByMemberLevelId(Long memberLevelId);

    /**
     * 查询会员等级是否被引用
     * @param memberLevelId
     * @return
     */
    Boolean isUsed(Long memberLevelId);
}

package com.renxin.psychology.service;

import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.common.core.domain.vo.LoginVO;
import com.renxin.psychology.domain.PsyUser;
import com.renxin.psychology.request.VisitorDetailReq;

/**
 * 用户Service接口
 *
 * @author renxin
 * @date 2022-08-26
 */
public interface IPsyUserService {
    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    public PsyUser selectPsyUserById(Long id);

    /**
     * 查询用户列表
     *
     * @param psyUser 用户
     * @return 用户集合
     */
    public List<PsyUser> selectPsyUserList(PsyUser psyUser);

    /**
     * 新增用户
     *
     * @param psyUser 用户
     * @return 结果
     */
    public int insertPsyUser(PsyUser psyUser);

    /**
     * 修改用户
     *
     * @param psyUser 用户
     * @return 结果
     */
    public int updatePsyUser(PsyUser psyUser);

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的用户主键集合
     * @return 结果
     */
    public int deletePsyUserByIds(Long[] ids);

    /**
     * 删除用户信息
     *
     * @param id 用户主键
     * @return 结果
     */
    public int deletePsyUserById(Long id);

    PsyUser queryUserByAccount(String account);

    LoginVO checkPsyUser(String openId, JSONObject userInfo);

    void bindPhone(LoginDTO loginDTO);

    /**
     * 查询用户详情
     */
    public PsyUser queryUserDetail(VisitorDetailReq req);
}

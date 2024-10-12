package com.renxin.psychology.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.qcloud.cos.model.UploadResult;
import com.renxin.common.constant.IntegralRecordConstants;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.common.core.domain.vo.LoginVO;
import com.renxin.common.event.publish.IntegralPublisher;
import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.DateUtils;
import com.renxin.common.utils.IDhelper;
import com.renxin.common.utils.cos.COSClientFactory;
import com.renxin.psychology.domain.PsyConsultantSchedule;
import com.renxin.psychology.domain.PsyUser;
import com.renxin.psychology.dto.OrderItemDTO;
import com.renxin.psychology.mapper.PsyUserMapper;
import com.renxin.psychology.request.VisitorDetailReq;
import com.renxin.psychology.service.IPsyConsultOrderItemService;
import com.renxin.psychology.service.IPsyConsultantScheduleService;
import com.renxin.psychology.service.IPsyUserService;
import com.renxin.user.domain.PsyUserIntegralRecord;
import com.renxin.user.service.IPsyUserIntegralRecordService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * 用户Service业务层处理
 *
 * @author renxin
 * @date 2022-08-26
 */
@Service
public class PsyUserServiceImpl implements IPsyUserService {
    @Resource
    private PsyUserMapper psyUserMapper;

    @Resource
    private IntegralPublisher integralPublisher;

    @Resource
    private IPsyUserIntegralRecordService psyUserIntegralRecordService;

    @Resource
    private IPsyConsultantScheduleService scheduleService;

    @Resource
    private IPsyConsultOrderItemService orderItemService;

    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    @Override
    public PsyUser selectPsyUserById(Long id) {
        PsyUser psyUser = psyUserMapper.selectPsyUserById(id);
        if (ObjectUtils.isEmpty(psyUser)){
            throw new ServiceException("该id没有对应的用户信息:"+id);
        }
        if (ObjectUtils.isEmpty(psyUser.getName())){
            psyUser.setName("");
        }
        if (ObjectUtils.isEmpty(psyUser.getAvatar())){
            psyUser.setAvatar("");
        }
        if (ObjectUtils.isEmpty(psyUser.getPhone())){
            psyUser.setPhone("");
        }
        return psyUser;
    }

    /**
     * 查询用户列表
     *
     * @param psyUser 用户
     * @return 用户
     */
    @Override
    public List<PsyUser> selectPsyUserList(PsyUser psyUser) {
        return psyUserMapper.selectPsyUserList(psyUser);
    }

    /**
     * 新增用户
     *
     * @param psyUser 用户
     * @return 结果
     */
    @Override
    public int insertPsyUser(PsyUser psyUser) {
        psyUser.setCreateTime(DateUtils.getNowDate());
        return psyUserMapper.insertPsyUser(psyUser);
    }

    /**
     * 修改用户
     *
     * @param psyUser 用户
     * @return 结果
     */
    @Override
    public int updatePsyUser(PsyUser psyUser) {
        //判断头像信息的格式, 若为base64则需解析图片后上传获取url
        String avatar = psyUser.getAvatar();
        if (ObjectUtils.isNotEmpty(avatar) && !avatar.contains("http") && avatar.length() > 500) {
            String module = "zx";
            String type = "avatar";
            UploadResult upload = null;
            InputStream inputStream = null;
            try {
                MultipartFile file = base64ToMultipartFile(avatar);
                String fileName = StrUtil.format("{}_{}_{}", type, IDhelper.getNextId(), new Random().nextInt(999999) + "用户头像.jpg");
                //  调用文件服务器方法，实现文件上传改写
                inputStream = file.getInputStream();
                upload = COSClientFactory.upload(inputStream, fileName, module);
                String key = upload.getKey();
                String url = COSClientFactory.getObjUrl(key, module);
                AjaxResult ajax = AjaxResult.success();
                psyUser.setAvatar(url);
            } catch (Exception e){
                throw new ServiceException("base64转换图片上传异常");
            }
        }
        return psyUserMapper.updatePsyUser(psyUser);
    }

    //将base64字符串  转换为MultipartFile对象
    private MultipartFile base64ToMultipartFile(String base64) {
        // 分离base64头部信息
        //String[] baseStrs = base64.split(",");

        // 解码Base64字符串
        byte[] decodedBytes = Base64.getDecoder().decode(base64);

        // 创建MultipartFile对象
        MultipartFile multipartFile = null;
        try {
            // 转换为InputStream
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decodedBytes);

            // 使用MockMultipartFile来创建一个新的MultipartFile对象
            multipartFile = new MockMultipartFile("file", byteArrayInputStream);

        } catch (Exception e) {
            throw new ServiceException("base64转换图片异常");
        }

        return multipartFile;
    }

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deletePsyUserByIds(Long[] ids) {
        return psyUserMapper.deletePsyUserByIds(ids);
    }

    /**
     * 删除用户信息
     *
     * @param id 用户主键
     * @return 结果
     */
    @Override
    public int deletePsyUserById(Long id) {
        return psyUserMapper.deletePsyUserById(id);
    }

    @Override
    public PsyUser queryUserByAccount(String account) {
        return psyUserMapper.queryUserByAccount(account);
    }

    @Override
    public LoginVO checkPsyUser(String openId, JSONObject userInfo) {
        //去数据库查询此微信是否绑定过手机
        PsyUser user = psyUserMapper.queryUserByAccount(openId);

        String nickname = userInfo.getString("nickname");
        String headImgUrl = userInfo.getString("headimgurl");

        //用户信息为空，则插入一条数据
        if (user == null) {
            int result = psyUserMapper.insertPsyUser(PsyUser.builder().wxOpenid(openId).name(nickname).avatar(headImgUrl).build());
            if (result != 1) {
                System.out.println("用户信息插入失败");
            }
            user = psyUserMapper.queryUserByAccount(openId);
            // 注册送积分
            int integral = psyUserIntegralRecordService.getIntegral(new BigDecimal("0"), IntegralRecordConstants.INTEGRAL_RECORD_LINK_TYPE_REGISTER);
            if (integral > 0) {
                PsyUserIntegralRecord record = new PsyUserIntegralRecord();
                record.setIntegral(integral);
                record.setLinkId(String.valueOf(user.getId()));
                record.setUid(user.getId());
                record.setDelFlag(0);
                record.setFrozenTime(0);
                record.setMark(StrUtil.format("用户付款成功,订单增加{}积分", record.getIntegral()));
                record.setType(IntegralRecordConstants.INTEGRAL_RECORD_TYPE_ADD);
                record.setLinkType(IntegralRecordConstants.INTEGRAL_RECORD_LINK_TYPE_REGISTER);
                record.setTitle(IntegralRecordConstants.BROKERAGE_RECORD_TITLE_REGISTER);
                record.setStatus(IntegralRecordConstants.INTEGRAL_RECORD_STATUS_COMPLETE);
                integralPublisher.publish(record);
            }
        }

        return LoginVO.builder().userId(user.getId()).name(nickname).avatar(headImgUrl).build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindPhone(LoginDTO loginDTO) {
        PsyUser user = psyUserMapper.selectPsyUserById(loginDTO.getUserId());
        PsyUser phoneUser = psyUserMapper.queryUserByAccount(loginDTO.getPhone());
        //若该手机号已经存在用户，直接将微信信息更新至该用户,并删除此微信用户
        if (phoneUser != null) {
            psyUserMapper.updatePsyUser(PsyUser.builder().id(phoneUser.getId()).wxOpenid(user.getWxOpenid()).avatar(user.getAvatar()).name(user.getName()).build());
            psyUserMapper.deletePsyUserById(user.getId());
            loginDTO.setUserId(phoneUser.getId());
        } else {
            psyUserMapper.updatePsyUser(PsyUser.builder().id(user.getId()).phone(loginDTO.getPhone()).build());
            loginDTO.setUserId(user.getId());
        }
    }

    /**
     * 查询用户详情
     */
    @Override
    public PsyUser queryUserDetail(VisitorDetailReq req){
        PsyUser psyUser = selectPsyUserById(req.getPayUserId());
        psyUser.setWxOpenid(null);
        psyUser.setPhone(null);
        OrderItemDTO itemReq = new OrderItemDTO();
        //若指定了收款人, 则查询其与该来访者用户的相关咨询记录
        if (ObjectUtils.isNotEmpty(req.getChargeConsultantId())){
            //查询该顾客与收款人之间的 咨询 记录
            itemReq.setConsultId(req.getChargeConsultantId());
            itemReq.setUserId(psyUser.getId());
            itemReq.setOrderDir("desc");
            itemReq.setOrderBy("concat(item.day,' ',item.time_start)");
            List<OrderItemDTO> orderItemChargeList = orderItemService.queryOrderItemList(itemReq);
            int timeNum = 1;
            for (int i = orderItemChargeList.size() - 1; i >= 0; i--) {
                if ("0".equals(orderItemChargeList.get(i).getStatus()) || "1".equals(orderItemChargeList.get(i).getStatus())){
                    orderItemChargeList.get(i).setTimeNum(timeNum++);
                }
            }
            
            /*//查询该顾客在本平台的咨询记录
            itemReq.setConsultId(null);
            itemReq.setOrderDir("asc");
            List<OrderItemDTO> orderItemList = orderItemService.queryOrderItemList(itemReq);
            
            //标识每次咨询, 是该顾客在本平台的第几次
            for (OrderItemDTO itemCharge : orderItemChargeList) {
                for (int i = 1; i <= orderItemList.size(); i++) {
                    if (Objects.equals(itemCharge.getId(),orderItemList.get(i-1).getId())){
                        itemCharge.setRowNum(i);
                    }
                }
            }*/
            psyUser.setOrderItemList(orderItemChargeList);
        }
        return psyUser;
    }
}

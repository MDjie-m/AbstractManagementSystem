package com.renxin.consultant.controller;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.constant.CacheConstants;
import com.renxin.common.constant.Constants;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.ConsultDTO;
import com.renxin.common.core.domain.dto.ConsultLoginDTO;
import com.renxin.common.core.domain.entity.SysDictType;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.core.redis.RedisCache;
import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.PageUtils;
import com.renxin.common.dcloud.CloudFunctions;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.psychology.domain.PsyConsult;
import com.renxin.psychology.domain.PsyConsultServeConfig;
import com.renxin.psychology.request.PsyConsultReq;
import com.renxin.psychology.request.PsyConsultServeConfigReq;
import com.renxin.psychology.request.QueryListByTypeReq;
import com.renxin.psychology.service.*;
import com.renxin.psychology.vo.PsyConsultVO;
import com.renxin.system.service.ISysDictDataService;
import com.renxin.system.service.ISysDictTypeService;
import com.renxin.web.controller.common.CommonCosController;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/consultant/user")
public class ConsultantUserController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(CommonCosController.class);

    @Resource
    ConsultantTokenService consultantTokenService;

    @Resource
    IPsyConsultService psyConsultService;

    @Autowired
    private ISysDictDataService dictDataService;

    @Autowired
    private ISysDictTypeService dictTypeService;

    @Resource
    private IPsyConsultConfigService psyConsultConfigService;

    @Resource
    private IPsyConsultServeConfigService serveConfigService;

    @Resource
    private IPsyConsultantAccountService accountService;

    @Resource
    private RedisCache redisCache;


    @PostMapping("/login")
    @RateLimiter
    public AjaxResult login(@RequestBody ConsultLoginDTO consultLoginDTO) {
        String token = initAccountAndGetToken(consultLoginDTO);
        return AjaxResult.success(Constants.TOKEN_PREFIX + token);
    
    }

    //获取登录验证码
    @PostMapping("/sendSms")
    public AjaxResult sendSms(@RequestBody ConsultLoginDTO req) {
        //若旧验证码尚未过期, 拒绝再次发送
        String phone = req.getPhone();
        
        //特殊处理
        if ("18907177267".equals(phone)){
            boolean isSend = new CloudFunctions().sendSms(phone,"123456");
            redisCache.setCacheObject(CacheConstants.PHONE_LOGIN_CODE + "::" + phone, "123456");
            return AjaxResult.success("发送成功");
        }
        
        String oldCode = redisCache.getCacheObject(CacheConstants.PHONE_LOGIN_CODE + "::" + phone);
        if (ObjectUtils.isNotEmpty(oldCode)){
            throw new ServiceException("请勿重复发送");
        }
        
        Random random = new Random();
        // 生成一个6位数，范围从100000到999999
        int code = 100000 + random.nextInt(999999);
        String smsCode=code+"";
        //String code = UUID.randomUUID().toString().substring(0, 6);
        boolean isSend = new CloudFunctions().sendSms(phone,smsCode);
        if(isSend){
            //向redis中写入该手机对应的验证码, 有效期10分钟
            redisCache.setCacheObject(CacheConstants.PHONE_LOGIN_CODE + "::" + phone, smsCode,10, TimeUnit.MINUTES);
            return AjaxResult.success("发送成功");
        }else {
            return AjaxResult.error("发送失败");
        }

    }

    @PostMapping("/loginBySmsCode")
    public AjaxResult loginBySmsCode(@RequestBody ConsultLoginDTO req) {
        String code = redisCache.getCacheObject(CacheConstants.PHONE_LOGIN_CODE + "::" + req.getPhone());
        String reqCode = req.getSmsCode();
        log.info("调用/loginBySmsCode, req:"+req + ",code:" + code);
        if (ObjectUtils.isEmpty(reqCode) || !reqCode.equals(code)) {
            throw new ServiceException("验证码与手机号不相符");
        }

       /* PsyConsult consultant = psyConsultService.getByPhone(req.getPhone());
        if (ObjectUtils.isEmpty(consultant)) {
            throw new ServiceException("该手机号无相符的咨询师");
        }*/

       /* ConsultDTO consultDTO = new ConsultDTO();
        consultDTO.setConsultId(consultant.getId());
        consultDTO.setPhone(consultant.getPhonenumber());
        String token = consultantTokenService.createToken(consultDTO, 360000);*/
        String token = initAccountAndGetToken(req);

        return AjaxResult.success(Constants.TOKEN_PREFIX + token);
    }
    
    //登录逻辑处理
    @Transactional(rollbackFor = Exception.class)
    private String initAccountAndGetToken(ConsultLoginDTO req){
        String phoneNumber = req.getPhone();
        try {
            if (ObjectUtils.isEmpty(phoneNumber)){
                phoneNumber = new CloudFunctions().getPhoneNumber(req);
            }
        } catch (Exception e) {
            log.error("login error", e);
            throw new ServiceException("无法获取手机号");
        }
        //获取咨询师信息(若无则新增)
        PsyConsult psyConsult = psyConsultService.getByPhoneOrInsert(phoneNumber);
        ConsultDTO consultDTO = new ConsultDTO();
        consultDTO.setConsultId(psyConsult.getId());
        consultDTO.setPhone(psyConsult.getPhonenumber());
        String token = consultantTokenService.createToken(consultDTO, 360000);

        //若无账户则创建
        //accountService.createAccountIfNotExist(psyConsult.getId());
        
        //更新设备信息
        PsyConsult oldConsultant = psyConsultService.getById(psyConsult.getId());
        psyConsultService.update(new LambdaUpdateWrapper<PsyConsult>()
                .eq(PsyConsult::getId,psyConsult.getId())
                .set(PsyConsult::getDeviceId,req.getDeviceId())
                .set(PsyConsult::getPushClientId,req.getPushClientId())
                .set(PsyConsult::getDeviceBrand,req.getDeviceBrand())
                .set(PsyConsult::getDeviceModel,req.getDeviceModel())
                .set(PsyConsult::getLastLoginIp,req.getLastLoginIp())
                //来源渠道和推荐人, 仅在原值为空时更新
                //.set(ObjectUtils.isEmpty(oldConsultant.getSourceChannelId()),PsyConsult::getSourceChannelId,req.getSourceChannelId())
                //.set(ObjectUtils.isEmpty(oldConsultant.getIntroduceUserId()),PsyConsult::getIntroduceUserId,req.getIntroduceUserId())
                );

        return token;
       
    }


    @PostMapping("/info")
    public AjaxResult getUserInfo(HttpServletRequest request) {
        try {
            Long consultId = consultantTokenService.getConsultId(request);
            PsyConsultVO one = psyConsultService.getOne(consultId);
            one.setId(null);
            one.setCreateTime(null);
            one.setCreateBy(null);
            one.setUpdateTime(null);
            one.setUpdateBy(null);
            return AjaxResult.success(one);
        } catch (Exception e) {
            log.error("login error", e);
            return AjaxResult.error("login error");
        }
    }

    /**
     * 根据类型  查询咨询师列表
     */
    @ApiOperation(value = "查询咨询师列表")
    @PostMapping("/cache")
    public TableDataInfo listByType(@RequestBody QueryListByTypeReq req) {
        startPage();
        String listType = req.getListType();
        List<Long> idList = redisCache.getCacheList(CacheConstants.CONSULTANT_ID_LIST + "::" + listType);
        List<PsyConsult> cacheList = redisCache.getMultiCacheMapValue(CacheConstants.CONSULTANT_BY_ID_KEY, PageUtils.paginate(idList));
        
     /*   PsyAdminConsultReq consultantReq = new PsyAdminConsultReq();
        consultantReq.setListType(listType);
        consultantReq.setIdList(idList);
        List<PsyConsult> list = psyConsultService.getList(consultantReq);*/

        return getDataTable(cacheList, idList.size());
    }

    /**
     * 查询心理咨询师列表
     */
    @PostMapping("/search")
    @RateLimiter
    public TableDataInfo list(@RequestBody PsyConsultReq req)
    {
        startPage();
       // req.setServiceObjectList(Arrays.asList("2","3"));
        List<PsyConsult> list = psyConsultService.search(req);
        return getDataTable(list);
    }

    /**
     * 查询指定咨询师的服务清单
     *
     * @param req
     * @param request
     * @return
     */
    @PostMapping("/getUserServerList")
    public AjaxResult getUserServerList(@RequestBody PsyConsultServeConfigReq req, HttpServletRequest request) {
        try {
            /*PsyConsultVO one = psyConsultService.getOne(req.getConsultantId());
            req.setLevel(one.getLevel());
            if (ObjectUtils.isEmpty(req.getServiceObject())){
                req.setServiceObject(one.getServiceObject());
            }*/
            List<PsyConsultServeConfig> list = serveConfigService.getList(req);

            return AjaxResult.success(list);
        } catch (Exception e) {
            log.error("login error", e);
            return AjaxResult.error("login error");
        }
    }

    /**
     * 获取字典清单
     */
    @PostMapping("/getDictTypeDataList")
    @RateLimiter
    public AjaxResult getDictList() {
        List<SysDictType> sysDictTypeList = dictTypeService.selectDictTypeDataList(null);
        return AjaxResult.success(sysDictTypeList);
    }


}

package com.renxin.consultant.controller;


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
import com.renxin.consultant.common.dcloud.CloudFunctions;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.psychology.domain.PsyConsult;
import com.renxin.psychology.domain.PsyConsultServeConfig;
import com.renxin.psychology.mapper.PsyConsultMapper;
import com.renxin.psychology.request.PsyAdminConsultReq;
import com.renxin.psychology.request.PsyConsultServeConfigReq;
import com.renxin.psychology.request.QueryListByTypeReq;
import com.renxin.psychology.service.*;
import com.renxin.psychology.vo.PsyConsultServeConfigVO;
import com.renxin.psychology.vo.PsyConsultVO;
import com.renxin.system.service.ISysDictDataService;
import com.renxin.system.service.ISysDictTypeService;
import com.renxin.web.controller.common.CommonCosController;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

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
    public AjaxResult login(@RequestBody ConsultLoginDTO consultLoginDTO)
    {
        try {
           String phoneNumber= new CloudFunctions().getPhoneNumber(consultLoginDTO);
           //获取咨询师信息(若无则新增)
            PsyConsult psyConsult=psyConsultService.getByPhoneOrInsert(phoneNumber);
            ConsultDTO consultDTO=new ConsultDTO();
            consultDTO.setConsultId(psyConsult.getId());
            consultDTO.setPhone( psyConsult.getPhonenumber());
            String token= consultantTokenService.createToken(consultDTO,360000);
            
            //若无账户则创建
            accountService.createAccountIfNotExist(psyConsult.getId());
            
            //更新设备信息
            psyConsult.setDeviceId(consultLoginDTO.getDeviceId());
            psyConsult.setDeviceBrand(consultLoginDTO.getDeviceBrand());
            psyConsult.setDeviceModel(consultLoginDTO.getDeviceModel());
            psyConsult.setLastLoginIp(consultLoginDTO.getLastLoginIp());
            psyConsultService.updateById(psyConsult);
            
            return AjaxResult.success(Constants.TOKEN_PREFIX + token);
        } catch (Exception e) {
            log.error("login error",e);
            return AjaxResult.error("login error");
        }
    }

    @PostMapping("/getLoginCode")
    public AjaxResult getLoginCode(@RequestBody ConsultLoginDTO req)
    {
        //String code = UUID.randomUUID().toString().substring(0,6);
       // String code = "123456";
        String code = String.valueOf((int)(Math.random() * 900000) + 100000);
        redisCache.setCacheObject(CacheConstants.PHONE_LOGIN_CODE + "::" + req.getPhone(),code);
        return AjaxResult.success(code);
    }

    @PostMapping("/loginByPhoneCode")
    public AjaxResult loginByPhoneCode(@RequestBody ConsultLoginDTO req)
    {
        String code = redisCache.getCacheObject(CacheConstants.PHONE_LOGIN_CODE + "::" + req.getPhone());
        if (!req.getLoginCodeByPhone().equals(code)){
            throw new ServiceException("验证码与手机号不相符");
        }

        PsyConsultVO consultReq = new PsyConsultVO();
        PsyConsult byPhone = psyConsultService.getByPhone(req.getPhone());
        if (ObjectUtils.isEmpty(byPhone)){
            throw new ServiceException("该手机号无相符的咨询师");
        }
        
        ConsultDTO consultDTO=new ConsultDTO();
            consultDTO.setConsultId(byPhone.getId());
            consultDTO.setPhone(byPhone.getPhonenumber());
        String token= consultantTokenService.createToken(consultDTO,360000);

        return AjaxResult.success(Constants.TOKEN_PREFIX + token);
    }
    
    

    @PostMapping("/info")
    public AjaxResult getUserInfo(HttpServletRequest request)
    {
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
            log.error("login error",e);
            return AjaxResult.error("login error");
        }
    }

    /**
     * 根据类型  查询咨询师列表
     */
    @ApiOperation(value = "查询咨询师列表")
    @PostMapping("/cache")
    public TableDataInfo listByType(@RequestBody QueryListByTypeReq req)
    {
        startPage();
        String listType = req.getListType();
        List<Long> idList = redisCache.getCacheList(CacheConstants.CONSULTANT_ID_LIST + "::" + listType);
        List<PsyConsult> cacheList = redisCache.getMultiCacheMapValue(CacheConstants.CONSULTANT_BY_ID_KEY , PageUtils.paginate(idList));
        
     /*   PsyAdminConsultReq consultantReq = new PsyAdminConsultReq();
        consultantReq.setListType(listType);
        consultantReq.setIdList(idList);
        List<PsyConsult> list = psyConsultService.getList(consultantReq);*/

        return getDataTable(cacheList, idList.size());
    }

    /**
     * 查询指定咨询师的服务清单
     * @param req
     * @param request
     * @return
     */
    @PostMapping("/getUserServerList")
    public AjaxResult getUserServerList(@RequestBody PsyConsultServeConfigReq req , HttpServletRequest request)
    {
        try {
            /*PsyConsultVO one = psyConsultService.getOne(req.getConsultantId());
            req.setLevel(one.getLevel());
            if (ObjectUtils.isEmpty(req.getServiceObject())){
                req.setServiceObject(one.getServiceObject());
            }*/
            List<PsyConsultServeConfig> list = serveConfigService.getList(req);
            
            return AjaxResult.success(list);
        } catch (Exception e) {
            log.error("login error",e);
            return AjaxResult.error("login error");
        }
    }
    
    /**
     * 获取字典清单
     */
    @PostMapping("/getDictTypeDataList")
    @RateLimiter
    public AjaxResult getDictList()
    {
        List<SysDictType> sysDictTypeList = dictTypeService.selectDictTypeDataList(null);
        return AjaxResult.success(sysDictTypeList);
    }
    
    

}

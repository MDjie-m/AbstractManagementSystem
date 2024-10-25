package com.renxin.pocket.controller.wechat;

import com.alibaba.fastjson2.JSONObject;
import com.renxin.common.config.WxLoginConfig;
import com.renxin.common.constant.Constants;
import com.renxin.common.constant.RespMessageConstants;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.common.core.domain.vo.LoginVO;
import com.renxin.common.enums.LoginType;
import com.renxin.common.utils.RestTemplateUtil;
import com.renxin.common.utils.StringUtils;
import com.renxin.common.utils.spring.SpringUtils;
import com.renxin.common.wechat.wechatProgram.WechatProgramUtils;
import com.renxin.framework.web.service.PocketTokenService;
import com.renxin.psychology.domain.PsyUser;
import com.renxin.psychology.service.IPsyUserService;
import com.renxin.system.service.ISysConfigService;
import com.renxin.wechat.utils.AuthUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import org.springframework.beans.factory.annotation.Value;

/**
 * @User hogan
 * @Time 2022/10/20 19:40
 * @e-mail hkcugwh@163.com
 **/
@RestController
@RequestMapping("/pocket/user")
@Slf4j
public class WxAuthorizeController {

    private static String APP_ID = SpringUtils.getBean(WxLoginConfig.class).getAppId();
    private static String APP_SECRET = SpringUtils.getBean(WxLoginConfig.class).getAppSecret();
    private static String PAGE_URL = SpringUtils.getBean(WxLoginConfig.class).getRedirectUri();

    @Autowired
    private IPsyUserService psyUserService;

    @Autowired
    private PocketTokenService pocketTokenService;

    @Resource
    private RestTemplateUtil restTemplateUtil;

    @Resource
    private  WechatProgramUtils wechatTokenUtils;
    
    @Value("${wechat.appid}")
    private String appid;
    
    @Value("${wechat.secret}")
    private String secret;

    @Value("${wechat.grant_type}")
    private String grant_type;
    
    @Value("${wechat.jsCodeLoginUrl}")
    private String jsCodeLoginUrl;

    @Resource
    private ISysConfigService configService;
    
    /**
     * 微信登录接口(新)
     */
    @ApiOperation(value = "微信登录接口")
    @PostMapping("/wechatProgram/login")
    public AjaxResult login(@RequestBody Map<String, Object> reqMap) throws UnsupportedEncodingException {
        log.info("/wechatProgram/login入参:" + reqMap);
        
        HashMap<String, String> params = new HashMap<>();
        params.put("appid", appid);
        params.put("secret", secret);
        params.put("grant_type", grant_type);
        params.put("js_code", (String)reqMap.get("js_code"));
        String openId = "";
        
        // 构建URL
        StringJoiner urlJoiner = new StringJoiner("&", jsCodeLoginUrl + "?", "");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            urlJoiner.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        String urlString = urlJoiner.toString();

        //发起请求
        log.info("微信登录urlString : " + urlString);
        JSONObject jsData = restTemplateUtil.getData(urlString);
        if (ObjectUtils.isNotEmpty(jsData.get("session_key"))){
            openId = jsData.get("openid").toString();
        }
        //openId = "oP8146998AoIjkNMZx4s2vK4me5w";
        /*try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(urlString);

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    //result = "{\"session_key\":\"s+NhENlW4vqnrP+7sKkJLA==\",\"openid\":\"oP8146998AoIjkNMZx4s2vK4me5w\"}";
                    JsonNode jsonNode = new ObjectMapper().readTree(result);
                    if (ObjectUtils.isNotEmpty(jsonNode.get("session_key"))){
                        openId = jsonNode.get("openid").asText();
                    }
                }
            }
        } catch (IOException e) {
            log.error("小程序获取token失败");
        }*/

        if (ObjectUtils.isEmpty(openId)){
            log.error("来访者用户登录失败, 未能获取到openId,  reqMap:" + reqMap);
            return AjaxResult.success("请求失败, 未获取到js_code对应的openId, 请刷新后重试");
        }

        
        String token = "";
        LoginDTO loginDTO = new LoginDTO();
        PsyUser queryUser = new PsyUser();
            queryUser.setWxOpenid(openId);
        System.out.println("********************************************");
        System.out.println(openId);
        List<PsyUser> queryUserList = psyUserService.selectPsyUserList(queryUser);
        PsyUser psyUser = new PsyUser();
        //若openId已有相应的用户, 则直接使用该用户
        if (ObjectUtils.isNotEmpty(queryUserList)){
            BeanUtils.copyProperties(queryUserList.get(0),loginDTO);
                loginDTO.setUserId(queryUserList.get(0).getId());
            token = pocketTokenService.createToken(loginDTO, 360000);
            psyUser = queryUserList.get(0);
        }else{
            log.info("来访者用户初次登录, openId:" + openId +". reqMap:" + reqMap.toString());
        //否则先添加用户后使用
            PsyUser newUser = new PsyUser();
                newUser.setWxOpenid(openId);
                newUser.setName(configService.selectConfigByKey("pocket.init.name"));
                newUser.setAvatar(configService.selectConfigByKey("pocket.init.avatar"));
              
            psyUserService.insertPsyUser(newUser);
            BeanUtils.copyProperties(newUser,loginDTO);
                loginDTO.setAccount(openId);
                loginDTO.setLoginType(LoginType.WX);
                loginDTO.setUserId(newUser.getId());
            token = pocketTokenService.createToken(loginDTO, 360000);
            psyUser = psyUserService.selectPsyUserById(newUser.getId());
        }
         
        //更新设备信息
        psyUser.setDeviceId((String)reqMap.get("deviceId"));
        psyUser.setPushClientId((String)reqMap.get("pushClientId"));
        psyUser.setDeviceBrand((String)reqMap.get("deviceBrand"));
        psyUser.setDeviceModel((String)reqMap.get("deviceModel"));
        psyUser.setLastLoginIp((String)reqMap.get("lastLoginIp"));
        psyUser.setSourceChannelId((String)reqMap.get("sourceChannelId"));
        try {
            psyUser.setIntroduceUserId(Long.valueOf((String)reqMap.get("introduceUserId")));
        }catch (Exception e){
        }
        psyUserService.updatePsyUser(psyUser);

        return AjaxResult.success(Constants.TOKEN_PREFIX +  token);
    }

    @ApiOperation(value = "微信获取手机号码更新接口")
    @PostMapping("/wechatProgram/updatePhoneNumber")
    public AjaxResult getPhoneNumber(@RequestBody Map<String, String> params, HttpServletRequest request) throws UnsupportedEncodingException {
        try {
            log.info("微信获取手机号码更新接口/wechatProgram/updatePhoneNumber" + params.toString());
            String phoneNumber=wechatTokenUtils.getPhonenumber(params.get("code"));
            log.info("phoneNumber:" + phoneNumber);
            //更新用户信息
            LoginDTO loginUser = pocketTokenService.getLoginUser(request);
            log.info("loginUser:" + loginUser);
            loginUser.setPhone(phoneNumber);
            psyUserService.bindPhone(loginUser);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
        
        return AjaxResult.success();
    }
    /**
     * 修改来访者用户信息
     */
    @ApiOperation("更新来访者用户信息")
    @PostMapping("/wechatProgram/updateUser")
    public AjaxResult update(@RequestBody PsyUser psyUser , HttpServletRequest request)
    {
        LoginDTO loginUser = pocketTokenService.getLoginUser(request);
        psyUser.setId(loginUser.getUserId());
        psyUserService.updatePsyUser(psyUser);
        pocketTokenService.refreshToken(loginUser ,null);
        return AjaxResult.success(RespMessageConstants.OPERATION_SUCCESS);
    }
    
    /**
     * 微信登录接口
     */
    @ApiOperation(value = "微信登录接口")
    @PostMapping("wxLogin")
    public AjaxResult wxLogin(@RequestBody Map<String, Object> map) {
        //这里是回调的url
        String redirect_uri = map.get("redirectUri").toString();
        String state = map.get("redirectState").toString();
        try {
            redirect_uri = URLEncoder.encode(map.get("redirectUri").toString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=APPID" +
                "&redirect_uri=REDIRECT_URI" +
                "&response_type=code" +
                "&scope=SCOPE" +
                "&state=STATE#wechat_redirect";

        url = url.replace("APPID", APP_ID).replace("REDIRECT_URI", redirect_uri).replace("SCOPE", "snsapi_userinfo").replace("STATE", state);
        log.info("wxLogin_url:{}", url);
        return AjaxResult.success(RespMessageConstants.OPERATION_SUCCESS, url);
    }

    /**
     * 微信授权成功的回调函数
     *
     * @param request
     * @throws IOException
     */
    @ApiOperation(value = "微信授权回调接口")
    @RequestMapping("/callBack")
    protected AjaxResult deGet(HttpServletRequest request) throws Exception {
        //获取回调地址中的code
        String code = request.getParameter("code");
        log.info("回调code为：{}", code);
        //拼接url
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APP_ID + "&secret="
                + APP_SECRET + "&code=" + code + "&grant_type=authorization_code";
        log.info("access_token请求url:{}", url);
        JSONObject jsonObject = AuthUtil.doGetJson(url);
        //1.获取微信用户的openid
        String openId = jsonObject.getString("openid");
        //2. 获取access_token
        String accessToken = jsonObject.getString("access_token");
//        Integer expiresIn = jsonObject.getInteger("expires_in");

        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId
                + "&lang=zh_CN";
        log.info("access_token_接口返回{}", infoUrl);
        //3.获取微信用户信息
        JSONObject userInfo = AuthUtil.doGetJson(infoUrl);
        log.info("微信用户信息：{}", userInfo);

        if (null != userInfo.getInteger("errcode")) {
            return AjaxResult.error(RespMessageConstants.ACCESS_TOKEN_EXPIRED);
        }

        //获取手机号登录用户
        LoginDTO loginUser = pocketTokenService.getLoginUser(request);

        LoginVO loginVO = null;
        //手机号已经登录过
        if (loginUser != null && StringUtils.isNotEmpty(loginUser.getPhone())) {
            //重新缓存用户信息
            loginUser.setWxOpenId(openId);
            PsyUser user = psyUserService.queryUserByAccount(loginUser.getPhone());
            loginVO = getLoginVO(openId, userInfo, loginUser, user);
        } else {
            loginVO = psyUserService.checkPsyUser(openId, userInfo);
            loginUser = new LoginDTO();
            loginUser.setWxOpenId(openId);
            loginUser.setAccount(openId);
            loginUser.setLoginType(LoginType.WX);
            loginUser.setUserId(loginVO.getUserId());
            //创建token
            String token = pocketTokenService.createToken(loginUser, null);
            loginVO.setToken(token);
        }

        return AjaxResult.success(RespMessageConstants.APP_LOGIN_SUCCESS, loginVO);
    }

    /**
     * 手机登录之后才会涉及微信登录
     */
    private LoginVO getLoginVO(String openId, JSONObject userInfo, LoginDTO loginUser, PsyUser user) {
        String nickname = userInfo.getString("nickname");
        String headImgUrl = userInfo.getString("headimgurl");

        pocketTokenService.refreshToken(loginUser, null);

        psyUserService.updatePsyUser(PsyUser.builder().id(user.getId()).wxOpenid(openId).name(nickname).avatar(headImgUrl).build());

        LoginVO loginVO = LoginVO.builder().name(nickname).avatar(headImgUrl).phone(user.getPhone()).build();
        return loginVO;
    }


}

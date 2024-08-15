package com.renxin.app;

import com.alibaba.fastjson2.JSONObject;
import com.renxin.RuoYiApplication;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.common.core.domain.vo.LoginVO;
import com.renxin.common.enums.LoginType;
import com.renxin.framework.web.service.PocketTokenService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RuoYiApplication.class)
@Slf4j
public class WxAuthorizeControllerTest {

    @Autowired
    private PocketTokenService pocketTokenService;

    @Test
    public void deGet() {
//        LoginDTO loginUser = new LoginDTO();
//        loginUser.setWxOpenId("oviCP5w-2yeCASS2sZWs9f4xJO90");
//        loginUser.setAccount("oviCP5w-2yeCASS2sZWs9f4xJO90");
//        loginUser.setLoginType(LoginType.WX);
//        loginUser.setUserId(84);

        LoginDTO loginUser = new LoginDTO();
        loginUser.setWxOpenId("oviCP50GGq_RKKZ7X-2Zl2181O2U");
        loginUser.setAccount("oviCP50GGq_RKKZ7X-2Zl2181O2U");
        loginUser.setLoginType(LoginType.WX);
        loginUser.setUserId(55L);

//        LoginVO loginVO = LoginVO.builder().userId(84).token(appTokenService.createToken(loginUser, null)).name("进").avatar("https://thirdwx.qlogo.cn/mmopen/vi_32/3SMCDnLMChqt0Flb0icHvwPQmxnrMEtM7jxV6VMLRibY5oA8BhicqCR3RSwvjdZia2ERA2broS7XLOqSmNfpXolbPw/132").build();
        LoginVO loginVO = LoginVO.builder().userId(55L).token(pocketTokenService.createToken(loginUser, null)).name("姗姗").avatar("https://thirdwx.qlogo.cn/mmopen/vi_32/ibkVKJebFcicyZAibpxzLy3uwu5icYZrWWrvPJBOJz7ld1mQuCpXyjCy0EiaibDbjAVibOmOg5VCSHyjmAbpVhPf6DrEw/132").build();
        log.info("用户登录模拟::::::userInfo::" + JSONObject.toJSONString(loginVO));
        log.info("token::::::" + JSONObject.toJSONString(loginVO.getToken()));
//        return AjaxResult.success(RespMessageConstants.APP_LOGIN_SUCCESS, loginVO);

    }

    // 08:44:10.377 [main] INFO  c.r.a.WxAuthorizeControllerTest - [deGet,33] - 用户登录模拟::::::userInfo::{"avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/3SMCDnLMChqt0Flb0icHvwPQmxnrMEtM7jxV6VMLRibY5oA8BhicqCR3RSwvjdZia2ERA2broS7XLOqSmNfpXolbPw/132","name":"进","openIdFlag":false,"token":"eyJhbGciOiJIUzUxMiJ9.eyJhcHBfbG9naW5fdXNlcl9rZXkiOiJlN2YyMDJmNy1jMmYwLTRkZjAtYmYyMi05ZDc1MmQ5NjZkNTcifQ.6uXAQn1x2X9fM-pTbpCDZQ2h8-T18ALN_xKIEjePo3uIhvPjB-q5byp99xo-OfQYoGdP936b2eOaQ4pPtJR8JA","userId":84}
    // 14:53:39.951 [main] INFO  c.r.a.WxAuthorizeControllerTest - [deGet,40] - 用户登录模拟::::::userInfo::{"avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/ibkVKJebFcicyZAibpxzLy3uwu5icYZrWWrvPJBOJz7ld1mQuCpXyjCy0EiaibDbjAVibOmOg5VCSHyjmAbpVhPf6DrEw/132","name":"姗姗","openIdFlag":false,"token":"eyJhbGciOiJIUzUxMiJ9.eyJhcHBfbG9naW5fdXNlcl9rZXkiOiIxNGE4MDZjMC05NDRhLTQ3OTktODQ4MS0zYTQ0MTVjZWZlNTkifQ.z34rMZYAKXJDdr9uRQEcNL9Uni-GMbf3PtQ3qhiywXgLix0uIvP6uMFGedT92x-5XPt-ijHLaHKEP2EqkfFDLA","userId":55}

}

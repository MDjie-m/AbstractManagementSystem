package com.renxin.framework.web.service;

import com.renxin.common.constant.CacheConstants;
import com.renxin.common.constant.Constants;
import com.renxin.common.core.domain.dto.ConsultDTO;
import com.renxin.common.core.domain.entity.SysDictData;
import com.renxin.common.core.redis.RedisCache;
import com.renxin.common.utils.ServletUtils;
import com.renxin.common.utils.StringUtils;
import com.renxin.common.utils.ip.AddressUtils;
import com.renxin.common.utils.ip.IpUtils;
import com.renxin.common.utils.spring.SpringUtils;
import com.renxin.common.utils.uuid.IdUtils;
import com.renxin.system.service.ISysDeptService;
import com.renxin.system.service.ISysDictDataService;
import com.renxin.system.service.ISysUserService;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * token验证处理
 *
 * @author renxin
 */
@Component
public class ConsultantTokenService {
    // 令牌自定义标识
    @Value("${consultant.token.header}")
    private String header;

    // 令牌秘钥
    @Value("${consultant.token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟）单位为s
    @Value("${consultant.token.expireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysDictDataService sysDictDataService;

    // 定义常量：咨询师授权令牌的关键字，用于在令牌存储或传输中作为标识。
    private static final String DICT_CONSULTANT_KEY = "consultant_auth_token";
    // 定义常量：咨询师令牌的头部标识，用于在HTTP请求中携带令牌。
    private static final String DICT_CONSULTANT_HEARDER = "consultant.token.header";
    // 定义常量：咨询师令牌的密钥，用于生成和验证令牌的完整性。
    private static final String DICT_CONSULTANT_SECRET = "consultant.token.secret";
    // 定义常量：咨询师令牌的过期时间关键字，用于存储或检查令牌的有效期。
    private static final String DICT_CONSULTANT_EXPIRETIME = "consultant.token.expireTime";


   /* @PostConstruct
    public String initData() {

        SysDictData dictData = new SysDictData();
        dictData.setDictType(DICT_CONSULTANT_KEY);

        List<SysDictData> list = sysDictDataService.selectDictDataList(dictData);
        if (ObjectUtils.isEmpty(list)) {
            throw new RuntimeException("获取令牌秘钥失败");
        }

        for (SysDictData dict : list){
            if (DICT_CONSULTANT_HEARDER.equals(dict.getDictLabel())){
                header = dict.getDictValue();
            }else if (DICT_CONSULTANT_SECRET.equals(dict.getDictLabel())){
                secret = dict.getDictValue();
            }else if (DICT_CONSULTANT_EXPIRETIME.equals(dict.getDictLabel())){
                expireTime = Integer.parseInt(dict.getDictValue());
            }
        }
        return header;
    }
*/
    /**
     *
     * @return 用户id
     */
    public Long getUserId(HttpServletRequest request) {
        ConsultDTO loginUser = getLoginUser(request);
        return loginUser != null && loginUser.getUserId() != null ? loginUser.getUserId() : -1;
    }

    /**
     *
     * @return 咨询师id
     */
    public Long getConsultId(HttpServletRequest request) {
        ConsultDTO loginUser = getLoginUser(request);
        return loginUser != null && loginUser.getConsultId() != null ? loginUser.getConsultId() : -1;
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public ConsultDTO getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            try {
                Claims claims = parseToken(token);
                // 解析对应的权限以及用户信息
                String uuid = (String) claims.get(Constants.CONSULT_LOGIN_USER_KEY);
                String userKey = getTokenKey(uuid);
                ConsultDTO user = redisCache.getCacheObject(userKey);
                return user;
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(ConsultDTO loginUser) {
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken())) {
            refreshToken(loginUser ,null);
        }
    }

    /**
     * 删除用户身份信息
     */
    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userKey = getTokenKey(token);
            redisCache.deleteObject(userKey);
        }
    }

    /**
     * 创建令牌
     *
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createToken(ConsultDTO loginUser , Integer expire) {
        String token = IdUtils.fastUUID();
        loginUser.setToken(token);
        setUserAgent(loginUser);
        refreshToken(loginUser ,expire);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.CONSULT_LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param loginUser
     * @return 令牌
     */
    public void verifyToken(ConsultDTO loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser ,null);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(ConsultDTO loginUser, Integer expire) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + (ObjectUtils.isEmpty(expire) ? expireTime : expire) * MILLIS_SECOND);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        int expireTime = (int) (redisCache.getExpire(userKey) + this.expireTime);
        redisCache.setCacheObject(userKey, loginUser, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 设置用户代理信息
     *
     * @param loginUser 登录信息
     */
    public void setUserAgent(ConsultDTO loginUser) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        loginUser.setIpaddr(ip);
        loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        loginUser.setBrowser(userAgent.getBrowser().getName());
        loginUser.setOs(userAgent.getOperatingSystem().getName());
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims) {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    private String getTokenKey(String uuid) {
        return CacheConstants.CONSULT_LOGIN_TOKEN_KEY + uuid;
    }

    public static ConsultantTokenService getInstance() {
        return SpringUtils.getBean(ConsultantTokenService.class);
    }
}

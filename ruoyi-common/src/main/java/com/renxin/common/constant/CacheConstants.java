package com.renxin.common.constant;

/**
 * 缓存的key 常量
 * 
 * @author renxin
 */
public class CacheConstants
{
    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";


    /**
     * 登录用户 redis key
     */
    public static final String APP_LOGIN_TOKEN_KEY = "app_login_tokens:";

    /**
     * 登录用户 redis key
     */
    public static final String CONSULT_LOGIN_TOKEN_KEY = "consult_login_tokens:";
    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 限流 redis key
     */
    public static final String RATE_LIMIT_KEY = "rate_limit:";

    /**
     * 登录账户密码错误次数 redis key
     */
    public static final String PWD_ERR_CNT_KEY = "pwd_err_cnt:";
    

    /**
     * 团队督导id清单 redis key
     */
    public static final String TEAM_SUP_ID_LIST = "team_sup_id_list";
    
    /**
     * 团队督导byId redis key
     */
    public static final String TEAM_SUP_BY_ID_KEY = "team_sup_by_id";

    /**
     * 课程id清单 redis key
     */
    public static final String COURSE_ID_LIST = "course_id_list";

    /**
     * 课程byId redis key
     */
    public static final String COURSE_BY_ID_KEY = "course_by_id";

    /**
     * 套餐id清单 redis key
     */
    public static final String PACKAGE_ID_LIST = "package_id_list";

    /**
     * 套餐byId redis key
     */
    public static final String PACKAGE_BY_ID_KEY = "package_by_id";

    /**
     * 测评id清单 redis key
     */
    public static final String GAUGE_ID_LIST = "gauge_id_list";

    /**
     * 测评byId redis key
     */
    public static final String GAUGE_BY_ID_KEY = "gauge_by_id";

    /**
     * 测评问题id清单 redis key
     */
    public static final String QUESTION_ID_LIST = "question_id_list";

    /**
     * 测评问题byId redis key
     */
    public static final String QUESTION_BY_ID_KEY = "question_by_id";

    /**
     * 咨询师id清单 redis key
     */
    public static final String CONSULTANT_ID_LIST = "consultant_id_list";

    /**
     * 咨询师byId redis key
     */
    public static final String CONSULTANT_BY_ID_KEY = "consultant_by_id";

    /**
     * 登录验证码 redis key
     */
    public static final String PHONE_LOGIN_CODE = "phone_login_code";

    /**
     * 注销验证码 redis key
     */
    public static final String PHONE_LOG_OFF_CODE = "phone_log_off_code";

    
}

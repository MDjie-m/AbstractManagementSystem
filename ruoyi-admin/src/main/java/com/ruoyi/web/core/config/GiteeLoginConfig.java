package com.ruoyi.web.core.config;

import com.ruoyi.framework.config.properties.GiteeLoginProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Version v1.0
 * @Author zhou
 * @Date 2024/8/15
 * @Description
 */
@Configuration
@EnableConfigurationProperties({GiteeLoginProperties.class})
public class GiteeLoginConfig {
}

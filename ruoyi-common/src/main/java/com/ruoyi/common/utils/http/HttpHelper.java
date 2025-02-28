package com.ruoyi.common.utils.http;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通用http工具封装
 * 
 * @author ruoyi
 */
public class HttpHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpHelper.class);

    public static String getBodyString(ServletRequest request) {
        try (
                InputStream inputStream = request.getInputStream()
        ) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.warn("getBodyString出现问题！");
        }
        return "";
    }
}

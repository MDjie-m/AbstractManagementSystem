package com.ruoyi.billiard.domain.vo;

import cn.hutool.core.date.DatePattern;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.AESUtils;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import lombok.Data;
import org.springframework.core.env.Environment;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class BaseDeviceReqVo {

    @NotNull(message = "非法请求")
    private String apiKey;

    private Long storeId;

    public void setApiKey(String apiKey) {

        try {
            Environment environment = SpringUtils.getBean(Environment.class);
            String aesKey = environment.getProperty("cashier.aes-key");
            String val = AESUtils.decryptECB(apiKey, aesKey);
            String[] array = val.split("\\|");
            AssertUtil.notNullOrEmpty(array, "非法参数");
            Date date = DateUtils.parseDate(array[1], DatePattern.NORM_DATETIME_PATTERN);
            int seconds = Math.abs(DateUtils.diffSeconds(date, DateUtils.getNowDate()));
            AssertUtil.isTrue(seconds < 5, "非法参数");
            this.apiKey = apiKey;
            this.storeId = Long.parseLong(array[0]);
        } catch (Exception e) {
            throw new ServiceException("非法参数");
        }

    }
}

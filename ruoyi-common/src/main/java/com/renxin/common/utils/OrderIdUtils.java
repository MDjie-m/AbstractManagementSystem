package com.renxin.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @User hogan
 * @Time 2022/10/24 17:14
 * @e-mail hkcugwh@163.com
 **/
public class OrderIdUtils {

    /**
     * 时间串 + 随机数
     * @return
     */
    public static String getOrderId(){
        //时间（精确到毫秒）
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String localDate = LocalDateTime.now().format(ofPattern);
        //随机数
        String randomNumeric = RandomStringUtils.randomNumeric(8);
        return new StringBuilder().append(localDate).append(randomNumeric).toString();
    }

    /**
     * 创建商户订单号
     * 要求 32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一
     * 组成 两位前缀 + 17位时间戳 + 9位id补零 + 4位随机数 合计32位
     *
     * @param head 例如 商品-SP 订金-DJ 退款-TK 等等
     * @param id   用户id
     * @return
     */
    public static String createOrderNo(String head, Long id) {
//        StringBuilder uid = new StringBuilder(id.toString());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        int length = uid.length();
//        for (int i = 0; i < 8 - length; i++) {
//            uid.insert(0, "0");
//        }
        return head + sdf.format(date) + (int) ((Math.random() * 9 + 1) * 1000);
    }
}

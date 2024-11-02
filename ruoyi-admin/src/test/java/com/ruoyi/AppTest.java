package com.ruoyi;

import com.alipay.api.AlipayApiException;
import com.ruoyi.common.utils.AliPayUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author:
 * @description:
 * @create: 2024-10-30 11:17
 **/

@SpringBootTest
public class AppTest {


    @Test
    public void                                                                   test01() throws AlipayApiException {
        System.out.println(111);
        String result = AliPayUtils.createTradeCreate("123456","111222","测试当面付","2088722048548785");
        System.out.println(result);


    }


}

package com.ruoyi.common.utils;

import junit.framework.TestCase;
import org.junit.Assert;

public class AESUtilsTest extends TestCase {

    public void testEncryptECB() {
//       String val = AESUtils.encryptECB("1", "1234567890123456");
//        Assert.assertEquals(val,"OrXKoRfGX/MpJoqNyzqoHw==");
    }

    public void testDecryptECB() {
       String val = AESUtils.decryptECB("h2THIE6ArwTKLuPqkIh/1g==","1234567890123456");
        Assert.assertEquals(val,"1");
    }
}
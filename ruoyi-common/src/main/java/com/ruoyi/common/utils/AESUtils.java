package com.ruoyi.common.utils;


import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author gogo
 */
public class AESUtils {
    /**
     * 16字节
     */

    private static final Mode MODE = Mode.ECB;
    private static final Padding PADDING = Padding.ISO10126Padding;

    public static String encryptECB(String data, String key) {
        AES aes;

        aes = new AES(MODE, PADDING,
                new SecretKeySpec(key.getBytes(), "AES"));

        return aes.encryptBase64(data, StandardCharsets.UTF_8);
    }

    public static String decryptECB(String data, String key) {
        AES aes;

        aes = new AES(MODE, PADDING,
                new SecretKeySpec(key.getBytes(), "AES"));

        byte[] decryptDataBase64 = aes.decrypt(data);
        return new String(decryptDataBase64, StandardCharsets.UTF_8);
    }

}


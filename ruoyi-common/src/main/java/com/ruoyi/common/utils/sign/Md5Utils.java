package com.ruoyi.common.utils.sign;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * Md5加密方法
 *
 * @author ruoyi
 */
public class Md5Utils
{
    private static final Logger log = LoggerFactory.getLogger(Md5Utils.class);
    private static final ThreadLocal<MessageDigest> md5DigestThreadLocal = ThreadLocal.withInitial(() -> {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    });

    private static byte[] md5(String s)
    {
        MessageDigest algorithm;
        try
        {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        }
        catch (Exception e)
        {
            log.error("MD5 Error...", e);
        }
        return null;
    }

    private static final String toHex(byte hash[])
    {
        if (hash == null)
        {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++)
        {
            if ((hash[i] & 0xff) < 0x10)
            {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String hash(String s)
    {
        try
        {
            return new String(toHex(md5(s)).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        }
        catch (Exception e)
        {
            log.error("not supported charset...{}", e);
            return s;
        }
    }

    /**
     * 计算文件的md5
     * @param file 文件
     * @return
     */
    public static String getMd5(MultipartFile file) {
        try {
            long fileSize = file.getSize();
            // 10MB作为分界点
            if (fileSize < 10 * 1024 * 1024) {
                return getMd5ForSmallFile(file);
            } else {
                return getMd5ForLargeFile(file);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 计算小文件的md5
     *
     * @param file
     * @return
     */
    public static String getMd5ForSmallFile(MultipartFile file) {
        try {
            // 获取文件的byte信息
            byte[] uploadBytes = file.getBytes();
            // 拿到一个MD5转换器
            MessageDigest md5 = md5DigestThreadLocal.get();
            byte[] digest = md5.digest(uploadBytes);
            // 转换为16进制
            String md5Hex = new BigInteger(1, digest).toString(16);
            // 确保MD5值始终是32位
            while (md5Hex.length() < 32) {
                md5Hex = "0" + md5Hex;
            }
            return md5Hex;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 计算大文件的md5
     *
     * @param file
     * @return
     */
    private static String getMd5ForLargeFile(MultipartFile file) throws IOException {
        MessageDigest md = md5DigestThreadLocal.get();
        try (InputStream is = file.getInputStream()) {
            byte[] buffer = new byte[81920];
            int read;
            while ((read = is.read(buffer)) != -1) {
                md.update(buffer, 0, read);
            }
        }

        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

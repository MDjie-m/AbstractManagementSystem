package com.ruoyi.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	private static final ThreadLocal<MessageDigest> MESSAGE_DIGEST_LOCAL = new ThreadLocal<MessageDigest>() {
		@Override
		protected MessageDigest initialValue() {
			try {
				return MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				return null;
			}
		}
	};

	/**
	 * Calculate MD5 hex string.
	 *
	 * @param bytes byte arrays
	 * @return MD5 hex string of input
	 * @throws NoSuchAlgorithmException if can't load md5 digest spi.
	 */
	public static String md5Hex(byte[] bytes) throws NoSuchAlgorithmException {
		try {
			MessageDigest messageDigest = MESSAGE_DIGEST_LOCAL.get();
			if (messageDigest != null) {
				return encodeHexString(messageDigest.digest(bytes));
			}
			throw new NoSuchAlgorithmException("MessageDigest get MD5 instance error");
		} finally {
			MESSAGE_DIGEST_LOCAL.remove();
		}
	}

	/**
	 * Calculate MD5 hex string with encode charset.
	 *
	 * @param value  value
	 * @param encode encode charset of input
	 * @return MD5 hex string of input
	 */
	public static String md5Hex(String value, String encode) {
		try {
			return md5Hex(value.getBytes(encode));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Convert a byte array into a visible string.
	 */
	public static String encodeHexString(byte[] bytes) {
		int l = bytes.length;

		char[] out = new char[l << 1];

		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS_LOWER[(0xF0 & bytes[i]) >>> 4];
			out[j++] = DIGITS_LOWER[0x0F & bytes[i]];
		}

		return new String(out);
	}

    public static String getMD5(byte[] bytes) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] str = new char[32];

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            byte[] tmp = md.digest();
            int k = 0;

            for(int i = 0; i < 16; ++i) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return new String(str);
    }

    public static String getMD5(String value, String encode) {
        String result = "";

        try {
            result = getMD5(value.getBytes(encode));
        } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
        }

        return result;
    }
}

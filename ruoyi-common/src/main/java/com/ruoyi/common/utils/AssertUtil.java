package com.ruoyi.common.utils;

import com.ruoyi.common.exception.ExceptionCodeEnum;
import com.ruoyi.common.exception.ServiceException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

public class AssertUtil {


        public static <T> void isNullOrEmpty(T obj, String msg) {
            isNullOrEmpty(obj, null, msg);
        }

        public static <T> void isNullOrEmpty(T obj, ExceptionCodeEnum code) {
            isNullOrEmpty(obj, code, null);
        }

        public static <T> void isNullOrEmpty(T obj, ExceptionCodeEnum code, String msg) {
            checkEmpty(obj, true, code, msg);
        }

        public static <T> void notNullOrEmpty(T obj, String msg) {
            notNullOrEmpty(obj, null, msg);
        }

        public static <T> void notNullOrEmpty(T obj, ExceptionCodeEnum code) {
            notNullOrEmpty(obj, code, null);
        }

        public static <T> void notNullOrEmpty(T obj, ExceptionCodeEnum code, String msg) {
            checkEmpty(obj, false, code, msg);
        }

        private static <T> void checkEmpty(T obj, boolean exceptIsEmpty, ExceptionCodeEnum code, String msg) {

            if (obj instanceof String) {
                if (!Objects.equals(StringUtils.isEmpty((String) obj), exceptIsEmpty)) {
                    throw new ServiceException(msg, code);
                }
                return;
            }
            if (obj instanceof List) {
                if (!Objects.equals(CollectionUtils.isEmpty((List) obj), exceptIsEmpty)) {
                    throw new ServiceException(msg, code);
                }
                return;
            }

            if (!Objects.equals(Objects.isNull(obj), exceptIsEmpty)) {
                throw new ServiceException(msg, code);
            }


        }

        public static void isTrue(Boolean obj, ExceptionCodeEnum code, String msg) {
            if (!Objects.equals(Boolean.TRUE, obj)) {
                throw new ServiceException(msg, code);
            }
        }

        public static <T> void equals(T obj, T except, ExceptionCodeEnum code, String msg) {
            if (!Objects.equals(obj, except)) {
                throw new ServiceException(msg, code);
            }
        }

        public static <T> void notEquals(T obj, T except, ExceptionCodeEnum code, String msg) {
            if (Objects.equals(obj, except)) {
                throw new ServiceException(msg, code);
            }
        }

        public static <T> void notEquals(T obj, T except, String msg) {
            notEquals(obj, except, null, msg);
        }

        public static <T> void equals(T obj, T except, String msg) {
            equals(obj, except, null, msg);
        }

        public static void isTrue(Boolean obj, ExceptionCodeEnum code) {
            isTrue(obj, code, null);
        }

        public static void isTrue(Boolean obj, String msg) {
            isTrue(obj, null, msg);
        }
}

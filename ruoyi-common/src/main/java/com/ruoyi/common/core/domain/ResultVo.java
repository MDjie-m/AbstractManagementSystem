package com.ruoyi.common.core.domain;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;


/**
 * 操作消息提醒
 *
 * @author ruoyi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultVo <T> implements Serializable {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回消息文本
     */
    private String msg;
    /**
     * 数据对象
     */
    private T data;
    private static final long serialVersionUID = 1L;


    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static <T> ResultVo<T> success(T data) {
        return new ResultVo<>(HttpStatus.SUCCESS, "操作成功", data);
    }


    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static <T> ResultVo<T> success() {
        return ResultVo.success("操作成功");
    }


    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static <T> ResultVo<T> success(String msg) {
        return ResultVo.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static <T> ResultVo<T> success(String msg, T data) {
        return new ResultVo<T>(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static <T> ResultVo<T> warn(String msg) {
        return ResultVo.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static <T> ResultVo<T> warn(String msg, T data) {
        return new ResultVo<T>(HttpStatus.WARN, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static <T> ResultVo<T> error() {
        return ResultVo.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 错误消息
     */
    public static <T> ResultVo<T> error(String msg) {
        return ResultVo.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 错误消息
     */
    public static <T> ResultVo<T> error(String msg, T data) {
        return new ResultVo<>(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 错误消息
     */
    public static <T> ResultVo<T> error(int code, String msg) {
        return new ResultVo<>(code, msg, null);
    }

    /**
     * 是否为成功消息
     *
     * @return 结果
     */
    public boolean isSuccess() {
        return Objects.equals(HttpStatus.SUCCESS, code);
    }

    /**
     * 是否为警告消息
     *
     * @return 结果
     */
    public boolean isWarn() {
        return Objects.equals(HttpStatus.WARN, code);
    }

    /**
     * 是否为错误消息
     *
     * @return 结果
     */
    public boolean isError() {
        return Objects.equals(HttpStatus.ERROR, code);
    }
}
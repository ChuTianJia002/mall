package com.neutron.edge.commons.base;

import org.springframework.core.NestedRuntimeException;

/**
 * @author LiuJiaJia
 * @version 1.0
 * @description 基础异常类
 * @date 2022/11/6 9:58
 */
public class BaseException extends NestedRuntimeException {

    private static final long serialVersionUID = 1412085389887154939L;

    /**
     * 自定义异常编码
     */
    private String errCode;

    /**
     * 抛出异常信息
     * @param message 异常信息
     * @author Liu JiaJia
     * @date 2022/11/6 12:51
     */
    public BaseException(String message) {
        super(message);
    }

    /**
     * 抛出异常状态码
     * @param errCode 常状态码
     * @param message 异常信息
     * @author Liu JiaJia
     * @date 2022/11/6 12:54
     */
    public BaseException(String errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

    /**
     * 抛出异常信息详情，嵌套其他异常
     * @param message 异常详情
     * @param cause 其他异常
     * @author Liu JiaJia
     * @date 2022/11/6 12:51
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }





}

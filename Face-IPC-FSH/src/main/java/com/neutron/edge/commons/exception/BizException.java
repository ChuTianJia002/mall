package com.neutron.edge.commons.exception;


import com.neutron.edge.commons.base.BaseException;

/**
 * @author LiuJiaJia
 * @version 1.0
 * @description 异常类
 * @date 2022/11/6 12:47
 */
public class BizException extends BaseException {

    private static final long serialVersionUID = -8705253478122503487L;


    /**
     * 抛出异常信息
     * @param message 异常信息
     * @author Liu JiaJia
     * @date 2022/11/6 12:51
     */
    public BizException(String message){
        super(message);
    }

    /**
     * 抛出异常状态码
     * @param errCode 状态码
     * @param message 异常信息
     * @return null
     */
    public BizException(String errCode , String message){
        super(errCode, message);
    }

    /**
     * 抛出异常信息详情，嵌套其他异常
     * @param message 异常信息
     * @param cause 其他异常
     */
    public BizException(String message , Throwable cause){
        super(message, cause);
    }

}

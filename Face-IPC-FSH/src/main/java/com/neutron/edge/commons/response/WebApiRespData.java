package com.neutron.edge.commons.response;

import com.neutron.edge.commons.base.BaseRespData;
import io.swagger.annotations.ApiModel;

/**
 * @author LiuJiaJia
 * @version 1.0
 * @description 响应Data平台返回结果模型
 * @date 2022/11/8 18:10
 */
@ApiModel(value="WebApiRespData",description="响应平台返回结果模型")
public class WebApiRespData<T> extends BaseRespData<T> {

    /**
     * 默认返回对象
     * @param result 返回对象
     * @return null
     */
    public WebApiRespData(T result) {
        super(BaseRespData.DEFAULT_SUCCESS_CODE, BaseRespData.DEFAULT_SUCCESS_MESSAGE, result);
    }


    /**
     * 特定状态码+结果 对象
     * @param code 特定状态码
     * @param result 结果
     * @return null
     */
    public WebApiRespData(Integer code, T result) {
        super(code, "", result);
        if (code.equals(BaseRespData.DEFAULT_SUCCESS_CODE)) {
            this.setMessage(BaseRespData.DEFAULT_SUCCESS_MESSAGE);
        } else {
            this.setMessage(BaseRespData.DEFAULT_ERROR_MESSAGE);
        }
    }

    /**
     * 特定状态码+特定消息+结果 对象
     * @param code 特定状态码
     * @param message 特定消息
     * @param result 结果
     * @return null
     */
    public WebApiRespData(Integer code, String message, T result) {
        super(code, message, result);
    }

    /**
     *  默认成功（无返结果对象）
     * @return WebApiRespData<Object>
     */
    public static WebApiRespData<Object> success() {
        return new WebApiRespData<>(BaseRespData.DEFAULT_SUCCESS_CODE, BaseRespData.DEFAULT_SUCCESS_MESSAGE, null);
    }

    /**
     *  默认成功（有结果对象）
     * @return WebApiRespData<Object>
     */
    public static <T> WebApiRespData<T> success(T object) {
        return new WebApiRespData<>(BaseRespData.DEFAULT_SUCCESS_CODE, BaseRespData.DEFAULT_SUCCESS_MESSAGE, object);
    }

    /**
     *  成功 返回特定状态码+特定提示信息+结果对象
     * @return WebApiRespData<Object>
     */
    public static <T> WebApiRespData<T> success(Integer code, String message, T object) {
        return new WebApiRespData<>(code, message, object);
    }

    /**
     *  默认失败（无返结果对象）
     * @return WebApiRespData<Object>
     */
    public static WebApiRespData<Object> error() {
        return new WebApiRespData<>(BaseRespData.DEFAULT_ERROR_CODE, BaseRespData.DEFAULT_ERROR_MESSAGE, new Object[0]);
    }

    /**
     * code：默认错误返回：102（参数错误）
     * @param message 返回信息
     * @return WebApiRespData<Object>
     */
    public static WebApiRespData<Object> error(String message) {
        return new WebApiRespData<>(BaseRespData.DEFAULT_ERROR_CODE, message, new Object[0]);
    }
    
    /**
     * 特殊错误返回
     * @param code 错误码
     * @param message 错误信息
     * @return WebApiRespData
     */
    public static WebApiRespData<Object> error(Integer code, String message) {
        return new WebApiRespData<>(code, message, new Object[0]);
    }
    
    /**
     *
     * @param code 错误码
     * @param message 错误信息
     * @param object 错误数据
     * @param <T> 错误对象类型
     * @return WebApiRespData
     */
    public static <T> WebApiRespData<T> error(Integer code, String message, T object) {
        return new WebApiRespData<>(code, message, object);
    }


}

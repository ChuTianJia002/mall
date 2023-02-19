package com.neutron.edge.commons.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

/**
 * 响应结果基础返回体
 * @author LiuJiaJia
 */
@Component
@ApiModel(value="BaseRespData",description="基础返回结果模型")
public class BaseRespData<T> {

    public static final String DEFAULT_SUCCESS_MESSAGE = "请求成功";

    public static final String DEFAULT_ERROR_MESSAGE = "请求失败";

    /**
     * 默认请求成功码：0
     */
    public static final Integer DEFAULT_SUCCESS_CODE = 0;

    /**
     * 默认错误码：102（参数错误）
     */
    public static final Integer DEFAULT_ERROR_CODE = 102;

    /**
     * 响应状态码
     */
    @ApiModelProperty("状态码（0：请求成功）")
    private Integer code = DEFAULT_SUCCESS_CODE;

    /**
     * 响应信息
     */
    @ApiModelProperty("消息描述")
    private String message;

    /**
     * 响应对象
     */
    @ApiModelProperty("消息对象")
    private T result;

    public BaseRespData() {
    }

    public BaseRespData(Integer code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}


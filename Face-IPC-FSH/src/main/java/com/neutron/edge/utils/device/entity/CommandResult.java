package com.neutron.edge.utils.device.entity;

import lombok.Data;

import javax.annotation.security.DenyAll;

/**
 * @author Liu JiaJia
 * @version 1.0
 * @description 返回心跳信息实体类
 * @date 2023/2/14 17:54
 */
@Data
public class CommandResult {
    private Integer ReturnCode;
    private String ReturnStr;
    private boolean PushEventInfo;
    private boolean PushEventPic;
    
    public CommandResult()
    {
        ReturnCode = 0;
        ReturnStr = "OK";
        PushEventInfo = true;
        PushEventPic = true;
    }
    
    public CommandResult(Integer ReturnCode, String ReturnStr, boolean PushEventInfo, boolean PushEventPic)
    {
        this.ReturnCode = ReturnCode;
        this.ReturnStr = ReturnStr;
        this.PushEventInfo = PushEventInfo;
        this.PushEventPic = PushEventPic;
    }
    
}

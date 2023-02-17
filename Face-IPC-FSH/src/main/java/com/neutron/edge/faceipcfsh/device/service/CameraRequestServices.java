package com.neutron.edge.faceipcfsh.device.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neutron.edge.commons.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @description 设备主动访问请求业务
 * @author Liu JiaJia
 * @date 2023/2/14 17:12
 */
@Slf4j
@Service
public class CameraRequestServices {
    
    
    /**
     * 设备主动访问请求数据包校验
     * @param valueStr 请求数据包
     * @param ip 请求IP地址来源
     * @param type 请求类型
     * @return String 设备序列号
     */
    public String judgeRequest(String valueStr, String ip, String type) {
        //判断设备请求信息
        if (StringUtils.isBlank(valueStr)) {
            log.error("设备心跳请求失败，发送空包，设备ip为:{}: ", ip);
            throw new BizException("设备心跳有误,发送心跳失败！");
        }
        log.info(valueStr);
        String heart = "heart"; String command = "command";
    
        JSONObject Obj = JSON.parseObject(valueStr);
        // 从请求数据包 获取设备序列号
        String DevType = Obj.getString("DevType");
        String DevNo = Obj.getString("DevNo");
        String serialNum = Obj.getString("SerialNum");
        String LocalTime = Obj.getString("LocalTime");
        String ReportCount = Obj.getString("ReportCount");
    
        if (DevType == null || DevNo == null ||
                serialNum == null || StringUtils.isBlank(serialNum) ||
                LocalTime == null) {
            log.error("设备主动访问请求失败，参数错误，设备ip为:{}: ",ip);
            throw new BizException("设备{}有误,发送心跳失败！",type);
        }else if(type.equals(heart) && ReportCount == null ){
            log.error("设备心跳请求失败，参数错误，设备ip为:{}: ", ip);
            throw new BizException("设备心跳有误,发送心跳失败！");
        }else{
            return serialNum;
        }
        
    }
    
 
    
}

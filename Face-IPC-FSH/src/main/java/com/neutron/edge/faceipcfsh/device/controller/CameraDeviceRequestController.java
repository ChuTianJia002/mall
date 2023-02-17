package com.neutron.edge.faceipcfsh.device.controller;

import com.alibaba.fastjson.JSONObject;
import com.neutron.edge.commons.constants.config.RedisConfig;
import com.neutron.edge.commons.constants.defined.DeviceDefined;
import com.neutron.edge.commons.exception.BizException;
import com.neutron.edge.faceipcfsh.device.service.CameraRequestServices;
import com.neutron.edge.utils.device.CameraUtil;
import com.neutron.edge.utils.device.ResultAsJsonUtil;
import com.neutron.edge.utils.http.HttpUtils;
import com.neutron.edge.utils.jedis.JedisPoolUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiuJiaJia
 */
@Slf4j
@Api(tags = "设备自动请求接口")
@RestController
public class CameraDeviceRequestController {
    
    /**
     * 今日数据总请求次数
     */
    private volatile Integer times = 0;
    
    @Autowired
    private CameraRequestServices cameraRequestServices;
    
    /**
     * 接收设备心跳请求-json
     * 设备定时访问
     * @param request 设备Http请求包
     * @return 返回心跳信息
     */
    @ApiOperation(value = "接收设备心跳请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "DevType",value = "设备类型",paramType ="body",required = false,dataType ="String"),
            @ApiImplicitParam(name = "DevNo",value = "管理域中可唯一标识",paramType ="body ",required = false,dataType ="String"),
            @ApiImplicitParam(name = "serialNum",value = "设备序列号",paramType ="body ",required = true,dataType ="String"),
            @ApiImplicitParam(name = "LocalTime",value = "本地时间",paramType ="body ",required = false,dataType ="String"),
            @ApiImplicitParam(name = "ReportCount",value = "截至当前已经报告的总次数",paramType ="body ",required = false,dataType ="Integer",defaultValue = "0")
    })
    @RequestMapping(value = "/DeviceEndianHeartbeat", method = RequestMethod.PUT, produces = "application/json")
    public String heartbeat(HttpServletRequest request) {
    
        log.info("device send heartbeat");
        String valueStr = CameraUtil.resolveRequest(request);
        String ip = HttpUtils.getAddr(request);
        if (StringUtils.isBlank(valueStr)) {
            log.error("设备心跳请求参数有误，设备ip为:{}: ",ip);
            throw new BizException("设备心跳有误");
        }
        String type = "heart";
        //判断设备请求数据包信息格式 并获取序列号
        String serialNum = cameraRequestServices.judgeRequest(valueStr,ip,type);
    
        Jedis jedis = JedisPoolUtils.getJedis();
        String returnHeartBeat = "";
        try {
            
            // 判断redis 返回信息是否存在(是否有操作指令下发：StartCommand)  jedis = JedisPoolUtils.getJedis()
            if (!jedis.exists(DeviceDefined.HEART_BEAT + serialNum)){
                // 不存在  新生成一个返回信息放入redis+ex（空指令）
                JSONObject jsonObject = new JSONObject();
                ResultAsJsonUtil.getResultJsonObject(jsonObject,jedis,serialNum);
            }else {
                //存在 更新命令过期时间（告知设备右指令下发，下次主动访问命令接口）
                jedis.expire(DeviceDefined.HEART_BEAT + serialNum, RedisConfig.ExpireTime_Result);
            }
            returnHeartBeat = jedis.get(DeviceDefined.HEART_BEAT + serialNum);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
    
        return returnHeartBeat;
    }

    
    /**
     * 向设备请求获取指令
     * 设备发送心跳时从返回信息获知有指令下发
     * 主动访问此接口
     * @param request 设备获取指令接口
     * @return java.lang.String 返回下发的操作指令
     */
    @ApiOperation(value = "设备请求获取指令")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "DevType",paramType ="body ",value = "设备类型",required = true),
            @ApiImplicitParam(name = "DevNo",paramType ="body ",value = "管理域中可唯一标识",required = false),
            @ApiImplicitParam(name = "serialNum",paramType ="body ",value = "设备序列号",required = true),
            @ApiImplicitParam(name = "LocalTime",paramType ="body ",value = "本地时间",required = true),
            @ApiImplicitParam(name = "Command",paramType ="body ",value = "上次操作的命令",required = false),
            @ApiImplicitParam(name = "CommandSeq",paramType ="body ",value = "上次操作的命令的序号",required = false)
    })
    @RequestMapping(value = "/DeviceEndianCommand", method = RequestMethod.PUT, produces = "application/json")
    public String deviceEndianCommand(HttpServletRequest request) {
        log.info("请求接收指令消息");
        // 获取设备请求信息
        String valueStr = CameraUtil.resolveRequest(request);
        String ip = HttpUtils.getAddr(request);
        String type = "command";
        // 请求指令数据包格式判断
        cameraRequestServices.judgeRequest(valueStr,ip,type);
    
        return "";
    }
    
    /**
     * 设备向服务端推送事件
     * 有推送事件，才主动访问
     * @param request 主动推送Http数据包
     */
    @RequestMapping(value = "/DeviceEndianEvent", method = RequestMethod.POST)
    public void deviceEndianEvent(HttpServletRequest request){
    
    }
    
    
    
    
}

package com.neutron.edge.utils.device;

import com.alibaba.fastjson.JSONObject;
import com.neutron.edge.commons.constants.config.RedisConfig;
import com.neutron.edge.commons.constants.defined.DeviceDefined;
import com.neutron.edge.utils.device.entity.CommandResult;
import redis.clients.jedis.Jedis;

/**
 *
 * @author LiuJiaJia
 * @version 1.0
 * @description 返回结果 JSON 转换工具类
 * @date 2022/11/6 14:48
 */
public class ResultAsJsonUtil {


    public static void getResultJsonObject(JSONObject jsonObject, Jedis jedis, String serialNum) {
        //不存在 重新放入一个操作指令执行结果返回信息
        jsonObject.put("ReturnCode",0);
        jsonObject.put("ReturnStr","OK");
        jsonObject.put("PushEventInfo",true);
        jsonObject.put("PushEventPic",true);
        String s = jsonObject.toJSONString();
        // CommandResult commandResult = new CommandResult();
        // String s = new JSONObject(commandResult).toJSONString();
        jedis.setex(DeviceDefined.HEART_BEAT + serialNum, RedisConfig.ExpireTime_Result,s);
        
    }
}

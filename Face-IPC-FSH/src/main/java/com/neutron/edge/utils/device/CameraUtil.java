package com.neutron.edge.utils.device;


import com.neutron.edge.commons.constants.config.RedisConfig;
import com.neutron.edge.commons.constants.defined.DeviceDefined;
import com.neutron.edge.commons.exception.BizException;
// import com.neutron.edge.faceipcfsh.device.entity.CameraCommandMap;
import com.neutron.edge.utils.device.mapconfig.CameraCommandMap;
import com.neutron.edge.utils.jedis.JedisPoolUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;


/**
 *
 * @author LiuJiaJia
 * @version 1.0
 * @description 设备发送接受指令工具类
 * @date 2022/11/6 10:15
 */
@Slf4j
public class CameraUtil {

    /**
     * 命令开始执行，为执行结果设置过时间
     * @param cameraId 设备序列号
     * @author Liu JiaJia
     * @date 2022/11/6 12:12
     */
    public static void commandStart(String cameraId) {
        
        try (Jedis jedis = JedisPoolUtils.getJedis()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ReturnCode", 0);
            jsonObject.put("ReturnStr", "OK");
            jsonObject.put("PushEventInfo", true);
            jsonObject.put("PushEventPic", true);
            jsonObject.put("StartCommand", true);
            String s = jsonObject.toString();
            
            jedis.setex(DeviceDefined.HEART_BEAT + cameraId, RedisConfig.expireTimeCommandInformTime, s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // finally {
        //     jedis.close();
        // }

    }

    /**
     * 处理解析http请求，返回请求信息
     * @param request  http请求
     * @return java.lang.String
     * @author Liu JiaJia
     * @date 2022/11/6 12:21
     * edit: new StringBuilder("");
     */
    public static String resolveRequest(HttpServletRequest request) {

        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            bufferedReader = request.getReader();
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 处理操作命令
     * @param cameraId 设备序列号
     * @param command 操作指令名前缀
     * @param workingException 指令进行中前缀
     * @author Liu JiaJia
     * @date 2022/11/6 16:15
     */
    public static void dealCommand(String cameraId, String command, String workingException) {
        // 判断命令集合中该设备是否已经存在此命令
        if (CameraCommandMap.commandMap.containsKey(command + cameraId)) {
            throw new BizException(workingException);
        }
        // 不存在则把命令放入 命令集合中
        CameraCommandMap.commandMap.put(command + cameraId, 0);
        // 更新一下心跳信息-通知设备有操作指令下发
        commandStart(cameraId);
    }

    /**
     * 获取命令操作结果
     * @param cameraId 设备序列号
     * @param commandFlag 操作指令标志前缀
     * @return java.lang.String
     * @author Liu JiaJia
     * @date 2022/11/7 16:01
     */
    public static String getReturn(String cameraId, String commandFlag) {
        long start = System.currentTimeMillis();
        // Jedis jedis = null;
        //一直监听redis中 是否存在该设备此指令执行结果
        while (true) {
            
            try(Jedis jedis = JedisPoolUtils.getJedis()){
                // jedis = JedisPoolUtils.getJedis()
                String returnKey = "response_" + commandFlag + cameraId;
                Boolean exists = jedis.exists(returnKey);
                //如果存在，返回信息
                if (exists) {
                    String response = jedis.get(returnKey);
                    jedis.del(returnKey);
                    return response;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // finally {
            //     jedis.close();
            // }
            
            //等待非重启操作指令返回结果超过10秒 抛出超时异常
            long end = System.currentTimeMillis();
            if ((end - start > RedisConfig.expireTimeWaitCommandReturnTime)
                    && (!commandFlag.equals(DeviceDefined.OPT_frmDeviceReboot))) {
                throw new BizException("查询超时，请稍后再试");
            }
        }
    }

    

}

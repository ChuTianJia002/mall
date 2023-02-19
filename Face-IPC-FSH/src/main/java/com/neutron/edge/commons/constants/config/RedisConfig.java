package com.neutron.edge.commons.constants.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Liu JiaJia
 * @version 1.0
 * @description redis配置类
 * @date 2023/2/14 16:27
 */
@Component
public class RedisConfig {
    
    //***********************Redis访问地址信息*************************//
    /**
     * redis主机地址
     */
    public static String redisHost;
    /**
     * redis 访问端口号
     */
    public static String redisPort;
    /**
     * redis 访问密码
     */
    public static String redisPassword;
    //==========================================================================//
    
    
    //******************************Redis 缓存过期时间************************//
    /**
     * 时间单位: 时分秒
     */
    public static String timeUnitHour;
    public static String timeUnitMinute;
    public static String timeUnitSecond;
    
    /**
     * 心跳信息有效期
     */
    public static Integer expireTimeHeartTime;
    /**
     * 操作指令存活时间
     */
    public static Integer expireTimeCommandAliveTime;
    
    /**
     * 操作指令通知过期时间(通知设备)
     */
    public static Integer expireTimeCommandInformTime;
    
    /**
     * 更新操作指令通知过期时间
     */
    public static Integer expireTimeCommand;
    /**
     * 执行指令返回结果过期时间
     */
    public static Integer expireTimeCommandReturn;
    /**
     * 等待返回结果过期时间
     * 命令执行超时时间
     */
    public static Integer expireTimeWaitCommandReturnTime;
    
    
    
    //=======================================================================//
    
    
    @Value("${spring.redis.host}")
    public void setRedisHost(String redisHost) {
        RedisConfig.redisHost = redisHost;
    }
    
    @Value("${spring.redis.port}")
    public void setRedisPort(String redisPort) {
        RedisConfig.redisPort = redisPort;
    }
    
    @Value("${spring.redis.password}")
    public void setRedisPassword(String redisPassword) {
        RedisConfig.redisPassword = redisPassword;
    }
    
    
    @Value("${timeUnit.hour}")
    public void setTimeUnitHour(String timeUnitHour) {
        RedisConfig.timeUnitHour = timeUnitHour;
    }
    
    @Value("${timeUnit.minute}")
    public void setTimeUnitMinute(String timeUnitMinute) {
        RedisConfig.timeUnitMinute = timeUnitMinute;
    }
    
    @Value("${timeUnit.second}")
    public void setTimeUnitSecond(String timeUnitSecond) {
        RedisConfig.timeUnitSecond = timeUnitSecond;
    }
    
    @Value("${redisExpireTime.heartTime}")
    public void setExpireTimeHeartTime(Integer expireTimeHeartTime) {
        RedisConfig.expireTimeHeartTime = expireTimeHeartTime;
    }
    
    @Value("${redisExpireTime.commandAliveTime}")
    public void setExpireTimeCommandAliveTime(Integer expireTimeCommandAliveTime) {
        RedisConfig.expireTimeCommandAliveTime = expireTimeCommandAliveTime;
    }
    
    @Value("${redisExpireTime.commandInformTime}")
    public void setExpireTimeCommandInformTime(Integer expireTimeCommandInformTime) {
        RedisConfig.expireTimeCommandInformTime = expireTimeCommandInformTime;
    }
    
    @Value("${redisExpireTime.commandTime}")
    public void setExpireTimeCommand(Integer expireTimeCommand) {
        RedisConfig.expireTimeCommand = expireTimeCommand;
    }
    
    @Value("${redisExpireTime.commandReturnTime}")
    public void setExpireTimeCommandReturn(Integer expireTimeCommandReturn) {
        RedisConfig.expireTimeCommandReturn = expireTimeCommandReturn;
    }
    
    @Value("${redisExpireTime.waitCommandReturnTime}")
    public void setExpireTimeWaitCommandReturnTime(Integer expireTimeWaitCommandReturnTime) {
        RedisConfig.expireTimeWaitCommandReturnTime = expireTimeWaitCommandReturnTime;
    }
}

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
     * 时间单位
     */
    public static String timeUnit_Hour;
    public static String timeUnit_Minute;
    public static String timeUnit_Second;
    /**
     * 操作指令过期时间
     */
    public static Integer ExpireTime_Command;
    /**
     * 执行返回结果过期时间
     */
    public static Integer ExpireTime_Result;
    
    
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
    public void setTimeUnit_h(String timeUnit_Hour) {
        RedisConfig.timeUnit_Hour = timeUnit_Hour;
    }
    
    @Value("${timeUnit.minute}")
    public void setTimeUnit_m(String timeUnit_Minute) {
        RedisConfig.timeUnit_Minute = timeUnit_Minute;
    }
    
    @Value("${timeUnit.second}")
    public void setTimeUnit_s(String timeUnit_Second) {
        RedisConfig.timeUnit_Second = timeUnit_Second;
    }

    @Value("${redisExpireTime.command}")
    public void setExpireTimeCommand(Integer expireTimeCommand) {
        ExpireTime_Command = expireTimeCommand;
    }

    @Value("${redisExpireTime.result}")
    public void setExpireTimeResult(Integer expireTimeResult) {
        ExpireTime_Result = expireTimeResult;
    }
}

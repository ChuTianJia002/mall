package com.neutron.edge.redisConfigTest;

import com.neutron.edge.commons.constants.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @version 1.0
 * @author Liu JiaJia
 * @description TODO
 * @date 2023/2/17 16:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class RedisTest {
    
    // @Autowired
    // RedisConfig redisConfig;
    //
    // @Test
    // public void getRedisParam(){
    //     System.out.println("host: " + RedisConfig.redisHost);
    //     System.out.println("host: " + redisConfig.redisPort);
    //     System.out.println("host: " + redisConfig.redisPassword);
    // }
    
    @Test
    public void getRedisParam2(){
        System.out.println("host: " + RedisConfig.redisHost);
        System.out.println("port: " + RedisConfig.redisPort);
        System.out.println("password: " + RedisConfig.redisPassword);
    }
    
    @Test
    public void getRedisTimeUnit(){
        System.out.println("Hour: " + RedisConfig.timeUnitHour);
        System.out.println("Minute: " + RedisConfig.timeUnitMinute);
        System.out.println("Second: " + RedisConfig.timeUnitSecond);
    }
    
    @Test
    public void getRedisExpireTime(){
        System.out.println("expireTimeHeartTime: " + RedisConfig.expireTimeHeartTime);
        System.out.println("expireTimeCommandAliveTime: " + RedisConfig.expireTimeCommandAliveTime);
        System.out.println("expireTimeCommandInformTime: " + RedisConfig.expireTimeCommandInformTime);
        System.out.println("expireTimeCommand: " + RedisConfig.expireTimeCommand);
        System.out.println("expireTimeCommandReturn: " + RedisConfig.expireTimeCommandReturn);
        System.out.println("expireTimeWaitCommandReturnTime: " + RedisConfig.expireTimeWaitCommandReturnTime);
        
    }
    
}

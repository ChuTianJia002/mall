package com.neutron.edge.utils.jedis;


import com.neutron.edge.commons.constants.config.RedisConfig;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 *
 * @author LiuJiaJia
 * @version 1.0
 * @description redis连接池工具类
 * @date 2022/11/6 10:23
 */
@Slf4j
public class JedisPoolUtils {

    /**
     * Jedis 缓冲池实例对象
     */
    private static JedisPool pool;

    /**
     *  建立连接池
     *  (真实环境，一般把配置参数缺抽取出来。)
     * @author Liu JiaJia
     * @date 2022/11/6 10:31
     */
    private static void createJedisPool() {

        // 建立连接池配置参数
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(1000);
        // 设置最大阻塞时间，记住是毫秒数milliseconds
        config.setMaxWaitMillis(1000);
        // 设置最大空闲连接
        config.setMaxIdle(10);

        // 根据配置参数创建redis连接池
        pool = new JedisPool(config, RedisConfig.redisHost, Integer.parseInt(RedisConfig.redisPort),1000,RedisConfig.redisPassword,1);
    }

    /**
     * 在多线程环境同步初始化
     * @author Liu JiaJia
     * @date 2022/11/6 11:30
     */
    private static synchronized void poolInit() {
        if (pool == null) {
            createJedisPool();
        }
    }


    /**
     * 获取数据
     * @param key 待获取的数据的键
     * @return java.lang.String
     * @author Liu JiaJia
     * @date 2022/11/6 11:29
     */
    public static String get(String key) {
        //初始化
        poolInit();
        String value = null;
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            log.error("获取redis数据异常:", e);
        } finally {
            //返还到连接池
            closeJedis(jedis);
        }

        return value;
    }

    /**
     * 获取一个连接
     * @return redis.clients.jedis.Jedis
     * @author Liu JiaJia
     * @date 2022/11/6 11:30
     */
    public static Jedis getJedis() {

        if (pool == null) {
            poolInit();
        }
        return pool.getResource();
    }

    /**
     * 释放一个连接
     * @param jedis redis连接对象
     * @author Liu JiaJia
     * @date 2022/11/6 11:28
     */
    public static void closeJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


}

package com.way.fact.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *
 * redisTemplate集成Redis工具类
 * @author yrz
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    /**
     * 批量插入集合到List
     * @param key 键值
     * @param obj 储存对象
     */
    public void forList(String key , Object obj){
        redisTemplate.opsForList().leftPushAll(key,obj);
    }

    /**
     * 获取List
     * @param key 键值
     * @param star 起点
     * @param end 终点
     * @return 列表
     */
    public Object getList(String key , int star , int end){
        try {
            return redisTemplate.opsForList().range(key,star,end);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除List
     * @param key 键值
     * @param count 长度
     * @param value 对应值
     */
    public void delList(String key , int count , String value){
        redisTemplate.opsForList().remove(key , count , value);
    }

    /**
     * 获取List长度
     * @param key 键值
     * @return 长度
     */
    public Long sizeList(String key){
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 添加Set
     * @param key 键值
     * @param list 添加对象
     */
    public void setSet(String key , Object list){
        redisTemplate.opsForSet().add(key , list);
    }

    /**
     * 获取Set
     * @param key 键值
     * @return 对应值
     */
    public Set getSet(String key){
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 设置过期时间
     * @param key 键值
     * @param lifetime 过期时间
     * @param timeUnit 时间格式
     * @return 过期
     */
    public void setKeyLifeTime(String key , long lifetime , TimeUnit timeUnit){
        redisTemplate.expire(key, lifetime, timeUnit);
    }

}

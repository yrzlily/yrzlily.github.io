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
     * @param key
     * @param obj
     */
    public void forList(String key , Object obj){
        redisTemplate.opsForList().leftPushAll(key,obj);
    }

    /**
     * 获取List
     * @param key
     * @param star
     * @param end
     * @return
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
     * @param key
     * @param count
     * @param value
     */
    public void delList(String key , int count , String value){
        redisTemplate.opsForList().remove(key , count , value);
    }

    /**
     * 获取List长度
     * @param key
     * @return
     */
    public Long sizeList(String key){
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 添加Set
     * @param key
     * @param list
     */
    public void setSet(String key , Object list){
        redisTemplate.opsForSet().add(key , list);
    }

    /**
     * 获取Set
     * @param key
     * @return
     */
    public Set getSet(String key){
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 设置过期时间
     * @param key
     * @param lifetime
     * @param timeUnit
     * @return
     */
    public void setKeyLifeTime(String key , long lifetime , TimeUnit timeUnit){
        redisTemplate.expire(key, lifetime, timeUnit);
    }

}

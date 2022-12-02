package com.feather.bz.common.service;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.service
 * @className: RedisServiceImpl
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-12-02 11:03
 * @version: 1.0
 */
@Slf4j
@Service
public class RedisService  {


    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private ValueOperations<String, String> valueOperations;

    @PostConstruct
    public void init() {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
        valueOperations = redisTemplate.opsForValue();
    }



    public boolean delete(String key) {
        boolean result = redisTemplate.delete(key);
        log.info("delete from redis, key is: {}", key);
        return result;
    }


    public Long getExpireTime(String key) {
        return valueOperations.getOperations().getExpire(key);
    }

    /**
     * 设置key的过期时间
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key,long time){
        try{
            if (time > 0){
                redisTemplate.expire(key,time,TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            log.error("Redis设置过期时间失败:"+e);
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     * @param key
     * @return
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean exists(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 根据key删除缓存
     * @param keys
     * @return
     */
    public boolean remove(String... keys){
        try {
            for (int i = 0 ;i < keys.length;i++){
                redisTemplate.delete(keys[i]);
            }
            return true;
        }catch (Exception e){
            log.error("[Redis删除Key失败：]",e);
            return false;
        }
    }

    //=========================String====================

    /**
     * String类型的缓存存入
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key,String value){
        try{
            redisTemplate.opsForValue().set(key,value);
            log.info("key={}, value is: {} into redis cache", key, value);
            return true;
        }catch (Exception e){
            log.error("[Redis保存数据失败:]",e);
            return false;
        }
    }

    /**
     * 设置Object类型的
     * @param key
     * @param value
     * @return
     */
    public boolean setObj(String key,Object value){
        try{
            redisTemplate.opsForValue().set(key, JSON.toJSONString(value));
            return true;
        }catch (Exception e){
            log.error("[Redis保存数据失败:]",e);
            return false;
        }
    }

    /**
     * String类型的缓存存入并设置过期时间
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean set(String key,String value,Long expireTime){
        try {
            redisTemplate.opsForValue().set(key,value,expireTime,TimeUnit.SECONDS);
            return true;
        }catch (Exception e){
            log.error("[Redis保存数据失败:]",e);
            return false;
        }
    }

    /**
     * 获取String类型的缓存
     * @param key
     * @return
     */
    public String get(String key){
        return key == null ? null :  valueOperations.get(key);
    }

    /**
     * 获取Object类型的
     * @param key
     * @return
     */
    public Object getObj(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    //============================hash=====================

    /**
     * 获取到某个键值对
     * @param key
     * @param field
     * @return
     */
    public String hget(String key,String field){
        return key == null ? null : (String) redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 获取到一个hash表
     * @param key
     * @return
     */
    public Map<Object,Object> hget(String key){
        return key == null ? null : redisTemplate.opsForHash().entries(key);
    }

    /**
     * 设置hash表的一个键值对
     * @param key
     * @param field
     * @param value
     * @return
     */
    public boolean hset(String key, String field, String value) {
        try {
            redisTemplate.opsForHash().put(key, field, value);
            return true;
        } catch (Exception e) {
            log.error("[Redis保存数据失败:]",e);
            return false;
        }
    }

    /**
     * 保存一个hash表
     * @param key
     * @param map
     * @return
     */
    public boolean hmset(String key, Map<String,Object> map){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            return true;
        }catch (Exception e){
            log.error("[Redis保存数据失败:]",e);
            return false;
        }
    }

    /**
     * 保存一个hash表并设置过期时间
     * @param key
     * @param map
     * @param time
     * @return
     */
    public boolean hmset(String key,Map<String,Object> map,long time){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            redisTemplate.expire(key,time,TimeUnit.SECONDS);
            return true;
        }catch (Exception e){
            log.error("Redis保存数据失败:"+e);
            return false;
        }
    }

    /**
     * 设置hash表的一个键值对，并设置过期时间
     * @param key
     * @param field
     * @param value
     * @param time
     * @return
     */
    public boolean hset(String key, String field, String value,long time) {
        try {
            redisTemplate.opsForHash().put(key, field, value);
            if (time > 0){
                redisTemplate.expire(key,time,TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("Redis保存数据失败:"+e);
            return false;
        }
    }

    /**
     * 删除hash表里的多项
     * @param key
     * @param fields
     */
    public void hdel(String key,String... fields){
        redisTemplate.opsForHash().delete(key,fields);
    }

    /**
     * 判断hash表里是否包含某项
     * @param key
     * @param filed
     * @return
     */
    public boolean hHasKey(String key ,String filed){
        return redisTemplate.opsForHash().hasKey(key,filed);
    }

    //==============================Set===================

    /**
     * 向Set集合中添加数据
     * @param key
     * @param value
     * @return
     */
    public boolean sadd(String key,String... value){
        try {
            return redisTemplate.opsForSet().add(key,value) > 0 ? true : false;
        }catch (Exception e){
            log.error("Redis保存数据失败:"+e);
            return false;
        }
    }

    /**
     * 获取Set集合中的所有元素
     * @param key
     * @return
     */
    public Set<String> members(String key){
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 判断元素是否是Set的一个元素
     * @param key
     * @param value
     * @return
     */
    public boolean isMember(String key,String value){
        return  redisTemplate.opsForSet().isMember(key,value);
    }

    /**
     * 删除Set集合中的元素
     * @param key
     * @param value
     * @return
     */
    public void srem(String key,String... value){
        redisTemplate.opsForSet().remove(key,value);
    }

    //==================list======================

}

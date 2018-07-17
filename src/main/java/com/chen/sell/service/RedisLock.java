package com.chen.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.xml.core.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

@Commit
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 加锁
     * @param key
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key,String value){
        if(stringRedisTemplate.opsForValue().setIfAbsent(key,value)){
            return true;
        }

        String currentValue = stringRedisTemplate.opsForValue().get(key);
        //如果锁过期
        if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue)<System.currentTimeMillis()){
            //获取上一个锁的时间
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key,value);
            if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)){
                return true;
            }
        }
        return false;
    }

    public void unlock(String key,String value){
        try {
            String currentValue = stringRedisTemplate.opsForValue().get(key);
            if(!StringUtils.isEmpty(currentValue) &&currentValue.equals(value)){
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("[redis分布式锁]解锁异常{}",e.getMessage());
        }
    }

}

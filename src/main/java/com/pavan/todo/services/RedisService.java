package com.pavan.todo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@Slf4j
public class RedisService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public <T> void set(String key, T data, Duration duration) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(data);
        log.info("Redis cache set with {} {} for {}", key, value, duration.toString());
        redisTemplate.opsForValue().set(key, value, duration);
    }

    public <T> void set(String key, T data) throws JsonProcessingException {
        set(key, data, Duration.ofDays(1));
    }

    public <T> T get(String key, Class<T> tClass) throws JsonProcessingException {
        String value = (String) redisTemplate.opsForValue().get(key);
        if (value == null || value.isEmpty()) {
            log.info("Redis cache not found for key {}", key);
            return null;
        }

        log.info("Redis cache found for key {}", key);
        return objectMapper.readValue(value, tClass);
    }

    public void addToList(String key, String value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    public <T> List<T> getList(String key, long start, long end) {
        return (List<T>) redisTemplate.opsForList().range(key, start, end);
    }

    public Long removeFromList(String key, String value) {
        return redisTemplate.opsForList().remove(key, 0, value);
    }

    public void delete(String key) {
        log.info("Redis cache deleted for key {}", key);
        redisTemplate.delete(key);
    }

}

package com.nordnet.blog.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;

@Configuration
public class RedisConfiguration {

    @Bean
    public ReactiveValueOperations<String, String> hash(ReactiveStringRedisTemplate reactiveStringRedisTemplate) {
        return reactiveStringRedisTemplate.opsForValue();
    }
}

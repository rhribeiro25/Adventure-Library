package com.pictet.technologies.adventurelibrary.infrastructure.shared.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.time.Duration;
import java.util.Map;

import static com.pictet.technologies.adventurelibrary.infrastructure.shared.constants.RedisConstants.BOOKS_CACHE;
import static com.pictet.technologies.adventurelibrary.infrastructure.shared.constants.RedisConstants.BOOKS_SEARCH_CACHE;

@EnableCaching
@Configuration
public class RedisCacheConfig {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        ObjectMapper objectMapper = JsonMapper.builder()
                .findAndAddModules()
                .build();

        GenericJacksonJsonRedisSerializer serializer =
                new GenericJacksonJsonRedisSerializer(objectMapper);

        RedisCacheConfiguration defaultConfig =
                RedisCacheConfiguration.defaultCacheConfig()
                        .disableCachingNullValues()
                        .serializeValuesWith(
                                RedisSerializationContext.SerializationPair.fromSerializer(serializer)
                        );

        Map<String, RedisCacheConfiguration> cacheConfigurations = Map.of(
                BOOKS_CACHE, defaultConfig.entryTtl(Duration.ofMinutes(10)),
                BOOKS_SEARCH_CACHE, defaultConfig.entryTtl(Duration.ofMinutes(10))
        );

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(defaultConfig)
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }
}
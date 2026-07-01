package com.pictet.technologies.adventurelibrary.infrastructure.shared.config;

import com.pictet.technologies.adventurelibrary.domain.model.Book;
import com.pictet.technologies.adventurelibrary.domain.model.Game;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.Map;

import static com.pictet.technologies.adventurelibrary.infrastructure.shared.constants.RedisConstants.*;

@EnableCaching
@Configuration
public class RedisCacheConfig {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        RedisCacheConfiguration defaultConfig =
                RedisCacheConfiguration.defaultCacheConfig()
                        .disableCachingNullValues()
                        .entryTtl(Duration.ofMinutes(10));

        JacksonJsonRedisSerializer<Book> bookSerializer =
                new JacksonJsonRedisSerializer<>(Book.class);

        JacksonJsonRedisSerializer<Game> gameSerializer =
                new JacksonJsonRedisSerializer<>(Game.class);

        RedisCacheConfiguration booksConfig =
                defaultConfig.serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(bookSerializer)
                );

        RedisCacheConfiguration gamesConfig =
                defaultConfig.serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(gameSerializer)
                );

        Map<String, RedisCacheConfiguration> cacheConfigurations = Map.of(
                BOOKS_CACHE, booksConfig,
                BOOKS_SEARCH_CACHE, booksConfig,
                GAMES_CACHE, gamesConfig
        );

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(defaultConfig)
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }
}
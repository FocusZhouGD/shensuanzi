package com.example.springcache.zgd.RedisController.config;

import com.example.springcache.zgd.RedisController.EntityHelper;
import com.example.springcache.zgd.RedisController.annotation.RedisExpirationTime;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@Configuration
public class RedisConfigOne extends CachingConfigurerSupport implements RedisCacheManagerBuilderCustomizer {




    private final Environment environment;

    @Autowired
    public RedisConfigOne(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.redis.cluster.lettuce.pool")
    public GenericObjectPoolConfig redisPool() {
        return new GenericObjectPoolConfig();
    }

    @Bean("redisClusterConfiguration")
    @Primary
    public RedisClusterConfiguration redisClusterConfiguration() {
        Map<String, Object> source = new HashMap<>(8);
        source.put("spring.redis.cluster.nodes", this.environment.getProperty("spring.redis.cluster.nodes"));
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
        redisClusterConfiguration.setPassword(this.environment.getProperty("spring.redis.password"));
        return redisClusterConfiguration;
    }

    @Bean("lettuceConnectionFactory")
    @Primary
    public LettuceConnectionFactory lettuceConnectionFactory(GenericObjectPoolConfig redisPool, @Qualifier("redisClusterConfiguration") RedisClusterConfiguration redisClusterConfiguration) {
        LettuceClientConfiguration lettuceClientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(redisPool).build();
        return new LettuceConnectionFactory(redisClusterConfiguration, lettuceClientConfiguration);
    }

    @Bean("redisTemplate")
    @Primary
    public RedisTemplate redisTemplate(@Qualifier("lettuceConnectionFactory") RedisConnectionFactory redisConnectionFactory,
                                       @Qualifier("jacksonRedisSerializer") GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer) {
        return getRedisTemplate(redisConnectionFactory, genericJackson2JsonRedisSerializer);
    }



    @Bean("jacksonRedisSerializer")
    public GenericJackson2JsonRedisSerializer jacksonRedisSerializer(Jackson2ObjectMapperBuilder builder){
        ObjectMapper objectMapper = new ObjectMapper();
        builder.configure(objectMapper);
        objectMapper = objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }





    //实现redis自定义缓存失效时间
    @Override
    public void customize(RedisCacheManager.RedisCacheManagerBuilder builder) {
        builder.withInitialCacheConfigurations(getCacheConfigurations());
    }

    private Map<String, RedisCacheConfiguration> getCacheConfigurations() {
        Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
        /*
         * 收集注解中设置的过期时间
         */
        Class<Cacheable> cacheAbleClass = Cacheable.class;
        Class<CachePut> cachePutClass = CachePut.class;
        Class<RedisExpirationTime> redisExpireClass = RedisExpirationTime.class;
        for (Class<?> e : EntityHelper.getPackageClassByAnnotation("com.zgd.demo", Service.class)) {
            for (Method method : e.getMethods()) {
                if (method.isAnnotationPresent(redisExpireClass)) {
                    if (method.isAnnotationPresent(cacheAbleClass)) {
                        extractCacheInfo(configurationMap, redisExpireClass, method, method.getDeclaredAnnotation(cacheAbleClass).value());
                    }
                    if (method.isAnnotationPresent(cachePutClass)) {
                        extractCacheInfo(configurationMap, redisExpireClass, method, method.getDeclaredAnnotation(cachePutClass).value());
                    }
                }
            }
        }
        return configurationMap;
    }


    /**
     * 读方法上的注解值设置相应缓存的过期时间
     */

    private void extractCacheInfo(Map<String, RedisCacheConfiguration> configurationMap, Class<RedisExpirationTime> redisExpireClass, Method method, String[] value) {
        for (String cacheValue : value) {
            if (!StringUtils.isEmpty(cacheValue)) {
                RedisCacheConfiguration customTtlConfig = getCustomCacheConfiguration(Duration.ofSeconds(method.getDeclaredAnnotation(redisExpireClass).value()));
                configurationMap.put(cacheValue, customTtlConfig);
            }
        }

    }


    @Autowired(required = false)
    private CacheProperties cacheProperties;
    @Autowired
    private GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer;
    /**
     * 获取redis的缓存配置(针对于键)
     *
     */
    private RedisCacheConfiguration getCustomCacheConfiguration(Duration timeToLive) {
        if (cacheProperties != null) {
            return createConfiguration(cacheProperties).entryTtl(timeToLive)
                    .serializeKeysWith(fromSerializer(RedisSerializer.string()))
                    .serializeValuesWith(fromSerializer(genericJackson2JsonRedisSerializer));
        } else {
            return RedisCacheConfiguration.defaultCacheConfig().entryTtl(timeToLive)
                    .serializeKeysWith(fromSerializer(RedisSerializer.string()))
                    .serializeValuesWith(fromSerializer(genericJackson2JsonRedisSerializer));
        }

    }

    private RedisCacheConfiguration createConfiguration(CacheProperties cacheProperties) {
        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        org.springframework.data.redis.cache.RedisCacheConfiguration config = org.springframework.data.redis.cache.RedisCacheConfiguration
                .defaultCacheConfig();
        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }
        return config;
    }


    private RedisTemplate getRedisTemplate(RedisConnectionFactory factory, GenericJackson2JsonRedisSerializer jacksonRedisSerializer) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        // key采用String的序列化方式
        template.setKeySerializer(RedisSerializer.string());
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(RedisSerializer.string());
        // value序列化方式采用jackson
        template.setValueSerializer(jacksonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jacksonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}

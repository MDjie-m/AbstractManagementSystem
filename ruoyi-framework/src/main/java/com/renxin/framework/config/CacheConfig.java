//package com.renxin.framework.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.script.DefaultRedisScript;
//import org.springframework.data.redis.serializer.*;
//
//import java.time.Duration;
//
///**
// * @author liangxuch
// * @describe: 缓存配置 springcache和rdisTemplate配置
// * @date 2023/5/10
// */
//@Configuration
//@EnableCaching
//public class CacheConfig extends CachingConfigurerSupport {
//    @Value("${renxin.name}")
//    private String name;
//
//    /**
//     * 缓存管理器 过期时间为3600，vaule为string类型
//     */
//    @Bean("cacheManager3600ToStr")
//    public RedisCacheManager cacheManager3600ToStr(RedisConnectionFactory redisConnectionFactory) {
//        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofSeconds(3600))
//                .disableCachingNullValues()
//                .prefixCacheNameWith(name + ":")
//                .computePrefixWith(cacheName -> name + ":" + cacheName + ":")
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
//        return RedisCacheManager.builder(redisConnectionFactory)
//                .cacheDefaults(cacheConfiguration)
//                .build();
//    }
//
//    /**
//     * 缓存管理器 过期时间为3600，vaule为json类型
//     */
//    @Bean("cacheManager3600ToJson")
//    @Primary
//    public RedisCacheManager cacheManager3600ToJson(RedisConnectionFactory redisConnectionFactory) {
//        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofSeconds(3600))
//                .disableCachingNullValues()
//                .prefixCacheNameWith(name + ":")
//                .computePrefixWith(cacheName -> name + ":" + cacheName + ":")
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//        return RedisCacheManager.builder(redisConnectionFactory)
//                .cacheDefaults(cacheConfiguration)
//                .build();
//    }
//
//    /**
//     * RedisTemplate
//     * 自定义RedisTemplate，设置key和value的序列化规则
//     * key采用key+::
//     */
//    @Bean
//    @Primary
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
//        // 设置值（value）的序列化采用FastJsonRedisSerializer。
//        redisTemplate.setKeySerializer(new MyKeySerializer());
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        // 设置键（key）的序列化采用StringRedisSerializer。
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        return redisTemplate;
//    }
//
//    @Bean
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory);
//        stringRedisTemplate.setKeySerializer(new MyKeySerializer());
//        return stringRedisTemplate;
//    }
//
//
//    /**
//     * 自定义key序列化器
//     */
//    public class MyKeySerializer implements RedisSerializer<String> {
//        @Override
//        public byte[] serialize(String s) throws SerializationException {
//            return (name + ":" + s).getBytes();
//        }
//
//        @Override
//        public String deserialize(byte[] bytes) throws SerializationException {
//            return new String(bytes);
//        }
//    }
//
//
//   /* @Bean
//    @SuppressWarnings(value = { "unchecked", "rawtypes" })
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory)
//    {
//        RedisTemplate<Object, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory);
//
//        FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);
//
//        // 使用StringRedisSerializer来序列化和反序列化redis的key值
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(serializer);
//
//        // Hash的key也采用StringRedisSerializer的序列化方式
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setHashValueSerializer(serializer);
//
//        template.afterPropertiesSet();
//        return template;
//    }*/
//
//    @Bean
//    public DefaultRedisScript<Long> limitScript()
//    {
//        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
//        redisScript.setScriptText(limitScriptText());
//        redisScript.setResultType(Long.class);
//        return redisScript;
//    }
//
//    /**
//     * 限流脚本
//     */
//    private String limitScriptText()
//    {
//        return "local key = KEYS[1]\n" +
//                "local count = tonumber(ARGV[1])\n" +
//                "local time = tonumber(ARGV[2])\n" +
//                "local current = redis.call('get', key);\n" +
//                "if current and tonumber(current) > count then\n" +
//                "    return tonumber(current);\n" +
//                "end\n" +
//                "current = redis.call('incr', key)\n" +
//                "if tonumber(current) == 1 then\n" +
//                "    redis.call('expire', key, time)\n" +
//                "end\n" +
//                "return tonumber(current);";
//    }
//
//}

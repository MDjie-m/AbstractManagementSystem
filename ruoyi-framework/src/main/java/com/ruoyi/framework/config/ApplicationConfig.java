package com.ruoyi.framework.config;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.ruoyi.common.enums.IEnum;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * 程序注解配置
 *
 * @author ruoyi
 */
@Configuration
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
// 指定要扫描的Mapper类的包的路径
@MapperScan("com.ruoyi.**.mapper")
public class ApplicationConfig
{


    /**
     * localDateTime 序列化器,后台返回给前端时间格式化
     * @return
     */
    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN));
    }

    /**
     * localDateTime 反序列化器，前端传给后台时间格式化
     * @return
     */
    @Bean
    public LocalDateTimeDeserializer localDateTimeDeserializer() {
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN));
    }
    @Bean
    public LocalTimeSerializer localTimeSerializer() {
        return new LocalTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN));
    }

    /**
     * localDateTime 反序列化器，前端传给后台时间格式化
     * @return
     */
    @Bean
    public LocalTimeDeserializer localTimeDeserializer() {
        return new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN));
    }
    @Bean
    public LocalDateSerializer localDateSerializer() {
        return new LocalDateSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN));
    }

    /**
     * localDateTime 反序列化器，前端传给后台时间格式化
     * @return
     */
    @Bean
    public LocalDateDeserializer localDateDeserializer() {
        return new LocalDateDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN));
    }
    /**
     * 时区配置
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization()
    {
        return jacksonObjectMapperBuilder -> {
            Jackson2ObjectMapperBuilder builder =   jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault())
                    .serializerByType(Long.class, ToStringSerializer.instance)
                    .serializerByType(LocalDateTime.class,localDateTimeSerializer())
                    .deserializerByType(LocalDateTime.class,localDateTimeDeserializer())

                    .serializerByType(LocalTime.class,localTimeSerializer())
                    .deserializerByType(LocalTime.class,localTimeDeserializer())

                    .serializerByType(LocalDate.class,localDateSerializer())
                    .deserializerByType(LocalDate.class,localDateDeserializer())

                    .serializerByType(BigInteger.class, ToStringSerializer.instance)
                    .serializerByType(IEnum.class, BaseEnumSerializer.instance)
                    .deserializerByType(IEnum.class, BaseEnumDeserializer.instance)
                    .serializerByType(BigDecimal.class, ToPlainTextString.instance);
//            ObjectMapper objectMapper = builder.timeZone(TimeZone.getDefault())
//                    .serializerByType(Long.class, ToStringSerializer.instance)
//                    .serializerByType(BigInteger.class, ToStringSerializer.instance)
//                    .serializerByType(IEnum.class,BaseEnumSerializer.instance)
//                    .deserializerByType(IEnum.class,BaseEnumDeserializer.instance)
//                    .serializerByType(BigDecimal.class,ToPlainTextString.instance).createXmlMapper(false).build();
//            SimpleModule simpleModule = new SimpleModule();
//
//
//            //封装类型 Long ==> String
//            simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//            simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
//            simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
//            simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
//            simpleModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
//            simpleModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
//            simpleModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
//            objectMapper.registerModule(simpleModule);
//            objectMapper.setDateFormat(new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN));
//            objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        };

    }
//    @Bean
//    @Primary
//    @ConditionalOnMissingBean(ObjectMapper.class)
//    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
//        ObjectMapper objectMapper = builder.timeZone(TimeZone.getDefault())
//                .serializerByType(Long.class, ToStringSerializer.instance)
//                .serializerByType(BigInteger.class, ToStringSerializer.instance)
//                .serializerByType(IEnum.class,BaseEnumSerializer.instance)
//                .deserializerByType(IEnum.class,BaseEnumDeserializer.instance)
//                .serializerByType(BigDecimal.class,ToPlainTextString.instance).createXmlMapper(false).build();
//
//
//        // 全局配置序列化返回 JSON 处理
//        SimpleModule simpleModule = new SimpleModule();
//
//
//        //封装类型 Long ==> String
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
//        simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
//        simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
//        simpleModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
//        simpleModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
//        simpleModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
//        objectMapper.registerModule(simpleModule);
//        objectMapper.setDateFormat(new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN));
//        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//        return objectMapper;
//    }



}

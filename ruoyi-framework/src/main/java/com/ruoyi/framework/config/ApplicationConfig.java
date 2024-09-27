package com.ruoyi.framework.config;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.enums.IEnum;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

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
     * 时区配置
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization()
    {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault())
                .serializerByType(Long.class, ToStringSerializer.instance)
                .serializerByType(BigInteger.class, ToStringSerializer.instance)
                .serializerByType(IEnum.class,BaseEnumSerializer.instance)
                .deserializerByType(IEnum.class,BaseEnumDeserializer.instance)
                .serializerByType(BigDecimal.class,ToPlainTextString.instance);

    }


}

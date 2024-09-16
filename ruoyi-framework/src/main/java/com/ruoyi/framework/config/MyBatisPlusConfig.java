package com.ruoyi.framework.config;


import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.ruoyi.common.config.CustomizedSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

/**
 * Mybatis支持*匹配扫描包
 * 
 * @author ruoyi
 */
@Configuration
public class MyBatisPlusConfig
{
    @Bean
    public ISqlInjector customizedSqlInjector() {
        return new CustomizedSqlInjector();
    }


    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor()
    {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        interceptor.addInnerInterceptor(paginationInnerInterceptor());
        // 乐观锁插件
        interceptor.addInnerInterceptor(optimisticLockerInnerInterceptor());
        // 阻断插件
        interceptor.addInnerInterceptor(blockAttackInnerInterceptor());

        return interceptor;
    }

    /**
     * 分页插件，自动识别数据库类型 https://baomidou.com/guide/interceptor-pagination.html
     */
    public PaginationInnerInterceptor paginationInnerInterceptor()
    {
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        // 设置数据库类型为mysql
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInnerInterceptor.setMaxLimit(-1L);
        return paginationInnerInterceptor;
    }

    /**
     * 乐观锁插件 https://baomidou.com/guide/interceptor-optimistic-locker.html
     */
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor()
    {
        return new OptimisticLockerInnerInterceptor();
    }

    /**
     * 如果是对全表的删除或更新操作，就会终止该操作 https://baomidou.com/guide/interceptor-block-attack.html
     */
    public BlockAttackInnerInterceptor blockAttackInnerInterceptor()
    {
        return new BlockAttackInnerInterceptor();
    }
//    @Value("${mybatis-plus.mapperLocations}")
//    private String mapperLocations;
//    @Value("${mybatis-plus.type-handlers-package}")
//    private String typeHandlersPackage;
//
//    @Value("${mybatis-plus.typeAliasesPackage}")
//    private String typeAliasesPackage;
//
//
//     @Resource
//    private CustomMyBatisPlusGlobalConfig globalConfig;
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)
//            throws Exception {
//        final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
//        sessionFactory.setTypeHandlersPackage(typeHandlersPackage);
//
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
//
//
//        GlobalConfig globalConfig = GlobalConfigUtils.defaults();
//        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
//        globalConfig.setDbConfig(dbConfig);
//        //【看到了吗？我在这呢！】
//        globalConfig.setSqlInjector(new CustomizedSqlInjector());
//        sessionFactory.setGlobalConfig(globalConfig);
//
//        return sessionFactory.getObject();
//    }
}
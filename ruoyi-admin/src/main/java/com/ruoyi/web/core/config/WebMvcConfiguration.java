package com.ruoyi.web.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author tangyicheng
 * 配置静态导入模板资源的路径映射
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    //详情请参考ruoyi-admin包下的application-dev.yml和admin包下的application-prod.yml
    @Value("${staticFile.staticPatternPath}")
    private String staticPatternPath;//前端访问的路径
    @Value("${staticFile.actualFolder}")
    private String actualFolder;//实际在本地或服务器保存的的地址---绝对路径


    //记得到package com.ruoyi.framework.config;下添加
    //先同样注入
//    @Value("${staticFile.staticPatternPath}")
//    private String staticPatternPath;//前端访问的路径
    //然后在SecurityFilterChain方法中加入
//    .antMatchers(staticPatternPath).permitAll()

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        我这里为了不把application.yml文件更改就暂时用的这个，同时上面的参数注释掉，启用第二个的话就先去对应的yml文件查看操作步骤并把上面两个参数取消注释
//        registry.addResourceHandler("/template/**").addResourceLocations("file:"+"D:/tmp/");
        registry.addResourceHandler(staticPatternPath).addResourceLocations("file:"+actualFolder);
    }
}

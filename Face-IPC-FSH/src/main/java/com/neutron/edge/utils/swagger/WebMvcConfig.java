package com.neutron.edge.utils.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 *
 * @author LiuJiaJia
 * @version 1.0
 * @description 放行Knife4j请求
 * @date 2022/11/5 20:25
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     *  静态资源映射
     * @param registry knife4j自带增强的静态资源
     * @author Liu JiaJia
     * @date 2022/11/5 20:27
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // swagger - knife4j自带增强的静态资源映射
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");


    }


}

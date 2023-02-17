package com.neutron.edge.utils.swagger;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 *
 * @author LiuJiaJia
 * @version 1.0
 * @description swagger 配置
 * @date 2022/11/5 20:02
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {

    /**
     * 引入Knife4j提供的扩展类
     * @date 2022/7/18 10:42
     **/
    private final OpenApiExtensionResolver openApiExtensionResolver;


    @Autowired
    public Knife4jConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;

    }

    @Bean(value = "defaultApi")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(defaultApiInfo())
                .groupName("FaceIPC-API接口文档")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.neutron.edge"))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions("1.0"));
    }

    private ApiInfo defaultApiInfo() {
        return new ApiInfoBuilder()
                //标题
                .title("FaceIPC-FSH-API接口文档")
                //版本信息
                .version("1.0")
                //描述消息
                .description("FaceIPC FSH SDK - API接口")
                .build();
    }





}

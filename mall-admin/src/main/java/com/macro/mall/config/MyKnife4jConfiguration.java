package com.macro.mall.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Date:2023/8/20 12:34
 * <p>
 * 问：这里很好奇EDM中是怎么把这些配置 配到 yml文件中去的
 */

@Configuration
//@EnableOpenApi
//@EnableKnife4j     //开启动态配置   ,详情解释见此启动类当中
@Slf4j
public class MyKnife4jConfiguration {

    /**
     * 打印swagger状态
     */
    MyKnife4jConfiguration() {
        log.info("启动swagger文档规范... ...");
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径,控制器类包  'RequestHandlerselectors：配置要扫描接口的方式    .basePackage(...)基于包扫描    //注：这里填要扫描的包名
                .apis(RequestHandlerSelectors.basePackage("com.macro.mall.controller"))
                .paths(PathSelectors.any())  //路径
                .build()                    //build成一个Docket对象
                .groupName("default")
                .enable(true)
//                .ex
                ; //enable是否启动Swagger，如果为False，则Swagger不能再浏览器中访问

    }

    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("用户管理 接口文档")
                //描述
                .description("API 描述")
                //创建人
                .contact(new Contact("帅气的那个男人", "http://www.baidu.com", "1374438718@qq.com"))
                //版本号
                .version("1.0")
                .termsOfServiceUrl("https://www.weili.com")
                .build();
    }




}


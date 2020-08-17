package com.learn.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.ApiSelector;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
//开启swagger2
@EnableSwagger2
public class SwaggerConfig {

    //Swagger分组就需要建立多个Docker实例
    @Bean
    public Docket docket(Environment environment){
        //判断是否是开发环境，如果是开发环境才启用swagger
        boolean flag = environment.acceptsProfiles(Profiles.of("dev"));
        return new Docket(DocumentationType.SWAGGER_2).groupName("default").enable(flag);
    }

    @Bean
    public Docket docketBan(Environment environment){
        //判断是否是开发环境，如果是开发环境才启用swagger
        boolean flag = environment.acceptsProfiles(Profiles.of("dev"));
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .groupName("Ban")
                .enable(flag)
                .select()
                //swagger指定扫描包
                .apis(RequestHandlerSelectors.basePackage("com.learn.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("","","");
        return new ApiInfo("学习SwaggerAPI文档","简单样例","1.0","",
                contact, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
    }
}

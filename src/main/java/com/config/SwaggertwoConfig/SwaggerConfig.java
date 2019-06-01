package com.config.SwaggertwoConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author 胡字蒙.
 * @Date 2018/12/12   16:55
 */
@Configuration
@EnableSwagger2
//@EnableWebMvc
//扫描api controller包
@ComponentScan(basePackages = {"com.contaat.sys.controller"})
public class SwaggerConfig {
    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }
    private ApiInfo apiInfo(){
        /*import springfox.documentation.service.Contact;*/
        Contact contact = new Contact("胡胡道人","localhost","2897005447@qq.com");
        return new ApiInfoBuilder()
                .title("Taat_Api接口")     //项目接口
                .description("Api接口")    //接口描述
                .contact(contact)   //联系人信息
                .version("1.1.0")       //版本
                .build();       //创建api信息
    }

}

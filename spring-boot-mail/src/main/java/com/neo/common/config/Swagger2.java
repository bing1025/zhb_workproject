package com.neo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.neo.controller"))   // 扫描注解的配置，即你的API接口位置
                    .paths(PathSelectors.any())
                    .build();
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("邮件管理 APIs")
                    .description("收件人列表 后台")
                    //.termsOfServiceUrl("http://192.168.1.198:10070/platformgroup/ms-admin")
                    .contact("zhouhb")
                    .version("1.0")
                    .build();
        }
        
        //  ====================  上面配置完后 其实就已经可以正常访问 API 接口了，下面的只做参考和积累
        
        //  SpringBoot集成Swagger2中遇到的问题   https://www.jianshu.com/p/840320d431a1

    }


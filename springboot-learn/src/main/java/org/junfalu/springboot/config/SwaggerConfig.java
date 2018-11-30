package org.junfalu.springboot.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lujunfa  2018/11/30 11:13
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
       /* Map<String, Header> headerMap = new HashMap<>();
        headerMap.put("X-Error-Code", new Header("错误码", "", new ModelRef("String")));
        headerMap.put("X-Error-Message", new Header("错误消息", "", new ModelRef("String")));
        List<ResponseMessage> messages = Lists.newArrayList(
                new ResponseMessage(200, "OK", new ModelRef("void"), Collections.emptyMap(), Collections.emptyList()),
                new ResponseMessage(400, "Bad Request", new ModelRef("void"), headerMap, Collections.emptyList())
        );
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, messages)
                .globalResponseMessage(RequestMethod.POST, messages)
                .globalResponseMessage(RequestMethod.PUT, messages)
                .globalResponseMessage(RequestMethod.DELETE, messages)
                .apiDescriptionOrdering(new Ordering<ApiDescription>() {
                    @Override
                    public int compare(ApiDescription left, ApiDescription right) {
                        return left.getDescription().compareTo(right.getDescription());
                    }
                })
                .securitySchemes(Lists.newArrayList(new ApiKey("X-Auth-Token", "X-Auth-Token", "header")))
//                .securityContexts(Lists.newArrayList(SecurityContext.builder().forPaths().securityReferences(Lists.newArrayList(SecurityReference.builder().))))
                .apiInfo(apiInfo());*/
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("org.junfalu.springboot.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        /*return new ApiInfo(
                "org.junfalu.springcloud",
                "",
                "",
                "",
                new Contact("", "", ""),
                "", "", Collections.emptyList());*/

        return new ApiInfoBuilder()
                .title("org.junfalu.springcloud.api")
                .description("restful 风格接口")
                //服务条款网址
                //.termsOfServiceUrl("http://blog.csdn.net/forezp")
                .version("1.0")
                //.contact(new Contact("帅呆了", "url", "email"))
                .build();

    }

   /* @Bean
    public UiConfiguration uiConfiguration() {
        return new UiConfiguration(null,
                "none",
                "alpha",
                "model",
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                false,
                true,
                null);
    }*/
}

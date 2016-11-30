package com.ironyard;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by reevamerchant on 11/7/16.
 */
@EnableSwagger2
@SpringBootApplication
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }


    @Bean
    public Docket goalApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("goal-api")
                .apiInfo(apiInfoGoals())
                .select()
                .paths(regex("/rest/goal.*"))
                .build()
                .globalOperationParameters(
                        newArrayList(new ParameterBuilder()
                                .name("x-authorization-key")
                                .description("API Authorization Key")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(true)
                                .build()));

    }



    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user-api")
                .apiInfo(apiInfoUser())
                .select()
                .paths(regex("/rest/user.*"))
                .build()
                .globalOperationParameters(
                        newArrayList(new ParameterBuilder()
                                .name("x-authorization-key")
                                .description("API Authorization Key")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(true)
                                .build()));
    }



    @Bean
    public Docket permissionApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("permission-api")
                .apiInfo(apiInfoPermissions())
                .select()
                .paths(regex("/rest/permission.*"))
                .build()
                .globalOperationParameters(
                        newArrayList(new ParameterBuilder()
                                .name("x-authorization-key")
                                .description("API Authorization Key")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(true)
                                .build()));
    }



    private ApiInfo apiInfoGoals() {
        // create a  contact
        Contact name = new Contact("Reeva Merchant","rm.com", "reeva.merchant@gmail.com");

        return new ApiInfoBuilder()
                .title("This is the API for managing the user's goals.")
                .description("Create your list of goals here!")
                .termsOfServiceUrl("http://theironyard.com")
                .contact(name)
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
                .version("2.1")
                .build();
    }



    private ApiInfo apiInfoUser() {
        Contact name = new Contact("Reeva Merchant","rm.com", "reeva.merchant@gmail.com");

        return new ApiInfoBuilder()
                .title("This is my API")
                .description("Create your list of users here!")
                .termsOfServiceUrl("http://theironyard.com")
                .contact(name)
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
                .version("2.1")
                .build();
    }



    private ApiInfo apiInfoPermissions() {
        Contact name = new Contact("Reeva Merchant","rm.com", "reeva.merchant@gmail.com");

        return new ApiInfoBuilder()
                .title("This is my API")
                .description("Create your list of users here!")
                .termsOfServiceUrl("http://theironyard.com")
                .contact(name)
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
                .version("2.1")
                .build();
    }
}

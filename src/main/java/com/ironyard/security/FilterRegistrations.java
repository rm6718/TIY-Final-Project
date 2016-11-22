package com.ironyard.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by reevamerchant on 11/14/16.
 */
@Configuration
public class FilterRegistrations {

    /**
     * Applies security filter to any request that matches the url patterns
     */



    @Bean
    public FilterRegistrationBean restApiFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean(new RestSecurityFilter());
        registration.addUrlPatterns("/rest/*");
        return registration;
    }



    @Bean
    public FilterRegistrationBean MvcApiFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new MvcSecurityFilter());
        registration.addUrlPatterns("/mvc/secure/*");
        return registration;
    }

}

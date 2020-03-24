/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/3/13 3:05 PM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.hansun.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.hansun.web.filter.XssFilter;
import com.hansun.web.interceptor.AuthorityInterceptor;
import com.hansun.web.interceptor.ParameterInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * TODO(web-mvc信息配置类)
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/3/13 3:05 PM
 */
@Configuration
public class SpringBootWebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorityInterceptor()).addPathPatterns("/interceptor/*");
        registry.addInterceptor(new ParameterInterceptor()).addPathPatterns("/interceptor/*");
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/web/*");
        registration.setName("XssFilter");
        registration.setOrder(6);
        return registration;
    }

    /**
     * 配置HttpMessageConverter消息转换器
     * @param converters spring多个转换器converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        // 删除MappingJackson2HttpMessageConverter
        converters.removeIf(httpMessageConverter -> httpMessageConverter
                instanceof MappingJackson2HttpMessageConverter);

        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                // 输出key时是否使用双引号
                //SerializerFeature.QuoteFieldNames,
                // 是否输出值为null的字段
                SerializerFeature.WriteMapNullValue,
                //数值字段如果为null,输出为0,而非null
                SerializerFeature.WriteNullNumberAsZero,
                //List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullListAsEmpty,
                //字符类型字段如果为null,输出为"",而非null
                SerializerFeature.WriteNullStringAsEmpty,
                //Boolean字段如果为null,输出为false,而非null
                SerializerFeature.WriteNullBooleanAsFalse,
                //Date的日期转换器
                SerializerFeature.WriteDateUseDateFormat
                //禁止循环引用
                //SerializerFeature.DisableCircularReferenceDetect
        );
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastJsonHttpMessageConverter);

        // 添加GsonHttpMessageConverter
        /*Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'")
                .create();
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setGson(gson);
        converters.add(gsonHttpMessageConverter);*/
    }
}

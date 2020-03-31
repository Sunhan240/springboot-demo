/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/3/26 4:37 PM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.hansun.web.interceptor;

import com.hansun.annotation.ResetBody;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 〈一句话功能简述〉<br>
 * TODO(响应报文包装---拦截器)
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/3/26 4:37 PM
 */
@Component
public class ResponseResultInterceptor implements HandlerInterceptor {

    private static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> clazz = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();
            // 判断是否在类对象上面加了注解
            if (clazz.isAnnotationPresent(ResetBody.class)) {
                // 设置此请求返回体，需要包装，往下传递，在ResponseBodyAdvice接口进行判断
                request.setAttribute(RESPONSE_RESULT_ANN, clazz.getAnnotation(ResetBody.class));
            } else if (method.isAnnotationPresent(ResetBody.class)) {
                // 方法体上是否有该注解
                request.setAttribute(RESPONSE_RESULT_ANN, method.getAnnotation(ResetBody.class));
            }
            request.setAttribute("startTime",
                    LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {

    }
}

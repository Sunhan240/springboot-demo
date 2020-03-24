/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/3/13 3:37 PM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.hansun.web.filter;

import com.hansun.util.StringUtils;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * TODO()
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/3/13 3:37 PM
 */
@WebFilter(filterName = "requestParamFilter", urlPatterns = "/testFilter/*")
@Order(3)
public class RequestParamFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding("UTF-8");
        System.out.println("进入了参数请求-过滤器！！！");

        String msgId = request.getParameter("msgId");
        if (StringUtils.isEmpty(msgId)) {
            Map<String, Object> map = new HashMap<>(1);
            map.put("msgId", System.currentTimeMillis());
            filterChain.doFilter(new RequestParameterWrapper(request, map), servletResponse);
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}

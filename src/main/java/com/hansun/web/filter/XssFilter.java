/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/3/13 3:37 PM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.hansun.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * TODO()
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/3/13 3:37 PM
 */
public class XssFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding("UTF-8");
        System.out.println("进入了XSS漏洞过滤器！！！");

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //解决动态脚本获取网页cookie，将cookie设置成HttpOnly
        String sessionId = request.getSession().getId();
        response.setHeader("SET-COOKIE", "JSESSIONID=" + sessionId + "; HttpOnly");
        response.setHeader("x-frame-options", "SAMEORIGIN");

        filterChain.doFilter(new XssHttpServletRequestWrapper(request), response);


    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}

/*
 * Copyright (C), 2001-2019, xiaoi机器人
 * Author:   han.sun
 * Date:     2019/6/12 17:40
 * History:
 * <author>          <time>          <version>         <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.hansun.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * <p>向filter过滤器中的request对象添加额外的参数</p><br>
 * TODO(重写 HttpServletRequestWrapper)
 *
 * @author han.sun
 * @version 1.0.0
 * @since 2019/9/16 09:22
 */
public class RequestParameterWrapper extends HttpServletRequestWrapper {


    private Map<String, String[]> params = new HashMap<>();

    /**
     * override构造器
     *
     * @param request     HttpServletRequest
     * @param extraParams 自定义参数Map
     */
    RequestParameterWrapper(HttpServletRequest request, Map<String, Object> extraParams) {
        this(request);
        addParameters(extraParams);
    }

    /**
     * 重载构造函数
     *
     * @param request HttpServletRequest
     */
    @SuppressWarnings(value = "unchecked")
    private RequestParameterWrapper(HttpServletRequest request) {
        super(request);
        //将现有parameter传递给params
        this.params.putAll(request.getParameterMap());
    }

    /**
     * 获取所有参数名
     *
     * @return all parameter names
     */
    @Override
    public Enumeration<String> getParameterNames() {
        Vector<String> vector = new Vector<>(params.keySet());
        return vector.elements();
    }


    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }


    /**
     * 添加参数
     *
     * @param extraParams 自定义参数Map
     */
    private void addParameters(Map<String, Object> extraParams) {
        for (Map.Entry<String, Object> entry : extraParams.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }


    /**
     * 添加参数
     *
     * @param name  key
     * @param value value
     */
    private void addParameter(String name, Object value) {
        if (value != null) {
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
        }
    }

    /**
     * 重写getParameter，参数从当前类中的map获取
     *
     * @param name key
     * @return value
     */
    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    /**
     * 重写getParameter，参数从当前类中的map获取
     *
     * @param name key
     * @return value
     */
    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }


}
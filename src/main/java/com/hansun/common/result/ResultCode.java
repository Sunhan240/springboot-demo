/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/3/30 5:49 PM
 * History:
 * <author>          <time>          <version>         <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.hansun.common.result;

/**
 * 〈一句话功能简述〉<br>
 * TODO(返回报文---响应码接口)
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/3/30 5:49 PM
 */
public interface ResultCode {


    /**
     * 操作是否成功
     *
     * @return 成功与否
     */
    boolean success();


    /**
     * 【操作响应码】
     *
     * @return 报文对应的响应码
     * @since 2020/3/30 6:15 PM sunhan
     */
    long code();

    /**
     * 【提示消息】
     *
     * @return 报文的提示消息
     * @since 2020/3/30 6:14 PM sunhan
     */
    String message();


}
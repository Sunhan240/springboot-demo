/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/3/30 8:09 PM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.hansun.common.result;

import com.alibaba.fastjson.JSON;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 〈自定义响应报文参数〉<br>
 * TODO()
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/3/30 8:09 PM
 */
@Getter
public class ResponseResult implements Response {


    private long code = SUCCESS_CODE;

    private boolean success = SUCCESS;

    private String message = SUCCESS_MSG;


    /**
     * 额外的参数
     */
    private String msgId;

    private Long time;

    private Object data;

    /**
     * 【从枚举中设置业务异常信息】
     *
     * @param resultCode 异常响应码
     * @since 2020/3/30 11:57 PM sunhan
     */
    public ResponseResult(ResultCode resultCode) {
        this.code = resultCode.code();
        this.success = resultCode.success();
        this.message = resultCode.message();
    }

    public ResponseResult(Object data, String msgId) {
        this.data = data;
        this.msgId = msgId;
    }


    // 链式编程设置自身对象

    public ResponseResult data(Object data) {
        this.data = data;
        return this;
    }

    public ResponseResult msgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

    public ResponseResult time(Long time) {
        this.time = LocalDateTime.now().
                toInstant(ZoneOffset.of("+8")).toEpochMilli() - time;
        return this;
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

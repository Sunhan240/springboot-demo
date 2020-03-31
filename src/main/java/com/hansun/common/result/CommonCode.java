/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/3/30 8:01 PM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.hansun.common.result;

/**
 * 〈一句话功能简述〉<br>
 * TODO()
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/3/30 8:01 PM
 */
public enum CommonCode implements ResultCode {


    //
    SUCCESS(200, true, "成功"),
    FAIL(500, false, "操作失败！"),
    UNAUTHORISE(510, false, "没有权限"),
    NO_PAGE(404, false, "没有信息"),
    UNKNOWNERROR(999, false, "未知异常"),
    ;


    /**
     * 结果信息
     */


    long code;

    boolean success;

    String message;



    CommonCode(long code, boolean success, String message) {
        this.message = message;
        this.success = success;
        this.code = code;
    }


    @Override
    public boolean success() {
        return true;
    }

    @Override
    public long code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }





}

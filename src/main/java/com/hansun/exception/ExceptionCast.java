/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/3/30 8:16 PM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.hansun.exception;

import com.hansun.common.result.ResultCode;

/**
 * 〈一句话功能简述〉<br>
 * TODO(自定义异常抛出类)
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/3/30 8:16 PM
 */
public class ExceptionCast {

    private ExceptionCast() {
    }

    /**
     * 【抛出自定义业务异常】
     *
     * @param resultCode 业务异常码
     * @since 2020/3/30 8:23 PM sunhan
     */
    public static void castCustomExp(ResultCode resultCode) {
        throw new CustomException(resultCode);
    }
}

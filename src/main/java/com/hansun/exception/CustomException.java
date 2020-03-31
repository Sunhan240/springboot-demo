/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/3/30 8:13 PM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.hansun.exception;

import com.hansun.common.result.ResultCode;
import lombok.Getter;

/**
 * 〈一句话功能简述〉<br>
 * TODO(自定义业务---异常类)
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/3/30 8:13 PM
 */
@Getter
public class CustomException extends RuntimeException {


    private ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }


}

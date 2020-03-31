package com.hansun.annotation;

import java.lang.annotation.*;

/**
 * 〈用于controller层标识，该api报文是否需要重新包装标准化的json〉<br>
 * TODO(用来标记方法的返回值，是否需要重新包装)
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/3/26 4:33 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResetBody {

}
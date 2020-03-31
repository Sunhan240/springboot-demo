package com.hansun.exception;

import com.google.common.collect.ImmutableMap;
import com.hansun.common.result.CommonCode;
import com.hansun.common.result.ResponseResult;
import com.hansun.common.result.ResultCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 〈一句话功能简述〉<br>
 * TODO(全部异常处理类)
 *
 * @author : sunhan
 * @version 1.0
 * @since : 2020/3/25 23:20
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LogManager.getLogger(GlobalExceptionHandler.class);

    /**
     * 定义map，存贮常见错误信息。该类map不可修改
     */
    private static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;

    /**
     * ImmutableMap存放不可预知的异常
     */
    private static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode>
            builder = ImmutableMap.builder();


    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return "redirect:/uploadStatus";
    }

    /**
     * 【捕获CustomException类异常】
     *
     * @param customException 自定义业务异常类
     * @return 结果信息, json数据
     * @since 2020/3/30 8:23 PM sunhan
     */
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public ResponseResult customException(CustomException customException) {
        ResultCode resultCode = customException.getResultCode();
        return new ResponseResult(resultCode);
    }


    /**
     * 【捕获非自定义类异常】
     *
     * @param exception Exception(不可预知的异常状态)
     * @return 结果信息, json数据
     * @since 2020/3/30 8:31 PM sunhan
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseResult exception(Exception exception) {
        // 记录日志
        log.error("catch exception ==> ", exception.getMessage());
        if (EXCEPTIONS == null) {
            EXCEPTIONS = builder.build();
        }
        ResultCode resultCode = EXCEPTIONS.get(exception.getClass());
        if (resultCode != null) {
            return new ResponseResult(resultCode);
        } else {
            return new ResponseResult(CommonCode.UNKNOWNERROR);
        }
    }

    static {
        builder.put(HttpMessageNotReadableException.class, CommonCode.UNKNOWNERROR);
    }
}

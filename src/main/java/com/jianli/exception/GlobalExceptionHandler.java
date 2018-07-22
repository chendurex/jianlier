package com.jianli.exception;

import com.jianli.response.ResResult;
import com.jianli.response.ResUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.mail.MethodNotSupportedException;
import java.util.stream.Collectors;

/**
 * @todo 所有的异常都统一控制 每个异常对应的实现是什么都写上注释，目前参数验证只支持responseBody，requestParam目前发现不支持
 * 异常包括请求路径异常，请求参数类型不匹配异常，请求方法类型异常、请求头格式不匹配异常、参数验证异常、全局异常
 *
 * @author chendurex
 * @date 2018-07-01 20:48
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResResult handle(MethodArgumentNotValidException exception) {
        return error(exception.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList()));
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResResult handle(MissingPathVariableException exception) {
        return error(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResResult handle(MissingServletRequestParameterException exception) {
        return error(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResResult handle(UnsatisfiedServletRequestParameterException exception) {
        return error(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResResult handle(HttpMediaTypeException exception) {
        return error(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResResult handle(HttpMediaTypeNotAcceptableException exception) {
        return error(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResResult handle(HttpMediaTypeNotSupportedException exception) {
        return error(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResResult handle(MethodNotSupportedException exception) {
        return error(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResResult handle(Exception exception) {
        return error(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResResult handle(BaseException exception) {
        return error(exception.getMessage());
    }

    private ResResult error(Object message) {
        return ResUtils.fail(message);
    }
}

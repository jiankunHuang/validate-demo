package com.example.demo.exception;

import com.example.demo.model.AppResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 异常信息统一处理
 */
@RestControllerAdvice(annotations = {Controller.class, RestController.class})
public class AppControllerExceptionHandler {
    private static final String BAD_REQUEST_MSG = "客户端请求参数错误";
    private static final String INTERNAL_SERVER_ERROR_MSG = "系统内部处理错误";

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public AppResponseResult exception(Exception e) {
        return new AppResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR_MSG, e.getCause());
    }

    /**
     * 处理 form data方式调用接口校验失败抛出的异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({BindException.class})
    public AppResponseResult bindExceptionHandler(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(t -> t.getField() + t.getDefaultMessage())
                .collect(Collectors.toList());
        return new AppResponseResult(HttpStatus.BAD_REQUEST.value(), BAD_REQUEST_MSG, collect);
    }

    /**
     * 处理 json 请求体调用接口校验失败抛出的异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public AppResponseResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(t -> t.getField() + t.getDefaultMessage())
                .collect(Collectors.toList());
        return new AppResponseResult(HttpStatus.BAD_REQUEST.value(), BAD_REQUEST_MSG, collect);
    }

    /**
     * 处理单个参数校验失败抛出的异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({ConstraintViolationException.class})
    public AppResponseResult constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> collect = constraintViolations.stream()
                .map(t -> t.getMessage())
                .collect(Collectors.toList());
        return new AppResponseResult(HttpStatus.BAD_REQUEST.value(), BAD_REQUEST_MSG, collect);
    }

}

package com.example.demo.utils;


import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

/**
 * 参数校验工具类
 * @param <P> 校验对象
 * @param <T> 校验器实现类
 */
public final class ParamValidationUtil<P, T extends Validator> {

    private ParamValidationUtil() {}

    public void validate(P p, T t) throws BindException {
        DataBinder binder = new DataBinder(p);
        binder.setValidator(t);
        binder.validate();
        BindingResult result = binder.getBindingResult();
        if (result.hasErrors()) {
            throw new BindException(result);
        }
    }

    public static ParamValidationUtil newInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        public static final ParamValidationUtil INSTANCE = new ParamValidationUtil();
    }
}

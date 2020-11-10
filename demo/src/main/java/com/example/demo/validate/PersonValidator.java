package com.example.demo.validate;

import com.example.demo.model.Person;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Person.class.isAssignableFrom(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Person person = (Person) obj;
        if (person.getAge() > 120) {
            errors.rejectValue("age", "too old", "年龄不能超过120");
        }
    }
}

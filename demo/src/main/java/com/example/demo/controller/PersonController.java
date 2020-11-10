package com.example.demo.controller;

import com.example.demo.model.AppResponseResult;
import com.example.demo.model.Person;
import com.example.demo.utils.ParamValidationUtil;
import com.example.demo.validate.Create;
import com.example.demo.validate.PersonValidator;
import com.example.demo.validate.Update;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@RestController
@RequestMapping("/person")
@Validated
public class PersonController {

    @ResponseBody
    @GetMapping("/info/{id}")
    public AppResponseResult queryPersonInfo(@PathVariable @Size(min = 10, max = 15) String id) {
        Person person = new Person();
        person.setId(id);
        person.setName("Tom");
        person.setAge(18);
        person.setSex(1);
        person.setBirthday(LocalDate.of(2002, 7, 15));
        person.setBirthplace("云南昆明");
        return new AppResponseResult(person);
    }

    @ResponseBody
    @PutMapping("/create")
    public AppResponseResult createPersonInfo(@RequestBody @Validated({Create.class}) Person person) throws BindException {
        ParamValidationUtil.newInstance().validate(person, new PersonValidator());
        return new AppResponseResult();
    }

    @ResponseBody
    @PostMapping("/modify")
    public AppResponseResult modifyPersonInfo(@RequestBody @Validated({Update.class}) Person person) {
        return new AppResponseResult();
    }
}

package com.example.demo.model;

import com.example.demo.validate.Create;
import com.example.demo.validate.Update;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class Person {
    @NotNull
    @Size(min = 10, max = 15)
    private String id;

    @NotBlank(groups = {Create.class})
    private String name;

    @Min(value = 1)
    @Max(value = 2)
    private int sex;

    @Max(value = 150)
    @Min(value = 0)
    private int age;

    @Past
    private LocalDate birthday;

    @NotBlank(groups = {Update.class})
    private String birthplace;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }
}

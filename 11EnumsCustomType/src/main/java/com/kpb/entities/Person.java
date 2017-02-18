package com.kpb.entities;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 创建人：贾林朋
 * 创建时间：2017 年 02 月 18 日
 * 联系邮箱：15638181059@163.com
 * 描述 ：Example 24. Enum mapping with custom Type example
 */
@Entity(name = "Person")
public class Person {

    @Id
    private Long id;

    private String name;

    @Type(type = "com.kpb.entities.GenderType")
    private Gender gender;

    //Getters and setters are omitted for brevity


    public Long getId() {
        return id;
    }

    public Person setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public Person setGender(Gender gender) {
        this.gender = gender;
        return this;
    }
}

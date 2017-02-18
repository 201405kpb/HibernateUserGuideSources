package com.kpb.entities;

import javax.persistence.*;

/**
 * 创建人：贾林朋
 * 创建时间：2017 年 02 月 18 日
 * 联系邮箱：15638181059@163.com
 * 描述 ：
 */
@Entity(name = "Phone")
public class Phone {

    @Id
    private Long id;

    @Column(name = "phone_number")
    private String number;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "phone_type")
    private PhoneType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "str_phone_type")
    private PhoneType string_type;

    public Long getId() {
        return id;
    }

    public Phone setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public Phone setNumber(String number) {
        this.number = number;
        return this;
    }

    public PhoneType getType() {
        return type;
    }

    public Phone setType(PhoneType type) {
        this.type = type;
        return this;
    }

    public PhoneType getString_type() {
        return string_type;
    }

    public Phone setString_type(PhoneType string_type) {
        this.string_type = string_type;
        return this;
    }
}

package com.kpb.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 创建人：贾林朋
 * 创建时间：2017 年 02 月 17 日
 * 联系邮箱：15638181059@163.com
 */
@Entity(name = "Product")
public class Product {

    @Id
    private Integer id;

    private String sku;

    @org.hibernate.annotations.Type(type = "nstring")
    private String name;

    @org.hibernate.annotations.Type(type = "materialized_nclob")
    private String description;

    public Integer getId() {
        return id;
    }

    public Product setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getSku() {
        return sku;
    }

    public Product setSku(String sku) {
        this.sku = sku;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }
}

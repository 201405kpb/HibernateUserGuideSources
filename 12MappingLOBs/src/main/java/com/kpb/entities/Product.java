package com.kpb.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.sql.Blob;
import java.sql.Clob;

/**
 * 创建人：贾林朋
 * 创建时间：2017 年 02 月 18 日
 * 联系邮箱：15638181059@163.com
 * 描述 ：
 */
@Entity(name = "Product")
public class Product {

    @Id
    private Integer id;

    private String name;

    @Lob
    private Clob warranty;

    @Lob
    private String description;

    @Lob
    private char[] charwarranty;

    @Lob
    private Blob image;

    //Getters and setters are omitted for brevity


    public Integer getId() {
        return id;
    }

    public Product setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Clob getWarranty() {
        return warranty;
    }

    public Product setWarranty(Clob warranty) {
        this.warranty = warranty;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public char[] getCharwarranty() {
        return charwarranty;
    }

    public Product setCharwarranty(char[] charwarranty) {
        this.charwarranty = charwarranty;
        return this;
    }

    public Blob getImage() {
        return image;
    }

    public Product setImage(Blob image) {
        this.image = image;
        return this;
    }
}

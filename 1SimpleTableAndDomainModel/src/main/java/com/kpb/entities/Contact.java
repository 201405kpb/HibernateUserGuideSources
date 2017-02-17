package com.kpb.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.net.URL;

/**
 * 创建人：贾林朋
 * 创建时间：2017 年 02 月 17 日
 * 联系邮箱：15638181059@163.com
 */
@Entity(name = "Contract")
public class Contact {
    @Id
    private Integer id;

    private Name name;

    private String notes;

    private URL website;

    private boolean starred;

    public Integer getId() {
        return id;
    }

    public Contact setId(Integer id) {
        this.id = id;
        return this;
    }

    public Name getName() {
        return name;
    }

    public Contact setName(Name name) {
        this.name = name;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public Contact setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public URL getWebsite() {
        return website;
    }

    public Contact setWebsite(URL website) {
        this.website = website;
        return this;
    }

    public boolean isStarred() {
        return starred;
    }

    public Contact setStarred(boolean starred) {
        this.starred = starred;
        return this;
    }
}

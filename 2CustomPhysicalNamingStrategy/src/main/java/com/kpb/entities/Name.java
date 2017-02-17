package com.kpb.entities;

import javax.persistence.Embeddable;

/**
 * 创建人：贾林朋
 * 创建时间：2017 年 02 月 17 日
 * 联系邮箱：15638181059@163.com
 */
@Embeddable
public class Name {

    private String first;

    private String middle;

    private String last;

    public String getFirst() {
        return first;
    }

    public Name setFirst(String first) {
        this.first = first;
        return this;
    }

    public String getMiddle() {
        return middle;
    }

    public Name setMiddle(String middle) {
        this.middle = middle;
        return this;
    }

    public String getLast() {
        return last;
    }

    public Name setLast(String last) {
        this.last = last;
        return this;
    }
}

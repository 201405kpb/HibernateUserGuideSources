package com.kpb.entities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 创建人：贾林朋
 * 创建时间：2017 年 02 月 18 日
 * 联系邮箱：15638181059@163.com
 * 描述 ：Example 23. Enum mapping with AttributeConverter example
 */
@Converter
public class GenderConverter implements AttributeConverter<Gender, Character> {

    public Character convertToDatabaseColumn(Gender value) {
        if (value == null) {
            return null;
        }

        return value.getCode();
    }

    public Gender convertToEntityAttribute(Character value) {
        if (value == null) {
            return null;
        }

        return Gender.fromCode(value);
    }
}

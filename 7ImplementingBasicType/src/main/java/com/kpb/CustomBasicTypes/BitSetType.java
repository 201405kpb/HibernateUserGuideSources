package com.kpb.CustomBasicTypes;

import org.hibernate.dialect.Dialect;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.DiscriminatorType;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import java.util.BitSet;

/**
 * 创建人：贾林朋
 * 创建时间：2017 年 02 月 13 日
 * 联系邮箱：15638181059@163.com
 * 描述 ：Example 7. Custom BasicType implementation
 */
public class BitSetType extends AbstractSingleColumnStandardBasicType<BitSet> implements DiscriminatorType<BitSet> {

    public static final BitSetType INSTANCE = new BitSetType();

    public BitSetType() {
        super(VarcharTypeDescriptor.INSTANCE, BitSetTypeDescriptor.INSTANCE);
    }

    public BitSet stringToObject(String xml) throws Exception {
        return fromString(xml);
    }

    public String objectToSQLString(BitSet value, Dialect dialect) throws Exception {
        return toString(value);
    }

    public String getName() {
        return "bitset";
    }

}
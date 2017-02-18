package com.kpb.ImplementingUserType;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.BitSet;
import java.util.Objects;

/**
 * 创建人：贾林朋
 * 创建时间：2017 年 02 月 18 日
 * 联系邮箱：15638181059@163.com
 * 描述 ：Example 13. Custom UserType implementation
 */
public class BitSetUserType implements UserType {

    public static final BitSetUserType INSTANCE = new BitSetUserType();

    private static final Logger log = Logger.getLogger(BitSetUserType.class);

    public int[] sqlTypes() {
        return new int[]{StringType.INSTANCE.sqlType()};
    }

    public Class returnedClass() {
        return String.class;
    }


    public boolean equals(Object x, Object y)
            throws HibernateException {
        return Objects.equals(x, y);
    }


    public int hashCode(Object x)
            throws HibernateException {
        return Objects.hashCode(x);
    }


    public Object nullSafeGet(
            ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
            throws HibernateException, SQLException {
        String columnName = names[0];
        String columnValue = (String) rs.getObject(columnName);
        log.debug(String.format("Result set column {0} value is {1}", columnName, columnValue));
        return columnValue == null ? null : BitSetTypeDescriptor.INSTANCE.fromString(columnValue);
    }


    public void nullSafeSet(
            PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
            throws HibernateException, SQLException {
        if (value == null) {
            log.debug(String.format("Binding null to parameter {0} ", index));
            st.setNull(index, Types.VARCHAR);
        } else {
            String stringValue = BitSetTypeDescriptor.INSTANCE.toString((BitSet) value);
            log.debug(String.format("Binding {0} to parameter {1} ", stringValue, index));
            st.setString(index, stringValue);
        }
    }

    public Object deepCopy(Object value)
            throws HibernateException {
        return value == null ? null :
                BitSet.valueOf(BitSet.class.cast(value).toLongArray());
    }


    public boolean isMutable() {
        return true;
    }

    public Serializable disassemble(Object value)
            throws HibernateException {
        return (BitSet) deepCopy(value);
    }


    public Object assemble(Serializable cached, Object owner)
            throws HibernateException {
        return deepCopy(cached);
    }

    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        return deepCopy(original);
    }
}
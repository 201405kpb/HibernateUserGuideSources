package com.kpb.test;

import com.kpb.entities.Phone;
import com.kpb.entities.PhoneType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 创建人：贾林朋
 * 创建时间：2017 年 02 月 18 日
 * 联系邮箱：15638181059@163.com
 * 描述 ：
 */
public class HibernateTest {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Before
    public void Init() {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
        Metadata metadata = metadataBuilder.build();
        sessionFactory = metadata.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @After
    public void Destroy() {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void MappingEnumsTest() {
        Phone phone = new Phone();
        phone.setId(1L);
        phone.setNumber("123-456-78990");
        phone.setType(PhoneType.MOBILE);
        phone.setString_type(PhoneType.LAND_LINE);
        session.save(phone);
    }
}

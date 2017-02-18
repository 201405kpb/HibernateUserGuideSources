package com.kpb.test;

import com.kpb.entities.Gender;
import com.kpb.entities.GenderType;
import com.kpb.entities.Person;
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
        metadataBuilder.applyBasicType(GenderType.INSTANCE);
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
    public void EnumsCustomTypeTest() {
        Person person = new Person();
        person.setId(1L);
        person.setName("测试");
        person.setGender(Gender.FEMALE);
        session.save(person);
    }
}

package com.kpb.test;

import com.kpb.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

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
    public void MappingLOBsTest() throws IOException, SQLException {
        String warranty = "My product warranty";
        final Product product = new Product();
        product.setId(1);
        product.setName("Mobile phone");
        session.doWork(connection -> {
            product.setWarranty(connection.createNClob());
            product.getWarranty().setString(1, warranty);

        });
        product.setDescription(warranty)
                .setCharwarranty(warranty.toCharArray())
                .setImage(warranty);
        session.save(product);
        Product product2 = session.find(Product.class, 1);
        Assert.assertEquals("My product warranty", ClobToString(product2.getWarranty()));
        Assert.assertEquals("My product warranty", product2.getImage());
        Assert.assertEquals("My product warranty", product2.getDescription());
        Assert.assertEquals(warranty, String.valueOf(product2.getCharwarranty()));


    }


    public static String ClobToString(Clob clob) throws SQLException, IOException {

        String reString;
        Reader is = clob.getCharacterStream();// 得到流
        BufferedReader br = new BufferedReader(is);
        String s = br.readLine();
        StringBuffer sb = new StringBuffer();
        while (s != null) {// 执行循环将字符串全部取出付值给 StringBuffer
            sb.append(s);
            s = br.readLine();
        }
        reString = sb.toString();
        return reString;
    }
}

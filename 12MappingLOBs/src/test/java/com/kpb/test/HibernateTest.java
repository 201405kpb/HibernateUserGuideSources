package com.kpb.test;

import com.kpb.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.jdbc.BlobProxy;
import org.hibernate.engine.jdbc.ClobProxy;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Blob;
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
        byte[] image = new byte[]{1, 2, 3};
        final Product product = new Product();
        product.setId(1);
        product.setName("Mobile phone");
        session.doWork(connection -> {
            product.setWarranty(ClobProxy.generateProxy(warranty))
                    .setImage(BlobProxy.generateProxy(image));

        });
        product.setDescription(warranty)
                .setCharwarranty(warranty.toCharArray());
        session.save(product);
        Product product2 = session.find(Product.class, 1);
        Assert.assertEquals(product2.getDescription(), warranty);
        Assert.assertEquals("My product warranty", ClobToString(product2.getWarranty()));
        Assert.assertArrayEquals(image, toBytes(product2.getImage()));

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

    public static byte[] toBytes(Blob blob) {
        BufferedInputStream is = null;
        try {
            is = new BufferedInputStream(blob.getBinaryStream());
            byte[] bytes = new byte[(int) blob.length()];
            int len = bytes.length;
            int offset = 0;
            int read = 0;
            while (offset < len
                    && (read = is.read(bytes, offset, len - offset)) >= 0) {
                offset += read;
            }
            return bytes;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                is.close();
                is = null;
            } catch (IOException e) {
                return null;
            }
        }
    }
}

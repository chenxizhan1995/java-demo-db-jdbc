package cc.xizhan.demo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class DemoOneTest {
    private DemoOne app = new DemoOne();
    @Before
    public void setUp() throws Exception {
        System.setProperty("socksProxyHost", "172.16.12.16");
        System.setProperty("socksProxyPort", "1080");
    }


    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = SQLException.class)
    public void testConnection() throws SQLException {
        app.getConnection();
    }

    @Test()
    public void testConnection2() {
        app.getConnection2();
    }
    @Test()
    public void testConnection3() {
        System.out.println(System.getProperty("socksProxyHost"));
        System.out.println(System.getProperty("socksProxyPort"));
        app.getConnection3();
    }
    @Test
    public void testGetConnection3(){
        Assert.assertTrue(app.getConnection3());
    }

    @Test
    public void testQuery(){
        Assert.assertTrue(app.query());
    }

    @Test
    public void testQuery2(){
        app.query2();
    }
}

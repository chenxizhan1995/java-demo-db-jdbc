package cc.xizhan.demo;

import java.sql.*;

/**
 * <p> Description: 第一个jdbc示例</p>
 */
public class DemoOne {

    /**
     * jdbc示例：连接数据库
     */
    public void getConnection() throws SQLException {
        String JDBC_URL = "jdbc:mysql://localhost:3308/school?useSSL=false&characterEncoding=utf8";
        String JDBC_USER = "dev";
        String JDBC_PASSWORD = "123456";
        try {
            Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            // java.sql.SQLException: No suitable driver found for
            // jdbc:mysql://localhost:3306/school?useSSL=false&characterEncoding=utf8
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void getConnection2() {
        String JDBC_URL = "jdbc:mysql://localhost:3308/school?useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true";
        String JDBC_USER = "dev";
        String JDBC_PASSWORD = "123456";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            System.out.println("连接数据库成功");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("连接数据库失败");
            e.printStackTrace();
        }
    }
    public boolean getConnection3(){
        // jdbc 连接是一种昂贵的资源，用 try-with-resource 语句自动释放是个好习惯
        // final String JDBC_URL = "jdbc:mysql://localhost:3308/school?useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true";
        final String JDBC_URL = "jdbc:mysql://localhost:3308/school?useSSL=false&characterEncoding=utf8";
        final String JDBC_USER = "dev";
        final String JDBC_PASSWORD= "123456";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("加载数据库驱动成功");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("加载数据库驱动失败");
        }

        try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)){
            System.out.println("连接数据库成功");
            return true;
        } catch (SQLException e){
            System.out.println("连接数据库失败");

            e.printStackTrace();
            return false;
        }
    }

    public boolean query(){
        // 1. 载入数据库驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }
        // 2. 连接数据库
        String url = "jdbc:mysql://localhost:3308/school?useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true";
        String user = "dev";
        String password = "123456";
        try (Connection con = DriverManager.getConnection(url, user, password)){
            try (Statement st = con.createStatement()){
                try (ResultSet rs = st.executeQuery("select sid, name, birthday from school.student")){
                    while (rs.next()){
                        // 注意，索引从 1 开始
                        Long sid = rs.getLong(1);
                        String sname = rs.getString(2);
                        Date birthday = rs.getDate(3);
                        System.out.println(String.format("%d, %s, %s", sid, sname, birthday));
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void query2(){
        // 1. 载入数据库驱动
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
         } catch (ClassNotFoundException e){
             e.printStackTrace();
         }
         // 2. 连接数据库
         String url = "jdbc:mysql://localhost:3308/school?useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true";
         String user = "dev";
         String password = "123456";
         try (Connection con = DriverManager.getConnection(url, user, password)){
             try (PreparedStatement st = con.prepareStatement("select sid, name ,birthday from student where name = ?")){
                 st.setString(1, "Jack"); // 索引从 1 开始
                 try (ResultSet rs = st.executeQuery()){
                     while (rs.next()){
                         // 注意，索引从 1 开始
                         Long sid = rs.getLong("sid");
                         String sname = rs.getString("name");
                         Date birthday = rs.getDate("birthday");
                         System.out.println(String.format("%d, %s, %s", sid, sname, birthday));
                     }
                 }
             }
         } catch (SQLException e){
             e.printStackTrace();
         }
    }
}

package com.company.util;

import java.sql.*;
import java.util.Objects;
import java.util.Properties;


public class JdbcUtils {

    private static Properties prop = new Properties();

    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    static {
        try {
            prop.load(JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            Class.forName(prop.getProperty("jdbc.driver"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("缺少jdbc.properties文件");
        }
    }

    private JdbcUtils(){}


    private static synchronized Connection getConnection(){

        Connection conn = tl.get(); //从当前线程上取出连接实例

        if(conn == null){
            try {
                conn = DriverManager.getConnection(
                        prop.getProperty("jdbc.url"),
                        prop.getProperty("jdbc.user"),
                        prop.getProperty("jdbc.password"));
                tl.set(conn);  //将连接绑定到当前线程上
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("连接失败 ");
            }
        }
        return conn;
    }


    //为什么要同步？？
    public static synchronized TransactionManager getTranManager(){
        return new TransactionManager(getConnection());
    }



    public  static PreparedStatement getPreparedStatement(String sql) {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }


    protected static void close(Connection conn) throws Exception{
        if(Objects.isNull(conn)){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new Exception("关闭连接时出现异常",e);
            } finally {
                tl.remove(); //卸装线程绑定
            }
        }
    }


    protected   static void closeAll(ResultSet rs, Statement stmt, Connection conn) throws Exception{
        if (Objects.isNull(rs)) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (Objects.isNull(stmt)) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(conn);
    }


}

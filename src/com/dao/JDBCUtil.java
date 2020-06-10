package com.dao;

import java.sql.*;

public class JDBCUtil {
    // 连接信息
    private static String url = "jdbc:mysql://localhost:3306/shopping";
    private static String user = "root";
    private static String password = "zhurk9750";
    private Connection conn;

    public JDBCUtil() {
        this.conn = this.getConn();
    }

    // 加载驱动
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // 连接数据库
    private Connection getConn() {
        try {
            return DriverManager.getConnection(JDBCUtil.url, JDBCUtil.user, JDBCUtil.password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // 查询操作
    public ResultSet query(String sql) {
        try {
            Statement st = this.conn.createStatement();
            return st.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // 增加、更新、删除操作
    public boolean cud(String sql) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

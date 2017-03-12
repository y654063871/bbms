package cn.qlu.yhy.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class DBUtil {

    public static Connection getConnection() {

        try {
            Class.forName(PropertyUtil.getJDBCPropertiesValues("jdbc.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


        String url = PropertyUtil.getJDBCPropertiesValues("jdbc.url");
        String username = PropertyUtil.getJDBCPropertiesValues("jdbc.username");
        String password = PropertyUtil.getJDBCPropertiesValues("jdbc.password");

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e1) {
            e1.printStackTrace();
            throw new RuntimeException(e1);
        }

        return conn;
    }

    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RuntimeException(e2);
            } finally {

                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                    throw new RuntimeException(e3);
                }
            }
        }
    }

}

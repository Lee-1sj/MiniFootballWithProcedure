package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    public static Connection getConnection() {
        // db.properties 파일 경로
        String filePath = "src/db.properties";
        Connection con = null;

        try { // db.properties db 주소, 사용자명, 암호 가져오기
            Properties properties = new Properties();
            properties.load(new FileReader(filePath));
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            // 오라클 jdbc 로드하기
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // System.out.println("jdbc driver load success");
            // 데이터베이스 연결
            con = DriverManager.getConnection(url, user, password);
            // System.out.println("db connect success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("jdbc driver load fail");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("db connection fail");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("db.properties file not found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("db.properties file load fail");
        }
        return con;
    }

    public static void closeResource(PreparedStatement pstmt, Connection con) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResource(CallableStatement cstmt, Connection con) {
        try {
            if (cstmt != null) {
                cstmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResource(ResultSet rs, PreparedStatement pstmt, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResource(ResultSet rs, CallableStatement cstmt, Connection con){
        try {
            if (rs != null) {
                rs.close();
            }
            if (cstmt != null) {
                cstmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

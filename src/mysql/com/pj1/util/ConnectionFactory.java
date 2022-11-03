package com.test.springtest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    public static Connection createMYSQLConnection(){
        Properties props = new Properties();

        FileInputStream fis = null;
        Connection conn =null;
        try {
            fis = new FileInputStream("D:\\javaSSD\\project1\\src/db.properties");
            props.load(fis);
            Class.forName(props.getProperty("MYSQL_DB_Driver_Class"));
//            "bin/db.properties"
            conn = DriverManager.getConnection(props.getProperty("MYSQL_DB_URL"),
            props.getProperty("MYSQL_DB_UserName"),
            props.getProperty("MYSQL_DB_Password"));
            System.out.println("資料庫已連線");
        } catch (IOException | SQLException e) {
            System.out.println("無法連線");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

}

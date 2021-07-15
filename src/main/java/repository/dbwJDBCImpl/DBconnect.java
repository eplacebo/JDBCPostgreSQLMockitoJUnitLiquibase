package repository.dbwJDBCImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {

    static final String USER = "postgres";
    static final String PASSWORD = "postgres";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/dbw";
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static Connection connection;

    static Connection getConnectDB()  {
         try {
             Class.forName(JDBC_DRIVER);
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
         try {
             connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
         } catch (SQLException throwables) {
             throwables.printStackTrace();
         }
         return connection;
    }
}




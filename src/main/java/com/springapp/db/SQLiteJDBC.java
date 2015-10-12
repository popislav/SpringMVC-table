package com.springapp.db;
import java.sql.*;


/**
 * Created by hrvoje on 02.10.15..
 */
public class SQLiteJDBC {
    public static void main(String args[]) {
        getConnection();
//        System.out.println("Opened database successfully");
    }

    public static Connection getConnection() {
        Connection c = null;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/home/hrvoje/SVProjects/SVTask4/src/main/java/com/springapp/db/employees.sqlite3");
        }catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        return c;
    }


}

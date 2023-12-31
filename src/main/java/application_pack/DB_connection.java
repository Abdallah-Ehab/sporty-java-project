package application_pack;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public abstract class DB_connection extends alerts_and_warning {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/club";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static Connection conn = null;

    public static Connection connection(){
        try{
            Class.forName(JDBC_DRIVER);
            conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASSWORD);
            get_alert("happy","congrats connection success").showAndWait();
        }catch(Exception e){
            get_alert("happy","congrats connection success").showAndWait();

            System.out.println(e);

            return null;
        }
        return conn;
    }
}

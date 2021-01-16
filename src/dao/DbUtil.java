package dao;

import java.sql.*;

/**
 * 该类的方法用来获取数据库连接，关闭连接
 */
public class DbUtil{

    private static Connection conn;
    static Connection getConnection(){
        try {
            String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(DRIVER_NAME);
            String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=mydb;";
            String NAME = "sa";
            String PASSWORD = "12345";
            conn=DriverManager.getConnection(URL,NAME,PASSWORD);
            return conn;//返回加载好的链接
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    static void closeCon(){
        try{
            if (conn != null) {
                conn.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeCon(Connection connection, Statement statement){
        try{
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void closeCon(ResultSet resultSet){
        try{
            if (resultSet != null) {
                resultSet.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void closeCon(Connection connection, Statement statement, ResultSet resultSet) {
        try{
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}

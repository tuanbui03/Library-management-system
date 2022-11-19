/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class JDBCConnect {
    public static Connection getJDBCConnection(){
        Connection con = null;
        String connectionURL = "jdbc:mysql://localhost:3306/" + IDBConfig.DBNAME;
        System.out.println(connectionURL);
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Where is your MySQL JDBC Driver?");
            return con;
        }
        System.out.println("MySQL JDBC Driver Registered!");
        
        try {
            con = DriverManager.getConnection(connectionURL, IDBConfig.USERNAME, IDBConfig.PASSWORD);
        } catch (SQLException e) {
            System.err.println("Connection Failed! Check output console");
            return con;
        }
        
        return con;
    }
    
    public static void closeConnection(Connection con){
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Close Connection fails");
        }
    }
    
    public static void closeResultSet(ResultSet rs){
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("Close ResultSet fails");
        }
    }
    
    public static void closePreparedStatement(PreparedStatement prepare){
        try {
            if (prepare != null) {
                prepare.close();
            }
        } catch (SQLException e) {
            System.out.println("Close PreparedStatement fails");
        }
    }
    
    public static void main(String[] args) {
        JDBCConnect.getJDBCConnection();
    }
}

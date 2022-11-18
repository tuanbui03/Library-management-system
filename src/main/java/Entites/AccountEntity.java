/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entites;

import Models.Account;
import java.sql.*;
import db.*;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class AccountEntity {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    public ArrayList<Account> getAll() {
        ArrayList<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM accounts";

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Account acc = new Account(rs.getInt("id"),
                        rs.getString("user_number"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getInt("gender"),
                        rs.getString("email"),
                        rs.getString("dob"),
                        rs.getString("mobile"),
                        rs.getInt("status"),
                        rs.getInt("roleId"));

                list.add(acc);

            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCConnect.closeResultSet(rs);
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }

        return null;
    }

    public Account getOne(String txt_user) {
        String sql = "SELECT * FROM accounts WHERE username = ?";

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, txt_user);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Account acc = new Account(rs.getInt("id"),
                        rs.getString("user_number"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getInt("gender"),
                        rs.getString("email"),
                        rs.getString("dob"),
                        rs.getString("mobile"),
                        rs.getInt("status"),
                        rs.getInt("roleId"));

                return acc;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCConnect.closeResultSet(rs);
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }

        return null;
    }

    public boolean insert(Account acc) {
        boolean flag = false;
        String insertOne = "INSERT INTO accounts (username, password, full_name) VALUES (?, ?, ?)";

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(insertOne);
            preparedStatement.setString(1, acc.getUsername());
            preparedStatement.setString(2, acc.getPassword());
            preparedStatement.setString(3, acc.getFull_name());

            if (preparedStatement.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCConnect.closeResultSet(rs);
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }

        return flag;
    }

    public ArrayList<Account> search(String name) {
        ArrayList<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE username like ?";

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Account acc = new Account(rs.getInt("id"),
                        rs.getString("user_number"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getInt("gender"),
                        rs.getString("email"),
                        rs.getString("dob"),
                        rs.getString("mobile"),
                        rs.getInt("status"),
                        rs.getInt("roleId"));

                list.add(acc);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCConnect.closeResultSet(rs);
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }

        return null;
    }

    public boolean update(String txt_user, Account account) {
        boolean flag = false;
        AccountEntity am = new AccountEntity();
        String query = "UPDATE accounts SET password = ? WHERE username = ?";

        try {
            connection = JDBCConnect.getJDBCConnection();

            if (am.getOne(txt_user) == null) {
                System.out.println("This account doesn't exists!");
            } else {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, account.getPassword());
                preparedStatement.setString(2, account.getUsername());

                if (preparedStatement.executeUpdate() > 0) {
                    flag = true;
                }

                return flag;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCConnect.closeResultSet(rs);
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }

        return flag;
    }

    public boolean Delete(int user_number) {
        boolean flag = false;

        String query = "DELETE FROM accounts WHERE user_number = ?";
    
        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user_number);

            if (preparedStatement.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }

        return flag;
    }
    
    public static void main(String[] args) {
        System.out.println(new AccountEntity().getAll());
    }
}

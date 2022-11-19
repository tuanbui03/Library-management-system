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
                        rs.getString("UID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getInt("gender"),
                        rs.getString("email"),
                        rs.getDate("dob"),
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

    
    public Account getOne(int id) {
        String sql = "SELECT * FROM accounts WHERE id = ?";

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Account acc = new Account(rs.getInt("id"),
                        rs.getString("UID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getInt("gender"),
                        rs.getString("email"),
                        rs.getDate("dob"),
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
                        rs.getString("UID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getInt("gender"),
                        rs.getString("email"),
                        rs.getDate("dob"),
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

    public boolean insert(Account acc) {
        boolean flag = false;
        String insertOne = "INSERT INTO accounts (UID, username, password, full_name, gender, email, dob, mobile, status, roleId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(insertOne);
            preparedStatement.setString(1, acc.getUID());
            preparedStatement.setString(2, acc.getUsername());
            preparedStatement.setString(3, acc.getPassword());
            preparedStatement.setString(4, acc.getFull_name());
            preparedStatement.setInt(5, acc.getGender());
            preparedStatement.setString(6, acc.getEmail());
            preparedStatement.setDate(7, acc.getDob());
            preparedStatement.setString(8, acc.getMobile());
            preparedStatement.setInt(9, acc.getStatus());
            preparedStatement.setInt(10, acc.getRoleId());

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


    public boolean update(Account acc) {
        boolean flag = false;
        AccountEntity am = new AccountEntity();
        String query = "UPDATE accounts SET UID = ?, username = ?, password = ?, full_name = ?, gender = ?, email = ?, dob = ?, mobile = ?, status = ?, roleId = ?  WHERE id = ?";

        try {
            connection = JDBCConnect.getJDBCConnection();

            if (am.getOne(acc.getId()) == null) {
                System.out.println("This account doesn't exists!");
            } else {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, acc.getUID());
                preparedStatement.setString(2, acc.getUsername());
                preparedStatement.setString(3, acc.getPassword());
                preparedStatement.setString(4, acc.getFull_name());
                preparedStatement.setInt(5, acc.getGender());
                preparedStatement.setString(6, acc.getEmail());
                preparedStatement.setDate(7, acc.getDob());
                preparedStatement.setString(8, acc.getMobile());
                preparedStatement.setInt(9, acc.getStatus());
                preparedStatement.setInt(10, acc.getRoleId());

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


    public boolean delete(int id) {
        boolean flag = false;

        String query = "DELETE FROM accounts WHERE id = ?";

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

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
        System.out.println(new AccountEntity().getOne(1));
    }
}

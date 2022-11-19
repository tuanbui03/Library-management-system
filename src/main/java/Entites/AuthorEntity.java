/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entites;

import Models.Author;
import java.sql.*;
import db.*;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class AuthorEntity {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;


    public ArrayList<Author> getAll() {
        ArrayList<Author> list = new ArrayList<>();
        String query = "SELECT * FROM authors";

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Author author = new Author(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("dob"),
                        rs.getString("sign_name"));

                list.add(author);
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


    public Author getOne(int id) {
        String query = "SELECT * FROM authors WHERE id = ?";

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Author author = new Author(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("dob"),
                        rs.getString("sign_name"));

                return author;
            } else {
                System.out.println("This Author doesn't exists!");
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


    public boolean insert(Author obj) {
        boolean flag = false;
        String query = "INSERT INTO accounts (name, dob, sign_name) VALUES (?, ?, ?)";

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setDate(2, obj.getDob());
            preparedStatement.setString(3, obj.getSign_name());

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


    public boolean update(Author obj) {
        boolean flag = false;
        AuthorEntity ae = new AuthorEntity();
        String query = "UPDATE authors SET name = ?, dob = ?, sign_name = ? WHERE id = ?";

        try {
            connection = JDBCConnect.getJDBCConnection();
            
            if (ae.getOne(obj.getId()) == null) {
                System.out.println("This author doesn't exists!");
            } else {
                connection = JDBCConnect.getJDBCConnection();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setDate(2, obj.getDob());
                preparedStatement.setString(3, obj.getSign_name());
                preparedStatement.setInt(4, obj.getId());
                
                if (preparedStatement.executeUpdate() > 0) {
                    flag = true;
                }
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

        String query = "DELETE FROM authors WHERE id = ?";

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

}

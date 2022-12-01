/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package Entites;

import Models.Author;

import db.JDBCConnect;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 84563
 */
public class AuthorEntity {

    public static Connection connection = null;
    public static PreparedStatement preparedStatement = null;
    public static ResultSet rs = null;

    public static ObservableList<Author> GetAll() {
        ObservableList<Author> list = FXCollections.observableArrayList();

        String sql = "SELECT * FROM authors";

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            for (int i = 1; rs.next(); i++) {
                Author author = new Author();

                author.setIndex(i);
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                author.setDob(rs.getString("dob"));
                author.setSign_name(rs.getString("sign_name"));
                author.setCreatedAt(rs.getString("createdAt"));
                author.setUpdatedAt(rs.getString("updatedAt"));

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

    public static Author GetAuthorWithId(int id) {
        String sql = "SELECT * FROM authors WHERE id = ?";

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Author author = new Author();

                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                author.setDob(rs.getString("dob"));
                author.setSign_name(rs.getString("sign_name"));
                author.setCreatedAt(rs.getString("createdAt"));
                author.setUpdatedAt(rs.getString("updatedAt"));

                return author;
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
    
    public static Author GetAuthorWithName(String name) {
        String sql = "SELECT * FROM authors WHERE name = ?";

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Author author = new Author();

                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                author.setDob(rs.getString("dob"));
                author.setSign_name(rs.getString("sign_name"));
                author.setCreatedAt(rs.getString("createdAt"));
                author.setUpdatedAt(rs.getString("updatedAt"));

                return author;
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

    public static ObservableList<Author> Search(String name) {
        ObservableList<Author> list = FXCollections.observableArrayList();

        String sql = "SELECT * FROM authors WHERE name like ?";

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");

            rs = preparedStatement.executeQuery();

            for (int i = 1; rs.next(); i++) {
                Author author = new Author();

                author.setIndex(i);
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                author.setDob(rs.getString("dob"));
                author.setSign_name(rs.getString("sign_name"));
                author.setCreatedAt(rs.getString("createdAt"));
                author.setUpdatedAt(rs.getString("updatedAt"));

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

    public static boolean Add(Author author) {
        String sql = "INSERT INTO authors"
                + "(name, dob ,signName, createdAt, updatedAt) "
                + "VALUES(?, ?, ?, ?, ?)";

//      set time at present with accuracy approximately is millis
        long milis = System.currentTimeMillis();
        Date preDate = new Date(milis);

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(1, author.getName());
            preparedStatement.setDate(2, convertStringToDate(author.getDob()));
            preparedStatement.setString(3, author.getSign_name());
            preparedStatement.setDate(4, preDate);
            preparedStatement.setDate(5, preDate);
            preparedStatement.executeUpdate();

            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public static boolean Update(Author author) {
        String sql = "UPDATE authors SET name = ?, dob = ?, signName = ?,  WHERE (`id` = ?);";

//      set time at present with accuracy approximately is millis
        long milis = System.currentTimeMillis();
        Date preDate = new Date(milis);

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(1, author.getName());
            preparedStatement.setDate(2, convertStringToDate(author.getDob()));
            preparedStatement.setString(3, author.getSign_name());
            preparedStatement.setDate(4, preDate);
            preparedStatement.setInt(1, author.getId());
            preparedStatement.executeUpdate();

            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public static boolean Delete(int id) {
        String sql = "Delete from authors WHERE id = ?";

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    private static Date convertStringToDate(String date) {
        String[] dateArray = date.split("-");
        int year = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int day = Integer.parseInt(dateArray[2]);
        LocalDate localdate = LocalDate.of(year, month, day);
        Date newDate = Date.valueOf(localdate);

        return newDate;
    }
}

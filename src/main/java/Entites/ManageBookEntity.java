/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entites;

import Models.ManageBook;
import db.*;
import java.sql.*;
import javafx.collections.*;

/**
 *
 * @author PC
 */
public class ManageBookEntity {

    public static Connection connection = null;
    public static PreparedStatement preparedStatement = null;
    public static ResultSet rs = null;

    public static ObservableList<ManageBook> GetAllBookInfo() {
        ObservableList<ManageBook> categories = FXCollections.observableArrayList();
        String sql = "Select manage_book.*, accounts.UID AS accountUID, books.name AS bookName, books.co_year, books.price, books.quantity, books.description, books.categoryId, books.authorId, books.publishId, status_manage.name AS statusManageName "
                + "FROM manage_book "
                + "JOIN accounts ON manage_book.accountId = accounts.id "
                + "JOIN books ON manage_book.bookId = books.id "
                + "JOIN status_manage ON manage_book.statusId = status_manage.id";

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall(sql);
            rs = preparedStatement.executeQuery();

            for (int i = 1; rs.next(); i++) {
                ManageBook mb = new ManageBook();

                mb.setIndex(i);
                mb.setId(rs.getInt("id"));
                mb.getAccount().setId(rs.getInt("accountId"));
                mb.getAccount().setUID(rs.getString("accountUID"));
                mb.getBook().setId(rs.getInt("bookId"));
                mb.getBook().setName(rs.getString("bookName"));
                mb.getBook().setCoyear(rs.getString("co_year"));
                mb.getBook().setPrice(rs.getFloat("price"));
                mb.getBook().setQuantity(rs.getInt("quantity"));
                mb.getBook().setDescription(rs.getString("description"));
                mb.getBook().setCategoryId(rs.getInt("categoryId"));
                mb.getBook().setAuthorId(rs.getInt("authorId"));
                mb.getBook().setPublishingId(rs.getInt("publishId"));
                mb.getStatus().setId(rs.getInt("statusId"));
                mb.getStatus().setName(rs.getString("statusManageName"));
                mb.setCreatedAt(rs.getString("createdAt"));
                mb.setUpdatedAt(rs.getString("updatedAt"));

                categories.add(mb);
            }

            return categories;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
//          Close databse at end
            JDBCConnect.closeResultSet(rs);
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }
        return null;
    }

    public static ObservableList<ManageBook> SearchByBookName(String search) {
        ObservableList<ManageBook> categories = FXCollections.observableArrayList();
        String sql = "Select manage_book.*, accounts.UID AS accountUID, books.name AS bookName, books.co_year, books.price, books.quantity, books.description, books.categoryId, books.authorId, books.publishId, status_manage.name AS statusManageName "
                + "FROM manage_book "
                + "JOIN accounts ON manage_book.accountId = accounts.id "
                + "JOIN books ON manage_book.bookId = books.id "
                + "JOIN status_manage ON manage_book.statusId = status_manage.id "
                + "WHERE books.name like ?";
        try {
//          connect to database and execute query with hidden value
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall(sql);
//          set hidden value in query
            preparedStatement.setString(1, "%" + search + "%");
            rs = preparedStatement.executeQuery();

//          set a Category, add and return ObservableList with name is categories
            for (int i = 1; rs.next(); i++) {
                ManageBook mb = new ManageBook();

                mb.setIndex(i);
                mb.setId(rs.getInt("id"));
                mb.getAccount().setId(rs.getInt("accountId"));
                mb.getAccount().setUID(rs.getString("accountUID"));
                mb.getBook().setId(rs.getInt("bookId"));
                mb.getBook().setName(rs.getString("bookName"));
                mb.getBook().setCoyear(rs.getString("co_year"));
                mb.getBook().setPrice(rs.getFloat("price"));
                mb.getBook().setQuantity(rs.getInt("quantity"));
                mb.getBook().setDescription(rs.getString("description"));
                mb.getBook().setCategoryId(rs.getInt("categoryId"));
                mb.getBook().setAuthorId(rs.getInt("authorId"));
                mb.getBook().setPublishingId(rs.getInt("publishId"));
                mb.getStatus().setId(rs.getInt("statusId"));
                mb.getStatus().setName(rs.getString("statusManageName"));
                mb.setCreatedAt(rs.getString("createdAt"));
                mb.setUpdatedAt(rs.getString("updatedAt"));

                categories.add(mb);
            }

            return categories;
        } catch (SQLException ex) {
//          show message in console screen when wrong at query
            System.out.println(ex.getMessage());
        } finally {
//          Close databse at end
            JDBCConnect.closeResultSet(rs);
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }
        return null;
    }

    public static boolean Add(ManageBook obj) {
        String sql = "INSERT INTO manage_book (price_per_book, accountId, bookId, statusId, createdAt, updatedAt) values(?, ?, ?, ?, ?, ?)";
//      set time at present with accuracy approximately is millis
        long milis = System.currentTimeMillis();
        Date preDate = new Date(milis);

        try {
//          connect to database and execute query with hidden value
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall(sql);
//          set hidden value in query
            preparedStatement.setFloat(1, obj.getPricePerBook());
            preparedStatement.setInt(2, obj.getAccount().getId());
            preparedStatement.setInt(3, obj.getBook().getId());
            preparedStatement.setInt(4, obj.getStatus().getId());
            preparedStatement.setDate(5, preDate);
            preparedStatement.setDate(6, preDate);

//          if add sucess reset all feild and reset table view, all feild else show message fail
            if (preparedStatement.executeUpdate() > 0) {

                System.out.println("Add successfully !");

                return true;
            } else {
                System.out.println("Add fail !");

                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean Update(ManageBook obj) {
        String sql = "UPDATE manage_book SET price_per_book = ?, accountId = ?, bookId = ?, statusId = ?, updatedAt = ? WHERE bookId = ?";
        long milis = System.currentTimeMillis();
        Date preDate = new Date(milis);

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall(sql);
            preparedStatement.setFloat(1, obj.getPricePerBook());
            preparedStatement.setInt(2, obj.getAccount().getId());
            preparedStatement.setInt(3, obj.getBook().getId());
            preparedStatement.setInt(4, obj.getStatus().getId());
            preparedStatement.setDate(5, preDate);
            preparedStatement.setInt(6, obj.getId());

//          if update sucess reset all feild and reset table view, all feild else show message fail
            if (preparedStatement.executeUpdate() > 0) {
                System.out.println("Updated Successfully !");

                return true;
            } else {
                System.out.println("Updated Fail !");

                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean Delete(int id) {
        String sql = "Delete FROM manage_book WHERE bookId = ?";

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() > 0) {
                System.out.println("Delete Successfully !");

                return true;
            } else {
                System.out.println("Delete Faild !");

                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}

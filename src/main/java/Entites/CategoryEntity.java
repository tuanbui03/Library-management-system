/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entites;

import Models.Category;
import java.sql.*;
import db.*;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author PC
 */
public class CategoryEntity {

    public static Connection connection = null;
    public static PreparedStatement preparedStatement = null;
    public static ResultSet rs = null;

    public static ObservableList<Category> GetAll() {
        ObservableList<Category> categories = FXCollections.observableArrayList();

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall("Select * from categories");
            rs = preparedStatement.executeQuery();

            for (int i = 1; rs.next(); i++) {
                Category c = new Category();

                c.setIndex(i);
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setCreatedAt(rs.getString("createdAt"));
                c.setUpdatedAt(rs.getString("updatedAt"));

                categories.add(c);
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

    public static Category GetCategoryById(int id) {

        try {
//          connect to database and execute query with hidden value
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall("Select * from categories WHERE id = ?");
//          set hidden value in query
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

//          set a Category, add and return ObservableList with name is categories
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setCreatedAt(rs.getString("createdAt"));
                c.setUpdatedAt(rs.getString("updatedAt"));

                return c;
            }

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
    
    public static Category GetCategoryByName(String name) {

        try {
//          connect to database and execute query with hidden value
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall("Select * from categories WHERE name = ?");
//          set hidden value in query
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();

//          set a Category, add and return ObservableList with name is categories
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setCreatedAt(rs.getString("createdAt"));
                c.setUpdatedAt(rs.getString("updatedAt"));

                return c;
            }

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

    public static ObservableList<Category> Search(String search) {
        ObservableList<Category> categories = FXCollections.observableArrayList();

        try {
//          connect to database and execute query with hidden value
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall("Select * from categories WHERE name like ?");
//          set hidden value in query
            preparedStatement.setString(1, "%" + search + "%");
            rs = preparedStatement.executeQuery();

//          set a Category, add and return ObservableList with name is categories
            for (int i = 1; rs.next(); i++) {
                Category c = new Category();

                c.setIndex(i);
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setCreatedAt(rs.getString("createdAt"));
                c.setUpdatedAt(rs.getString("updatedAt"));

                categories.add(c);
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

    public static boolean Add(Category obj) {
        String sql = "Insert into categories (name, createdAt, updatedAt) values(?, ?, ?)";
//      set time at present with accuracy approximately is millis
        long milis = System.currentTimeMillis();
        Date preDate = new Date(milis);

        try {
//          connect to database and execute query with hidden value
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall(sql);
//          set hidden value in query
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setDate(2, preDate);
            preparedStatement.setDate(3, preDate);

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

    public static boolean Update(Category obj) {
        String sql = "UPDATE categories SET name = ?, updatedAt = ? WHERE id = ?";
        long milis = System.currentTimeMillis();
        Date preDate = new Date(milis);

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setDate(2, preDate);
            preparedStatement.setInt(3, obj.getId());
            System.out.println(preparedStatement.getResultSet());

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
        String sql = "Delete FROM categories WHERE id = ?";

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

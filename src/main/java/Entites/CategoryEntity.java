/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entites;

import Models.Category;
import java.sql.*;
import db.*;
import java.util.ArrayList;


/**
 *
 * @author PC
 */
public class CategoryEntity implements ICommon<Category> {
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    @Override
    public ArrayList<Category> getAll() {
//      Call array list with type is Category
        ArrayList<Category> list = new ArrayList<>();
//      Query SELECT in SQL
        String query = "SELECT * FROM categories";

        try {
//          Connect to database and execute query
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

//          Call value in databse and set for list Category
            while (rs.next()) {
                Category category = new Category(rs.getInt("id"),
                        rs.getString("name"));

                list.add(category);
            }

            return list;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//          Close databse at end
            JDBCConnect.closeResultSet(rs);
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }

        return null;
    }

    @Override
    public Category getOne(int id) {
//      Query select in database with hidden value "?"
        String query = "SELECT * FROM categories where id = ?";

        try {
//            Connect to database, set hidden value and execute query
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            
//          if id exists in database: set value in Category of Models else print in console: "This category doesn't exists!"
            if (rs.next()) {
                Category category = new Category(rs.getInt("id"),
                        rs.getString("name"));

                return category;
            } else {
                System.out.println("This category doesn't exists!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//          Close database at end
            JDBCConnect.closeResultSet(rs);
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }

        return null;
    }

    @Override
    public boolean insert(Category obj) {
        boolean flag = false;
//      Query insert in database with hidden value
        String query = "INSERT INTO categories (name) VALUES (?)";

        try {
//          Connect to database and set hidden value
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, obj.getName());

//          Execute Query, if insert successfull set flag equal true
            if (preparedStatement.executeUpdate() > 0) {
                flag = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//          Close database at end
            JDBCConnect.closeResultSet(rs);
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }

        return flag;
    }

    @Override
    public boolean update(Category obj) {
        boolean flag = false;
        CategoryEntity ce = new CategoryEntity();
//        Query Update in Database with hidden value "?"
        String query = "UPDATE categories SET name = ? WHERE id = ?";

        try {
//          Connect to database
            connection = JDBCConnect.getJDBCConnection();

            if (ce.getOne(obj.getId()) == null) {
                System.out.println("This category doesn't exists!");
            } else {
//              Set hidden value
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setInt(2, obj.getId());

//              Execute Query, if update successfull set flag equal true
                if (preparedStatement.executeUpdate() > 0) {
                    flag = true;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//          Close database at end
            JDBCConnect.closeResultSet(rs);
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }

        return flag;
    }

    @Override
    public boolean delete(int id) {
        boolean flag = false;

//      Query Delete in database with hidden value
        String query = "DELETE FROM categories WHERE id = ?";

        try {
//          Connect to Database, set value for hidden value
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

//              Execute Query, if delete successfull set flag equal true
            if (preparedStatement.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//          Close database at end
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }

        return flag;
    }

}

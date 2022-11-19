/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entites;

import Models.Book;
import java.sql.*;
import db.*;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class BookEntity implements ICommon<Book> {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    @Override
    public ArrayList<Book> getAll() {
//      Call array list with type is Borrow
        ArrayList<Book> list = new ArrayList<>();
//      Query SELECT in SQL
        String query = "SELECT * FROM books";

        try {
//          Connect to database and execute query
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

//          Call value in databse and set for list Borrow
            while (rs.next()) {
                Book book = new Book(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getDate("co_year"),
                        rs.getFloat("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getInt("categoryId"),
                        rs.getInt("authorId"),
                        rs.getInt("publishId"));

                list.add(book);
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
    public Book getOne(int id) {
//      Query select in database with hidden value "?"
        String query = "SELECT * FROM books where id = ?";

        try {
//            Connect to database, set hidden value and execute query
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            
//          if id exists in database: set value in Borrow of Models else print in console: "This borrow doesn't exists!"
            if (rs.next()) {
                Book book = new Book(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getDate("co_year"),
                        rs.getFloat("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getInt("categoryId"),
                        rs.getInt("authorId"),
                        rs.getInt("publishId"));

                return book;
            } else {
                System.out.println("This book doesn't exists!");
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
    public boolean insert(Book obj) {
        boolean flag = false;
//      Query insert in database with hidden value
        String query = "INSERT INTO books (name, image, co_year, price, quantity, description, categoryId, authorId, publishId) VALUES (?, ?, ?, ? ,? ,? ,? ,? ,?)";

        try {
//          Connect to database and set hidden value
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getImage());
            preparedStatement.setDate(3, obj.getCo_year());
            preparedStatement.setFloat(4, obj.getPrice());
            preparedStatement.setInt(5, obj.getQuantity());
            preparedStatement.setString(6, obj.getDescription());
            preparedStatement.setInt(7, obj.getCategoryId());
            preparedStatement.setInt(8, obj.getAuthorId());
            preparedStatement.setInt(9, obj.getPublishId());

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
    public boolean update(Book obj) {
        boolean flag = false;
        BookEntity be = new BookEntity();
//        Query Update in Database with hidden value "?"
        String query = "UPDATE books SET name = ?, image = ?, co_year = ?, price = ?, quantity = ?, description = ?, categoryId = ?, authorId = ?, publishId = ? WHERE id = ?";

        try {
//          Connect to database
            connection = JDBCConnect.getJDBCConnection();

            if (be.getOne(obj.getId()) == null) {
                System.out.println("This book doesn't exists!");
            } else {
//              Set hidden value
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getImage());
                preparedStatement.setDate(3, obj.getCo_year());
                preparedStatement.setFloat(4, obj.getPrice());
                preparedStatement.setInt(5, obj.getQuantity());
                preparedStatement.setString(6, obj.getDescription());
                preparedStatement.setInt(7, obj.getCategoryId());
                preparedStatement.setInt(8, obj.getAuthorId());
                preparedStatement.setInt(9, obj.getPublishId());
                preparedStatement.setInt(10, obj.getId());

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
        String query = "DELETE FROM books WHERE id = ?";

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

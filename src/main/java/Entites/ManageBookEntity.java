/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entites;

import Models.ManageBook;
import java.sql.*;
import db.*;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class ManageBookEntity implements ICommon<ManageBook> {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    @Override
    public ArrayList<ManageBook> getAll() {
//      Call array list with type is ManageBook
        ArrayList<ManageBook> list = new ArrayList<>();
//      Query SELECT in SQL
        String query = "SELECT * FROM manage_book";

        try {
//          Connect to database and execute query
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

//          Call value in databse and set for list ManageBook
            while (rs.next()) {
                ManageBook manageBook = new ManageBook(rs.getInt("id"),
                        rs.getDate("createdAt"),
                        rs.getFloat("price_per_book"),
                        rs.getInt("accountId"),
                        rs.getInt("bookId"),
                        rs.getInt("statusId"));

                list.add(manageBook);
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
    public ManageBook getOne(int id) {
//      Query select in database with hidden value "?"
        String query = "SELECT * FROM manage_book where id = ?";

        try {
//            Connect to database, set hidden value and execute query
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

//          if id exists in database: set value in ManageBook of Models else print in console: "This manage book doesn't exists!"
            if (rs.next()) {
                ManageBook manageBook = new ManageBook(rs.getInt("id"),
                        rs.getDate("createdAt"),
                        rs.getFloat("price_per_book"),
                        rs.getInt("accountId"),
                        rs.getInt("bookId"),
                        rs.getInt("statusId"));

                return manageBook;
            } else {
                System.out.println("This manage book doesn't exists!");
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
    public boolean insert(ManageBook obj) {
        boolean flag = false;
//      Query insert in database with hidden value
        String query = "INSERT INTO manage_book (createdAt, price_per_book, accountId, bookId, statusId) VALUES (?, ?, ?, ?, ?)";

        try {
//          Connect to database and set hidden value
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, obj.getCreatedAt());
            preparedStatement.setFloat(2, obj.getPrice_per_book());
            preparedStatement.setInt(3, obj.getAccountId());
            preparedStatement.setInt(4, obj.getBookId());
            preparedStatement.setInt(5, obj.getStatusId());

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
    public boolean update(ManageBook obj) {
        boolean flag = false;
        ManageBookEntity mbe = new ManageBookEntity();
//        Query Update in Database with hidden value "?"
        String query = "UPDATE manage_book SET createdAt = ?, price_per_book = ?, accountId = ?, bookId = ?, statusId = ? WHERE id = ?";

        try {
//          Connect to database
            connection = JDBCConnect.getJDBCConnection();

            if (mbe.getOne(obj.getId()) == null) {
                System.out.println("This manage book doesn't exists!");
            } else {
//              Set hidden value
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setDate(1, obj.getCreatedAt());
                preparedStatement.setFloat(2, obj.getPrice_per_book());
                preparedStatement.setInt(3, obj.getAccountId());
                preparedStatement.setInt(4, obj.getBookId());
                preparedStatement.setInt(5, obj.getStatusId());
                preparedStatement.setInt(6, obj.getId());

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
        String query = "DELETE FROM manage_book WHERE id = ?";

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

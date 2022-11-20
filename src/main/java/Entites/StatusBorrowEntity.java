/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entites;

import Models.StatusBorrow;
import java.sql.*;
import db.*;
import java.util.ArrayList;
/**
 *
 * @author PC
 */
public class StatusBorrowEntity {
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    public ArrayList<StatusBorrow> getAll() {
//      Call array list with type is StatusBorrow
        ArrayList<StatusBorrow> list = new ArrayList<>();
//      Query SELECT in SQL
        String query = "SELECT * FROM status_borrow";

        try {
//          Connect to database and execute query
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

//          Call value in databse and set for list StatusBorrow
            while (rs.next()) {
                StatusBorrow statusBorrow = new StatusBorrow(rs.getInt("id"),
                        rs.getString("name"));

                list.add(statusBorrow);
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

    public StatusBorrow getOne(int id) {
//      Query select in database with hidden value "?"
        String query = "SELECT * FROM status_borrow where id = ?";

        try {
//            Connect to database, set hidden value and execute query
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            
//          if id exists in database: set value in StatusBorrow of Models else print in console: "This statusBorrow doesn't exists!"
            if (rs.next()) {
                StatusBorrow statusBorrow = new StatusBorrow(rs.getInt("id"),
                        rs.getString("name"));

                return statusBorrow;
            } else {
                System.out.println("This StatusBorrow doesn't exists!");
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

    public boolean insert(StatusBorrow obj) {
        boolean flag = false;
//      Query insert in database with hidden value
        String query = "INSERT INTO status_borrow (name) VALUES (?)";

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

    public boolean update(StatusBorrow obj) {
        boolean flag = false;
        StatusBorrowEntity sbe = new StatusBorrowEntity();
//        Query Update in Database with hidden value "?"
        String query = "UPDATE status_borrow SET name = ? WHERE id = ?";

        try {
//          Connect to database
            connection = JDBCConnect.getJDBCConnection();

            if (sbe.getOne(obj.getId()) == null) {
                System.out.println("This StatusBorrow doesn't exists!");
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

    public boolean delete(int id) {
        boolean flag = false;

//      Query Delete in database with hidden value
        String query = "DELETE FROM status_borrow WHERE id = ?";

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

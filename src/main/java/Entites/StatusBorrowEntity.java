/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entites;

import Models.StatusBorrow;
import db.JDBCConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author PC
 */
public class StatusBorrowEntity {

    public static Connection connection = null;
    public static PreparedStatement preparedStatement = null;
    public static ResultSet rs = null;

    public static ObservableList<StatusBorrow> GetAll() {
        ObservableList<StatusBorrow> statusBorrows = FXCollections.observableArrayList();

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall("Select * from status_borrow");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                StatusBorrow statusBorrow = new StatusBorrow();

                statusBorrow.setId(rs.getInt("id"));
                statusBorrow.setName(rs.getString("name"));

                statusBorrows.add(statusBorrow);
            }

            return statusBorrows;
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
    
    public static StatusBorrow GetStatusBorrowWithId(int id) {

        try {
//          connect to database and execute query with hidden value
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall("Select * from status_borrow WHERE id = ?");
//          set hidden value in query
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

//          set a StatusBorrow, add and return ObservableList with name is status_borrow
            while (rs.next()) {
                StatusBorrow statusBorrow = new StatusBorrow();

                statusBorrow.setId(rs.getInt("id"));
                statusBorrow.setName(rs.getString("name"));

                return statusBorrow;
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

    public static StatusBorrow GetStatusBorrowWithName(String name) {

        try {
//          connect to database and execute query with hidden value
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall("Select * from status_borrow WHERE name = ?");
//          set hidden value in query
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();

//          set a StatusBorrow, add and return ObservableList with name is status_borrow
            while (rs.next()) {
                StatusBorrow statusBorrow = new StatusBorrow();

                statusBorrow.setId(rs.getInt("id"));
                statusBorrow.setName(rs.getString("name"));

                return statusBorrow;
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
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entites;

import Models.Borrow;
import java.sql.*;
import db.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author PC
 */
public class BorrowEntity {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    public ObservableList<Borrow> GetAll() {
//      Call array list with type is Borrow
        ObservableList<Borrow> list = FXCollections.observableArrayList();
//      Query SELECT in SQL
        String query = "SELECT * FROM borrows";

        try {
//          Connect to database and execute query
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

//          Call value in databse and set for list Borrow
            int i = 1;
            while (rs.next()) {
                Borrow borrow = new Borrow();

                borrow.setIndex(i);
                borrow.setId(rs.getInt("id"));
                borrow.setBorrowAt(rs.getString("borrowAt"));
                borrow.setTime_out(rs.getInt("time_out"));
                borrow.setRefundAt(rs.getString("refundAt"));
                borrow.setAmount_of_pay(rs.getFloat("amount_of_pay"));
                borrow.setManageId(rs.getInt("manageId"));
                borrow.setStatusId(rs.getInt("statusId"));

                list.add(borrow);
                i++;
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

    public ObservableList<Borrow> GetBorrowById(int id) {
//      Call array list with type is Borrow
        ObservableList<Borrow> list = FXCollections.observableArrayList();
//      Query SELECT in SQL
        String query = "SELECT * FROM borrows WHERE id = ?";

        try {
//          Connect to database and execute query
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

//          Call value in databse and set for list Borrow
            while (rs.next()) {
                Borrow borrow = new Borrow();

                borrow.setId(rs.getInt("id"));
                borrow.setBorrowAt(rs.getString("borrowAt"));
                borrow.setTime_out(rs.getInt("time_out"));
                borrow.setRefundAt(rs.getString("refundAt"));
                borrow.setAmount_of_pay(rs.getFloat("amount_of_pay"));
                borrow.setManageId(rs.getInt("manageId"));
                borrow.setStatusId(rs.getInt("statusId"));

                list.add(borrow);
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

    public ObservableList<Borrow> Search(String name) {
//      Call array list with type is Borrow
        ObservableList<Borrow> list = FXCollections.observableArrayList();
//      Query SELECT in SQL
        String query = "SELECT * FROM borrows WHERE name like ?";

        try {
//          Connect to database and execute query
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();

//          Call value in databse and set for list Borrow
            int i = 1;
            while (rs.next()) {
                Borrow borrow = new Borrow();

                borrow.setIndex(i);
                borrow.setId(rs.getInt("id"));
                borrow.setBorrowAt(rs.getString("borrowAt"));
                borrow.setTime_out(rs.getInt("time_out"));
                borrow.setRefundAt(rs.getString("refundAt"));
                borrow.setAmount_of_pay(rs.getFloat("amount_of_pay"));
                borrow.setManageId(rs.getInt("manageId"));
                borrow.setStatusId(rs.getInt("statusId"));

                list.add(borrow);
                i++;
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

    public boolean insert(Borrow obj) {
        boolean flag = false;
        BorrowEntity be = new BorrowEntity();
//      Query insert in database with hidden value
        String query = "INSERT INTO borrows (borrowAt, time_out, refundAt, amount_of_pay, manageId, statusId) VALUES (?, ?, ?, ? ,? ,?)";

        try {
//          Connect to database and set hidden value
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, be.convertStringToDate(obj.getBorrowAt()));
            preparedStatement.setInt(2, obj.getTime_out());
            preparedStatement.setDate(3, be.convertStringToDate(obj.getRefundAt()));
            preparedStatement.setFloat(4, obj.getAmount_of_pay());
            preparedStatement.setInt(5, obj.getManageId());
            preparedStatement.setInt(6, obj.getStatusId());

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

    public boolean Update(Borrow obj) {
        BorrowEntity be = new BorrowEntity();
//        Query Update in Database with hidden value "?"
        String query = "UPDATE borrows SET borrowAt = ?, time_out = ?, refundAt = ?, amount_of_pay = ?, manageId = ?, statusId = ? WHERE id = ?";

        try {
//          Connect to database
            connection = JDBCConnect.getJDBCConnection();
//              Set hidden value
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, be.convertStringToDate(obj.getBorrowAt()));
            preparedStatement.setInt(2, obj.getTime_out());
            preparedStatement.setDate(3, be.convertStringToDate(obj.getRefundAt()));
            preparedStatement.setFloat(4, obj.getAmount_of_pay());
            preparedStatement.setInt(5, obj.getManageId());
            preparedStatement.setInt(6, obj.getStatusId());
            preparedStatement.setInt(7, obj.getId());

//          Execute Query, if update successfull set flag equal true
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//          Close database at end
            JDBCConnect.closeResultSet(rs);
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }

        return false;
    }

    public boolean Delete(int id) {
//      Query Delete in database with hidden value
        String query = "DELETE FROM borrows WHERE id = ?";

        try {
//          Connect to Database, set value for hidden value
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

//          Execute Query, if delete successfull set flag equal true
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//          Close database at end
            JDBCConnect.closeResultSet(rs);
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }

        return false;
    }

    private Date convertStringToDate(String date) {
        String[] dateArray = date.split("-");
        int year = Integer.parseInt(dateArray[1]);
        int month = Integer.parseInt(dateArray[2]);
        int day = Integer.parseInt(dateArray[3]);
        LocalDate localdate = LocalDate.of(year, month, day);
        Date newDate = Date.valueOf(localdate);

        return newDate;
    }
}

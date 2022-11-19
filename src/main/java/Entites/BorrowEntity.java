/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entites;

import Models.Borrow;
import java.sql.*;
import db.*;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class BorrowEntity {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    public ArrayList<Borrow> getAll() {
//      Call array list with type is Borrow
        ArrayList<Borrow> list = new ArrayList<>();
//      Query SELECT in SQL
        String query = "SELECT * FROM borrows";

        try {
//          Connect to database and execute query
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

//          Call value in databse and set for list Borrow
            while (rs.next()) {
                Borrow borrow = new Borrow(
                        rs.getInt("id"),
                        rs.getDate("borrowAt"),
                        rs.getInt("time_out"),
                        rs.getDate("refundAt"),
                        rs.getFloat("amount_of_pay"),
                        rs.getInt("manageId"),
                        rs.getInt("statusId")
                );

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

    public Borrow getOne(int id) {
//      Query select in database with hidden value "?"
        String query = "SELECT * FROM borrows where id = ?";

        try {
//            Connect to database, set hidden value and execute query
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

//          if id exists in database: set value in Borrow of Models else print in console: "This borrow doesn't exists!"
            if (rs.next()) {
                Borrow borrow = new Borrow(
                        rs.getInt("id"),
                        rs.getDate("borrowAt"),
                        rs.getInt("time_out"),
                        rs.getDate("refundAt"),
                        rs.getFloat("amount_of_pay"),
                        rs.getInt("manageId"),
                        rs.getInt("statusId")
                );

                return borrow;
            } else {
                System.out.println("This borrow doesn't exists!");
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

    public boolean insert(Borrow obj) {
        boolean flag = false;
//      Query insert in database with hidden value
        String query = "INSERT INTO borrows (borrowAt, time_out, refundAt, amount_of_pay, manageId, statusId) VALUES (?, ?, ?, ? ,? ,?)";

        try {
//          Connect to database and set hidden value
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, obj.getBorrowAt());
            preparedStatement.setInt(2, obj.getTime_out());
            preparedStatement.setDate(3, obj.getRefundAt());
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

    public boolean update(Borrow obj) {
        boolean flag = false;
        BorrowEntity be = new BorrowEntity();
//        Query Update in Database with hidden value "?"
        String query = "UPDATE borrows SET borrowAt = ?, time_out = ?, refundAt = ?, amount_of_pay = ?, manageId = ?, statusId = ? WHERE id = ?";

        try {
//          Connect to database
            connection = JDBCConnect.getJDBCConnection();

            if (be.getOne(obj.getId()) == null) {
                System.out.println("This borrow doesn't exists!");
            } else {
//              Set hidden value
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setDate(1, obj.getBorrowAt());
                preparedStatement.setInt(2, obj.getTime_out());
                preparedStatement.setDate(3, obj.getRefundAt());
                preparedStatement.setFloat(4, obj.getAmount_of_pay());
                preparedStatement.setInt(5, obj.getManageId());
                preparedStatement.setInt(6, obj.getStatusId());
                preparedStatement.setInt(7, obj.getId());
                
//              Execute Query, if update successfull set flag equal true
                if (preparedStatement.executeUpdate() > 0) {
                    flag = true;
                }
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

    public boolean delete(int id) {
        boolean flag = false;
//      Query Delete in database with hidden value
        String query = "DELETE FROM borrows WHERE id = ?";

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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entites;

import Models.Account;
import Models.Book;
import Models.Borrow;
import Models.StatusBorrow;
import java.sql.*;
import db.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

/**
 *
 * @author PC
 */
public class BorrowEntity {

    public static Connection connection = null;
    public static PreparedStatement preparedStatement = null;
    public static ResultSet rs = null;

    public static ObservableList<Borrow> GetAll() {
//      Call array list with type is Borrow
        ObservableList<Borrow> list = FXCollections.observableArrayList();
        String query = "Select bw.id,a.UID,bw.amount_of_pay, b.name as bookName,bw.borrowAt,bw.refundAt ,bw.time_out,sb.name as stautsName,a.username as accountName,bw.statusId,mb.accountId, mb.bookId,bw.manageId from borrow as bw\n"
                + " join status_borrow as sb on bw.statusId = sb.id\n"
                + " join manage_book as mb on bw.manageId = mb.id\n"
                + " join accounts as a on mb.accountId = a.id\n"
                + " join books as b on mb.bookId = b.id ";
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
                borrow.setStatusId(i);
                borrow.setStatusName(rs.getString("stautsName"));
                borrow.setBookName(rs.getString("bookName"));
                borrow.setAccountName(rs.getString("accountName"));
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

    public ObservableList<Borrow> GetBorrowByAccountId(int accountId) {
//      Call array list with type is Borrow
        ObservableList<Borrow> list = FXCollections.observableArrayList();
//      Query SELECT in SQL
        String query = "SELECT b.* "
                + "FROM borrow AS b "
                + "JOIN manage_book AS mb ON b.manageId = mb.id "
                + "WHERE mb.accountId = ?";

        try {
//          Connect to database and execute query
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, accountId);
            rs = preparedStatement.executeQuery();

//          Call value in databse and set for list Borrow
            for (int i=1; rs.next(); i++) {
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
    
    public ObservableList<Borrow> SearchBorrowByAccountId(int accountId, String search) {
//      Call array list with type is Borrow
        ObservableList<Borrow> list = FXCollections.observableArrayList();
//      Query SELECT in SQL
        String query = "SELECT b.* "
                + "FROM borrow AS b "
                + "JOIN manage_book AS mb ON b.manageId = mb.id "
                + "JOIN books ON mb.id = books.id "
                + "WHERE mb.accountId = ? AND books.name like ?";

        try {
//          Connect to database and execute query
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, accountId);
            preparedStatement.setString(2, "%" + search + "%");
            rs = preparedStatement.executeQuery();

//          Call value in databse and set for list Borrow
            for (int i=1; rs.next(); i++) {
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

    public static ObservableList<Borrow> Search(String name) {
//      Call array list with type is Borrow
        ObservableList<Borrow> list = FXCollections.observableArrayList();
//      Query SELECT in SQL
        String query = "Select bw.id,a.UID,bw.amount_of_pay, b.name as bookName,bw.borrowAt,bw.refundAt ,bw.time_out,sb.name as stautsName,a.username as accountName,bw.statusId,mb.accountId, mb.bookId,bw.manageId from borrow as bw\n"
                + " join status_borrow as sb on bw.statusId = sb.id\n"
                + " join manage_book as mb on bw.manageId = mb.id\n"
                + " join accounts as a on mb.accountId = a.id\n"
                + " join books as b on mb.bookId = b.id  where b.name like '%" + name + "%'";
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
                borrow.setStatusId(i);
                borrow.setStatusName(rs.getString("stautsName"));
                borrow.setBookName(rs.getString("bookName"));
                borrow.setAccountName(rs.getString("accountName"));
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

    public static boolean Add(Borrow obj) {
        boolean flag = false;
        BorrowEntity be = new BorrowEntity();
//      Query insert in database with hidden value
        String query = "INSERT INTO borrow (borrowAt, time_out, refundAt, amount_of_pay, manageId, statusId) VALUES (?, ?, ?, ? ,? ,?)";
        String sql_manage_lib = "Insert into `manage_book`(`price_per_book`, `accountId`, bookId, statusId , createdAt, updatedAt) values(?,?,?,?,?,?)";

        try {
//          Connect to database and set hidden value
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(sql_manage_lib);
            preparedStatement.setInt(1, 100);
            preparedStatement.setInt(2, obj.getAccountid().get());
            preparedStatement.setInt(3, obj.getBookid().get());
            preparedStatement.setInt(4, 1);
            preparedStatement.setString(5, obj.getBorrowAt());
            preparedStatement.setString(6, obj.getRefundAt());
            System.out.println(obj.getAccountid() + "b = " + obj.getBookid());
//          Execute Query, if insert successfull set flag equal true
            if (preparedStatement.executeUpdate() > 0) {

                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Connection");
                alert.setHeaderText(" Manager");
                alert.setContentText("Added Successfully!");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//          Close database at end
            JDBCConnect.closeResultSet(rs);
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }
//////////////////////////////////
        try {
//          Connect to database and set hidden value
            int id;
            id = newStatusID();
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, obj.getBorrowAt());
            preparedStatement.setInt(2, obj.getTime_out());
            preparedStatement.setString(3, obj.getRefundAt());
            preparedStatement.setFloat(4, obj.getAmount_of_pay());
            preparedStatement.setInt(5, id);
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

    public static int newStatusID() throws SQLException {
        String manageId = "select id from manage_book order by id DESC LIMIT 1";
        int id = 0;
        connection = JDBCConnect.getJDBCConnection();
        preparedStatement = connection.prepareStatement(manageId);
        rs = preparedStatement.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public static boolean Update(Borrow obj) {
//        Query Update in Database with hidden value "?"
        String sql_borrow = "UPDATE `borrow` SET `borrowAt` = ?,refundAt = ?,statusId = ? WHERE (`id` = ?);";
        String sql_manage_lib = "UPDATE `manage_book` SET  `accountId` = ?, bookId = ? WHERE (`id` = ?);";

        try {
            System.out.println("Insert Manage_book");
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall(sql_manage_lib);
            preparedStatement.setInt(1, obj.getAccountid().intValue());
            preparedStatement.setInt(2, obj.getBookid().intValue());
            preparedStatement.setInt(3, obj.getManageId());
            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() > 0) {
//                Alert alert = new Alert(Alert.AlertType.NONE);
//                alert.setAlertType(Alert.AlertType.INFORMATION);
//                alert.setTitle("Test Connection");
//                alert.setHeaderText("Status Manager");
//                alert.setContentText("Updated Successfully!");
//                alert.showAndWait();
            } else {
                return false;
            }
        } catch (SQLException | NullPointerException e) {
        }

        try {
//          Connect to database
            connection = JDBCConnect.getJDBCConnection();
//              Set hidden value
            preparedStatement = connection.prepareStatement(sql_borrow);
            preparedStatement.setString(1, obj.getBorrowAt());
            preparedStatement.setString(2, obj.getRefundAt());
            preparedStatement.setInt(3, obj.getStatusId());
            preparedStatement.setInt(4, obj.getId());

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
    
    public static boolean Delete(int id) {
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

    //lay id tu cac bang da chon
    public static int selectAccountIndex(ComboBox txtAccount) {
        ArrayList<Integer> list = new ArrayList<>();
        int accountID = 0;
        for (int i = 0; i < txtAccount.getItems().size(); i++) {
            if (txtAccount.getItems().get(i) == txtAccount.getValue()) {
                accountID = (i);
                break;
            }
        }

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall("SELECT * FROM accounts;");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("id"));
            }
        } catch (SQLException | NullPointerException ex) {
        }
        return list.get(accountID);
    }

    public static int selectBookIndex(ComboBox txtBook) {
        ArrayList<Integer> list = new ArrayList<>();
        int bookID = 0;
        for (int i = 0; i < txtBook.getItems().size(); i++) {
            if (txtBook.getItems().get(i) == txtBook.getValue()) {
                bookID = (i);
                break;
            }
        }
        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall("SELECT * FROM books;");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("id"));
            }
        } catch (SQLException | NullPointerException ex) {
        }
        return list.get(bookID);
    }

    public static int selectStatusIndex(ComboBox txtStatus) {
        ArrayList<Integer> list = new ArrayList<>();
        int StatusID = 0;
        for (int i = 0; i < txtStatus.getItems().size(); i++) {
            if (txtStatus.getItems().get(i) == txtStatus.getValue()) {
                StatusID = (i);
                break;
            }
        }
        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall("SELECT * FROM status_borrow;");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("id"));
            }
        } catch (SQLException | NullPointerException ex) {
        }

        return list.get(StatusID);
    }

    public static void data_Status(ComboBox<StatusBorrow> txtStatus) {
        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall("SELECT * FROM status_borrow;");
            rs = preparedStatement.executeQuery();
            ObservableList<StatusBorrow> list = FXCollections.observableArrayList();
            while (rs.next()) {
                StatusBorrow status_borrow = new StatusBorrow();
                status_borrow.setId(rs.getInt("id"));
                status_borrow.setName(rs.getString("name"));
                list.add(status_borrow);
            }
            txtStatus.setItems(list);
        } catch (SQLException | NullPointerException ex) {
        }
    }

    public static void data_Books(ComboBox<Book> txtBook) {
        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall("SELECT * FROM books;");
            rs = preparedStatement.executeQuery();
            ObservableList<Book> list = FXCollections.observableArrayList();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                list.add(book);
            }
            txtBook.setItems(list);
        } catch (SQLException | NullPointerException ex) {
        }
    }

    public static void data_Account(ComboBox<Account> txtAccount) {
        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall("SELECT * FROM accounts;");
            rs = preparedStatement.executeQuery();
            ObservableList<Account> list = FXCollections.observableArrayList();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setUsername(rs.getString("username"));
                list.add(account);
            }
            txtAccount.setItems(list);
        } catch (SQLException | NullPointerException ex) {
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import Entites.*;
import Models.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author 84563
 */
public class ManagementBorrowingController implements Initializable {

    @FXML
    private Label labelClock;

    int id, myIndex, manaId;
    @FXML
    private TableView<Borrow> table;

    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;

    @FXML
    private TextField txtID, txtTimeOut, txtSearch;

    @FXML
    private ComboBox<Account> txtAccount;

    @FXML
    private ComboBox<Book> txtBook;

    @FXML
    private ComboBox<StatusBorrow> txtStatus;

    @FXML
    private DatePicker txtBorrowAt, txtRefundAt;

    @FXML
    private Label errorTimeOut, errorStatus;

    @FXML
    private TableColumn<Borrow, String> colIndex, colUID, colBook, colBorrow, colRefund, colTime, colStatus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnRefesh;
    @FXML
    private Button btnSearch;
    @FXML
    private Label sessionUsername;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnManageBooks;
    @FXML
    private Button btnManageAuthors;
    @FXML
    private Button btnManageCategories;
    @FXML
    private Button btnManagePublishing;
    @FXML
    private Button btnManageAccounts;
    @FXML
    private Button btnManageBorrowing;
    @FXML
    private Button btnSignout;
    @FXML
    private AnchorPane page;

    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss\ndd/MM/yyyy");
            labelClock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void CheckId() {
//      get value of UID feild
        String id = txtID.getText();
        txtTimeOut.setDisable(false);
        txtStatus.setDisable(false);
//      Set button delete disable when UID is empty else unset disable
        if (id.isEmpty()) {
            btnSave.setDisable(true);
            btnDelete.setDisable(true);
        } else {
            btnSave.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    @FXML
    private void Validated() {
        boolean flag = false;
        String timeOut = txtTimeOut.getText();
        String NUMBER_PATTERN = "^\\d+$";

        if (!timeOut.matches(NUMBER_PATTERN)) {
            errorTimeOut.setVisible(true);

            flag = true;
        } else {
            errorTimeOut.setVisible(false);
        }

        btnSave.setDisable(flag);
    }

    @FXML
    private void BtnDeleteClick() {
        int id = Integer.parseInt(txtID.getText());
//      Call Alert box
        Alert alert = new Alert(Alert.AlertType.NONE);

        Book book = txtBook.getValue();
        book.setQuantity(book.getQuantity() + 1);
        BookEntity.Update(book);

//      delete data for database, if success show message "Deleted successfully !" else show message "Delete Fail !"
        if (BorrowEntity.Delete(id)) {
//          set titile, header, content for alert box
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Test Connection");
            alert.setHeaderText("Borrows Manager");
            alert.setContentText("Deleted Successfully!");
            alert.showAndWait();
        } else {
//          set titile, header, content for alert box
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Test Connection");
            alert.setHeaderText("Borrows Manager");
            alert.setContentText("Deleted Fail!");
            alert.showAndWait();
        }
        RefeshData();
    }

    @FXML
    private void ResetFeild() {
        txtID.clear();
        txtTimeOut.clear();
        txtAccount.setValue(null);
        txtBook.setValue(null);
        txtStatus.setValue(null);
        txtBorrowAt.setValue(null);
        txtSearch.clear();
        txtRefundAt.setValue(null);

        errorTimeOut.setVisible(false);
        errorStatus.setVisible(false);

        CheckId();
        txtTimeOut.setDisable(true);
        txtStatus.setDisable(true);
    }

    @FXML
    private void RefeshData() {
        ResetFeild();
        InitData();
    }

    @FXML
    private void Search() {
        String search = txtSearch.getText();
        ObservableList<Borrow> borrow = BorrowEntity.Search(search);
        table(borrow);
    }

    public void data_Box() {
        BorrowEntity.data_Account(txtAccount);
        BorrowEntity.data_Books(txtBook);
        BorrowEntity.data_Status(txtStatus);
    }

    public void InitData() {
        ObservableList<Borrow> borrow = BorrowEntity.GetAll();

        table(borrow);
    }

    public void table(ObservableList<Borrow> borrow) {
        table.setItems(borrow);

        colIndex.setCellValueFactory(f -> f.getValue().indexProperty().asString());
        colUID.setCellValueFactory(f -> {
            int manageId = f.getValue().getManageId();

            ManageBook mg = ManageBookEntity.GetAllBookInfoById(manageId);
            Account acc = AccountEntity.GetAccountByID(mg.getAccount().getId());

            return acc.UIDProperty();
        });
        colBook.setCellValueFactory(f -> {
            int manageId = f.getValue().getManageId();

            ManageBook mg = ManageBookEntity.GetAllBookInfoById(manageId);
            Book book = BookEntity.GetBookWithBookId(mg.getBook().getId());

            return book.nameProperty();
        });
        colBorrow.setCellValueFactory(f -> f.getValue().borrowAtProperty());
        colRefund.setCellValueFactory(f -> f.getValue().refundAtProperty());
        colTime.setCellValueFactory(f -> f.getValue().time_outProperty().asString());
        colStatus.setCellValueFactory(f -> {
            int statusId = f.getValue().getStatusId();

            StatusBorrow status = StatusBorrowEntity.GetStatusBorrowWithId(statusId);

            return status.nameProperty();
        });

        table.setRowFactory(tv -> {
            TableRow<Borrow> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = table.getSelectionModel().getSelectedIndex();

                    String[] dateBorrow = table.getItems().get(myIndex).getBorrowAt().split("-");
                    String[] dataRefund = table.getItems().get(myIndex).getRefundAt().split("-");

                    LocalDate dobBorrow = LocalDate.of(Integer.parseInt(dateBorrow[0]), Integer.parseInt(dateBorrow[1]), Integer.parseInt(dateBorrow[2]));
                    LocalDate dobRefund = LocalDate.of(Integer.parseInt(dataRefund[0]), Integer.parseInt(dataRefund[1]), Integer.parseInt(dataRefund[2]));

                    txtBorrowAt.setValue(dobBorrow);
                    txtRefundAt.setValue(dobRefund);

                    int manageId = table.getItems().get(myIndex).getManageId();

                    ManageBook mg = ManageBookEntity.GetAllBookInfoById(manageId);
                    Account acc = AccountEntity.GetAccountByID(mg.getAccount().getId());
                    Book book = BookEntity.GetBookWithBookId(mg.getBook().getId());
                    StatusBorrow sb = StatusBorrowEntity.GetStatusBorrowWithId(table.getItems().get(myIndex).getStatusId());

                    txtID.setText(String.valueOf(table.getItems().get(myIndex).getId()));
                    txtAccount.setValue(acc);
                    txtTimeOut.setText(String.valueOf(table.getItems().get(myIndex).getTime_out()));
                    txtBook.setValue(book);
                    txtStatus.setValue(sb);

                    CheckId();
                    Validated();
                }
            });
            return myRow;
        });
    }

    @FXML
    private void switchToAdminDashboard() throws IOException {
        page.setDisable(true);
        App.setRoot("AdminDashboard");
    }

    @FXML
    private void switchToManagementAuthors() throws IOException {
        page.setDisable(true);
        App.setRoot("ManagementAuthors");
    }

    @FXML
    private void switchToManagementBooks() throws IOException {
        page.setDisable(true);
        App.setRoot("ManagementBooks");
    }

    @FXML
    private void switchToManagementCategories() throws IOException {
        page.setDisable(true);
        App.setRoot("ManagementCategories");
    }

    @FXML
    private void switchToManagementPublishing() throws IOException {
        page.setDisable(true);
        App.setRoot("ManagementPublishing");
    }

    @FXML
    private void switchToManagementAccounts() throws IOException {
        page.setDisable(true);
        App.setRoot("ManagementAccounts");
    }

    @FXML
    private void switchToManagementBorrowing() throws IOException {
        page.setDisable(true);
        App.setRoot("ManagementBorrow");
    }

    @FXML
    private void SignOut() throws Exception {
        page.setDisable(true);
        App.setRoot("SignIn");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user = User.getInstace();
        String sessionUser = user.getUserName();
        try {

            if (sessionUser.equals("") || sessionUser.equals(null)) {
                SignOut();
            } else {
                sessionUsername.setText(sessionUser);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        initClock();
        RefeshData();
        this.data_Box();
    }

    @FXML
    private void BtnSaveClick() {
        int id = Integer.parseInt(txtID.getText());
        Account account = txtAccount.getValue();
        Book book = txtBook.getValue();
        int timeOut = Integer.parseInt(txtTimeOut.getText());
        String borrowAt = txtBorrowAt.getValue().toString();
        String refundAt = txtRefundAt.getValue().toString();
        StatusBorrow status = txtStatus.getValue();
        StatusBorrow refundedStatus = StatusBorrowEntity.GetStatusBorrowWithName("Refunded");
        Borrow preBorrow = BorrowEntity.GetBorrowById(id);
        StatusBorrow preBorrowStatus = StatusBorrowEntity.GetStatusBorrowWithId(preBorrow.getStatusId());

        if (!preBorrowStatus.equals(refundedStatus) && status.equals(refundedStatus)) {
            book.setQuantity(book.getQuantity() + 1);
            BookEntity.Update(book);
        }

        Borrow borrow = new Borrow();
        borrow.setId(id);
        borrow.setAccountID(account.getId());
        borrow.setBookID(book.getId());
        borrow.setTime_out(timeOut);
        borrow.setBorrowAt(borrowAt);
        borrow.setRefundAt(refundAt);
        borrow.setStatusId(status.getId());
        
        Alert alert = new Alert(Alert.AlertType.NONE);
        if (BorrowEntity.Update(borrow)) {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Test Connection");
            alert.setHeaderText("Borrows Manager");
            alert.setContentText("Updated Successfully!");
            alert.showAndWait();
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Test Connection");
            alert.setHeaderText("Borrows Manager");
            alert.setContentText("Updated Fail!");
            alert.showAndWait();
        }

        RefeshData();
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import Models.Publishing;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ManagementPublishingController implements Initializable {

    @FXML
    private Pane management_publishing;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private DatePicker txtCoyear;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtCreatedAt;
    @FXML
    private TextField txtUpdatedAt;
    @FXML
    private Label errorName;
    @FXML
    private Label errorCoyear;
    @FXML
    private Label errorAddress;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnReset;
    @FXML
    private TableView<Publishing> table;
    @FXML
    private TableColumn<Publishing, String> colId;
    @FXML
    private TableColumn<Publishing, String> colIndex;
    @FXML
    private TableColumn<Publishing, String> colName;
    @FXML
    private TableColumn<Publishing, String> colCoyear;
    @FXML
    private TableColumn<Publishing, String> colAddress;
    @FXML
    private TableColumn<Publishing, String> colCreatedAt;
    @FXML
    private TableColumn<Publishing, String> colUpdatedAt;
    @FXML
    private Button btnRefesh;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Label labelClock;
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
    private Button btnSignout;

    int myIndex;
    int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initClock();
        CheckId();
//        table();
    }

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            labelClock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    public void CheckId() {
        String id = txtId.getText();

        if (id.isEmpty()) {
            btnDelete.setVisible(false);
        } else {
            btnDelete.setVisible(true);
        }
    }

    @FXML
    private void Validated() {
        boolean flag = false;

        String name = txtName.getText();
        String address = txtAddress.getText();
        LocalDate coYear = txtCoyear.getValue();

        if (name.matches("^.{1,64}$")) {
            errorName.setVisible(true);

            flag = true;
        } else {
            errorName.setVisible(false);
        }

        if (address.isEmpty()) {
            errorAddress.setVisible(true);

            flag = true;
        } else {
            errorAddress.setVisible(false);
        }

        if (coYear == null) {
            errorCoyear.setVisible(true);

            flag = true;
        } else {
            errorCoyear.setVisible(false);
        }

        btnSave.setDisable(flag);
    }

    @FXML
    private void BtnSaveClick(ActionEvent event) {
    }

    @FXML
    private void BtnDeleteClick(ActionEvent event) {
    }

    @FXML
    private void ResetFeild() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtCoyear.setValue(null);
        txtCreatedAt.setText("");
        txtUpdatedAt.setText("");

        errorName.setVisible(false);
        errorAddress.setVisible(false);
        errorCoyear.setVisible(false);
        
        CheckId();
    }

    @FXML
    private void RefeshData() {
        ResetFeild();
//        table();
    }

    @FXML
    private void Search() {
    }

    @FXML
    private void switchToAdminDashboard() throws IOException {
        App.setRoot("AdminDashboard");
    }

    @FXML
    private void switchToManagementAuthors() throws IOException {
        App.setRoot("ManagementAuthors");
    }

    @FXML
    private void switchToManagementBooks() throws IOException {
        App.setRoot("ManagementBooks");
    }

    @FXML
    private void switchToManagementCategories() throws IOException {
        App.setRoot("ManagementCategories");
    }

    @FXML
    private void switchToManagementPublishing() throws IOException {
        App.setRoot("ManagementPublishing");
    }

    @FXML
    private void switchToManagementAccounts() throws IOException {
        App.setRoot("ManagementAccounts");
    }

    @FXML
    private void SignOut() throws Exception {
        App.setRoot("SignIn");
    }
}

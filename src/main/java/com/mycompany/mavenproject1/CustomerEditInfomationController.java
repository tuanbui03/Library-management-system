/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import Entites.AccountEntity;
import Models.Account;
import Models.Gender;
import Models.User;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CustomerEditInfomationController implements Initializable {

    User user = User.getInstace();
    @FXML
    private Label labelClock;
    @FXML
    private Label sessionUsername;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnProfile;
    @FXML
    private Button btnBorrowing;
    @FXML
    private Button btnSignout;
    @FXML
    private TextField txtUsername;
    @FXML
    private Label errorUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtFullname;
    @FXML
    private Label errorFullname;
    @FXML
    private ComboBox<Gender> boxGender;
    @FXML
    private Label errorGender;
    @FXML
    private TextField txtEmail;
    @FXML
    private Label errorEmail;
    @FXML
    private DatePicker datePickerDob;
    @FXML
    private Label errorDob;
    @FXML
    private TextField txtMobile;
    @FXML
    private Label errorMobile;
    @FXML
    private Label errorPassword;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Account acc = AccountEntity.GetAccountByUsername(user.getUserName());
        String sessionUser = acc.getFull_name();
        try {

            if (sessionUser.equals("") || sessionUser.equals(null)) {
                switchToSignIn();
            } else {
                sessionUsername.setText(sessionUser);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//      run real time and replace a time String for labelClock
        initClock();
        initDataBoxGender();
        initData();
        btnSave.setDisable(true);
    }

    @FXML
    private void switchToCustomerDashboard() throws Exception {
        App.setRoot("CustomerDashboard");
    }

    @FXML
    private void switchToCustomerInfomation() throws Exception {
        App.setRoot("CustomerInfomation");
    }

    @FXML
    private void switchToCustomerBorrowing() throws Exception {
        App.setRoot("CustomerBorrow");
    }

    @FXML
    private void switchToSignIn() throws Exception {
        App.setRoot("SignIn");
    }

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss\ndd/MM/yyyy");
            labelClock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void initData() {
        Account acc = AccountEntity.GetAccountByUsername(user.getUserName());
        txtUsername.setText(acc.getUsername());
        txtFullname.setText(acc.getFull_name());
        txtPassword.setText(acc.getPassword());

        boxGender.setValue(boxGender.getItems().get(acc.getGender() - 1));

        txtEmail.setText(acc.getEmail());

        if (acc.getDob() != null) {
            Date date = Date.valueOf(acc.getDob());
            datePickerDob.setValue(date.toLocalDate());
        }

        txtMobile.setText(acc.getMobile());
    }

    private void initDataBoxGender() {
        boxGender.setItems(FXCollections.observableArrayList(
                new Gender(1, "Male"),
                new Gender(2, "Female"),
                new Gender(3, "Other"))
        );
    }

    @FXML
    private void Validated() {
        boolean flag = false;

        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,20}$";
        String MOBILE_PATTERN = "^\\d{10}$";
        String EMAIL_PATTERN = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";

        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String fullname = txtFullname.getText();
        Gender gender = boxGender.getValue();
        String email = txtEmail.getText();
        LocalDate dob = datePickerDob.getValue();
        String mobile = txtMobile.getText();

        if (username.isEmpty()) {
            errorUsername.setVisible(true);

            flag = true;
        } else {
            errorUsername.setVisible(false);
        }

        if (!password.matches(PASSWORD_PATTERN) || password.isEmpty()) {
            errorPassword.setVisible(true);

            flag = true;
        } else {
            errorPassword.setVisible(false);
        }

        if (fullname.isEmpty()) {
            errorFullname.setVisible(true);

            flag = true;
        } else {
            errorFullname.setVisible(false);
        }

        if (gender.equals(null)) {
            errorGender.setVisible(true);

            flag = true;
        } else {
            errorGender.setVisible(false);
        }

        if (email.matches(EMAIL_PATTERN)) {
            errorEmail.setVisible(false);
        } else {
            errorEmail.setVisible(true);

            flag = true;
        }

        if (dob == null) {
            errorDob.setVisible(true);

            flag = true;
        } else {
            errorDob.setVisible(false);
        }

        if (!mobile.matches(MOBILE_PATTERN)) {
            errorMobile.setVisible(true);

            flag = true;
        } else {
            errorMobile.setVisible(false);
        }

        btnSave.setDisable(flag);
    }

    @FXML
    private void BtnSaveClick() {
        Account acc = AccountEntity.GetAccountByUsername(user.getUserName());

        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String fullname = txtFullname.getText();
        int gender = boxGender.getValue().getId();
        String email = txtEmail.getText();
        String dob = datePickerDob.getValue().toString();
        String mobile = txtMobile.getText();

        acc.setUsername(username);
        acc.setPassword(password);
        acc.setFull_name(fullname);
        acc.setGender(gender);
        acc.setEmail(email);
        acc.setDob(dob);
        acc.setMobile(mobile);

        Alert alert = new Alert(Alert.AlertType.NONE);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmPassword.fxml"));
        try {
            alert.setDialogPane(loader.load());
            alert.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
            alert.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ANY, c -> {
                ConfirmPasswordController confirmPage = loader.getController();
                if (confirmPage.Confirm()) {
                    try {
                        if (AccountEntity.Update(acc)) {
                            user.setUserSession(acc.getUsername());
                            switchToCustomerInfomation();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        alert.showAndWait();
    }
}

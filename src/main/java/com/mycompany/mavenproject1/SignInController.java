/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import Entites.AccountEntity;
import Models.Account;
import Models.User;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class SignInController implements Initializable {

    User user = User.getInstace();

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField passPassword;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnSignUp;
    @FXML
    private Button hide;
    @FXML
    private Button show;
    @FXML
    private Label labelClock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initClock();
        Validated();
    }

    @FXML
    private void Login() throws IOException {
        String inpUsername = txtUsername.getText();
        String showPassword = txtPassword.getText();
        String hidePassword = passPassword.getText();

        Account acc = AccountEntity.GetAccountByUsername(inpUsername);
        if (acc != null) {
            String password = acc.getPassword();
            String role = acc.getRoleName();
            if (password.equals(showPassword) || hidePassword.equals(password)) {

                user.setUserSession(inpUsername);

                if (role.equals("Admin")) {
                    System.out.println("is Admin");
                    switchToAdminDashboard();
                } else if (role.equals("Reader")) {
                    System.out.println("is Reader");
                    switchToSignUp();
                }
            } else {

            }
        } else {

        }

    }

    @FXML
    private void switchToAdminDashboard() throws IOException {
        App.setRoot("AdminDashboard");
    }

    @FXML
    private void switchToCustomerDashboard() throws IOException {
        App.setRoot("CustomerDashboard");
    }

    @FXML
    private void switchToSignUp() throws IOException {
        App.setRoot("SignUp");
    }

    @FXML
    private void HidePassword() {
        String showPassword = txtPassword.getText();
        passPassword.setText(showPassword);
        txtPassword.setVisible(false);
        passPassword.setVisible(true);
        hide.setVisible(false);
        show.setVisible(true);
    }

    @FXML
    private void ShowPassword() {
        String hidePassword = passPassword.getText();
        txtPassword.setText(hidePassword);
        passPassword.setVisible(false);
        txtPassword.setVisible(true);
        show.setVisible(false);
        hide.setVisible(true);
    }

    @FXML
    private void Validated() {
        boolean flag = false;
        String username = txtUsername.getText();
        String hidePassword = passPassword.getText();
        String showPassword = txtPassword.getText();

        if (username.isEmpty() || (hidePassword.isEmpty() && showPassword.isEmpty())) {
            flag = true;
        } else {
            flag = false;
        }

        btnSubmit.setDisable(flag);
    }

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            labelClock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}

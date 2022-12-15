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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleRole;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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
    private Button hide;
    @FXML
    private Button show;
    @FXML
    private Label labelClock;
    @FXML
    private Label errorPassword;
    @FXML
    private Button btnSignUp;
    @FXML
    private AnchorPane page;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initClock();
        Validated();
        errorPassword.setVisible(false);
        hide.setVisible(false);
    }

    @FXML
    private void Login() throws IOException {
        String inpUsername = txtUsername.getText();
        String inpPassword = "";
        String showPassword = txtPassword.getText();
        String hidePassword = passPassword.getText();

        if (hidePassword.length() > showPassword.length()) {
            inpPassword = hidePassword;
        } else {
            inpPassword = showPassword;
        }

//      Call Alert box
        Alert alert = new Alert(Alert.AlertType.NONE);
        Account acc = AccountEntity.GetAccountByUsername(inpUsername);
        if (acc != null) {
            String password = acc.getPassword();
            String role = acc.getRoleName();

            if (password.equals(inpPassword)) {

                user.setUserSession(inpUsername);

                if (role.equals("Admin")) {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
                    try {
                        alert.setDialogPane(loader.load());
                        MessageController mc = loader.getController();
                        mc.setMessage("Login Successfully!\nWelcome to Westmaster " + acc.getUsername());
                    } catch (Exception e) {
                    }
                    alert.showAndWait();

                    switchToAdminDashboard();
                } else if (role.equals("Reader")) {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
                    try {
                        alert.setDialogPane(loader.load());
                        MessageController mc = loader.getController();
                        mc.setMessage("Login Successfully!\nWelcome to Westmaster " + acc.getFull_name());
                    } catch (Exception e) {
                    }
                    alert.showAndWait();

                    switchToCustomerDashboard();
                }
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
                try {
                    alert.setDialogPane(loader.load());
                    MessageController mc = loader.getController();
                    mc.setMessage("Wrong Username or Password!");
                } catch (Exception e) {
                }

                alert.showAndWait();
            }
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
            try {
                alert.setDialogPane(loader.load());
                MessageController mc = loader.getController();
                mc.setMessage("Wrong Username or Password!");
            } catch (Exception e) {
            }

            alert.showAndWait();
        }

    }

    private void switchToAdminDashboard() throws IOException {
        page.setDisable(true);
        App.setRoot("AdminDashboard");
    }

    private void switchToCustomerDashboard() throws IOException {
        page.setDisable(true);
        App.setRoot("CustomerDashboard");
    }

    @FXML
    private void switchToSignUp() throws IOException {
        page.setDisable(true);
        App.setRoot("SignUp");
    }

    @FXML
    private void HidePassword() {
        String showPassword = txtPassword.getText();
        passPassword.setText(showPassword);
        txtPassword.setVisible(false);
        passPassword.setVisible(true);
        show.setVisible(true);
        hide.setVisible(false);
    }

    @FXML
    private void ShowPassword() {
        String hidePassword = passPassword.getText();
        txtPassword.setText(hidePassword);
        passPassword.setVisible(false);
        txtPassword.setVisible(true);
        hide.setVisible(true);
        show.setVisible(false);
    }

    @FXML
    private void Validated() {
        boolean flag = false;
        String username = txtUsername.getText();
        String hidePassword = passPassword.getText();
        String showPassword = txtPassword.getText();

        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,20}$";

        if (username.isEmpty()) {
            flag = true;
        } else {
        }

        if ((hidePassword.isEmpty() && showPassword.isEmpty())) {
            errorPassword.setVisible(true);

            flag = true;
        } else {
            if (hidePassword.matches(PASSWORD_PATTERN) && !hidePassword.isEmpty() || showPassword.matches(PASSWORD_PATTERN) && !showPassword.isEmpty()) {
                errorPassword.setVisible(false);
            } else {
                errorPassword.setVisible(true);

                flag = true;
            }
        }

        btnSubmit.setDisable(flag);
    }

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss\ndd/MM/yyyy");
            labelClock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

}

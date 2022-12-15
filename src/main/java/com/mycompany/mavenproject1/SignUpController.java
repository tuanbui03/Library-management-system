/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import Entites.AccountEntity;
import Entites.RoleEntity;
import Models.Account;
import Models.Gender;
import Models.Role;
import Models.User;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class SignUpController implements Initializable {

    User user = User.getInstace();

    @FXML
    private Label labelClock;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private PasswordField passPassword;
    @FXML
    private Button hidePassword;
    @FXML
    private Button showPassword;
    @FXML
    private TextField txtRePassword;
    @FXML
    private PasswordField passRePassword;
    @FXML
    private Button hideRePassword;
    @FXML
    private Button showRePassword;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnSignIn;
    @FXML
    private ComboBox<Gender> boxGender;
    @FXML
    private TextField txtMobile;
    @FXML
    private Label errorUsername;
    @FXML
    private Label errorPassword;
    @FXML
    private Label errorRePassword;
    @FXML
    private Label errorGender;
    @FXML
    private Label errorMobile;
    @FXML
    private TextField txtFullname;
    @FXML
    private Label errorFullname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initClock();
        btnSubmit.setDisable(true);
        boxGender.setItems(FXCollections.observableArrayList(
                new Gender(1, "Male"),
                new Gender(2, "Female"),
                new Gender(3, "Other"))
        );
    }

    @FXML
    private void HidePassword() {
        String showPass = txtPassword.getText();
        passPassword.setText(showPass);
        txtPassword.setVisible(false);
        passPassword.setVisible(true);
        hidePassword.setVisible(false);
        showPassword.setVisible(true);
    }

    @FXML
    private void ShowPassword() {
        String hidePass = passPassword.getText();
        txtPassword.setText(hidePass);
        passPassword.setVisible(false);
        txtPassword.setVisible(true);
        showPassword.setVisible(false);
        hidePassword.setVisible(true);
    }

    @FXML
    private void HideRePassword() {
        String showPass = txtRePassword.getText();
        passRePassword.setText(showPass);
        txtRePassword.setVisible(false);
        passRePassword.setVisible(true);
        hideRePassword.setVisible(false);
        showRePassword.setVisible(true);
    }

    @FXML
    private void ShowRePassword() {
        String hidePass = passRePassword.getText();
        txtRePassword.setText(hidePass);
        passRePassword.setVisible(false);
        txtRePassword.setVisible(true);
        showRePassword.setVisible(false);
        hideRePassword.setVisible(true);
    }

    @FXML
    private void Validated() {
        boolean flag = false;
        boolean validPass = false;

        String USERNAME_PATTERN = "^(?=\\S+$).{1,64}$";
        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,20}$";
        String MOBILE_PATTERN = "^\\d{10}$";
        String EMAIL_PATTERN = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";

        String username = txtUsername.getText();
        String hidePassword = passPassword.getText();
        String showPassword = txtPassword.getText();
        String hideRePassword = passRePassword.getText();
        String showRePassword = txtRePassword.getText();
        String fullname = txtFullname.getText();
        Gender gender = boxGender.getValue();
        String mobile = txtMobile.getText();

        if (!username.matches(USERNAME_PATTERN)) {
            errorUsername.setVisible(true);

            flag = true;
        } else {
            errorUsername.setVisible(false);
        }

        if ((hidePassword.isEmpty() && showPassword.isEmpty())) {
            errorPassword.setVisible(true);

            flag = true;
        } else {
            if (hidePassword.matches(PASSWORD_PATTERN) && !hidePassword.isEmpty() || showPassword.matches(PASSWORD_PATTERN) && !showPassword.isEmpty()) {
                validPass = true;
                errorPassword.setVisible(false);
            } else {
                errorPassword.setVisible(true);
                validPass = false;
                flag = true;
            }
        }

        if ((hideRePassword.isEmpty() && showRePassword.isEmpty())) {
            errorRePassword.setVisible(true);

            flag = true;
        } else {
            if (hidePassword.length() > showPassword.length()) {
                showPassword = hidePassword;
            } else {
                hidePassword = showPassword;
            }

            if (validPass && (showRePassword.equals(hidePassword) || hideRePassword.equals(hidePassword))) {
                errorRePassword.setVisible(false);

            } else {
                errorRePassword.setVisible(true);

                flag = true;
            }
        }

        if (fullname.isEmpty()) {
            errorFullname.setVisible(true);

            flag = true;
        } else {
            errorFullname.setVisible(false);
        }

        if (gender == null) {
            errorGender.setVisible(true);

            flag = true;
        } else {
            errorGender.setVisible(false);
        }

        if (!mobile.matches(MOBILE_PATTERN)) {
            errorMobile.setVisible(true);

            flag = true;
        } else {
            errorMobile.setVisible(false);
        }

        btnSubmit.setDisable(flag);
    }

    @FXML
    private void SignUp() {
        Role role = RoleEntity.GetOneByName("Reader");
        ObservableList<Account> accounts = AccountEntity.GetAccountByRole(role.getId());

        FormatFullName();
        
        String UID = role.getName() + accounts.size();
        String username = txtUsername.getText();
        String hidePass = passPassword.getText();
        String showPass = txtPassword.getText();
        String fullname = txtFullname.getText();
        Gender gender = boxGender.getValue();
        String mobile = txtMobile.getText();
        String password = "";
        if (hidePass != null) {
            password = hidePass;
        } else {
            password = showPass;
        }

        Account acc = new Account();
        acc.setUID(UID);
        acc.setAvatar(UID + ".png");
        acc.setUsername(username);
        acc.setPassword(password);
        acc.setFull_name(fullname);
        acc.setStatus(1);
        acc.setRoleId(role.getId());
        acc.setGender(gender.getId());
        acc.setMobile(mobile);

//      Call Alert box
        Alert alert = new Alert(Alert.AlertType.NONE);
        if (AccountEntity.GetAccountByUsername(username) == null) {
            if (AccountEntity.SignUp(acc)) {
                user.setUserSession(username);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
                try {
                    alert.setDialogPane(loader.load());
                    MessageController mc = loader.getController();
                    mc.setMessage("Signup Successfully! And Login again!");
                } catch (Exception e) {
                }

                alert.showAndWait();

                try {
                    switchToSignIn();
                } catch (Exception e) {
                }
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
                try {
                    alert.setDialogPane(loader.load());
                    MessageController mc = loader.getController();
                    mc.setMessage("Signup Fail!");
                } catch (Exception e) {
                }

                alert.showAndWait();
            }
        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
            try {
                alert.setDialogPane(loader.load());
                MessageController mc = loader.getController();
                mc.setMessage("This Username is exists! Let's Signup again!");
            } catch (Exception e) {
            }

            alert.showAndWait();
        }

    }
    
    @FXML
    private void FormatFullName() {
        String inpFullname = txtFullname.getText();
        String newFullname = "";
        inpFullname = inpFullname.trim().replaceAll("//s//s+", " ");
        String[] array = inpFullname.split(" ");

        for (String name : array) {
            newFullname += name.toUpperCase().charAt(0);
            newFullname += name.substring(1);
            newFullname += " ";
        }

        newFullname = newFullname.trim();

        txtFullname.setText(newFullname);
    }

    @FXML
    private void switchToSignIn() throws IOException {
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
}

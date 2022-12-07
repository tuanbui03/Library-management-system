/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import Entites.AccountEntity;
import Models.Account;
import Models.User;
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
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CustomerInfomationController implements Initializable {

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
    private Label labelUsername;
    @FXML
    private Label labelFullname;
    @FXML
    private Label labelGender;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelBirthday;
    @FXML
    private Label labelMobile;
    @FXML
    private Button btnEdit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Account acc = AccountEntity.GetAccountByUsername(user.getUserName());
        labelUsername.setText(acc.getUsername());
        labelFullname.setText(acc.getFull_name());

        if (acc.getGender() == 1) {
            labelGender.setText("Male");
        } else if (acc.getGender() == 2) {
            labelGender.setText("Female");
        } else if (acc.getGender() == 3) {
            labelGender.setText("Other");
        }

        if (acc.getEmail().isEmpty()) {
            labelEmail.setText("No Content");
        } else {
            labelEmail.setText(acc.getEmail());
        }

        if (acc.getDob() != null) {
            System.out.println(acc.getDob());
            Date date = Date.valueOf(acc.getDob());
            labelBirthday.setText(date.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } else {
            labelBirthday.setText("dd/mm/yyyy");
        }

        if (acc.getMobile().equals(null) || acc.getMobile().isEmpty()) {
            labelMobile.setText("No Content");
        } else {
            labelMobile.setText(acc.getMobile());
        }

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

    @FXML
    private void swtichToCustomerEditInfomation() throws Exception {
        App.setRoot("CustomerEditInfomation");
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

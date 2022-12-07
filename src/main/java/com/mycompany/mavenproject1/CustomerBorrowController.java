/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import Entites.AccountEntity;
import Models.Account;
import Models.Borrow;
import Models.User;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CustomerBorrowController implements Initializable {

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
    private TextField id;
    @FXML
    private ComboBox<?> boxPublishing;
    @FXML
    private ComboBox<?> boxAuthor;
    @FXML
    private ComboBox<?> boxCategory;
    @FXML
    private ComboBox<?> boxBook;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> colIndex;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colBook;
    @FXML
    private TableColumn<?, ?> colPublishing;
    @FXML
    private TableColumn<?, ?> colAuthor;
    @FXML
    private TableColumn<?, ?> colCategory;
    @FXML
    private TableColumn<?, ?> colBorrowAt;
    @FXML
    private TableColumn<?, ?> colRefundedAt;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnBooking;

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
    private void Search() {
    }
    
    @FXML
    private void InitData() {
    }
    
    @FXML
    private void table(Borrow b) {
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

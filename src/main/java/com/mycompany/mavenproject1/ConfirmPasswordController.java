/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import Entites.AccountEntity;
import Models.Account;
import Models.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ConfirmPasswordController implements Initializable {

    private PasswordField txtPassword;
    @FXML
    private Label message;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public boolean Confirm() {
        User user = User.getInstace();
        String username = user.getUserName();
        String password = txtPassword.getText();

        Alert alert = new Alert(Alert.AlertType.NONE);
        Account acc = AccountEntity.GetAccountByUsername(username);

        if (password.equals(acc.getPassword())) {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            System.out.println("is Admin");
            alert.setHeaderText("Westmaster");
            alert.setContentText("Confirm Successfully!");
            alert.showAndWait();

            return true;
        }

        alert.setAlertType(Alert.AlertType.ERROR);
        System.out.println("is Admin");
        alert.setHeaderText("Westmaster");
        alert.setContentText("Wrong Password! Let's confirm old password again!");
        alert.showAndWait();

        return false;
    }
}

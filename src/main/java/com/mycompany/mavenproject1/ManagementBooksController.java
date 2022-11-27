/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ManagementBooksController implements Initializable {

    @FXML
    private Label labelClock;
    @FXML
    private Circle avatar;
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
    @FXML
    private Pane pnlOverview;
    @FXML
    private TextField Image;
    @FXML
    private TextField txtName;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtId;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnReset;
    @FXML
    private TextField txtUpdatedAt;
    @FXML
    private TextField txtCreatedAt;
    @FXML
    private TextField txtCoyear;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtQuantity;
    @FXML
    private TextField txtDescription;
    @FXML
    private TableColumn<?, ?> colIndex;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colSignName1;
    @FXML
    private TableColumn<?, ?> colSignName;
    @FXML
    private TableColumn<?, ?> colBorn3;
    @FXML
    private TableColumn<?, ?> colBorn;
    @FXML
    private TableColumn<?, ?> colBorn4;
    @FXML
    private TableColumn<?, ?> colBorn1;
    @FXML
    private TableColumn<?, ?> colBorn2;
    @FXML
    private Button btnRefesh;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import Models.Category;
import Entites.CategoryEntity;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ManagementCategoriesController implements Initializable {

    @FXML
    private Label labelClock;
    @FXML
    private Circle avatar;
    @FXML
    private Label labelUsername;
    @FXML
    private Label errorName;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnBorrowing;
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
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> colIndex;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colCreatedAt;
    @FXML
    private TableColumn<?, ?> colUpdatedAt;
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
        errorName.setVisible(false);
        btnSave.setDisable(true);
    }

    public void BtnSaveClick() {
//      Call entity and model of category
        CategoryEntity ce = new CategoryEntity();
        Category category = new Category();
//      Uppercase first char of each word
        FomartInputName();
//      Call fx:id at fxml(id feild and name feild)
        String inpId = txtId.getText();
        String inpName = txtName.getText();

//        inpId is empty we create new categories else we update this

    }

    public void BtnDeleteClick() {
//      Call entity and model of category
        CategoryEntity ce = new CategoryEntity();
//      Call id at id feild
        int id = Integer.parseInt(txtId.getText());

//      delete data for database, if success show message "Delete this Category successfully !" else show message "Delete this Category faild !"

    }

    public void CheckInputName() {
//      if txtName empty show error else hide error and undisable button save
        if (txtName.getText().isEmpty()) {
            errorName.setVisible(true);
            btnSave.setDisable(true);
        } else {
            errorName.setVisible(false);
            btnSave.setDisable(false);
        }
        
        if(txtName.getText().length() > 64){
            btnSave.setDisable(true);
            Locale.setDefault(new Locale("en", "English"));

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("STOP");
            alert.setHeaderText("Name must be less than 64 character");
            alert.showAndWait();
        }
    }

    public void FomartInputName() {
        String inpName = txtName.getText();
        String newInpName = "";
        inpName = inpName.trim().replaceAll("//s//s+", " ");
        String[] array = inpName.split(" ");

        for (String name : array) {
            newInpName += name.toUpperCase().charAt(0);
            newInpName += name.substring(1);
            newInpName += " ";
        }

        newInpName = newInpName.trim();

        txtName.setText(newInpName);
    }
    
    public void RefeshData(){
        txtId.setText("");
        txtName.setText("");
        txtCreatedAt.setText("");
        txtUpdatedAt.setText("");
        
        
    }
    
    public void ResetFeild(){
        txtId.setText("");
        txtName.setText("");
        txtCreatedAt.setText("");
        txtUpdatedAt.setText("");
    }
}

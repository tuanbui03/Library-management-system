/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import Models.*;
import Entites.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ManagementCategoriesController implements Initializable {

    private Preferences prefs = Preferences.userRoot().node(this.getClass().getName());

    @FXML
    private Label labelClock;
    @FXML
    private Circle avatar;
    @FXML
    private Label sessionUsername;
    @FXML
    private Label errorName;
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
    private Button btnManageBorrowing;
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
    private TableView<Category> table;
    @FXML
    private TableColumn<Category, Integer> colIndex;
    @FXML
    private TableColumn<Category, String> colId;
    @FXML
    private TableColumn<Category, String> colName;
    @FXML
    private TableColumn<Category, String> colCreatedAt;
    @FXML
    private TableColumn<Category, String> colUpdatedAt;
    @FXML
    private Button btnRefesh;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;

    int myIndex;
    int id;

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
    private void switchToManagementBorrowing() throws IOException {
        App.setRoot("ManagementBorrow");
    }

    @FXML
    private void SignOut() throws Exception {
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

    public void table() {
        ObservableList<Category> categories = CategoryEntity.GetAll();

        table.setItems(categories);
        colIndex.setCellValueFactory(f -> f.getValue().indexProperty().asObject());
        colId.setCellValueFactory(f -> f.getValue().idProperty().asString());
        colName.setCellValueFactory(f -> f.getValue().nameProperty());
        colCreatedAt.setCellValueFactory(f -> f.getValue().createdAtProperty());
        colUpdatedAt.setCellValueFactory(f -> f.getValue().updatedAtProperty());

        table.setRowFactory(tv -> {
            TableRow<Category> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = table.getSelectionModel().getSelectedIndex();
                    txtId.setText(String.valueOf(table.getItems().get(myIndex).getId()));
                    txtName.setText(table.getItems().get(myIndex).getName());
                    txtCreatedAt.setText(table.getItems().get(myIndex).getCreatedAt());
                    txtUpdatedAt.setText(table.getItems().get(myIndex).getUpdatedAt());

                    CheckId();
                }
            });
            return myRow;
        });
    }

    public void BtnSearchClick() {
//      get value at search text feild
        String search = txtSearch.getText();
//      call ObservableList with name is categories and value from function search in CategoryEntity
        ObservableList<Category> categories = CategoryEntity.Search(search);

//      set item with type Category with list type is ObservableList and ObservableList's name is categories
        table.setItems(categories);
//      set value for col
        colIndex.setCellValueFactory(f -> f.getValue().indexProperty().asObject());
        colId.setCellValueFactory(f -> f.getValue().idProperty().asString());
        colName.setCellValueFactory(f -> f.getValue().nameProperty());
        colCreatedAt.setCellValueFactory(f -> f.getValue().createdAtProperty());
        colUpdatedAt.setCellValueFactory(f -> f.getValue().updatedAtProperty());

//      add event listener clicked each row
        table.setRowFactory(tv -> {
            TableRow<Category> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = table.getSelectionModel().getSelectedIndex();
                    txtId.setText(String.valueOf(table.getItems().get(myIndex).getId()));
                    txtName.setText(table.getItems().get(myIndex).getName());
                    txtCreatedAt.setText(table.getItems().get(myIndex).getCreatedAt());
                    txtUpdatedAt.setText(table.getItems().get(myIndex).getUpdatedAt());
                }
            });
            return myRow;
        });
    }

    public void BtnSaveClick() {

//      Uppercase first char of each word
        FomartInputName();

//      Call fx:id at fxml(id feild and name feild)
        String inpId = txtId.getText();
        String inpName = txtName.getText();

//      set entity
        Category category = new Category();
        category.setName(inpName);

//      Call Alert box
        Alert alert = new Alert(Alert.AlertType.NONE);
//      inpId is empty we create new categories else we update this
        if (inpId.isEmpty()) {

//          if category's name doesn't exists, add this category else show message "This Category exists!"
            if (CategoryEntity.GetCategoryByName(inpName) == null) {

//              if add success, show a box with message "Added Successfully!" else show message "Added Fail!"
                if (CategoryEntity.Add(category)) {
//              set titile, header, content for alert box
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Test Connection");
                    alert.setHeaderText("Categories Manager");
                    alert.setContentText("Added Successfully!");
                    alert.showAndWait();
                } else {
//              set titile, header, content for alert box
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Test Connection");
                    alert.setHeaderText("Categories Manager");
                    alert.setContentText("Added Fail!");
                    alert.showAndWait();
                }
            } else {
//              set titile, header, content for alert box
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Test Connection");
                alert.setHeaderText("Categories Manager");
                alert.setContentText("This Category exists!");
                alert.showAndWait();
            }

        } else {
//          set id for object category
            category.setId(Integer.parseInt(inpId));

//          if Update success, show a box with message "Updated Successfully!" else show message "Updated Fail!"
            if (CategoryEntity.Update(category)) {

//              set titile, header, content for alert box
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Connection");
                alert.setHeaderText("Categories Manager");
                alert.setContentText("Updated Successfully!");
                alert.showAndWait();

            } else {

//              set titile, header, content for alert box
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Test Connection");
                alert.setHeaderText("Categories Manager");
                alert.setContentText("Updated Fail!");
                alert.showAndWait();

            }

        }

        RefeshData();
    }

    public void BtnDeleteClick() {

        int id = Integer.parseInt(txtId.getText());

        Alert alert = new Alert(Alert.AlertType.NONE);
        ObservableList<Book> existsBook = BookEntity.GetBookByCategoryId(id);
        ObservableList<Book> noBook = FXCollections.observableArrayList();

        if (existsBook.equals(noBook) || existsBook.equals(null)) {
            if (CategoryEntity.Delete(id)) {

//          set titile, header, content for alert box
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Connection");
                alert.setHeaderText("Categories Manager");
                alert.setContentText("Deleted Successfully!");
                alert.showAndWait();

            } else {

//          set titile, header, content for alert box
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Test Connection");
                alert.setHeaderText("Categories Manager");
                alert.setContentText("Deleted Fail!");
                alert.showAndWait();

            }
        } else {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Test Connection");
            alert.setHeaderText("Categories Manager");
            alert.setContentText("This category have books! Delete books of category");
            alert.showAndWait();
        }

        RefeshData();
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
//      if txtName have length() > 64 show alert box and cannot click at button Save
        if (txtName.getText().length() > 64) {
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

    public void RefeshData() {
        ResetFeild();
        table();
    }

    public void ResetFeild() {
        txtId.setText("");
        txtName.setText("");
        txtCreatedAt.setText("");
        txtUpdatedAt.setText("");
        txtSearch.setText("");
        errorName.setVisible(false);

        CheckId();
    }

    public void CheckId() {
        String id = txtId.getText();

        if (id.isEmpty()) {
            btnDelete.setDisable(true);
        } else {
            btnDelete.setDisable(false);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        User user = User.getInstace();
        String sessionUser = user.getUserName();
        try {

            if (sessionUser.equals("") || sessionUser.equals(null)) {
                SignOut();
            } else {
                sessionUsername.setText(sessionUser);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        initClock();
        errorName.setVisible(false);
        btnSave.setDisable(true);
        CheckId();
        table();
    }
}

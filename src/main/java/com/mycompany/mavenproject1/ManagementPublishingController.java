/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import Entites.PublishingEntity;
import Models.Publishing;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ManagementPublishingController implements Initializable {

    @FXML
    private Pane management_publishing;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private DatePicker txtCoyear;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtCreatedAt;
    @FXML
    private TextField txtUpdatedAt;
    @FXML
    private Label errorName;
    @FXML
    private Label errorCoyear;
    @FXML
    private Label errorAddress;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnReset;
    @FXML
    private TableView<Publishing> table;
    @FXML
    private TableColumn<Publishing, String> colId;
    @FXML
    private TableColumn<Publishing, String> colIndex;
    @FXML
    private TableColumn<Publishing, String> colName;
    @FXML
    private TableColumn<Publishing, String> colCoyear;
    @FXML
    private TableColumn<Publishing, String> colAddress;
    @FXML
    private TableColumn<Publishing, String> colCreatedAt;
    @FXML
    private TableColumn<Publishing, String> colUpdatedAt;
    @FXML
    private Button btnRefesh;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Label labelClock;
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

    int myIndex;
    int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//      run real time and replace a time String for labelClock
        initClock();
//      get all data from database 
        table();
//      set button disable
        btnSave.setDisable(true);
        CheckId();
    }

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            labelClock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    public void CheckId() {
//      get value of UID feild
        String id = txtId.getText();
        
//      Set button delete disable when UID is empty else unset disable
        if (id.isEmpty()) {
            btnDelete.setDisable(true);
        } else {
            btnDelete.setDisable(false);
        }
    }

    @FXML
    private void Validated() {
        boolean flag = false;

        String name = txtName.getText();
        String address = txtAddress.getText();
        LocalDate coYear = txtCoyear.getValue();

        if (name.length() > 64 || name.isEmpty()) {
            errorName.setVisible(true);

            flag = true;
        } else {
            errorName.setVisible(false);
        }

        if (address.isEmpty()) {
            errorAddress.setVisible(true);

            flag = true;
        } else {
            errorAddress.setVisible(false);
        }

        if (coYear == null) {
            errorCoyear.setVisible(true);

            flag = true;
        } else {
            errorCoyear.setVisible(false);
        }

        btnSave.setDisable(flag);
    }

    @FXML
    private void BtnSaveClick() {
        Validated();

        Publishing pub = new Publishing();
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String coyear = txtCoyear.getValue().toString();

        pub.setName(name);
        pub.setAddress(address);
        pub.setCoyear(coyear);

//      Call Alert box
        Alert alert = new Alert(Alert.AlertType.NONE);

//      inpId is empty we create new categories else we update this
        if (id.isEmpty()) {

//          if publishing's name doesn't exists, add this category else show message "This Publishing exists!"
            if (PublishingEntity.GetPublishingWithName(name) == null) {

//              if add success, show a box with message "Added Successfully!" else show message "Added Fail!"
                if (PublishingEntity.Add(pub)) {
//              set titile, header, content for alert box
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Test Connection");
                    alert.setHeaderText("Publishings Manager");
                    alert.setContentText("Added Successfully!");
                    alert.showAndWait();
                } else {
//              set titile, header, content for alert box
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Test Connection");
                    alert.setHeaderText("Publishings Manager");
                    alert.setContentText("Added Fail!");
                    alert.showAndWait();
                }
            } else {
//              set titile, header, content for alert box
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Test Connection");
                alert.setHeaderText("Publishings Manager");
                alert.setContentText("This Publishing exists!");
                alert.showAndWait();
            }

        } else {
//          set id for object publishing
            pub.setId(Integer.parseInt(id));

//          if Update success, show a box with message "Updated Successfully!" else show message "Updated Fail!"
            if (PublishingEntity.Update(pub)) {

//              set titile, header, content for alert box
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Connection");
                alert.setHeaderText("Publishings Manager");
                alert.setContentText("Updated Successfully!");
                alert.showAndWait();

            } else {

//              set titile, header, content for alert box
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Test Connection");
                alert.setHeaderText("Publishings Manager");
                alert.setContentText("Updated Fail!");
                alert.showAndWait();

            }

        }

        RefeshData();
    }

    @FXML
    private void BtnDeleteClick() {
        int id = Integer.parseInt(txtId.getText());

//      Call Alert box
        Alert alert = new Alert(Alert.AlertType.NONE);

//      delete data for database, if success show message "Deleted successfully !" else show message "Delete Fail !"
        if (PublishingEntity.Delete(id)) {
//          set titile, header, content for alert box
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Test Connection");
            alert.setHeaderText("Accounts Manager");
            alert.setContentText("Deleted Successfully!");
            alert.showAndWait();
        } else {
//          set titile, header, content for alert box
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Test Connection");
            alert.setHeaderText("Accounts Manager");
            alert.setContentText("Deleted Fail!");
            alert.showAndWait();
        }

        RefeshData();
    }

    @FXML
    private void ResetFeild() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtCoyear.setValue(null);
        txtCreatedAt.setText("");
        txtUpdatedAt.setText("");

        errorName.setVisible(false);
        errorAddress.setVisible(false);
        errorCoyear.setVisible(false);

        CheckId();
        btnSave.setDisable(true);
    }

    @FXML
    private void RefeshData() {
        ResetFeild();
        table();
    }

    @FXML
    public void table() {
        ObservableList<Publishing> publishing = PublishingEntity.GetAll();

        table.setItems(publishing);
        colIndex.setCellValueFactory(f -> f.getValue().indexProperty().asString());
        colId.setCellValueFactory(f -> f.getValue().idProperty().asString());
        colName.setCellValueFactory(f -> f.getValue().nameProperty());
        colAddress.setCellValueFactory(f -> f.getValue().addressProperty());
        colCoyear.setCellValueFactory(f -> f.getValue().co_yearProperty());
        colCreatedAt.setCellValueFactory(f -> f.getValue().createdAtProperty());
        colUpdatedAt.setCellValueFactory(f -> f.getValue().updatedAtProperty());

        table.setRowFactory(tv -> {
            TableRow<Publishing> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = table.getSelectionModel().getSelectedIndex();

                    String[] date = table.getItems().get(myIndex).getCoyear().split("-");
                    System.out.println(table.getItems().get(myIndex).getCoyear());

                    LocalDate dob = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
                    txtCoyear.setValue(dob);

                    txtId.setText(String.valueOf(table.getItems().get(myIndex).getId()));
                    txtName.setText(table.getItems().get(myIndex).getName());
                    txtAddress.setText(table.getItems().get(myIndex).getAddress());
                    txtCreatedAt.setText(table.getItems().get(myIndex).getCreatedAt());
                    txtUpdatedAt.setText(table.getItems().get(myIndex).getUpdatedAt());

                    CheckId();
                    Validated();
                }
            });
            return myRow;
        });
    }

    @FXML
    private void Search() {
        String search = txtSearch.getText();
        ObservableList<Publishing> publishing = PublishingEntity.Search(search);

        table.setItems(publishing);
        colIndex.setCellValueFactory(f -> f.getValue().indexProperty().asString());
        colId.setCellValueFactory(f -> f.getValue().idProperty().asString());
        colName.setCellValueFactory(f -> f.getValue().nameProperty());
        colAddress.setCellValueFactory(f -> f.getValue().addressProperty());
        colCoyear.setCellValueFactory(f -> f.getValue().co_yearProperty());
        colCreatedAt.setCellValueFactory(f -> f.getValue().createdAtProperty());
        colUpdatedAt.setCellValueFactory(f -> f.getValue().updatedAtProperty());

        table.setRowFactory(tv -> {
            TableRow<Publishing> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = table.getSelectionModel().getSelectedIndex();

                    String[] date = table.getItems().get(myIndex).getCoyear().split("-");

                    LocalDate dob = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
                    txtCoyear.setValue(dob);

                    txtId.setText(String.valueOf(table.getItems().get(myIndex).getId()));
                    txtName.setText(table.getItems().get(myIndex).getName());
                    txtAddress.setText(table.getItems().get(myIndex).getAddress());
                    txtCoyear.setValue(LocalDate.of(id, Month.MARCH, myIndex));
                    txtCreatedAt.setText(table.getItems().get(myIndex).getCreatedAt());
                    txtUpdatedAt.setText(table.getItems().get(myIndex).getUpdatedAt());

                    CheckId();
                    Validated();
                }
            });
            return myRow;
        });
    }

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
    private void SignOut() throws Exception {
        App.setRoot("SignIn");
    }
}

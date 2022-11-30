/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import Models.*;
import Entites.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ManagementBooksController implements Initializable {

    private Preferences prefs = Preferences.userRoot().node(this.getClass().getName());

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
    @FXML
    private Pane pnlOverview;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private Label errorName;
    @FXML
    private Label errorCoyear;
    @FXML
    private TextField txtPrice;
    @FXML
    private Label errorPrice;
    @FXML
    private TextField txtQuantity;
    @FXML
    private Label errorQuantity;
    @FXML
    private TextField txtDescription;
    @FXML
    private Label errorDescription;
    @FXML
    private ComboBox<Category> boxCategories;
    @FXML
    private Label errorCategory;
    @FXML
    private ComboBox<Author> boxAuthors;
    @FXML
    private Label errorAuthors;
    @FXML
    private ComboBox<Publishing> boxPublishs;
    @FXML
    private Label errorPublish;
    @FXML
    private ComboBox<StatusManage> boxStatus;
    @FXML
    private Label errorStatus;
    @FXML
    private TextField txtUpdatedAt;
    @FXML
    private TextField txtCreatedAt;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnReset;
    @FXML
    private DatePicker txtCoyear;
    @FXML
    private TableView<Book> table;
    @FXML
    private TableColumn<Book, String> colIndex;
    @FXML
    private TableColumn<Book, String> colId;
    @FXML
    private TableColumn<Book, String> colName;
    @FXML
    private TableColumn<Book, String> colCoyear;
    @FXML
    private TableColumn<Book, String> colPrice;
    @FXML
    private TableColumn<Book, String> colQuantity;
    @FXML
    private TableColumn<Book, String> colDescription;
    @FXML
    private TableColumn<Book, String> colCategory;
    @FXML
    private TableColumn<Book, String> colAuthor;
    @FXML
    private TableColumn<Book, String> colPublish;
    @FXML
    private TableColumn<Book, String> colStatus;
    @FXML
    private TableColumn<Book, String> colCreatedAt;
    @FXML
    private TableColumn<Book, String> colUpdatedAt;
    @FXML
    private Button btnRefesh;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;

    int myIndex;
    int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initClock();
        RefeshData();
        InitItemsPublishBox();
        InitItemsCategoryBox();
        InitItemsAuthorBox();
        InitItemsStatusBox();
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

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            labelClock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    private void BtnSaveClick() {

        RefeshData();
    }

    @FXML
    private void BtnDeleteClick() {

        RefeshData();
    }

    @FXML
    private void ResetFeild() {
        txtId.setText("");
        txtName.setText("");
        txtCoyear.setValue(null);
        txtPrice.setText("");
        txtQuantity.setText("");
        txtDescription.setText("");
        txtCreatedAt.setText("");
        txtUpdatedAt.setText("");
        txtSearch.setText("");
        boxCategories.setValue(null);
        boxAuthors.setValue(null);
        boxPublishs.setValue(null);
//        boxStatus.setValue(null);

        errorAuthors.setVisible(false);
        errorCategory.setVisible(false);
        errorCoyear.setVisible(false);
        errorDescription.setVisible(false);
        errorName.setVisible(false);
        errorPrice.setVisible(false);
        errorPublish.setVisible(false);
        errorQuantity.setVisible(false);
        errorStatus.setVisible(false);

        CheckId();
    }

    @FXML
    private void RefeshData() {
        ResetFeild();
//        table();
    }

    @FXML
    private void Search() {
    }

    @FXML
    private void Validated() {
        boolean flag = false;

        String name = txtName.getText();
        LocalDate coYear = txtCoyear.getValue();
        String price = txtPrice.getText();
        String quantity = txtQuantity.getText();
        String description = txtDescription.getText();
        Category category = boxCategories.getValue();
        Author author = boxAuthors.getValue();
        Publishing publishing = boxPublishs.getValue();
        StatusManage status = boxStatus.getValue();

        if (name.isEmpty()) {
            errorName.setVisible(true);

            flag = true;
        } else {
            errorName.setVisible(false);
        }

        if (coYear == null) {
            errorCoyear.setVisible(true);

            flag = true;
        } else {
            errorCoyear.setVisible(false);
        }

        if (price.isEmpty()) {
            errorPrice.setVisible(true);

            flag = true;
        } else {
            errorPrice.setVisible(false);
        }

        if (quantity.isEmpty()) {
            errorQuantity.setVisible(true);

            flag = true;
        } else {
            errorQuantity.setVisible(false);
        }

        if (description.isEmpty()) {
            errorDescription.setVisible(true);

            flag = true;
        } else {
            errorDescription.setVisible(false);
        }

        if (category == null) {
            errorCategory.setVisible(true);

            flag = true;
        } else {
            errorCategory.setVisible(false);
        }

        if (author == null) {
            errorAuthors.setVisible(true);

            flag = true;
        } else {
            errorAuthors.setVisible(false);
        }

        if (publishing == null) {
            errorPublish.setVisible(true);

            flag = true;
        } else {
            errorPublish.setVisible(false);
        }

        if (status == null) {
            errorStatus.setVisible(false);

            flag = true;
        } else {
            errorStatus.setVisible(false);
        }

        btnSave.setDisable(flag);
    }

    private void CheckId() {
        String id = txtId.getText();

        if (id.isEmpty()) {
            btnDelete.setDisable(true);
        } else {
            btnDelete.setDisable(false);
        }
    }

    @FXML
    private void InitItemsPublishBox() {
        ObservableList<Publishing> publishs = PublishingEntity.GetAll();

        if (publishs == null) {
            boxPublishs.setPromptText("No publishs in this box!");
        } else if (publishs.isEmpty()) {
            boxPublishs.setPromptText("No publishs in this box!");
        } else {
            boxPublishs.setItems(publishs);
        }
    }

    @FXML
    private void InitItemsCategoryBox() {
        ObservableList<Category> categories = CategoryEntity.GetAll();
        if (categories == null) {
            boxCategories.setPromptText("No categories in this box!");
        } else if (categories.isEmpty()) {
            boxCategories.setPromptText("No categories in this box!");
        } else {
            boxCategories.setItems(categories);
        }
    }

    @FXML
    private void InitItemsAuthorBox() {
        ObservableList<Author> authors = AuthorEntity.GetAll();

        if (authors == null) {
            boxAuthors.setPromptText("No authors in this box!");
        } else if (authors.isEmpty()) {
            boxAuthors.setPromptText("No authors in this box!");
        } else {
            boxAuthors.setItems(authors);
        }
    }

    @FXML
    private void InitItemsStatusBox() {
        ObservableList<StatusManage> status = StatusManageEntity.GetAll();

        if (status == null) {
            boxStatus.setPromptText("No status in this box!");
        } else if (status.isEmpty()) {
            boxStatus.setPromptText("No status in this box!");
        } else {
            boxStatus.setItems(status);
        }
    }
}

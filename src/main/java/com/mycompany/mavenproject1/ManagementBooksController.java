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
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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
    private Button btnManageBorrowing;
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
    private TableView<ManageBook> table;
    @FXML
    private TableColumn<ManageBook, String> colIndex;
    @FXML
    private TableColumn<ManageBook, String> colId;
    @FXML
    private TableColumn<ManageBook, String> colAccount;
    @FXML
    private TableColumn<ManageBook, String> colBookId;
    @FXML
    private TableColumn<ManageBook, String> colName;
    @FXML
    private TableColumn<ManageBook, String> colCoyear;
    @FXML
    private TableColumn<ManageBook, String> colPrice;
    @FXML
    private TableColumn<ManageBook, String> colQuantity;
    @FXML
    private TableColumn<ManageBook, String> colDescription;
    @FXML
    private TableColumn<ManageBook, String> colCategory;
    @FXML
    private TableColumn<ManageBook, String> colAuthor;
    @FXML
    private TableColumn<ManageBook, String> colPublish;
    @FXML
    private TableColumn<ManageBook, String> colStatus;
    @FXML
    private TableColumn<ManageBook, String> colCreatedAt;
    @FXML
    private TableColumn<ManageBook, String> colUpdatedAt;
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
        RefeshData();
        InitItemsPublishBox();
        InitItemsCategoryBox();
        InitItemsAuthorBox();
        InitItemsStatusBox();
        InitData();
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
    private void switchToManagementBorrowing() throws IOException {
        App.setRoot("ManagementBorrow");
    }

    @FXML
    private void SignOut() throws Exception {
        prefs.clear();
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

    @FXML
    private void BtnSaveClick() {
        int accountId = AccountEntity.GetAccountByUsername(sessionUsername.getText()).getId();
        String bookId = txtId.getText();
        String bookName = txtName.getText();
        String bookCoYear = txtCoyear.getValue().toString();
        String price = txtPrice.getText();
        String quantity = txtQuantity.getText();
        String description = txtDescription.getText();
        Category category = boxCategories.getValue();
        Author author = boxAuthors.getValue();
        Publishing publish = boxPublishs.getValue();
        StatusManage status = boxStatus.getValue();

        Book book = new Book();
        book.setName(bookName);
        book.setCoyear(bookCoYear);
        book.setPrice(Float.valueOf(price));
        book.setQuantity(Integer.parseInt(quantity));
        book.setDescription(description);
        book.setCategoryId(category.getId());
        book.setAuthorId(author.getId());
        book.setPublishingId(publish.getId());

        ManageBook mg = new ManageBook();
        mg.setPricePerBook(Float.valueOf(price));
        mg.getAccount().setId(accountId);
        mg.getStatus().setId(status.getId());

//      Call Alert box
        Alert alert = new Alert(Alert.AlertType.NONE);

        if (bookId.isEmpty()) {
            if (BookEntity.GetBookWithBookName(bookName) == null) {
                if (BookEntity.Add(book)) {
                    Book newBook = BookEntity.GetBookWithBookName(bookName);

                    mg.getBook().setId(newBook.getId());

                    if (ManageBookEntity.Add(mg)) {
//                      set titile, header, content for alert box
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setTitle("Test Connection");
                        alert.setHeaderText("Books Manager");
                        alert.setContentText("Added Successfully!");
                        alert.showAndWait();

                    } else {
//                      set titile, header, content for alert box
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setTitle("Test Connection");
                        alert.setHeaderText("Books Manager");
                        alert.setContentText("Added Fail!");
                        alert.showAndWait();
                    }
                } else {
//                  set titile, header, content for alert box
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Test Connection");
                    alert.setHeaderText("Books Manager");
                    alert.setContentText("Added Fail!");
                    alert.showAndWait();
                }
            } else {
//              set titile, header, content for alert box
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Test Connection");
                alert.setHeaderText("Books Manager");
                alert.setContentText("This book is exists!");
                alert.showAndWait();
            }
        } else {
            book.setId(Integer.parseInt(bookId));

            mg.getBook().setId(Integer.parseInt(bookId));
            if (!BookEntity.GetBookWithBookId(mg.getBook().getId()).getName().equals(book.getName())) {
                if (BookEntity.GetBookWithBookName(bookName) == null) {
                    if (BookEntity.Update(book)) {

                        if (ManageBookEntity.Update(mg)) {
//                      set titile, header, content for alert box
                            alert.setAlertType(Alert.AlertType.INFORMATION);
                            alert.setTitle("Test Connection");
                            alert.setHeaderText("Books Manager");
                            alert.setContentText("Updated Successfully!");
                            alert.showAndWait();
                        } else {
//                      set titile, header, content for alert box
                            alert.setAlertType(Alert.AlertType.ERROR);
                            alert.setTitle("Test Connection");
                            alert.setHeaderText("Books Manager");
                            alert.setContentText("Updated Fail!");
                            alert.showAndWait();
                        }
                    } else {
//                  set titile, header, content for alert box
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setTitle("Test Connection");
                        alert.setHeaderText("Books Manager");
                        alert.setContentText("Updated Fail!");
                        alert.showAndWait();
                    }
                } else {
//              set titile, header, content for alert box
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Test Connection");
                    alert.setHeaderText("Books Manager");
                    alert.setContentText("This book is exists!");
                    alert.showAndWait();
                }
            } else {
                if (BookEntity.Update(book)) {

                    if (ManageBookEntity.Update(mg)) {
//                      set titile, header, content for alert box
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setTitle("Test Connection");
                        alert.setHeaderText("Books Manager");
                        alert.setContentText("Updated Successfully!");
                        alert.showAndWait();
                    } else {
//                      set titile, header, content for alert box
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setTitle("Test Connection");
                        alert.setHeaderText("Books Manager");
                        alert.setContentText("Updated Fail!");
                        alert.showAndWait();
                    }
                } else {
//                  set titile, header, content for alert box
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Test Connection");
                    alert.setHeaderText("Books Manager");
                    alert.setContentText("Updated Fail!");
                    alert.showAndWait();
                }
            }

        }

        RefeshData();
    }

    @FXML
    private void BtnDeleteClick() {
        int accountId = AccountEntity.GetAccountByUsername(sessionUsername.getText()).getId();
        int bookId = Integer.parseInt(txtId.getText());
        String bookName = txtName.getText();
        String bookCoYear = txtCoyear.getValue().toString();
        String price = txtPrice.getText();
        String quantity = txtQuantity.getText();
        String description = txtDescription.getText();
        Category category = boxCategories.getValue();
        Author author = boxAuthors.getValue();
        Publishing publish = boxPublishs.getValue();
        StatusManage status = boxStatus.getValue();

        Book book = new Book();
        book.setName(bookName);
        book.setId(bookId);
        book.setCoyear(bookCoYear);
        book.setPrice(Float.valueOf(price));
        book.setQuantity(0);
        book.setDescription(description);
        book.setCategoryId(category.getId());
        book.setAuthorId(author.getId());
        book.setPublishingId(publish.getId());

        StatusManage sm = StatusManageEntity.GetStatusManageByName("Deleted");

        ManageBook mg = new ManageBook();
        mg.setPricePerBook(Float.valueOf(price));
        mg.getAccount().setId(accountId);
        mg.getBook().setId(bookId);
        mg.getStatus().setId(sm.getId());

//      Call Alert box
        Alert alert = new Alert(Alert.AlertType.NONE);

        if (BookEntity.Update(book)) {

            if (ManageBookEntity.Update(mg)) {
//                      set titile, header, content for alert box
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Connection");
                alert.setHeaderText("Books Manager");
                alert.setContentText("Deleted Successfully!");
                alert.showAndWait();
            } else {
//                      set titile, header, content for alert box
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Test Connection");
                alert.setHeaderText("Books Manager");
                alert.setContentText("Deleted Fail!");
                alert.showAndWait();
            }
        } else {
//                  set titile, header, content for alert box
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Test Connection");
            alert.setHeaderText("Books Manager");
            alert.setContentText("Deleted Fail!");
            alert.showAndWait();
        }

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
        InitData();
        btnSave.setDisable(true);
        CheckId();
    }

    @FXML
    private void table(ObservableList<ManageBook> books) {

        table.setItems(books);
        colIndex.setCellValueFactory(f -> f.getValue().indexProperty().asString());
        colId.setCellValueFactory(f -> f.getValue().idProperty().asString());
        colAccount.setCellValueFactory(f -> f.getValue().getAccount().UIDProperty());
        colBookId.setCellValueFactory(f -> f.getValue().getBook().idProperty().asString());
        colName.setCellValueFactory(f -> f.getValue().getBook().nameProperty());
        colCoyear.setCellValueFactory(f -> f.getValue().getBook().coYearProperty());
        colPrice.setCellValueFactory(f -> {
            StringProperty price = new SimpleStringProperty();
            price.setValue(String.valueOf(f.getValue().getBook().getPrice()));
            return price;
        });
        colQuantity.setCellValueFactory(f -> {
            StringProperty quantity = new SimpleStringProperty();
            quantity.setValue(String.valueOf(f.getValue().getBook().getQuantity()));
            return quantity;
        });
        colDescription.setCellValueFactory(f -> f.getValue().getBook().descriptionProperty());
        colAuthor.setCellValueFactory(f -> {
            Author auth = AuthorEntity.GetAuthorWithId(f.getValue().getBook().getAuthorId());

            return auth.signNameProperty();
        });
        colCategory.setCellValueFactory(f -> {
            Category cat = CategoryEntity.GetCategoryById(f.getValue().getBook().getCategoryId());

            return cat.nameProperty();
        });
        colPublish.setCellValueFactory(f -> {
            Publishing pub = PublishingEntity.GetPublishingWithId(f.getValue().getBook().getPublishingId());

            return pub.nameProperty();
        });
        colStatus.setCellValueFactory(f -> {
            StatusManage status = StatusManageEntity.GetStatusManageById(f.getValue().getStatus().getId());

            return status.nameProperty();
        });
        colCreatedAt.setCellValueFactory(f -> f.getValue().createdAtProperty());
        colUpdatedAt.setCellValueFactory(f -> f.getValue().updatedAtProperty());

        table.setRowFactory(tv -> {
            TableRow<ManageBook> myRow = new TableRow<>();

            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = table.getSelectionModel().getSelectedIndex();

                    String[] date = table.getItems().get(myIndex).getBook().getCoyear().split("-");

                    LocalDate coYear = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
                    txtCoyear.setValue(coYear);
                    Author auth = AuthorEntity.GetAuthorWithId(table.getItems().get(myIndex).getBook().getAuthorId());
                    boxAuthors.setValue(auth);

                    Category cat = CategoryEntity.GetCategoryById(table.getItems().get(myIndex).getBook().getCategoryId());
                    boxCategories.setValue(cat);

                    Publishing pub = PublishingEntity.GetPublishingWithId(table.getItems().get(myIndex).getBook().getPublishingId());
                    boxPublishs.setValue(pub);

                    StatusManage stat = StatusManageEntity.GetStatusManageById(table.getItems().get(myIndex).getStatus().getId());
                    boxStatus.setValue(stat);

                    txtId.setText(String.valueOf(table.getItems().get(myIndex).getBook().getId()));
                    txtName.setText(table.getItems().get(myIndex).getBook().getName());
                    txtPrice.setText(String.valueOf(table.getItems().get(myIndex).getBook().getPrice()));
                    txtQuantity.setText(String.valueOf(table.getItems().get(myIndex).getBook().getQuantity()));
                    txtDescription.setText(String.valueOf(table.getItems().get(myIndex).getBook().getDescription()));
                    txtCreatedAt.setText(table.getItems().get(myIndex).getCreatedAt());
                    txtUpdatedAt.setText(table.getItems().get(myIndex).getUpdatedAt());

                    Validated();
                    CheckId();
                }
            });
            return myRow;
        });
    }

    @FXML
    private void Search() {
        ObservableList<ManageBook> books = ManageBookEntity.SearchByBookName(txtSearch.getText());

        table(books);
    }

    @FXML
    private void InitData() {
        ObservableList<ManageBook> books = ManageBookEntity.GetAllBookInfo();

        table(books);
    }

    @FXML
    private void Validated() {
        boolean flag = false;

        String NUMBER_PATTERN = "^\\d+$";
        String FLOAT_PATTERN = "\\d+\\.\\d+";

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

        if (!price.matches(FLOAT_PATTERN)) {
            errorPrice.setVisible(true);

            flag = true;
        } else {
            errorPrice.setVisible(false);
        }

        if (!quantity.matches(NUMBER_PATTERN)) {
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
            errorStatus.setVisible(true);

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

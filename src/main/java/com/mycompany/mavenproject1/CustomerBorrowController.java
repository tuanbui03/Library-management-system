/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import Entites.*;
import Models.*;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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
    private TextField txtId;
    @FXML
    private ComboBox<Publishing> boxPublishing;
    @FXML
    private ComboBox<Author> boxAuthor;
    @FXML
    private ComboBox<Category> boxCategory;
    @FXML
    private ComboBox<Book> boxBook;
    @FXML
    private TableView<Borrow> table;
    @FXML
    private TableColumn<Borrow, String> colIndex;
    @FXML
    private TableColumn<Borrow, String> colId;
    @FXML
    private TableColumn<Borrow, String> colBook;
    @FXML
    private TableColumn<Borrow, String> colBorrowAt;
    @FXML
    private TableColumn<Borrow, String> colRefundedAt;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnBooking;
    @FXML
    private Label errorBook;

    int myIndex;
    int id;
    @FXML
    private AnchorPane page;

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
        initBoxAuthors();
        initBoxCategory();
        initBoxPublishing();
        InitData();
    }

    @FXML
    private void ValidatedFilter() {
        boolean flag = false;

        Publishing pub = boxPublishing.getValue();
        Category cat = boxCategory.getValue();
        Author author = boxAuthor.getValue();

        if (pub == null || cat == null || author == null) {
            boxBook.setPromptText("Choose a Publishing, a Author and a Category!");
            errorBook.setText("Choose a Publishing, a Author and a Category!");
            errorBook.setVisible(true);

            flag = true;
        } else {
            boxBook.setPromptText("Choose a Book!");
            initBoxBook(pub, author, cat);
            errorBook.setText("Choose a Book!");
            errorBook.setVisible(true);
        }

        boxBook.setDisable(flag);
    }

    @FXML
    private void ValidatedBook() {
        boolean flag = false;

        if (boxBook.getValue() == null) {
            boxBook.setPromptText("Choose a Book!");
            errorBook.setText("Choose a Book!");
            errorBook.setVisible(true);

            flag = true;
        } else {
            errorBook.setVisible(false);
        }

        btnBooking.setDisable(flag);
    }

    @FXML
    private void switchToCustomerDashboard() throws Exception {
        page.setDisable(true);
        App.setRoot("CustomerDashboard");
    }

    @FXML
    private void switchToCustomerInfomation() throws Exception {
        page.setDisable(true);
        App.setRoot("CustomerInfomation");
    }

    @FXML
    private void switchToCustomerBorrowing() throws Exception {
        page.setDisable(true);
        App.setRoot("CustomerBorrow");
    }

    @FXML
    private void switchToSignIn() throws Exception {
        page.setDisable(true);
        App.setRoot("SignIn");
    }

    @FXML
    private void BtnBooking() {
//      Call Alert box
        Alert alert = new Alert(Alert.AlertType.NONE);

        StatusManage borrowStatusManage = StatusManageEntity.GetStatusManageByName("Borrowed");
        StatusManage outOfStockStatusManage = StatusManageEntity.GetStatusManageByName("Out Of Stock");
        StatusBorrow borrowStatusBorrow = StatusBorrowEntity.GetStatusBorrowWithName("Borrowing");

        Account acc = AccountEntity.GetAccountByUsername(user.getUserName());

        Book book = boxBook.getValue();
        ManageBook mg = new ManageBook();
        mg.setPricePerBook(book.getPrice());
        mg.getBook().setId(book.getId());
        mg.getAccount().setId(acc.getId());
        
        if (BorrowEntity.GetBorrowingBookByAccountId(acc.getId()).size() > 2) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
            try {
                alert.setDialogPane(loader.load());
                MessageController mc = loader.getController();
                mc.setMessage("Can't borrow more than 3 books!\nPlease refunded all borrowing book when borrowing more than book!");
            } catch (Exception e) {
            }

            alert.showAndWait();
        } else {
            if (book.getQuantity() == 0) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
                try {
                    alert.setDialogPane(loader.load());
                    MessageController mc = loader.getController();
                    mc.setMessage("The library is out of this books!");
                } catch (Exception e) {
                }

                alert.showAndWait();
                mg.getStatus().setId(outOfStockStatusManage.getId());

                RefeshData();
            } else {
                int newQuantityBook = book.getQuantity() - 1;
                book.setQuantity(newQuantityBook);

                if (newQuantityBook == 0) {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
                    try {
                        alert.setDialogPane(loader.load());
                        MessageController mc = loader.getController();
                        mc.setMessage("The library is out of this books! You were the last to choose it!");
                    } catch (Exception e) {
                    }

                    alert.showAndWait();
                    mg.getStatus().setId(outOfStockStatusManage.getId());
                } else {
                    mg.getStatus().setId(borrowStatusManage.getId());
                }

                ManageBookEntity.Add(mg);
                BookEntity.Update(book);

                ObservableList<ManageBook> newMg = ManageBookEntity.GetAllBookBorrowByUID(acc.getUID());
                ManageBook newBook = newMg.get(newMg.size() - 1);
                Borrow br = new Borrow();
                br.setAmount_of_pay(book.getPrice());
                br.setManageId(newBook.getId());
                br.setTime_out(3);
                LocalDate preDate = LocalDate.now();
                LocalDate refundAt = preDate.withDayOfYear(preDate.getDayOfYear() + 3);
                br.setRefundAt(refundAt.toString());
                br.setStatusId(borrowStatusBorrow.getId());

                if (BorrowEntity.AddByReader(br)) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
                    try {
                        alert.setDialogPane(loader.load());
                        MessageController mc = loader.getController();
                        mc.setMessage("Booking Successfully! You have read time this book: 3 days");
                    } catch (Exception e) {
                    }

                    alert.showAndWait();
                }
                RefeshData();
            }
        }

    }

    private void ResetFeild() {
        boxAuthor.setValue(null);
        boxBook.setValue(null);
        boxPublishing.setValue(null);
        boxCategory.setValue(null);
        errorBook.setVisible(false);
    }

    private void RefeshData() {
        ResetFeild();
        InitData();
    }

    @FXML
    private void Search() {
        Account acc = AccountEntity.GetAccountByUsername(user.getUserName());
        BorrowEntity be = new BorrowEntity();
        ObservableList<Borrow> b = be.SearchBorrowByAccountId(acc.getId(), txtSearch.getText());
        table(b);
    }

    private void InitData() {
        Account acc = AccountEntity.GetAccountByUsername(user.getUserName());
        BorrowEntity be = new BorrowEntity();
        ObservableList<Borrow> b = be.GetBorrowByAccountId(acc.getId());
        table(b);
    }

    private void table(ObservableList<Borrow> b) {
        table.setItems(b);
        colIndex.setCellValueFactory(f -> f.getValue().indexProperty().asString());
        colId.setCellValueFactory(f -> f.getValue().idProperty().asString());
        colBook.setCellValueFactory(f -> {
            int mangeId = f.getValue().getManageId();
            ManageBook mg = ManageBookEntity.GetAllBookInfoById(mangeId);
            Book book = BookEntity.GetBookWithBookId(mg.getBook().getId());
            return book.nameProperty();
        });
        colBorrowAt.setCellValueFactory(f -> f.getValue().borrowAtProperty());
        colRefundedAt.setCellValueFactory(f -> f.getValue().refundAtProperty());

        table.setRowFactory(tv -> {
            TableRow<Borrow> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = table.getSelectionModel().getSelectedIndex();

                    txtId.setText(String.valueOf(table.getItems().get(myIndex).getId()));
                    ManageBook mg = ManageBookEntity.GetAllBookInfoById(table.getItems().get(myIndex).getId());
                    Book book = BookEntity.GetBookWithBookId(mg.getBook().getId());
                    Publishing pub = PublishingEntity.GetPublishingWithId(book.getPublishingId());
                    Author author = AuthorEntity.GetAuthorWithId(book.getAuthorId());
                    Category cate = CategoryEntity.GetCategoryById(book.getCategoryId());

                    boxAuthor.setValue(author);
                    boxCategory.setValue(cate);
                    boxPublishing.setValue(pub);
                    boxBook.setValue(book);
                    ValidatedFilter();
                }
            });
            return myRow;
        });
    }

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss\ndd/MM/yyyy");
            labelClock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void initBoxPublishing() {
        ObservableList<Publishing> publishs = PublishingEntity.GetAll();
        boxPublishing.setItems(publishs);
    }

    private void initBoxAuthors() {
        ObservableList<Author> authors = AuthorEntity.GetAll();
        boxAuthor.setItems(authors);
    }

    private void initBoxCategory() {
        ObservableList<Category> categories = CategoryEntity.GetAll();
        boxCategory.setItems(categories);
    }

    private void initBoxBook(Publishing pub, Author author, Category cat) {
        ObservableList<Book> books = BookEntity.GetBookWithPublishCategoryAuthor(pub, author, cat);
        boxBook.setItems(books);
    }

}

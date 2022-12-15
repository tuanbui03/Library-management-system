/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import Models.*;
import Entites.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CustomerDashboardController implements Initializable {

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
    private TableView<Top5CategoriesInterested> tableCategories;
    @FXML
    private TableColumn<Top5CategoriesInterested, String> colCategoryName;
    @FXML
    private TableColumn<Top5CategoriesInterested, String> colCategoryTotal;
    @FXML
    private TableView<Top5AuthorsIntersted> tableAuthors;
    @FXML
    private TableColumn<Top5AuthorsIntersted, String> colAuthorsName;
    @FXML
    private TableColumn<Top5AuthorsIntersted, String> colAuthorsTotal;
    @FXML
    private Label totalBooksInterested;
    @FXML
    private Label totalAuthorsInterested;
    @FXML
    private Label totalCategoriesInterested;
    @FXML
    private Label totalPublishsingInterested;
    @FXML
    private AnchorPane page;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        Total();
        Top5CategoriesFavorites();
        Top5AuthorsFavorites();
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

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss\ndd/MM/yyyy");
            labelClock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void Total() {
        String username = user.getUserName();

        Account acc = AccountEntity.GetAccountByUsername(username);

        int totalBook = ManageBookEntity.CountBookInterestedByUID(acc.getUID());
        int totalCategory = ManageBookEntity.CountCategoryInterestedByUID(acc.getUID());
        int totalPublish = ManageBookEntity.CountPublishingInterestedByUID(acc.getUID());
        int totalAuthor = ManageBookEntity.CountAuthorInterestedByUID(acc.getUID());

        totalBooksInterested.setText(String.valueOf(totalBook));
        totalCategoriesInterested.setText(String.valueOf(totalCategory));
        totalPublishsingInterested.setText(String.valueOf(totalPublish));
        totalAuthorsInterested.setText(String.valueOf(totalAuthor));
    }

    private void Top5CategoriesFavorites() {
        String username = user.getUserName();

        Account acc = AccountEntity.GetAccountByUsername(username);
//        tableCategories
        ObservableList<Top5CategoriesInterested> t5c = ManageBookEntity.Top5CategoryInterestedByUID(acc.getUID());
        tableCategories.setItems(t5c);
        colCategoryName.setCellValueFactory(f -> {
            int id = f.getValue().getId();
            Category c = CategoryEntity.GetCategoryById(id);

            return c.nameProperty();
        });
        colCategoryTotal.setCellValueFactory(f -> f.getValue().totalProperty().asString());
    }

    private void Top5AuthorsFavorites() {
        String username = user.getUserName();

        Account acc = AccountEntity.GetAccountByUsername(username);
//        tableAuthors
        ObservableList<Top5AuthorsIntersted> t5c = ManageBookEntity.Top5AuthorInterestedByUID(acc.getUID());
        tableAuthors.setItems(t5c);
        colAuthorsName.setCellValueFactory(f -> {
            int id = f.getValue().getId();
            Author c = AuthorEntity.GetAuthorWithId(id);

            return c.nameProperty();
        });
        colAuthorsTotal.setCellValueFactory(f -> f.getValue().totalProperty().asString());
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entites;

import Models.Category;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.*;
import db.*;
import java.io.IOException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 *
 * @author PC
 */
public class CategoryEntity {

    @FXML
    private static TextField txtId;
    @FXML
    private static TextField txtName;
    @FXML
    private static TextField txtCreatedAt;
    @FXML
    private static TextField txtUpdatedAt;
    @FXML
    private static TableView<Category> table;

    @FXML
    private static TableColumn<Category, String> colIndex;

    @FXML
    private static TableColumn<Category, String> colName;

    @FXML
    private static TableColumn<Category, String> colCreatedAt;

    @FXML
    private static TableColumn<Category, String> colUpdatedAt;

    public static Connection connection = null;
    public static PreparedStatement preparedStatement = null;
    public static ResultSet rs = null;
    public static int myIndex;
    public static int id;

    @FXML
    public static void Add(ActionEvent event, String name) throws IOException {
        String sql = "Insert into authors(name, creaetedAt, updatedAt) values(?, ?, ?)";
        long milis = System.currentTimeMillis();
        Date preDate = new Date(milis);

        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setDate(2, preDate);
            preparedStatement.setDate(3, preDate);

//          if add sucess reset all feild and reset table view, all feild else show message fail
            if (preparedStatement.executeUpdate() > 0) {
//              show message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Connection");

                alert.setHeaderText("Management Cateogries");
                alert.setContentText("Add New Category Successfully !");

                alert.showAndWait();

//              get data in database fors table view
                table();
//              reset all feild
                ResetFeild();
            } else {
//              show message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Test Connection");

                alert.setHeaderText("Management Cateogries");
                alert.setContentText("Add Fail !");

                alert.showAndWait();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    public static void Delete(ActionEvent event, int myIndex, int id) {
        String sql = "Delete from categories WHERE id = ?";

        try {

            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall(sql);
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Connection");

                alert.setHeaderText("Management Categories");
                alert.setContentText("Deleted Successfully!");

                alert.showAndWait();

                table();
                ResetFeild();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Test Connection");

                alert.setHeaderText("Management Categories");
                alert.setContentText("Deleted Fail!");

                alert.showAndWait();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public static void ResetFeild() {
        txtId.setText("");
        txtName.setText("");
        txtCreatedAt.setText("");
        txtUpdatedAt.setText("");
    }

    private static void table() {
        JDBCConnect.getJDBCConnection();
        ObservableList<Category> categories = FXCollections.observableArrayList();
        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareCall("Select * from categories");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getString("id"));
                c.setName(rs.getString("name"));
                c.setCreatedAt(rs.getString("createdAt"));
                c.setUpdatedAt(rs.getString("updatedAt"));
                categories.add(c);
            }
            table.setItems(categories);
            colIndex.setCellValueFactory(f -> f.getValue().idProperty());
            colName.setCellValueFactory(f -> f.getValue().nameProperty());
            colCreatedAt.setCellValueFactory(f -> f.getValue().createdAtProperty());
            colUpdatedAt.setCellValueFactory(f -> f.getValue().updatedAtProperty());
        } catch (SQLException ex) {
        }
        table.setRowFactory(tv -> {
            TableRow<Category> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = table.getSelectionModel().getSelectedIndex();
                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                    txtName.setText(table.getItems().get(myIndex).getName());
                    txtCreatedAt.setText(table.getItems().get(myIndex).getCreatedAt());
                    txtUpdatedAt.setText(table.getItems().get(myIndex).getUpdatedAt());
                }
            });
            return myRow;
        });

    }
}

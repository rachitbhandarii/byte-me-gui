package com.rachitbhandari.bytemegui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuView {
    @FXML
    private TableView<Food> foodTable;

    @FXML
    private TableColumn<Food, String> foodNameColumn;

    @FXML
    private TableColumn<Food, Double> foodPriceColumn;

    @FXML
    private TableColumn<Food, Boolean> foodAvailableColumn;

    @FXML
    public void initialize() {
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        foodPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        foodAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
        ObservableList<Food> foodItems = FXCollections.observableArrayList(Admin.getMenu());
        foodTable.setItems(foodItems);
    }

    @FXML
    public void onBackButtonClick(ActionEvent actionEvent) {
        changeScene(actionEvent, "homescreen.fxml");
    }

    private void changeScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root,420,700));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

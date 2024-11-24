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
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

public class OrdersView {
    @FXML
    private TableView<Order> orderTable;

    @FXML
    private TableColumn<Order, Integer> orderIDColumn;

    @FXML
    private TableColumn<Order, Status> orderStatusColumn;

    @FXML
    private TableColumn<Order, HashMap<Food,Integer>> orderCartColumn;

    @FXML
    public void initialize() {
        orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        orderCartColumn.setCellValueFactory(new PropertyValueFactory<>("cart"));
        Queue<Order> orders = new ArrayDeque<>();
        orders.addAll(Admin.getVIPOrders());
        orders.addAll(Admin.getOrders());
        ObservableList<Order> orderItems = FXCollections.observableArrayList(orders);
        orderTable.setItems(orderItems);
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

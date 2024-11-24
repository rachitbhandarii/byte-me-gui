package com.rachitbhandari.bytemegui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Homescreen {
    @FXML
    public void onMenuButtonClick(ActionEvent actionEvent) {
        changeScene(actionEvent, "menu.fxml");
    }
    @FXML
    public void onOrdersButtonClick(ActionEvent actionEvent) {
        changeScene(actionEvent, "orders.fxml");
    }
    @FXML
    public void onExitButtonClick(ActionEvent actionEvent) {
        Platform.exit();
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

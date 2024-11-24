package com.rachitbhandari.bytemegui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class ByteMeGUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Homescreen.class.getResource("homescreen.fxml"));
        Scene home = new Scene(fxmlLoader.load(),420,700);
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Byte Me!");
        stage.setResizable(false);
        stage.setX(780);
        stage.setY(190);
        stage.setScene(home);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
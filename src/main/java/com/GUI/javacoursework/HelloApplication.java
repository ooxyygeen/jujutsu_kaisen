package com.GUI.javacoursework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game-scene.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu-scene.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
//        GameWindowController gameWindowController = fxmlLoader.getController();
//        scene.setOnKeyPressed(gameWindowController::keyListener);
        MenuWindowController menuWindowController = fxmlLoader.getController();
        stage.setTitle("Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
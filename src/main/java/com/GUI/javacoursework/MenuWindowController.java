package com.GUI.javacoursework;

import com.engine.javacoursework.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class MenuWindowController {
    private Stage stage;
    static String filename = "";
    static Scanner s = new Scanner(System.in);

    public void newGame(ActionEvent e) throws IOException {
        System.out.println("Enter session name");
        s.nextLine();
        filename = s.nextLine();
        while (filename == null && filename.trim().isEmpty()) {
            System.out.println("Please, enter the session name.");
            s.nextLine();
            filename = s.nextLine();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game-scene.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameWindowController gameWindowController = fxmlLoader.getController();
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(gameWindowController::keyListener);
        stage.setScene(scene);
        stage.show();
    }
}

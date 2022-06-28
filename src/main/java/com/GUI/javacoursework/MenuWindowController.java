package com.GUI.javacoursework;

import com.engine.javacoursework.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuWindowController {
    private int menuChoice;
    private Stage stage;
    private String filename = "";
    private String s;
    @FXML
    private Pane dialogPane;
    @FXML
    private Label dialogLabel;
    @FXML
    private TextField dialogErrorLabel;
    @FXML
    private TextField textField;

    public void startButton(ActionEvent e) throws IOException {
        filename = textField.getText();
        if (textField.getLength() != 0) {
            System.out.println(filename);
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

    public void newGame(ActionEvent e) throws IOException {
        menuChoice = 1;
        dialogLabel.setText("Enter session name");
        dialogPane.setVisible(true);


       /* System.out.println("Enter session name");
        s.nextLine();
        filename = s.nextLine();
        while (filename == null && filename.trim().isEmpty()) {
            System.out.println("Please, enter the session name.");
            s.nextLine();
            filename = s.nextLine();
        }*/
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game-scene.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameWindowController gameWindowController = fxmlLoader.getController();
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(gameWindowController::keyListener);
        stage.setScene(scene);
        stage.show();*/
    }

    public void saveGame(ActionEvent e) {

    }
}



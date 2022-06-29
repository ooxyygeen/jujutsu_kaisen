package com.GUI.javacoursework;

import com.engine.javacoursework.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

public class MenuWindowController {
    private FXMLLoader fxmlLoader;
    private GameWindowController gwc;
    private File saveDir;
    private ObjectInputStream ois;
    private Stage stage;
    private String sessionName = "";
    @FXML
    private Pane dialogPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label dialogLabel;
    @FXML
    private TextField dialogErrorLabel;
    @FXML
    private TextField textField;

    public void switchToGame(ActionEvent e, boolean load) {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game-scene.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        gwc = fxmlLoader.getController();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("/css/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        scene.setOnKeyPressed(gwc::keyListener);
        gwc.setSessionName(sessionName);
        gwc.setLoadFlag(load);
        stage.setScene(scene);
        stage.show();
    }

    public void startButton(ActionEvent e) {
        sessionName = textField.getText();
        System.out.println(sessionName);
        if (textField.getLength() != 0) {
            switchToGame(e, false);
        }
    }

    public void newGame() {
        dialogLabel.setText("Enter session name");
        dialogPane.setVisible(true);
    }

    public void continueGame() {

    }

    public void loadGame(ActionEvent e) {
        sessionName = "Van";
        switchToGame(e, true);
    }


    public void exit() {
        stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }
}
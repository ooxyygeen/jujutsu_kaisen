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

    public void switchToGame(ActionEvent e) {
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
        stage.setScene(scene);
        stage.show();
    }

    public void startButton(ActionEvent e) {
        sessionName = textField.getText();
        System.out.println(sessionName);
        if (textField.getLength() != 0) {
            switchToGame(e);
        }
    }

    public void newGame() {
        dialogLabel.setText("Enter session name");
        dialogPane.setVisible(true);
    }

    public void continueGame() {

    }

    @FXML
    public void saveGame() {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game-scene.fxml"));
        GameWindowController gwc = fxmlLoader.getController();
        saveDir = new File("/saveDirectory/" + sessionName);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gwc = fxmlLoader.getController();

        System.out.println(sessionName);
//        gwc.saveFile(gwc.getPlayer(), "", "./saveDirectory/"+sessionName);
//        gwc.saveFile(gwc.getGameMap(), "Map", "./saveDirectory/"+sessionName);
//        gwc.saveFile(gwc.getQuests(), "Quests", "./saveDirectory/"+sessionName);
    }

    public void exit() {
        stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
        /*Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're about to exit!");
        alert.setContentText("Do you want to save before exiting?");

        if (alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) anchorPane.getScene().getWindow();
            stage.close();*/
//            saveGame();
//            System.out.println("You successfully logged out");
        //}
    }
}



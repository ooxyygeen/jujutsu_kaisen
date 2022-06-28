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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

public class MenuWindowController {
    private FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game-scene.fxml"));
//    private FileOutputStream saveFilePlayer = null;
//    private FileOutputStream saveFileMap = null;
//    private FileOutputStream saveFileQuests = null;
//    private ObjectOutputStream oos = null;
    private File saveDir;
    private ObjectInputStream ois = null;
    private Stage stage;
    private String sessionName = "";
    @FXML
    private Pane dialogPane;
    @FXML
    private Label anchorPane;
    @FXML
    private Label dialogLabel;
    @FXML
    private TextField dialogErrorLabel;
    @FXML
    private TextField textField;

    public void switchToGame(ActionEvent e) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game-scene.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameWindowController gameWindowController = fxmlLoader.getController();
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(gameWindowController::keyListener);
        stage.setScene(scene);
        stage.show();
    }

    public void startButton(ActionEvent e) {
        sessionName = textField.getText();
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

    public void saveGame() {
        saveDir = new File("../saveDirectory/"+sessionName);
        if (!saveDir.exists()){
            saveDir.mkdirs();
        }
        ((GameWindowController)fxmlLoader.getController()).saveFile(((GameWindowController)fxmlLoader.getController()).getPlayer(), "", "../saveDirectory/"+sessionName);
        ((GameWindowController)fxmlLoader.getController()).saveFile(((GameWindowController)fxmlLoader.getController()).getGameMap(), "Map", "../saveDirectory/"+sessionName);
        ((GameWindowController)fxmlLoader.getController()).saveFile(((GameWindowController)fxmlLoader.getController()).getQuests(), "Quests", "../saveDirectory/"+sessionName);
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



package com.GUI.javacoursework;

import com.engine.javacoursework.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class MenuWindowController {
    private FXMLLoader fxmlLoader;
    private GameWindowController gwc;
    private File saveDir;
    private ObjectInputStream ois;
    private Stage stage;
    private String sessionName = "";
    @FXML
    private Pane dialogPane, loadPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label dialogLabel;
    @FXML
    private TextField dialogErrorLabel;
    @FXML
    private TextField textField;
    @FXML
    private VBox loadVBox;

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

    @FXML
    public void startButton(ActionEvent e) {
        sessionName = textField.getText();
        if (textField.getLength() != 0) {
            switchToGame(e, false);
        }
    }

    public void newGame() {
        dialogLabel.setText("Enter session name");
        dialogPane.setVisible(true);
    }

    @FXML
    public void continueGame(ActionEvent e) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("saveDirectory/lastSession.txt"));
            sessionName = reader.readLine();
            reader.close();
            switchToGame(e, true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void loadGame(ActionEvent e) {
        loadVBox.getChildren().clear();
        String path = "saveDirectory/";

        try (DirectoryStream<Path> ds = Files.newDirectoryStream(
                FileSystems.getDefault().getPath(path), Files::isDirectory)) {
            for (Path p : ds) {
                String dirName = p.getFileName().toString();
                Button temp = new Button(dirName);
                temp.resize(loadVBox.getWidth(), 20);
                temp.setOnAction(event -> {
                            sessionName = ((Button) event.getSource()).getText();
                            switchToGame(event, true);
                        }
                );
                loadVBox.getChildren().add(temp);
                Separator separator = new Separator(Orientation.VERTICAL);
                separator.setVisible(false);
                loadVBox.getChildren().add(separator);

            }
            loadPane.setVisible(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void exit() {
        stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }
}
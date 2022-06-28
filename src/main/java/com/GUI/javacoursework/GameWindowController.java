package com.GUI.javacoursework;

import com.engine.javacoursework.*;
import com.engine.javacoursework.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class GameWindowController {
    private final int mapSizeX = 100;
    private final int mapSizeY = 100;
    private GameMap gameMap = new GameMap(mapSizeY, mapSizeX);
    private int obstacleX, obstacleY;
    private Character player = new Character();
    private Quests quests = new Quests();
    private FileOutputStream saveFilePlayer;
    private FileOutputStream saveFileMap;
    private FileOutputStream saveFileQuests;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private String sessionName;

    public void setSessionName(String name) {
        this.sessionName = name;
    }

    public void setCharacter(Character arg) {
        this.player = arg;
    }

    public void setGameMap(GameMap arg) {
        this.gameMap = arg;
    }

    public void setQuests(Quests arg) {
        this.quests = arg;
    }

    public Character getPlayer() {
        return player;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Quests getQuests() {
        return quests;
    }

    public void readSaveFile(Object aObject, String aSuffix, ObjectInputStream aOis) {
        try {
//            aOis = new ObjectInputStream(new FileInputStream(filename + aSuffix + ".dat"));
            aObject = (aObject.getClass());
            aOis.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Something went wrong. Try again:(");
        }
    }

    public void saveFile() {
        try {
            saveFilePlayer = new FileOutputStream(HelloApplication.class.getResource("saveDirectory").toExternalForm() +
                    "/" + sessionName + "/" + sessionName + ".dat"); // create savefile
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            oos = new ObjectOutputStream(saveFilePlayer);
            oos.writeObject(player); // записываем значения всех полей перса в файл
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went wrong. Try again:(");
        }
        try {
            saveFileMap = new FileOutputStream(HelloApplication.class.getResource("saveDirectory").toExternalForm() +
                    "/" + sessionName + "/" + sessionName + "Map" + ".dat"); // create savefile
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            oos = new ObjectOutputStream(saveFileMap);
            oos.writeObject(gameMap); // записываем значения всех полей перса в файл
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went wrong. Try again:(");
        }
        try {
            saveFileQuests = new FileOutputStream(HelloApplication.class.getResource("saveDirectory").toExternalForm() +
                    "/" + sessionName + "/" + sessionName + "Quests" + ".dat"); // create savefile
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            oos = new ObjectOutputStream(saveFileQuests);
            oos.writeObject(quests); // записываем значения всех полей перса в файл
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went wrong. Try again:(");
        }

    }

    public void setTest() {
        gameMap.map[player.getPosY()][player.getPosX()] = player;
        gameMap.map[4][3] = new PlateWithText(4, 3, true, "Congrats, you have moved!");
        Character fushi = new Character("Megumi Fushiguro", 8, 2, mapSizeY, mapSizeX,
                false,
                new Inventory(),
                new Equipment(),
                new ArrayList<Technique>(),
                new Stats(35, 80, 20, 50, 100, 100),
                "dynamic register...");
        fushi.addTechnique("Ten Shadows Technique", 1, 20, 0, 50, 10);
        gameMap.map[8][2] = fushi;
        gameMap.map[4][1] = new TotemOfDexterity();
        gameMap.map[4][9] = new PaperWall();
        gameMap.map[4][5] = new BarbedBush();
        gameMap.map[4][7] = new ChestWithSockWithSoap();
        Character zhaba = new Character("Zhaba", 7, 15, mapSizeY, mapSizeX,
                false,
                new Inventory(),
                new Equipment(),
                new ArrayList<Technique>(),
                new Stats(1, 1, 1, 1, 30, 30),
                "You are strong. There is armor somewhere near gym (7, 17).");
        zhaba.addTechnique("Ten Shadows Technique", 0, 0, 0, 0, 1);
        gameMap.map[7][15] = zhaba;
        gameMap.map[7][17] = new ChestWithUniform();
    }

    private final int gridSizeX = 9, gridSizeY = 9;
    private final int centreX = Math.floorDiv(gridSizeX, 2), centreY = Math.floorDiv(gridSizeY, 2);
    @FXML
    private Pane mapObjectsPane, invPane, gameOverPane, gameMenuPane;
    GridPane mapObjectsGridPane;
    private Label[][] labels = new Label[gridSizeY][gridSizeX];
    @FXML
    private ProgressBar hpBar;
    @FXML
    private Label playerPosX_label, playerPosY_label, actionInfo, hpLabel, weaponLabel, uniformLabel;
    @FXML
    private Button startButton;
    @FXML
    private TextFlow questText;
    @FXML
    private VBox playerDataVBox, questVBox;
    @FXML
    private FlowPane invItemsPane;
    @FXML
    private AnchorPane scenePane;


    private void swap(MapObject first, int Y, int X) {
        gameMap.map[Y][X] = first;
        gameMap.map[first.getPosY()][first.getPosX()] = null;
    }

//    private void resizeEvent() {
//        playerDataVBox.resize(mapObjectsPane.getWidth() / 7, mapObjectsPane.getHeight() / 4);
//        playerDataVBox.setLayoutX(playerDataVBox.getScene().getWidth() - playerDataVBox.getWidth() * (1 + 0.2));
//        playerDataVBox.setLayoutY(playerDataVBox.getHeight() * (1 + 0.2));
//        questVBox.resize(mapObjectsPane.getWidth() / 7, mapObjectsPane.getHeight() / 3);
//        questVBox.setLayoutX(questVBox.getWidth() * (1 + 0.2));
//        questVBox.setLayoutY(questVBox.getHeight() * (1 + 0.2));
//    }

    private boolean isObstaclePresent(String direction, int steps, Character player) {
        if (direction.equals("up")) {
            for (int i = player.getPosY() - 1; i >= player.getPosY() - steps; i--) {
                if (i < 0 || gameMap.map[i][player.getPosX()] != null && ((MapObject) gameMap.map[i][player.getPosX()]).getPresence()) {
                    obstacleY = i;
                    obstacleX = player.getPosX();
                    return true;
                }
            }
        } else if (direction.equals("down")) {
            for (int i = player.getPosY() + 1; i <= player.getPosY() + steps; i++) {
                if (i >= mapSizeY || gameMap.map[i][player.getPosX()] != null && ((MapObject) gameMap.map[i][player.getPosX()]).getPresence()) {
                    obstacleY = i;
                    obstacleX = player.getPosX();
                    return true;
                }
            }
        } else if (direction.equals("right")) {
            for (int i = player.getPosX() + 1; i <= player.getPosX() + steps; i++) {
                if (i >= mapSizeX || gameMap.map[player.getPosY()][i] != null && ((MapObject) gameMap.map[player.getPosY()][i]).getPresence()) {
                    obstacleX = i;
                    obstacleY = player.getPosY();
                    return true;
                }
            }
        } else if (direction.equals("left")) {
            for (int i = player.getPosX() - 1; i >= player.getPosX() - steps; i--) {
                if (i < 0 || gameMap.map[player.getPosY()][i] != null && ((MapObject) gameMap.map[player.getPosY()][i]).getPresence()) {
                    obstacleX = i;
                    obstacleY = player.getPosY();
                    return true;
                }
            }
        }
        return false;
    }

    public void firstInitialization() {
        mapObjectsPane.setMinSize(mapObjectsPane.getScene().getHeight(), mapObjectsPane.getScene().getHeight());
        mapObjectsPane.setMaxSize(mapObjectsPane.getScene().getHeight(), mapObjectsPane.getScene().getHeight());
        mapObjectsPane.resize(mapObjectsPane.getMaxWidth(), mapObjectsPane.getMaxHeight());
        mapObjectsPane.setLayoutX((scenePane.getScene().getWidth() - scenePane.getScene().getHeight()) / 2);
        mapObjectsPane.setLayoutY(0);
        mapObjectsGridPane = new GridPane();
        mapObjectsGridPane.setId("mapObjectsGridPane");
        questText.setMaxSize(questVBox.getWidth(), questVBox.getHeight());
        questText.resize(questVBox.getWidth(), questVBox.getHeight());
        for (int i = 0; i < gridSizeX; i++) {
            for (int j = 0; j < gridSizeY; j++) {
                StackPane stackPanes = new StackPane(labels[j][i]);
                stackPanes.setId("cell");
                stackPanes.setMinSize(mapObjectsPane.getWidth() / gridSizeX, mapObjectsPane.getHeight() / gridSizeY);
                stackPanes.setMaxSize(mapObjectsPane.getWidth() / gridSizeX, mapObjectsPane.getHeight() / gridSizeY);
                mapObjectsGridPane.add(stackPanes, i, j);
            }
        }
        mapObjectsPane.getChildren().add(mapObjectsGridPane);
        mapObjectsPane.setVisible(true);
        actionInfo.setVisible(true);
        questText.setVisible(true);
        playerDataVBox.setVisible(true);
        startButton.setVisible(false);
    }

    public void gameOver() {
        gameOverPane.setDisable(false);
        gameOverPane.setVisible(true);
    }

    private void update() {
        if (player.getPosY() == 4 && player.getPosX() == 20) {
            player.addItem(new FingerOfSukuna());
            quests.HELP();
        }
        quests.updateQuestsStatus(player);
        questText.getChildren().clear();
        Label label = new Label();
        label.setMaxSize(questText.getWidth(), questText.getHeight());
        label.setWrapText(true);
        label.setText(quests.showMainQuest());
        questText.getChildren().add(label);

        labels[centreY][centreX].setText("YOU");
        int playerX = player.getPosX(), playerY = player.getPosY(), displayedX, displayedY;
        for (int i = 0; i < gridSizeY; i++) {
            for (int j = 0; j < gridSizeX; j++) {
                displayedX = playerX - Math.floorDiv(gridSizeX, 2) + j;
                displayedY = playerY - Math.floorDiv(gridSizeY, 2) + i;
                if (i == centreY && j == centreX)
                    continue;
                if (displayedY < 0 || displayedY >= mapSizeY ||
                        displayedX < 0 || displayedX >= mapSizeX) {
                    labels[i][j].setOpacity(1);
                    labels[i][j].setText("Out of map");
                } else if (gameMap.map[displayedY][displayedX] != null) {
                    labels[i][j].setOpacity(1);
                    labels[i][j].setText(((MapObject) gameMap.map[displayedY][displayedX]).showInfo());
                } else
                    labels[i][j].setText("");
            }
        }
        playerPosX_label.setText("" + player.getPosX());
        playerPosY_label.setText("" + player.getPosY());
        uniformLabel.setText(player.getEquipment().getUniform().getName());
        weaponLabel.setText(player.getEquipment().getWeapon().getName());
        hpLabel.setText("" + player.getStat("health") + "/" + player.getMaxStat("health"));
        hpBar.setProgress(((double) player.getStat("health")) / player.getMaxStat("health"));
        if (!player.isAlive())
            gameOver();
    }

    private boolean checkFront(Character player) {
        if (isInBound(player.getFrontX(), player.getFrontY()) && gameMap.map[player.getFrontY()][player.getFrontX()] != null)
            return ((MapObject) gameMap.map[player.getFrontY()][player.getFrontX()]).getPresence();
        else
            return false;
    }

    private String activate(Activate act, Character character) {
        String result = act.activate(character);
        if (result.contains("destroyed")) {
            gameMap.map[character.getFrontY()][character.getFrontX()] = null;
        }
        return result;
    }

    private boolean isInBound(int x, int y) {
        if (x >= 0 && x < mapSizeX && y >= 0 && y < mapSizeY) {
            return true;
        } else return false;
    }

    private void fight(Character player, Character enemy) {
        int i = 1;
        while (player.getStat("health") > 0 && enemy.getStat("health") > 0) {
            actionInfo.setText(i + ". Your health: " + player.getStat("health") + "\n"
                    + "   Enemy's health: " + enemy.getStat("health"));
            if (enemy.getStat("energy") >= enemy.getTechnique(0).getStat("cost")) {
                player.changeCurStat("health",
                        -(enemy.getStat("strength")
                                + enemy.getEquipment().getWeapon().getDamage()
                                + (enemy.getTechnique(0).getStat("accuracy") / 100)
                                * enemy.getTechnique(0).getStat("damage"))
                                * (1 - player.getEquipment().getUniform().getDefense() / 50));
            } else {
                player.changeCurStat("health",
                        -(enemy.getStat("strength")
                                + enemy.getEquipment().getWeapon().getDamage())
                                * (1 - player.getEquipment().getUniform().getDefense() / 50));
            }
            if (player.getStat("energy") >= player.getTechnique(0).getStat("cost")) {
                enemy.changeCurStat("health",
                        -(player.getStat("strength")
                                + enemy.getEquipment().getWeapon().getDamage()
                                + (player.getTechnique(0).getStat("accuracy") / 100)
                                * player.getTechnique(0).getStat("damage"))
                                * (1 - enemy.getEquipment().getUniform().getDefense() / 50));
            } else {
                enemy.changeCurStat("health",
                        -(player.getStat("strength")
                                + player.getEquipment().getWeapon().getDamage())
                                * (1 - enemy.getEquipment().getUniform().getDefense() / 50));
            }
            i++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            update();
        }
    }

    private boolean isControlLocked = true;
    private boolean isStarted = false;
    @FXML
    private void startButton(ActionEvent event) {
        setTest();
        for (int i = 0; i < gridSizeY; i++) {
            for (int j = 0; j < gridSizeX; j++) {
                labels[i][j] = new Label("");
                labels[i][j].setWrapText(true);
            }
        }
        firstInitialization();
        update();
        isControlLocked = false;
        isStarted = true;
    }

    private boolean inv_win_is_toggled = false;

    private void toggleInventoryWindow() {
        if (inv_win_is_toggled) {
            for (int i = 0; i < player.getIventory().size(); i++) {
                String itemName = ((ShowInfo) player.getIventory().get(i)).showInfo();
                Button temp = new Button(itemName);
                temp.setOnAction(event -> {
                            ShowInfo item = (ShowInfo) player.getItem((((Button) event.getSource()).getText()));
                            System.out.println("trying to equip " + item.showInfo());
                            if (player.equipItem(item)) {
                                invItemsPane.getChildren().clear();
                                toggleInventoryWindow();
                            }
                        }
                );
                temp.setFocusTraversable(false);
                invItemsPane.getChildren().add(temp);
            }
            invPane.setVisible(true);
        } else {
            invItemsPane.getChildren().clear();
            invPane.setVisible(false);
        }

    }

    boolean gameMenuIsToggled = false;

    public void continueButton() {
        gameMenuIsToggled = !gameMenuIsToggled;
        isControlLocked = false;
        gameMenuPane.setVisible(false);
    }

    public void returnToMenu(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu-scene.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Stage stage = (Stage) (((Button) event.getSource()).getScene()).getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("/css/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void toggleGameMenu() {
        if (gameMenuIsToggled) {
            isControlLocked = true;
            gameMenuPane.setVisible(true);
        } else {
            gameMenuPane.setVisible(false);
        }
    }


    @FXML
    public void keyListener(KeyEvent event) {
        int steps = 1;
        if (player.isAlive() && !isControlLocked && isStarted) {
            switch (event.getCode()) {
                case W:
                    if (isObstaclePresent("up", steps, player)) {
                        steps = player.getPosY() - obstacleY - 1;
                    }
                    swap(player, player.getPosY() - steps, player.getPosX());
                    player.move("up", steps, mapSizeX, mapSizeY);
                    break;
                case D:
                    if (isObstaclePresent("right", steps, player)) {
                        steps = obstacleX - player.getPosX() - 1;
                    }
                    swap(player, player.getPosY(), player.getPosX() + steps);
                    player.move("right", steps, mapSizeX, mapSizeY);
                    break;
                case S:
                    if (isObstaclePresent("down", steps, player)) {
                        steps = obstacleY - player.getPosY() - 1;
                    }
                    swap(player, player.getPosY() + steps, player.getPosX());
                    player.move("down", steps, mapSizeX, mapSizeY);
                    break;
                case A:
                    if (isObstaclePresent("left", steps, player)) {
                        steps = player.getPosX() - obstacleX - 1;
                    }
                    swap(player, player.getPosY(), player.getPosX() - steps);
                    player.move("left", steps, mapSizeX, mapSizeY);
                    break;
                case E:
                    if (checkFront(player)) {
                        actionInfo.setText(activate((MapObject) gameMap.map[player.getFrontY()][player.getFrontX()], player));
                    }
                    break;
                case TAB:
                    inv_win_is_toggled = !inv_win_is_toggled;
                    toggleInventoryWindow();
                    break;
                case F:
                    fight(player, (Character) gameMap.map[player.getFrontY()][player.getFrontX()]);
                    break;
                case ESCAPE:
                    gameMenuIsToggled = !gameMenuIsToggled;
                    isControlLocked = false;
                    toggleGameMenu();
                    break;
                default:
                    break;
            }
            update();
        } else {
            switch (event.getCode()) {
                case ESCAPE:
                    gameMenuIsToggled = !gameMenuIsToggled;
                    isControlLocked = false;
                    toggleGameMenu();
                    break;
                default:
                    break;
            }
        }
    }
}

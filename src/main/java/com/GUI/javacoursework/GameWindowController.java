package com.GUI.javacoursework;

import com.engine.javacoursework.*;
import com.engine.javacoursework.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameWindowController {
    private int mapSizeX = 100, mapSizeY = 100;
    private GameMap gameMap = new GameMap(mapSizeY, mapSizeX);
    private int obstacleX, obstacleY, actionChoice, directionOfMoving, equipmentChoice;
    private Character player = new Character();
    private String filename = "";
    private FileWriter fileSession;
    private Quests quests = new Quests();
    private boolean firstStart = true;
    private Scanner s = new Scanner(System.in);
    private FileOutputStream saveFilePlayer = null;
    private FileOutputStream saveFileMap = null;
    private FileOutputStream saveFileQuests = null;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;

    public void setTest() {
        System.out.println("x: " + player.getPosX() + " y: " + player.getPosY());
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

    private static int gridSizeX = 5, gridSizeY = 5;
    private int centreX = Math.floorDiv(gridSizeX, 2), centreY = Math.floorDiv(gridSizeY, 2);
    @FXML
    private Pane mapObjectsPane;
    private GridPane mapObjectsGridPane;
    @FXML
    private Pane baseMapPane;
    private Label[][] labels = new Label[gridSizeY][gridSizeX];
    @FXML
    private Label actionInfo;
    @FXML
    private ProgressBar hpBar;
    @FXML
    private Label playerPosX_label;
    @FXML
    private Label playerPosY_label;
    @FXML
    Button startButton;
    @FXML
    TextFlow questText;

    //    {
//        gridPane = new GridPane();
//        playerPosX_label = new Label();
//        playerPosY_label = new Label();
//        hpBar = new ProgressBar();
//        actionInfo = new Label();
//        setTest();
//        for (int i = 0; i < gridSizeY; i++) {
//            for (int j = 0; j < gridSizeX; j++) {
//                labels[i][j] = new Label();
//                labels[i][j].minHeight(150);
//                labels[i][j].setFont(new Font("Arial", 14));
//            }
//        }
//        firstInitialization();
//
//        update();
//    }
    private void outputInfo(ShowInfo aObject) {
        aObject.showInfo();
    }

    private void swap(MapObject first, int Y, int X) {
        gameMap.map[Y][X] = first;
        gameMap.map[first.getPosY()][first.getPosX()] = null;
    }

    private boolean isObstaclePresent(String direction, int steps, Character player) {
        if (direction.equals("up")) {
            for (int i = player.getPosY() - 1; i >= player.getPosY() - steps; i--) {
                if (i < 0 || gameMap.map[i][player.getPosX()] != null && ((MapObject) gameMap.map[i][player.getPosX()]).getPresence()) {
                    if (i < 0)
                        System.out.println("trying to get out of map");
                    obstacleY = i;
                    obstacleX = player.getPosX();
                    return true;
                }
            }
        } else if (direction.equals("down")) {
            for (int i = player.getPosY() + 1; i <= player.getPosY() + steps; i++) {
                if (i >= mapSizeY || gameMap.map[i][player.getPosX()] != null && ((MapObject) gameMap.map[i][player.getPosX()]).getPresence()) {
                    if (i >= mapSizeY)
                        System.out.println("trying to get out of map");
                    obstacleY = i;
                    obstacleX = player.getPosX();
                    return true;
                }
            }
        } else if (direction.equals("right")) {
            for (int i = player.getPosX() + 1; i <= player.getPosX() + steps; i++) {
                if (i >= mapSizeX || gameMap.map[player.getPosY()][i] != null && ((MapObject) gameMap.map[player.getPosY()][i]).getPresence()) {
                    if (i >= mapSizeX)
                        System.out.println("trying to get out of map");
                    obstacleX = i;
                    obstacleY = player.getPosY();
                    return true;
                }
            }
        } else if (direction.equals("left")) {
            for (int i = player.getPosX() - 1; i >= player.getPosX() - steps; i--) {
                if (i < 0 || gameMap.map[player.getPosY()][i] != null && ((MapObject) gameMap.map[player.getPosY()][i]).getPresence()) {
                    if (i < 0)
                        System.out.println("trying to get out of map");
                    obstacleX = i;
                    obstacleY = player.getPosY();
                    return true;
                }
            }
        }
        return false;
    }

    private void firstInitialization() {
        mapObjectsGridPane = new GridPane();
        mapObjectsGridPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.0))));
        for (int i = 0; i < gridSizeX; i++) {
            mapObjectsGridPane.getColumnConstraints().add(new ColumnConstraints(150));
            for (int j = 0; j < gridSizeY; j++) {
                mapObjectsGridPane.add(labels[j][i], i, j);
            }
        }
        mapObjectsPane.getChildren().add(mapObjectsGridPane);
    }

    private void update() {
        if (player.getStat("health") < 1) {
//            System.out.println("You have died, you can load your save or start the new game :-/\nили заплати мне $10 tg: @ooxyygeen");
        }
        if (player.getPosY() == 4 && player.getPosX() == 20) {
            player.addItem(new FingerOfSukuna());
            quests.HELP();
        }
        quests.updateQuestsStatus(player);
        questText.getChildren().clear();
        questText.getChildren().add(new Label(quests.showMainQuest()));

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
                    labels[i][j].setOpacity(0);
            }
        }
        playerPosX_label.setText("" + player.getPosX());
        playerPosY_label.setText("" + player.getPosY());
        hpBar.setProgress(((double) player.getStat("health")) / player.getMaxStat("health"));
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

    private boolean fight(Character player, Character enemy) {
        int i = 1;
        while (player.getStat("health") > 0 && enemy.getStat("health") > 0) {
            System.out.println(i + ". Your health: " + player.getStat("health") + "\n"
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
        }
        if (player.getStat("health") < 1) {
            System.out.println("You have died in the battle )-;");
            System.out.println("Your last words are:\n" + player.getDeathMessage());
            return false;
        } else {
            System.out.println("You have won the battle, congrats! ;-)");
            System.out.println("Your enemy last words are:\n" + enemy.getDeathMessage());
            return true;
        }

    }

    public void startButton(ActionEvent event) {
        setTest();
        for (int i = 0; i < gridSizeY; i++) {
            for (int j = 0; j < gridSizeX; j++) {
                labels[i][j] = new Label();
//                labels[i][j].minHeight(150);
                labels[i][j].setFont(new Font("Arial", 32));
            }

        }
        firstInitialization();
        baseMapPane.getChildren().add(new ImageView(new Image("file:src/main/java/com/GUI/javacoursework/textures/tileable_grass.png")));
        baseMapPane.setVisible(true);
        update();
        startButton.setVisible(false);
    }
//    {
//        try {
//            fileSession = new FileWriter("lastSession.txt", false);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Something went wrong...");
//            System.exit(2);
//        }
//    }

    @FXML
    public void keyListener(KeyEvent event) {
        int steps = 1;
        actionInfo.setText(event.getCode().getChar());
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
            default:
                break;
        }
        update();
    }
}

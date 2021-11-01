import java.io.*;
import java.util.Scanner;

public class Manager {
    static MapObject[][] map = new MapObject[100][100];
    static int steps, obstacleX, obstacleY, actionChoice, directionOfMoving;
    static Character player = new Character();
    static String filename;
    public static void main(String[] args) throws FileNotFoundException {
//        Character player = new Character();
        Scanner s = new Scanner(System.in);
//        String actionChoice, filename, directionOfMoving;
        OutputStream saveFile;
        int menuChoice;
        while (true) {
            System.out.println("""
                    Welcome to Jujutsu Kaisen game.

                    1. New game
                    2. Continue
                    3. Load game
                    4. Save game
                    5. Help
                    6. Exit


                    Enter your choice:""");
            menuChoice = s.nextInt();
            switch (menuChoice) {
                case 1 :
                    System.out.println("Enter session name");
                    filename = s.nextLine();
                    saveFile = new FileOutputStream(filename + ".dat");
                    break;
                case 2 :
                    // continue game, if it's started or start last session
                    game();
                    break;
                case 3 :
                    // choose session
                    // read data from file
                    // getter from MapObject
                    game();
                    break;
                case 4 :
                    // write data to file from current session
                    break;
                case 5 :
                    // instruction to the game(keyboard, goals, tips)
                    break;
                case 6 :
                    // close savefile
                    // exit program
                    return;
                default :
                    System.out.println("Something went wrong. Try again.");
                    break;
            }

        }
    }

    public static void game() {
        Scanner s = new Scanner(System.in);
        while (true) {
            update();
            System.out.println("""
                    1. Move
                    2. Interact
                    3. Menu

                    Enter your choice:""");
            System.out.println("What is your option?");
            actionChoice = s.nextInt();
            switch(actionChoice) {
                case 1 :
                    obstacleX = -1;
                    obstacleY = -1;
                    System.out.println("""
                    1. Up
                    2. Right
                    3. Down
                    4. Left

                    Enter your choice:""");
                    System.out.println("Where do you want to go?");
                    directionOfMoving = s.nextInt();
                    System.out.println("How far do you want to go?"); // move 0 steps for changing the direction
                    steps = s.nextInt();
                    switch (directionOfMoving) {
                        case 1:
                            if (isObstaclePresent("up", steps, player)) {
                                steps = player.getPosY() - obstacleY - 1;
                            }
                            swap(player, map[player.getPosY() - steps][player.getPosX()]);
                            player.move("up", steps);
                            break;
                        case 2:
                            if (isObstaclePresent("right", steps, player)) {
                                steps = obstacleX - player.getPosX() - 1;
                            }
                            swap(player, map[player.getPosY()][player.getPosX() + steps]);
                            player.move("right", steps);
                            break;
                        case 3:
                            if (isObstaclePresent("down", steps, player)) {
                                steps = obstacleY - player.getPosY() - 1;
                            }
                            swap(player, map[player.getPosY() + steps][player.getPosX()]);
                            player.move("down", steps);
                            break;
                        case 4:
                            if (isObstaclePresent("left", steps, player)) {
                                steps = player.getPosX() - obstacleX - 1;
                            }
                            swap(player, map[player.getPosY()][player.getPosX() - steps]);
                            player.move("left", steps);
                            break;
                        default:
                            System.out.println("Try choosing moving option again");
                            break;
                    }
                    break;
                case 2 :
                    // interact
                    checkFront(player);
                    break;
                case 3 :
                    return;
                default :
                    System.out.println("Try choosing action option again");
                    break;
            }
        }
    }

    public static void outputInfo(ShowInfo aObject) {
        aObject.showInfo();
    }

    public static void swap(MapObject first, MapObject second){
        MapObject temp = first;
        map[first.getPosY()][first.getPosX()] = map[second.getPosY()][second.getPosX()];
        map[second.getPosY()][second.getPosX()] = temp;
    }
    public static boolean isObstaclePresent(String direction, int steps, Character player) {
        if (direction.equals("up")) {
            for (int i = player.getPosY(); i >= player.getPosY() - steps; i--) {
                if (map[i][player.getPosX()].getPresence()) {
                    obstacleY = i;
                    return true;
                }
            }
        } else if (direction.equals("down")) {
            for (int i = player.getPosY(); i <= player.getPosY() + steps; i++) {
                if (map[i][player.getPosX()].getPresence()) {
                    obstacleY = i;
                    return true;
                }
            }
        } else if (direction.equals("right")) {
            for (int i = player.getPosX(); i <= player.getPosX() + steps; i++) {
                if (map[player.getPosY()][i].getPresence()) {
                    obstacleX = i;
                    return true;
                }
            }
        } else if (direction.equals("left")) {
            for (int i = player.getPosX(); i >= player.getPosX() - steps; i--) {
                if (map[player.getPosY()][i].getPresence()) {
                    obstacleX = i;
                    return true;
                }
            }
        }
        return false;
    }

    public static void update(){
        // checkers for events
        if (checkFront(player)) {
            System.out.println("There is " + map[player.getFrontY()][player.getFrontX()].getName() + " in front of you");
        }
    }

    public static boolean checkFront(Character player) {
        return map[player.getFrontY()][player.getFrontX()].getPresence();
    }
}

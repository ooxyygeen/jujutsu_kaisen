import java.io.*;
import java.util.Scanner;

public class Manager {
    static int mapSizeX = 100, mapSizeY = 100;
    static MapObject[][] map = new MapObject[mapSizeY][mapSizeX];
    static int steps, obstacleX, obstacleY, actionChoice, directionOfMoving;
    static Character player = new Character();
    static String filename;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(System.in);
        OutputStream saveFile;
        setTest();
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
            System.out.println("Your x position is " + player.getPosX());
            System.out.println("Your y position is " + player.getPosY());
            System.out.println("Your direction is " + player.getDirection());
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
                            swap(player, player.getPosY() - steps, player.getPosX());
                            player.move("up", steps, mapSizeX, mapSizeY);
                            break;
                        case 2:
                            if (isObstaclePresent("right", steps, player)) {
                                steps = obstacleX - player.getPosX() - 1;
                            }
                            swap(player, player.getPosY(),player.getPosX() + steps);
                            player.move("right", steps, mapSizeX, mapSizeY);
                            break;
                        case 3:
                            if (isObstaclePresent("down", steps, player)) {
                                steps = obstacleY - player.getPosY() - 1;
                            }
                            swap(player, player.getPosY() + steps,player.getPosX());
                            player.move("down", steps, mapSizeX, mapSizeY);
                            break;
                        case 4:
                            if (isObstaclePresent("left", steps, player)) {
                                steps = player.getPosX() - obstacleX - 1;
                            }
                            swap(player, player.getPosY(),player.getPosX() - steps);
                            player.move("left", steps, mapSizeX, mapSizeY);
                            break;
                        default:
                            System.out.println("Try choosing moving option again");
                            break;
                    }
                    break;
                case 2 :
                    if (checkFront(player)) {
                        activate(map[player.getFrontY()][player.getFrontX()], player);
                    }
                    else
                        System.out.println("There is nothing in front of you!");
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

    public static void swap(MapObject first, int Y, int X){
        map[Y][X] = first;
        map[first.getPosY()][first.getPosX()] = null;
    }
    public static boolean isObstaclePresent(String direction, int steps, Character player) {
        if (direction.equals("up")) {
            for (int i = player.getPosY() - 1; i >= player.getPosY() - steps; i--) {
                if (map[i][player.getPosX()] != null && map[i][player.getPosX()].getPresence()) {
                    obstacleY = i;
                    return true;
                }
            }
        } else if (direction.equals("down")) {
            for (int i = player.getPosY() + 1; i <= player.getPosY() + steps; i++) {
                if (map[i][player.getPosX()] != null && map[i][player.getPosX()].getPresence()) {
                    obstacleY = i;
                    return true;
                }
            }
        } else if (direction.equals("right")) {
            for (int i = player.getPosX() + 1; i <= player.getPosX() + steps; i++) {
                if (map[player.getPosY()][i] != null && map[player.getPosY()][i].getPresence()) {
                    obstacleX = i;
                    return true;
                }
            }
        } else if (direction.equals("left")) {
            for (int i = player.getPosX() - 1; i >= player.getPosX() - steps; i--) {
                if (map[player.getPosY()][i] != null && map[player.getPosY()][i].getPresence()) {
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
        if (map[player.getFrontY()][player.getFrontX()] != null)
            return map[player.getFrontY()][player.getFrontX()].getPresence();
        else
            return false;
    }

    public static void activate(Activate act, Character character){
        String result = act.activate(character);
            if (result.contains("Exception")){
                System.out.println(result);
            }
            else {
                /*тут треба шось придумать, шоб якось видаляти об'єкти і т.д.*/
            }
    }

    public static void setTest(){
        map[0][0] = player;
        map[7][2] = new PlateWithText("Congrats, you have moved!");
    }
}

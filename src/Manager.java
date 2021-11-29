import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    static int mapSizeX = 100, mapSizeY = 100;
    static MapObject[][] map = new MapObject[mapSizeY][mapSizeX];
    static int steps, obstacleX, obstacleY, actionChoice, directionOfMoving;
    static Character player = new Character();
    static String filename = "";
    static FileWriter fileSession;

    static {
        try {
            fileSession = new FileWriter("lastSession.txt", false);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went wrong...");
            System.exit(2);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        FileOutputStream saveFile = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        setTest();
        int menuChoice, exitChoice;
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
                case 1:
                    System.out.println("Enter session name");
                    s.nextLine();
                    filename = s.nextLine();
                    while (filename == null && filename.trim().isEmpty()) {
                        System.out.println("Please, enter the session name.");
                        s.nextLine();
                        filename = s.nextLine();
                    }
                    saveFile = new FileOutputStream(filename + ".dat");
                    game();
                    break;
                case 2:
                    // continue game, if it's started or start last session
                    if(filename == null && filename.trim().isEmpty()) {
                        System.out.println("Please, choose the session first.");
                        break;
                    }
                    game();
                    break;
                case 3:
                    // choose session
                    System.out.println("Enter name of your session:");
                    s.nextLine();
                    filename = s.nextLine();
                    // read data from file
                    try {
                        ois = new ObjectInputStream(new FileInputStream(filename + ".dat"));
                        player = (Character) ois.readObject();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("Something went wrong. Try again:(");
                        break;
                    }
                    game();
                    break;
                case 4:
                    // write data to file from current session
                    try {
                    oos = new ObjectOutputStream(saveFile);
                    oos.writeObject(player);
                    oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Something went wrong. Try again:(");
                        break;
                    }
                    break;
                case 5:
                    // instruction to the game(keyboard, goals, tips)
                    break;
                case 6:
                    System.out.println("""
                    Are you sure you want to exit? Unsaved experience will be deleted.
                    Exit (0)
                    Menu (1)
                    Enter your choice:""");
                    exitChoice = s.nextInt();
                    switch (exitChoice) {
                        case 0:
                            fileSession.write(filename);
                            fileSession.flush();
                            return;
                        case 1:
                            break;
                        default:
                            System.out.println("Something went wrong. Try again.");
                            break;
                    }
                default:
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
                    3. Fight
                    4. Menu

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
                    if (checkFront(player)) {
                        if (map[player.getFrontY()][player.getFrontX()].getClass() == player.getClass()) {
                            boolean result = fight(player, (Character) map[player.getFrontY()][player.getFrontX()]);
                            if (!result)
                                return;
                        }
                        else {
                            System.out.println("There is nobody in front of you!");
                        }
                    }
                    else {
                        System.out.println("There is nobody in front of you!"); // nothing получается
                    }
                    break;
                case 4 :
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
                if (i < 0 || map[i][player.getPosX()] != null && map[i][player.getPosX()].getPresence()) {
                    obstacleY = i;
                    obstacleX = player.getPosX();
                    return true;
                }
            }
        } else if (direction.equals("down")) {
            for (int i = player.getPosY() + 1; i <= player.getPosY() + steps; i++) {
                if (i >= mapSizeY || map[i][player.getPosX()] != null && map[i][player.getPosX()].getPresence()) {
                    obstacleY = i;
                    obstacleX = player.getPosX();
                    return true;
                }
            }
        } else if (direction.equals("right")) {
            for (int i = player.getPosX() + 1; i <= player.getPosX() + steps; i++) {
                if (i >= mapSizeX || map[player.getPosY()][i] != null && map[player.getPosY()][i].getPresence()) {
                    obstacleX = i;
                    obstacleY = player.getPosY();
                    return true;
                }
            }
        } else if (direction.equals("left")) {
            for (int i = player.getPosX() - 1; i >= player.getPosX() - steps; i--) {
                if (i < 0 || map[player.getPosY()][i] != null && map[player.getPosY()][i].getPresence()) {
                    obstacleX = i;
                    obstacleY = player.getPosY();
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
        if (isInBound(player.getFrontX(),player.getFrontY()) && map[player.getFrontY()][player.getFrontX()] != null)
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

    public static boolean isInBound(int x, int y){
        if (x >= 0 && x < mapSizeX && y >= 0 && y < mapSizeY){
            return true;
        }
        else return false;
    }

    public static boolean fight(Character player, Character enemy){
        int i = 1;
        while (player.getStats().getStat("health") > 0 && enemy.getStats().getStat("health") > 0){
            System.out.println(i + ". Your health: " + player.getStats().getStat("health") + "\n"
                    + "   Enemy's health: " + enemy.getStats().getStat("health"));
            if (enemy.getStats().getStat("energy") >= enemy.getTechnique(0).getStat("cost")) {
                player.getStats().change("health",
                - (enemy.getStats().getStat("strength")
                + enemy.getEquipment().getWeapon().getDamage()
                + (enemy.getTechnique(0).getStat("accuracy") / 100)
                * enemy.getTechnique(0).getStat("damage"))
                * (1 - player.getEquipment().getUniform().getDefense() / 50));
            }
            else{
                player.getStats().change("health",
                - (enemy.getStats().getStat("strength")
                + enemy.getEquipment().getWeapon().getDamage())
                * (1 - player.getEquipment().getUniform().getDefense() / 50));
            }
            if (player.getStats().getStat("energy") >= player.getTechnique(0).getStat("cost")) {
                enemy.getStats().change("health",
                - (player.getStats().getStat("strength")
                + enemy.getEquipment().getWeapon().getDamage()
                + (player.getTechnique(0).getStat("accuracy") / 100)
                * player.getTechnique(0).getStat("damage"))
                * (1 - enemy.getEquipment().getUniform().getDefense() / 50));
            }
            else {
                enemy.getStats().change("health",
                - (player.getStats().getStat("strength")
                + player.getEquipment().getWeapon().getDamage())
                * (1 - enemy.getEquipment().getUniform().getDefense() / 50));
            }
            i++;
        }
        if (player.getStats().getStat("health") < 1){
            System.out.println("You have died in the battle )-;");
            return false;
        }
        else{
            System.out.println("You have won the battle, congrats! (-;");
            return true;
        }

    }
// .equip("uniform", new Uniform("Jujutsu Tech uniform", 10))
//    "Ten Shadows Technique", 1, 20, 0, 50, 10
    public static void setTest(){
        map[0][0] = player;
        map[7][2] = new PlateWithText("Congrats, you have moved!");
        Character fushi = new Character(2, 8, mapSizeX, mapSizeY, "Megumi Fushiguro", new Inventory(),
                new Equipment(),
                new ArrayList<Technique>(),
                new Stats(35, 80, 20, 50, 100, 100));
        fushi.addTechnique("Ten Shadows Technique", 1, 20, 0, 50, 10);
        map[8][1] = fushi;

    }
}

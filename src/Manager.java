import java.util.Scanner;

public class Manager {
    static MapObject[][] map = new MapObject[100][100];
    static int steps, obstacleX, obstacleY;

    public static void main(String[] args) {
        Character player = new Character();
        Scanner s = new Scanner(System.in);
        String actionChoice, directionOfMovnig;
        while (true) {
            System.out.println("What is your option?");
            actionChoice = s.nextLine();
            switch(actionChoice) {
                case "move" :
                    obstacleX = -1;
                    obstacleY = -1;
                    System.out.println("Where do you want to go?");
                    directionOfMovnig = s.nextLine();
                    switch (directionOfMovnig) {
                        case "up":
                            System.out.println("How far do you want to go?");
                            steps = s.nextInt();
                            if (isObstaclePresent("up", steps, player)) {
                                steps = player.getPosY() - obstacleY - 1;
                            }
                            swap(player, map[player.getPosY() - steps][player.getPosX()]);
                            player.move("up", steps);
                            break;
                        case "down":
                            System.out.println("How far do you want to go?");
                            steps = s.nextInt();
                            if (isObstaclePresent("down", steps, player)) {
                                steps = obstacleY - player.getPosY() - 1;
                            }
                            swap(player, map[player.getPosY() + steps][player.getPosX()]);
                            player.move("down", steps);
                            break;
                        case "right":
                            System.out.println("How far do you want to go?");
                            steps = s.nextInt();
                            if (isObstaclePresent("right", steps, player)) {
                                steps = obstacleX - player.getPosX() - 1;
                            }
                            swap(player, map[player.getPosY()][player.getPosX() + steps]);
                            player.move("right", steps);
                            break;
                        case "left":
                            System.out.println("How far do you want to go?");
                            steps = s.nextInt();
                            if (isObstaclePresent("left", steps, player)) {
                                steps = player.getPosX() - obstacleX - 1;
                            }
                            swap(player, map[player.getPosY()][player.getPosX() - steps]);
                            player.move("left", steps);
                            break;
                        default:
                            System.out.println("Try choosing moving option again");
                    }
                    break;
                default:
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

    public void update(){

    }
    public static boolean checkFront(Character player){
        if (player.getDirection().equals("right")){
            return map[player.getPosY()][player.getPosX() + 1].getPresence();
        }
        else if (player.getDirection().equals("left")){
            return map[player.getPosY()][player.getPosX() - 1].getPresence();
        }
        else if (player.getDirection().equals("up")){
            return map[player.getPosY() - 1][player.getPosX()].getPresence();
        }
        else if (player.getDirection().equals("down")){
            return map[player.getPosY() + 1][player.getPosX()].getPresence();
        }
        return false;
    }
}

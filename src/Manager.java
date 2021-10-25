import java.util.Scanner;

public class Manager {
    public static void main(String[] args) {
        MapObject[][] map = new MapObject[100][100];
        Character player = new Character();
        Scanner s = new Scanner(System.in);
        String choice;
        int steps;
        while(true){
            choice = s.nextLine();
            switch(choice){
                case "up" :
                    System.out.println("How far do you want to go?");
                    steps = s.nextInt();
            }
        }
    }
}

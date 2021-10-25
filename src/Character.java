import java.util.ArrayList;

public class Character extends MapObject {
    private String name;
    private Inventory inventory;
    private Equipment equipment;
    private Stats stats;
    private ArrayList <Technique> techniques = new ArrayList<Technique>();
    private Technique domainExpansion;

    private String direction;

    public Character() {
        setPosX(0);
        setPosY(0);
        this.name = "Chelikbaser";
        this.inventory = new Inventory();
        this.equipment = new Equipment();
        this.stats = new Stats();
        this.direction = "down";
    }

    private void move(String aDirection, int aStepsNum) {
        switch (aDirection) {
            case "up":
                setPosY(getPosY() - aStepsNum);
                break;
            case "right":
                setPosX(getPosX() + aStepsNum);
                break;
            case "down":
                setPosY(getPosY() + aStepsNum);
                break;
            case "left":
                setPosX(getPosX() - aStepsNum);
                break;
            default:
//                System.out.println("iseemolodoypridurak");
                break;
        }
    }
}

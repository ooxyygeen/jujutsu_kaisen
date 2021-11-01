import java.util.ArrayList;

public class Character extends MapObject {
    private String name;
    private Inventory inventory;
    private Equipment equipment;
    private Stats stats;
    private ArrayList <Technique> techniques = new ArrayList<Technique>();
    private Technique domainExpansion;

    private String direction;

    public String getDirection(){
        return this.direction;
    }

    public void setDirection(String aDirection){
        this.direction = aDirection;
    }

    public Character() {
        super.setPresence(true);
        setPosX(0);
        setPosY(0);
        this.name = "Chelikbaser";
        this.inventory = new Inventory();
        this.equipment = new Equipment();
        this.stats = new Stats();
        this.direction = "down";
    }

    public void move(String aDirection, int aStepsNum) {
        boolean obstacle = false;
        this.direction = aDirection;
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
                System.out.println("iseemolodoypridurak");
                break;
        }
    }

    public int getFrontX() {
        if (direction.equals("up") || direction.equals("down")) {
            return this.getPosX();
        } else if (direction.equals("right")) {
            return this.getPosX() + 1;
        } else {
            return this.getPosX() - 1;
        }
    }

    public int getFrontY() {
        if (direction.equals("right") || direction.equals("left")) {
            return this.getPosY();
        } else if (direction.equals("down")) {
            return this.getPosY() + 1;
        } else {
            return this.getPosY() - 1;
        }
    }
}

import java.io.Serializable;
import java.util.ArrayList;

public class Character<Object> extends MapObject implements Serializable {
    private Inventory<Object> inventory;
    private Equipment equipment;
    private Stats stats;
    private ArrayList <Technique> techniques;
    private Technique domainExpansion;
    private String direction;
    private ArrayList <Shikigami> shikigamis;

    public Character() {
        super.setPresence(true);
//        super.setCoordinates(0,0);
        super.setPosX(0,1);
        super.setPosY(0,1);
        super.setName("Chelikbaser");
        this.inventory = new Inventory<>();
        this.equipment = new Equipment();
        this.techniques = new ArrayList<>();
        this.techniques.add(new Technique("Fists of Van Darkholme", 10, 1, 0, 20, 10));
        this.stats = new Stats();
        this.direction = "down";
    }
    public Character(int posX, int posY, int mapSizeX, int mapSizeY, String newName,
                      Inventory<Object> newInventory, Equipment<Object> newEquipment,
                      ArrayList<Technique> newTechniques, Stats newStats) {
        super.setPresence(true);
//        super.setCoordinates(0,0);
        super.setPosX(posX,mapSizeX);
        super.setPosY(posY,mapSizeY);
        super.setName(newName);
        this.inventory = newInventory;
        this.equipment = newEquipment;
        this.techniques = newTechniques;
        this.stats = newStats;
        this.direction = "down";
    }
    public String getDirection(){
        return this.direction;
    }
    public void setDirection(String aDirection){
        this.direction = aDirection;
    }
    public String changeStats(String aStat, int aValue){
        return this.stats.change(aStat, aValue);
    }
    public String addItem(Object item){
        return this.inventory.addElement(item);
    }
    public boolean findItem(String item){
        return this.inventory.findElement(item);
    }
    public String move(String aDirection, int aStepsNum, int mapSizeX, int mapSizeY) {
        this.direction = aDirection;
        switch (aDirection) {
            case "up":
                this.setPosY(this.getPosY() - aStepsNum, mapSizeY);
                break;
            case "right":
                this.setPosX(this.getPosX() + aStepsNum, mapSizeX);
                break;
            case "down":
                this.setPosY(this.getPosY() + aStepsNum, mapSizeY);
                break;
            case "left":
                this.setPosX(this.getPosX() - aStepsNum, mapSizeX);
                break;
            default:
                return "IncorrectDirectionException";
        }
        return aDirection;
    }
    public Technique getTechnique(int index){
        if (this.techniques.size() - 1 >= index)
            return this.techniques.get(index);
        return null;
    }
    public void addTechnique(String aName, int aDamage, int aRange, int aHeal, int aAccuracy, int aCost) {
        techniques.add(new Technique(aName, aDamage, aRange, aHeal, aAccuracy, aCost));
    }
    public Equipment getEquipment(){
        return this.equipment;
    }
    public Stats getStats(){
        return this.stats;
    }
    public int getFrontX() {
        if (this.direction.equals("up") || this.direction.equals("down")) {
            return this.getPosX();
        } else if (this.direction.equals("right")) {
            return this.getPosX() + 1;
        } else {
            return this.getPosX() - 1;
        }
    }
    public int getFrontY() {
        if (this.direction.equals("right") || this.direction.equals("left")) {
            return this.getPosY();
        } else if (this.direction.equals("down")) {
            return this.getPosY() + 1;
        } else {
            return this.getPosY() - 1;
        }
    }
}

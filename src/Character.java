import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Character<Object> extends MapObject implements Serializable {
    private Inventory<Object> inventory;
    private Equipment equipment;
    private Stats stats;
    private ArrayList <Technique> techniques;
    private Technique domainExpansion;
    private String direction;
    private ArrayList <Shikigami> shikigamis;
    private final boolean availabilityOfInnerSukuna;
    private boolean innerSukunaStatus;
    public Character() {
        super("Chelikbaser", 0, 0, true);
        this.availabilityOfInnerSukuna = false;
        this.inventory = new Inventory<>();
        this.equipment = new Equipment();
        this.techniques = new ArrayList<>();
        this.techniques.add(new Technique("Fists of Van Darkholme", 10, 1, 0, 20, 10));
        this.stats = new Stats();
        this.direction = "down";
    }
    public Character(String newName, int newPosY, int newPosX, int mapSizeY, int mapSizeX,
                     boolean newAvOfSukuna, Inventory<Object> newInventory,
                     Equipment<Object> newEquipment, ArrayList<Technique> newTechniques,
                     Stats newStats) {
        super(newName, newPosY, newPosX, true);
        this.availabilityOfInnerSukuna = newAvOfSukuna;
        this.inventory = newInventory;
        this.equipment = newEquipment;
        this.techniques = newTechniques;
        this.stats = newStats;
        this.direction = "down";
    }
    public Character(Character target){
        super(target.getName(), target.getPosY(), target.getPosX(), target.getPresence());
        this.availabilityOfInnerSukuna = target.availabilityOfInnerSukuna;
        this.inventory = target.inventory;
        this.equipment = target.equipment;
        this.techniques = target.techniques;
        this.stats = target.stats;
        this.direction = target.direction;
    }
    @Override
    public MapObject clone(){
        return new Character<>(this);
    }
    public String getDirection(){
        return this.direction;
    }
    public void setDirection(String aDirection){
        this.direction = aDirection;
    }
    public String changeCurStats(String aStat, int aValue){
        return this.stats.changeCurStat(aStat, aValue);
    }
    public String changeMaxStats(String aStat, int aValue){
        return this.stats.changeMaxStat(aStat, aValue);
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
    public int getStat(String aStat){
        return this.stats.getStat(aStat);
    }
    public int[] getAllStats(){
        return this.stats.getAllStats();
    }
    public String changeCurStat(String aStat, int aNum){
        return this.stats.changeCurStat(aStat, aNum);
    }
    public String changeMaxStat(String aStat, int aNum){
        return this.stats.changeMaxStat(aStat, aNum);
    }
    public String sukunaPower(){
        String message = this.stats.sukunaPower(inventory.countOfObjects(FingerOfSukuna.class));
        if (message.contains("deal"))
            this.innerSukunaStatus = true;
        else
            this.innerSukunaStatus = false;
        return message;
    }
    public boolean getAvailabilityOfInnerSukuna(){
        return this.availabilityOfInnerSukuna;
    }
    public boolean getInnerSukunaStatus(){
        return this.innerSukunaStatus;
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

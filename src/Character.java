import java.util.ArrayList;

public class Character {
    public Inventory inventory;
    public Equipment equipment;
    public Stats stats;
    public ArrayList <Technique> techniques = new ArrayList<Technique>();
    public DomainExpansion domainExpansion;

    public String direction;

    public Character() {
        inventory = new Inventory();
        equipment = new Equipment();
        stats = new Stats();
        direction = "down";
    }
}

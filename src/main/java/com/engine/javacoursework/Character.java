package com.engine.javacoursework;

import java.io.Serializable;
import java.util.ArrayList;

public class Character<Object extends ShowInfo> extends MapObject implements Serializable {
    private Inventory<Object> inventory;
    private Equipment equipment;
    private Stats stats;
    private ArrayList<Technique> techniques;
    private Technique domainExpansion;
    private String direction;
    private ArrayList<Shikigami> shikigamis;
    private final boolean availabilityOfInnerSukuna;
    private boolean innerSukunaStatus;
    private String DeathMessage;

    public Character() {
        super("Chelikbaser", 0, 0, true);
        this.availabilityOfInnerSukuna = false;
        this.inventory = new Inventory<>();
        this.equipment = new Equipment();
        this.techniques = new ArrayList<>();
        this.techniques.add(new Technique("Fists of Van Darkholme", 10, 1, 0, 20, 10));
        this.stats = new Stats();
        this.direction = "down";
        this.DeathMessage = "like sad, i feel like sad now ... i don't know why ..";
    }

    public Character(String newName, int newPosY, int newPosX, int mapSizeY, int mapSizeX,
                     boolean newAvOfSukuna, Inventory<Object> newInventory,
                     Equipment<Object> newEquipment, ArrayList<Technique> newTechniques,
                     Stats newStats, String aDeathMessage) {
        super(newName, newPosY, newPosX, true);
        this.availabilityOfInnerSukuna = newAvOfSukuna;
        this.inventory = newInventory;
        this.equipment = newEquipment;
        this.techniques = newTechniques;
        this.stats = newStats;
        this.direction = "down";
        this.DeathMessage = aDeathMessage;
    }

    public Character(Character<Object> target) {
        super(target.getName(), target.getPosY(), target.getPosX(), target.getPresence());
        this.availabilityOfInnerSukuna = target.availabilityOfInnerSukuna;
        this.inventory = target.inventory;
        this.equipment = target.equipment;
        this.techniques = target.techniques;
        this.stats = target.stats;
        this.direction = target.direction;
    }

    public boolean isAlive() {
        return this.stats.getStat("health") > 0;
    }

    @Override
    public MapObject clone() {
        return new Character<>(this);
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String aDirection) {
        this.direction = aDirection;
    }

    public String changeCurStats(String aStat, int aValue) {
        return this.stats.changeCurStat(aStat, aValue);
    }

    public String changeMaxStats(String aStat, int aValue) {
        return this.stats.changeMaxStat(aStat, aValue);
    }

    public String addItem(Object item) {
        return this.inventory.addElement(item);
    }

    public boolean findItem(String item) {
        return this.inventory.findElement(item);
    }

    public boolean equipItem(Object item) {
        if (item != null) {
            if (item.getClass() == Uniform.class) {
                this.inventory.addElement((Object) this.equipment.getUniform());
                this.equipment.equip("uniform", item);
                return true;
            } else if (item.getClass() == Weapon.class) {
                this.inventory.addElement((Object) this.equipment.getWeapon());
                this.equipment.equip("weapon", item);
                return true;
            } else
                return false;
        }
        return false;
    }

    public Object getItem(String nameOfItem) {
        return this.inventory.getItem(nameOfItem);
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

    public Technique getTechnique(int index) {
        if (this.techniques.size() - 1 >= index)
            return this.techniques.get(index);
        return null;
    }

    public void addTechnique(String aName, int aDamage, int aRange, int aHeal, int aAccuracy, int aCost) {
        techniques.add(new Technique(aName, aDamage, aRange, aHeal, aAccuracy, aCost));
    }

    public Equipment getEquipment() {
        return this.equipment;
    }

    public ArrayList<Object> getIventory() {
        return this.inventory.getInventory();
    }

    public String equipWeapon(String nameOfWeapon) {
        return equipment.equip("weapon", this.inventory.getItem(nameOfWeapon));
    }

    public String equipUniform(String nameOfUniform) {
        return equipment.equip("uniform", this.inventory.getItem(nameOfUniform));
    }

    public int getStat(String aStat) {
        return this.stats.getStat(aStat);
    }

    public int getMaxStat(String aStat) {
        return this.stats.getMaxStat(aStat);
    }

    public int[] getAllStats() {
        return this.stats.getAllStats();
    }

    public String changeCurStat(String aStat, int aNum) {
        return this.stats.changeCurStat(aStat, aNum);
    }

    public String changeMaxStat(String aStat, int aNum) {
        return this.stats.changeMaxStat(aStat, aNum);
    }

    public String sukunaPower() {
        String message = this.stats.sukunaPower(inventory.countOfObjects(FingerOfSukuna.class));
        if (message.contains("deal"))
            this.innerSukunaStatus = true;
        else
            this.innerSukunaStatus = false;
        return message;
    }

    public boolean getAvailabilityOfInnerSukuna() {
        return this.availabilityOfInnerSukuna;
    }

    public boolean getInnerSukunaStatus() {
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

    public String getDeathMessage() {
        return DeathMessage;
    }
}

import java.io.Serializable;

public class Weapon implements ShowInfo, Serializable {
    private final String name;
    private final int damage;

    public Weapon() {
        this.name = "DefaultWeaponName";
        this.damage = 0;
    }

    public Weapon(String aName, int aDamage) {
        this.name = aName;
        this.damage = aDamage;
    }
    @Override
    public String showInfo(){
        return "Name of the weapon: " + this.name + "\nDamage of the weapon: " + this.damage;
    }
    public int getDamage(){
        return this.damage;
    }
}

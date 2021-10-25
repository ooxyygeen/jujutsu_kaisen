public class Weapon {
    private String name;
    private int damage;

    public Weapon() {
        this.name = "DefaultWeaponName";
        this.damage = 0;
    }

    public Weapon(String aName, int aDamage) {
        this.name = aName;
        this.damage = aDamage;
    }
}

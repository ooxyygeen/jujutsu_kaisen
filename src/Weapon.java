public class Weapon {
    public String name;
    public int damage;

    public Weapon() {
        name = "DefaultWeaponName";
        damage = 0;
    }

    public Weapon(String aName, int aDamage) {
        name = aName;
        damage = aDamage;
    }
}

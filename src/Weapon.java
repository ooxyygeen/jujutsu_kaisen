public class Weapon implements ShowInfo{
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
    public void showInfo(){
        System.out.println("Name of the weapon: "+name);
        System.out.println("Damage of the weapon: "+damage);
    }
}

public class Technique {
    public String name;
    public int damage, range, heal, accuracy;

    public Technique() {
        name = "DefaultTechniqueName";
        damage = 0;
        range = 0;
        heal = 0;
        accuracy = 0;
    }

    public Technique(String aName, int aDamage, int aRange, int aHeal, int aAccuracy) {
        name =aName;
        damage = aDamage;
        range = aRange;
        heal = aHeal;
        accuracy = aAccuracy;
    }
}

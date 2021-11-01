public class Technique {
    private final String name;
    private int damage, range, heal, accuracy;

    public Technique() {
        this.name = "DefaultTechniqueName";
        this.damage = 0;
        this.range = 0;
        this.heal = 0;
        this.accuracy = 0;
    }

    public Technique(String aName, int aDamage, int aRange, int aHeal, int aAccuracy) {
        this.name =aName;
        this.damage = aDamage;
        this.range = aRange;
        this.heal = aHeal;
        this.accuracy = aAccuracy;
    }
}

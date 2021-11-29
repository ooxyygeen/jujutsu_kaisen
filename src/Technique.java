public class Technique {
    private final String name;
    /*
    0 - damage
    1 - range
    2 - heal
    3 - accuracy
    4 - cost
    */
    private final int[] stats;

    public Technique() {
        this.name = "DefaultTechniqueName";
        this.stats = new int[]{0, 0, 0, 0, 0};
    }

    public String getName(){
        return this.name;
    }

    public Technique(String aName, int aDamage, int aRange, int aHeal, int aAccuracy, int aCost) {
        this.name = aName;
        this.stats = new int[]{aDamage,aRange,aHeal,aAccuracy, aCost};
    }

    public int getStat(String aStat){
        switch (aStat) {
            case "damage":
                return this.stats[0];
            case "range":
                return this.stats[1];
            case "heal":
                return this.stats[2];
            case "accuracy":
                return this.stats[3];
            case "cost":
                return this.stats[4];
            default:
                return 0;
        }
    }

    public int[] getAllStats() {
        return this.stats;
    }
}

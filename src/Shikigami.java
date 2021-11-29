public class Shikigami extends Stats {
    private boolean possibilityOfBeing;
    private String name;

    public Shikigami() {
        super();
        this.possibilityOfBeing = false;
        this.name = "Shikigamus";
    }

    public Shikigami(int aStrength, int aIntel, int aLuck, int aAgility, int aHealth, int aEnergy,
                     boolean aPossibility, String aName) {
        super(aStrength, aIntel, aLuck, aAgility, aHealth, aEnergy);
        this.possibilityOfBeing = aPossibility;
        this.name = aName;
    }
}

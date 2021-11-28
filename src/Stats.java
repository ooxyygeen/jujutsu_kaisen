public class Stats {
    private int strength, intelligence, luck, agility, health, energy;

    public Stats() {
        this.strength = 1;
        this.intelligence = 1;
        this.luck = 1;
        this.agility = 1;
        this.health = 10;
        this.energy = 1;
    }

    public String change(String aStat, int aNum) {
        switch (aStat) {
            case "strength":
                this.strength += aNum;
                break;
            case "intelligence":
                this.intelligence += aNum;
                break;
            case "luck":
                this.luck += aNum;
                break;
            case "agility":
                this.agility += aNum;
                break;
            case "health":
                this.health += aNum;
                break;
            case "energy":
                this.energy += aNum;
                break;
            case "all":
                this.strength += aNum;
                this.intelligence += aNum;
                this.luck += aNum;
                this.agility += aNum;
                this.health += aNum;
                this.energy += aNum;
                break;
            default:
                return "IncorrectIncomingStatException";
        }
        return "new stats are applied";
    }
}

public class Stats {
    private int strength, intelligence, luck, agility, health, energy;

    public Stats() {
        strength = 1;
        intelligence = 1;
        luck = 1;
        agility = 1;
        health = 1;
        energy = 1;
    }

    public void increase(String aStat, int aNum) {
        switch (aStat) {
            case "strength":
                strength += aNum;
                break;
            case "intelligence":
                intelligence += aNum;
                break;
            case "luck":
                luck += aNum;
                break;
            case "agility":
                agility += aNum;
                break;
            case "health":
                health += aNum;
                break;
            case "energy":
                energy += aNum;
                break;
            case "all":
                strength += aNum;
                intelligence += aNum;
                luck += aNum;
                agility += aNum;
                health += aNum;
                energy += aNum;
                break;
            default:
                //System.out.println("Incorrect stat applied");
                break;
        }
    }

    public void decrease(String aStat, int aNum) {
        switch (aStat) {
            case "strength":
                strength -= aNum;
                break;
            case "intelligence":
                intelligence -= aNum;
                break;
            case "luck":
                luck -= aNum;
                break;
            case "agility":
                agility -= aNum;
                break;
            case "health":
                health -= aNum;
                break;
            case "energy":
                energy -= aNum;
                break;
            case "all":
                strength -= aNum;
                intelligence -= aNum;
                luck -= aNum;
                agility -= aNum;
                health -= aNum;
                energy -= aNum;
                break;
            default:
                //System.out.println("Incorrect stat applied");
                break;
        }
    }
}

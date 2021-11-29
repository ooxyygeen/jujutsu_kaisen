import java.io.Serializable;

public class Stats implements Serializable {
    /*
    0 - strength
    1 - intelligence
    2 - luck
    3 - agility
    4 - health
    5 - energy
    */
    private int[] stats;

    public Stats() {
        stats = new int[]{1, 1, 1, 1, 100, 100};
    }

    public int getStat(String aStat){
        switch (aStat) {
            case "strength":
                return this.stats[0];
            case "intelligence":
                return this.stats[1];
            case "luck":
                return this.stats[2];
            case "agility":
                return this.stats[3];
            case "health":
                return this.stats[4];
            case "energy":
                return this.stats[5];
            default:
                return -1;
        }
    }

    public int[] getAllStats(){
        return stats;
    }

    public String change(String aStat, int aNum) {
        switch (aStat) {
            case "strength":
                this.stats[0] += aNum;
                break;
            case "intelligence":
                this.stats[1] += aNum;
                break;
            case "luck":
                this.stats[2] += aNum;
                break;
            case "agility":
                this.stats[3] += aNum;
                break;
            case "health":
                this.stats[4] += aNum;
                break;
            case "energy":
                this.stats[5] += aNum;
                break;
            case "all":
                this.stats[0] += aNum;
                this.stats[1] += aNum;
                this.stats[2] += aNum;
                this.stats[3] += aNum;
                this.stats[4] += aNum;
                this.stats[5] += aNum;
                break;
            default:
                return "IncorrectIncomingStatException";
        }
        return "new stats are applied";
    }
}

package com.engine.javacoursework;

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
    private int[] cur_stats, max_stats;
    private boolean sukunaMode = false;

    public Stats() {
        max_stats = new int[]{1, 1, 1, 1, 100, 100};
        cur_stats = new int[]{1, 1, 1, 1, 100, 100};
    }

    public Stats(int aStrength, int aIntel, int aLuck, int aAgility, int aHealth, int aEnergy) {
        cur_stats = new int[]{aStrength, aIntel, aLuck, aAgility, aHealth, aEnergy};
    }

    public String sukunaPower(int countOfFingersOfSukuna) {
        if (countOfFingersOfSukuna > 0) {
            sukunaMode = !sukunaMode;
            if (sukunaMode) {
                this.cur_stats[0] *= countOfFingersOfSukuna + 1;
                this.cur_stats[1] *= countOfFingersOfSukuna + 1;
                this.cur_stats[2] *= countOfFingersOfSukuna + 1;
                this.cur_stats[3] *= countOfFingersOfSukuna + 1;
                this.cur_stats[4] *= countOfFingersOfSukuna + 1;
                this.cur_stats[5] *= countOfFingersOfSukuna + 1;
                return "Sukuna has stepped into the deal!";
            }
            this.cur_stats[0] /= countOfFingersOfSukuna + 1;
            this.cur_stats[1] /= countOfFingersOfSukuna + 1;
            this.cur_stats[2] /= countOfFingersOfSukuna + 1;
            this.cur_stats[3] /= countOfFingersOfSukuna + 1;
            this.cur_stats[4] /= countOfFingersOfSukuna + 1;
            this.cur_stats[5] /= countOfFingersOfSukuna + 1;
            return "Your consciousness has returned";
        }
        return "NoSukunaPartException";
    }

    public int getStat(String aStat) {
        switch (aStat) {
            case "strength":
                return this.cur_stats[0];
            case "intelligence":
                return this.cur_stats[1];
            case "luck":
                return this.cur_stats[2];
            case "agility":
                return this.cur_stats[3];
            case "health":
                return this.cur_stats[4];
            case "energy":
                return this.cur_stats[5];
            default:
                return -1;
        }
    }

    public int getMaxStat(String aStat) {
        switch (aStat) {
            case "strength":
                return this.max_stats[0];
            case "intelligence":
                return this.max_stats[1];
            case "luck":
                return this.max_stats[2];
            case "agility":
                return this.max_stats[3];
            case "health":
                return this.max_stats[4];
            case "energy":
                return this.max_stats[5];
            default:
                return -1;
        }
    }

    public int[] getAllStats() {
        return cur_stats;
    }

    public String changeMaxStat(String aStat, int aNum) {
        switch (aStat) {
            case "strength":
                this.max_stats[0] += aNum;
                this.cur_stats[0] += aNum;
                break;
            case "intelligence":
                this.max_stats[1] += aNum;
                this.cur_stats[1] += aNum;
                break;
            case "luck":
                this.max_stats[2] += aNum;
                this.cur_stats[2] += aNum;
                break;
            case "agility":
                this.max_stats[3] += aNum;
                this.cur_stats[3] += aNum;
                break;
            case "health":
                this.max_stats[4] += aNum;
                this.cur_stats[4] += aNum;
                break;
            case "energy":
                this.max_stats[5] += aNum;
                this.cur_stats[5] += aNum;
                break;
            case "all":
                this.max_stats[0] += aNum;
                this.max_stats[1] += aNum;
                this.max_stats[2] += aNum;
                this.max_stats[3] += aNum;
                this.max_stats[4] += aNum;
                this.max_stats[5] += aNum;
                this.cur_stats[0] += aNum;
                this.cur_stats[1] += aNum;
                this.cur_stats[2] += aNum;
                this.cur_stats[3] += aNum;
                this.cur_stats[4] += aNum;
                this.cur_stats[5] += aNum;
                break;
            default:
                return "IncorrectIncomingStatException";
        }
        return "New stats are applied";
    }

    public String changeCurStat(String aStat, int aNum) {
        switch (aStat) {
            case "strength":
                this.cur_stats[0] += aNum;
                break;
            case "intelligence":
                this.cur_stats[1] += aNum;
                break;
            case "luck":
                this.cur_stats[2] += aNum;
                break;
            case "agility":
                this.cur_stats[3] += aNum;
                break;
            case "health":
                this.cur_stats[4] += aNum;
                break;
            case "energy":
                this.cur_stats[5] += aNum;
                break;
            case "all":
                this.cur_stats[0] += aNum;
                this.cur_stats[1] += aNum;
                this.cur_stats[2] += aNum;
                this.cur_stats[3] += aNum;
                this.cur_stats[4] += aNum;
                this.cur_stats[5] += aNum;
                break;
            default:
                return "IncorrectIncomingStatException";
        }
        return "new stats are applied";
    }

}

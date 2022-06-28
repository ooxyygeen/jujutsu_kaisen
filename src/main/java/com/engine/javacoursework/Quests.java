package com.engine.javacoursework;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Quests implements Serializable {
    private int curNumberOfMainQuest = 0, totalCountOfMainQuests = 3;
    private boolean[] quests;

    public Quests() {
        quests = new boolean[totalCountOfMainQuests];
        Arrays.fill(this.quests, true);
    }

    public void HELP() {
        this.quests[1] = false;
        this.curNumberOfMainQuest++;
    }

    public void updateQuestsStatus(Character player) {
        // перейти к следующему квесту можно только выполнив все предыдущие
        // если выполнено условие прохождения квеста, то он перестает быть доступным
        if (player.getEquipment().getWeapon().getName().equals("Sock with soap") && this.quests[0]) {

            this.quests[0] = false;
            this.curNumberOfMainQuest++;
        } else if (player.findItem("Finger of Sukuna") && this.quests[1]) {
            System.out.println(player.findItem("Finger of Sukuna"));
            this.quests[1] = false;
            this.curNumberOfMainQuest++;
        } else if (Objects.equals(player.getEquipment().getUniform().getName(), "Paper bag") && this.quests[2]) {
            this.quests[2] = false;
            this.curNumberOfMainQuest++;
        }
    }

    public String showMainQuest() {
        switch (this.curNumberOfMainQuest) {
            case 0:
                return "Your quest is:\nTry to interact with some objects:" +
                        "\nTotem of dexterity (4,1);\nPlate with text (4,3);" +
                        "\nBarbed bush (4,5);\nChest (4,7)." +
                        "\nThe coordinates are given (y,x).";
            case 1:
                return """
                        Your quest is:
                        Your school friends asked you to check strange box (4, 20) in the school garden,
                        they said it may contain useful for the 'suspicious club' item.
                        The coordinates of the box are given (y,x).
                        """;
            case 2:
                return """
                        Your quest is:
                        You need to go to the hospital (7, 15) and see your grandfather. Looks easy, 
                        nothing can go wrong, right? ;)
                        The coordinates are given (y,x).
                        """;
            default:
                return "You have no quests left";
        }
    }
}

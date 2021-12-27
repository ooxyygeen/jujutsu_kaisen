import java.io.Serializable;
import java.util.Arrays;

public class Quests implements Serializable {
    private int curNumberOfMainQuest = 0, totalCountOfMainQuests = 2;
    private boolean[] quests;
    public Quests(){
        quests = new boolean[totalCountOfMainQuests];
        Arrays.fill(this.quests, true);
    }
    public void updateQuestsStatus(Character player){
        if (player.getEquipment().getWeapon().getName().equals("Sock with soap") && this.quests[0]) {

            this.quests[0] = false;
            this.curNumberOfMainQuest++;
        }
        else if (player.findItem("Finger of Sukuna") && this.quests[1]){
            System.out.println(player.findItem("Finger of Sukuna"));
            this.quests[1] = false;
            this.curNumberOfMainQuest++;
        }
    }
    public void showMainQuest(){
        switch (this.curNumberOfMainQuest){
            case 0:
                System.out.println("""
                        Your quest is:
                        Try to interact with some objects:
                        Totem of dexterity (4,1);
                        Plate with text (4,3);
                        Barbed bush (4,5);
                        Chest (4,7).
                        The coordinates are given (y,x).
                        """);
                break;
            case 1:
                System.out.println("""
                        Your quest is:
                        Your school friends asked you to check strange box (4, 20) in the school garden,
                        they said it may contain useful for the 'suspicious club' item.
                        The coordinates of the box are given (y,x).
                        """);
            default:
                System.out.println("You have no quests left");
                break;
        }
    }
}

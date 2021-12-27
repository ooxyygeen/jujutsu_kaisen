import java.io.Serializable;

public class BarbedBush extends MapObject implements Serializable {
    @Override
    public String activate(Character character) {
        if (character != null) {
            character.changeCurStats("health", -1);
            return "You touched barbed bush and got pricked :)";
        }
        return "NullPointerCharacterException";
    }
    @Override
    public String showInfo() {
        return "Barbed bush";
    }
}

public class BarbedBush extends MapObject{
    @Override
    public String activate(Character character) {
        if (character != null) {
            character.changeStats("health", -1);
            return "You touched barbed bush and got pricked :)";
        }
        return "NullPointerCharacterException";
    }
    @Override
    public String showInfo() {
        return "Barbed bush";
    }
}

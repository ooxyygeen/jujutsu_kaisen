public class BarbedBush extends MapObject{

    public BarbedBush(){
        super("Barbed bush",0,0,true);
    }
    public BarbedBush(int newPosY, int newPosX, boolean newPresence){
        super("Barbed bush", newPosY, newPosX, newPresence);
    }
    public BarbedBush(BarbedBush target){
        super(target);
    }
    @Override
    public MapObject clone() {
        return new BarbedBush(this);
    }
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

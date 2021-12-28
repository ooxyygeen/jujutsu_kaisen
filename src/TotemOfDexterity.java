public class TotemOfDexterity extends MapObject{
    TotemOfDexterity(){
        super("Totem of dexterity", 0,0, true);
    }
    TotemOfDexterity(int newPosY, int newPosX, boolean newPresence){
        super("Totem of dexterity", newPosY, newPosX,newPresence);
    }
    TotemOfDexterity(TotemOfDexterity target){
        super(target.getName(), target.getPosY(), target.getPosX(), target.getPresence());
    }
    @Override
    public MapObject clone(){
        return new TotemOfDexterity(this);
    }
    @Override
    public String showInfo() {
        return super.getName();
    }
    @Override
    public String activate(Character character){
        if (character != null) {
            return character.changeMaxStats("agility", 1) + "destroyed";
        }
        return "CharacterNullPointerException";
    }
}

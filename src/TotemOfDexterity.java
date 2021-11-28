public class TotemOfDexterity extends MapObject{
    TotemOfDexterity(){
        super.setName("Totem of dexterity");
    }
    @Override
    public String showInfo() {
        return super.getName();
    }
    @Override
    public String activate(Character character){
        if (character != null) {
            return character.changeStats("agility", 1);
        }
        return "CharacterNullPointerException";
    }
}

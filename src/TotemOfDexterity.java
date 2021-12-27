import java.io.Serializable;

public class TotemOfDexterity extends MapObject implements Serializable {
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
            return character.changeMaxStats("agility", 1);
        }
        return "CharacterNullPointerException";
    }
}

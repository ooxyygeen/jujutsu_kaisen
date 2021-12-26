public class BoxWithFingerOfSukuna extends MapObject{
    public BoxWithFingerOfSukuna(){
        super.setName("Box with finger of Sukuna");
    }
    @Override
    public String activate(Character character){
        if (character != null) {
            return character.addItem(new FingerOfSukuna());
        }
        return "CharacterNullPointerException";
    }
}
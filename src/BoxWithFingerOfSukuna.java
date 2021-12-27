public class BoxWithFingerOfSukuna extends MapObject{
    public BoxWithFingerOfSukuna(){
        super("Box with finger of Sukuna", 0,0,true);
    }
    public BoxWithFingerOfSukuna(int newPosY, int newPosX, boolean newPresence){
        super("Box with finger of Sukuna", newPosY, newPosX,newPresence);
    }
    public BoxWithFingerOfSukuna(BoxWithFingerOfSukuna target){
        super(target.getName(), target.getPosY(), target.getPosX(), target.getPresence());
    }
    @Override
    public MapObject clone(){
        return new BoxWithFingerOfSukuna(this);
    }
    @Override
    public String activate(Character character){
        if (character != null) {
            return character.addItem(new FingerOfSukuna());
        }
        return "CharacterNullPointerException";
    }
}

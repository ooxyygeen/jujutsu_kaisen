public class ChestWithSockWithSoap extends MapObject{
    ChestWithSockWithSoap(){
        super.setName("Chest with weapon");
    }
    @Override
    public String showInfo(){
        return super.getName();
    }
    @Override
    public String activate(Character character){
        if (character != null){
            return character.addItem(new Weapon("Sock with soap", 3));
        }
        return "CharacterNullPointerException";
    }
}

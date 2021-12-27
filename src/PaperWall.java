import java.io.Serializable;

public class PaperWall extends MapObject implements Serializable {
    PaperWall(){
        super.setName("Paper wall");
    }
    @Override
    public String showInfo(){
        return this.getName();
    }
    @Override
    public String activate(Character character) {
        if (character != null){
            if (character.findItem("Sock with soap")) {
                return "The wall was destroyed";
            }
        }
        return "You don't have item to break the wall";
    }
}

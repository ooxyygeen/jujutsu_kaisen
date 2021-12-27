import java.io.Serializable;

public class PlateWithText extends MapObject implements Activate, ShowInfo{
    private String text;
    public PlateWithText (){
        super("Plate with text", 0,0, true);
        text = "...";
    }
    public PlateWithText (int newPosY, int newPosX, boolean newPresence, String newText){
        super("Plate with text", newPosY, newPosX, newPresence);
        this.text = newText;
    }
    public PlateWithText(PlateWithText target){
        super(target.getName(), target.getPosY(), target.getPosX(), target.getPresence());
    }
    @Override
    public MapObject clone(){
        return new PlateWithText(this);
    }
    @Override
    public String activate(Character character){
        return "The plate says: "+text;
    }
    @Override
    public String showInfo(){
        return getName();
    }
}

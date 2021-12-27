import java.io.Serializable;

public class PlateWithText extends MapObject implements Activate, ShowInfo, Serializable {
    private String text;
    public PlateWithText (){
        text = "...";
        setPresence(true);
    }
    public PlateWithText (String aText){
        setName("Plate with text");
        this.text = aText;
        setPresence(true);
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

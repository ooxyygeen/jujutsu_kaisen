public class PlateWithText extends MapObject implements Activate, ShowInfo {
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
    public void activate(){
        System.out.println("The plate says: "+text);
    }
    @Override
    public void showInfo(){
        System.out.println(getName());
    }
}

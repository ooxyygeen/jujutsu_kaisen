public class PlateWithText extends ObjectOnMap implements Activate, ShowInfo {
    public String text;
    public PlateWithText (){
        text = "...";
    }
    public PlateWithText (String aName, String aText){
        name = aName;
        text = aText;
    }
    @Override
    public void activate(){
        System.out.println("The plate says: "+text);
    }
    @Override
    public void showInfo(){
        System.out.println(name);
    }
}

public class PlateWithText extends MapObject implements Activate, ShowInfo {
    private String text;
    private PlateWithText (){
        text = "...";
    }
    private PlateWithText (String aName, String aText){
        setName(aName);
        this.text = aText;
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

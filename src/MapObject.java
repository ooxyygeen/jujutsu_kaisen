
public class MapObject {
    private int posX;
    private int posY; // coordinates
    private String name;
    private boolean presence;

    public MapObject(){
        this.presence = false;
    }

    public void setPosX(int newPosX) {
        this.posX = newPosX;
    }

    public void setPosY(int newPosY) {
        this.posY = newPosY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPresence(boolean aState){
        this.presence = aState;
    }

    public boolean getPresence(){
        return this.presence;
    }

    public String getName() {
        return name;
    }
}

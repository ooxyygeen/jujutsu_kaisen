public class Coordinates {
    private int X;
    private int Y;
    Coordinates(){
        this.X = 0;
        this.Y = 0;
    }
    public int getX(){
        return this.X;
    }
    public int getY(){
        return this.Y;
    }
    public String setCoordinates(int newX, int newY){
        if (newX > -1 && newY > -1) {
            this.X = newX;
            this.Y = newY;
            return "new coordinates are (" + this.X + "," + this.Y + ")";
        }
        return "IncorrectIncomingCoordinatesException";
    }
}

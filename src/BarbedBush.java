public class BarbedBush extends MapObject implements ShowInfo, Activate{
    @Override
    public void activate() {
        System.out.println("You touched barbed bush and got pricked:)");
    }
    @Override
    public void showInfo() {
        System.out.println("Barbed bush");
    }
}

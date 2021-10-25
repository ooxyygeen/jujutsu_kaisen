public class Equipment<T> {
    public Weapon weapon;
    public Uniform uniform;
//    public <T> equip;

    public void equip(String aType, T aObj) {
        switch (aType) {
            case "weapon":
                weapon = (Weapon) aObj;
                break;
            case "uniform":
                uniform = (Uniform) aObj;
                break;
            default:
//                System.out.println("Incorrect equipment");
                break;
        }
    }
}

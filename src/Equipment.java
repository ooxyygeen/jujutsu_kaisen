public class Equipment<T> {
    private Weapon weapon;
    private Uniform uniform;
//    public <T> equip;

    private void equip(String aType, T aObj) {
        switch (aType) {
            case "weapon":
                this.weapon = (Weapon) aObj;
                break;
            case "uniform":
                this.uniform = (Uniform) aObj;
                break;
            default:
//                System.out.println("Incorrect equipment");
                break;
        }
    }
}

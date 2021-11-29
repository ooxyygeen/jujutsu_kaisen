import java.io.Serializable;

public class Equipment<Object> implements Serializable {
    private Weapon weapon;
    private Uniform uniform;
//    public <T> equip;

    private void equip(String aType, Object aObj) {
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

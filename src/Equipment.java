import java.io.Serializable;

public class Equipment<Object> implements Serializable {
    private Weapon weapon;
    private Uniform uniform;

    Equipment(){
        this.weapon = new Weapon();
        this.uniform = new Uniform();
    }
    public String equip(String aType, Object aObj) {
        switch (aType) {
            case "weapon":
                this.weapon = (Weapon) aObj;
                return "Weapon has been successfully equipped";
            case "uniform":
                this.uniform = (Uniform) aObj;
                return "Uniform has been successfully equipped";
            default:
                return "WrongTypeOfEquipmentException";
        }
    }
    public Weapon getWeapon(){
        return this.weapon;
    }
    public Uniform getUniform(){
        return this.uniform;
    }
}

package com.engine.javacoursework;

import java.io.Serializable;

public class Equipment<Object extends ShowInfo> implements Serializable {
    private Weapon weapon;
    private Uniform uniform;

    public Equipment() {
        this.weapon = new Weapon();
        this.uniform = new Uniform();
    }

    public String equip(String aType, Object aObj) {
        if (aObj != null) {
            switch (aType) {
                case "weapon":
                    if (aObj.getClass() == Weapon.class) {
                        this.weapon = (Weapon) aObj;
                        return "weapon " + aObj.showInfo() + " has been successfully equipped";
                    }
                    return "NotAWeaponException";
                case "uniform":
                    if (aObj.getClass() == Uniform.class) {
                        this.uniform = (Uniform) aObj;
                        return "uniform " + aObj.showInfo() + " has been successfully equipped";
                    }
                    return "NotAUniformException";
                default:
                    return "WrongTypeOfEquipmentException";
            }
        }
        return "EquippingNullPointerException";
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public Uniform getUniform() {
        return this.uniform;
    }
}

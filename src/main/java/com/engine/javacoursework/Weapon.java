package com.engine.javacoursework;

import java.io.Serializable;

public class Weapon implements ShowInfo, Serializable {
    private final String name;
    private final int damage;

    public Weapon() {
        this.name = "DefaultWeaponName";
        this.damage = 0;
    }

    public Weapon(String aName, int aDamage) {
        this.name = aName;
        this.damage = aDamage;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String showInfo() {
        return this.name;
    }

    public int getDamage() {
        return this.damage;
    }
}

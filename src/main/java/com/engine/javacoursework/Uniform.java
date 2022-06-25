package com.engine.javacoursework;

import java.io.Serializable;

public class Uniform implements ShowInfo, Serializable {
    private String name;
    private int defense;

    public Uniform() {
        this.name = "DefaultUniformName";
        this.defense = 0;
    }

    public Uniform(String aName, int aDefense) {
        this.name = aName;
        this.defense = aDefense;
    }

    @Override
    public String showInfo() {
        return this.name;
    }

    public int getDefense() {
        return this.defense;
    }

    public String getName() {
        return name;
    }
}

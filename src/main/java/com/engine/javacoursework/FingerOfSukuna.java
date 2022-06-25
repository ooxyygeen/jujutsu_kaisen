package com.engine.javacoursework;

import java.io.Serializable;

public class FingerOfSukuna implements ShowInfo, Serializable {
    private String name;

    public FingerOfSukuna() {
        this.name = "Finger of Sukuna";
    }

    @Override
    public String showInfo() {
        return this.name + " exudes enormous amount of energy";
    }
}

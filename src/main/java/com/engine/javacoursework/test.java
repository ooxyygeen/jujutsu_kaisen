package com.engine.javacoursework;

public class test {

    public static void main(String[] args) {
        ShowInfo s;
        Weapon w = new Weapon("bambuka", 10);
        Uniform u = new Uniform("Telniashka", 5);
        outputInfo(w);
        outputInfo(u);
    }

    public static void outputInfo(ShowInfo aObject) {
        aObject.showInfo();
    }
}

package com.engine.javacoursework;

import java.util.Map;

public class ChestWithSockWithSoap extends MapObject {
    public ChestWithSockWithSoap() {
        super("Chest with weapon", 0, 0, true);
    }

    ChestWithSockWithSoap(int newPosY, int newPosX, boolean newPresence) {
        super("Chest with weapon", newPosY, newPosX, newPresence);
    }

    ChestWithSockWithSoap(ChestWithSockWithSoap target) {
        super(target.getName(), target.getPosY(), target.getPosX(), target.getPresence());
    }

    @Override
    public MapObject clone() {
        return new ChestWithSockWithSoap(this);
    }

    @Override
    public String showInfo() {
        return super.getName();
    }

    @Override
    public String activate(Character character) {
        if (character != null) {
            return character.addItem(new Weapon("Sock with soap", 3)) + " and chest was destroyed";
        }
        return "CharacterNullPointerException";
    }
}

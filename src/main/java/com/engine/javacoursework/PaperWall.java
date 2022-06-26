package com.engine.javacoursework;

import java.io.Serializable;

public class PaperWall extends MapObject {
    public PaperWall() {
        super.setName("Paper wall");
    }

    PaperWall(int newPosY, int newPosX, boolean newPresence) {
        super("Paper wall", newPosY, newPosX, newPresence);
    }

    PaperWall(PaperWall target) {
        super(target.getName(), target.getPosY(), target.getPosX(), target.getPresence());
    }

    @Override
    public MapObject clone() {
        return new PaperWall(this);
    }

    @Override
    public String showInfo() {
        return this.getName();
    }

    @Override
    public String activate(Character character) {
        if (character != null) {
            if (character.findItem("Sock with soap")) {
                return "The wall was destroyed";
            }
        }
        return "You don't have item to break the wall";
    }
}

package com.engine.javacoursework;

public class ChestWithUniform extends MapObject {
    public ChestWithUniform() {
        super("Chest with uniform", 0, 0, true);
    }

    ChestWithUniform(int newPosY, int newPosX, boolean newPresence) {
        super("Chest with uniform", newPosY, newPosX, newPresence);
    }

    ChestWithUniform(ChestWithUniform target) {
        super(target.getName(), target.getPosY(), target.getPosX(), target.getPresence());
    }

    @Override
    public MapObject clone() {
        return new ChestWithUniform(this);
    }

    @Override
    public String showInfo() {
        return super.getName();
    }

    @Override
    public String activate(Character character) {
        if (character != null) {
            return character.addItem(new Uniform("Paper bag", 1)) + " and chest was destroyed";
        }
        return "CharacterNullPointerException";
    }
}

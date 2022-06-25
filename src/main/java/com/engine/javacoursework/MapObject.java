package com.engine.javacoursework;

import java.io.Serializable;
import java.util.Map;

public abstract class MapObject implements ShowInfo, Activate, Serializable {
    private Coordinates coordinates;
    private String name;
    private boolean presence;
    private int posX, posY;

    public MapObject() {
        this.name = "DefaultName";
        this.posY = 0;
        this.posX = 0;
        this.presence = true;
    }

    public MapObject(String newName, int newPosY, int newPosX, boolean newPresence) {
        this.name = newName;
        this.posY = newPosY;
        this.posX = newPosX;
        this.presence = newPresence;
    }

    public MapObject(MapObject target) {
        if (target != null) {
            this.name = target.name;
            this.posX = target.posX;
            this.posY = target.posY;
            this.presence = target.presence;
        }
    }

    public abstract MapObject clone();

    public String setCoordinates(int newX, int newY) {
        return this.coordinates.setCoordinates(newX, newY);
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public void setPosX(int newPosX, int mapSizeX) {
        if (newPosX >= 0 && newPosX < mapSizeX) {
            this.posX = newPosX;
        } else if (newPosX < 0) {
            this.posX = 0;
        } else
            this.posX = mapSizeX - 1;
    }

    public void setPosY(int newPosY, int mapSizeY) {
        if (newPosY >= 0 && newPosY < mapSizeY) {
            this.posY = newPosY;
        } else if (newPosY < 0) {
            this.posY = 0;
        } else
            this.posY = mapSizeY - 1;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPresence(boolean aState) {
        this.presence = aState;
    }

    public boolean getPresence() {
        return this.presence;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String activate(Character character) {
        return null;
    }

    @Override
    public String showInfo() {
        return null;
    }
}

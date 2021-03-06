package com.engine.javacoursework;

import java.io.Serializable;

public class GameMap implements Serializable {
    public Object[][] map;

    GameMap() {
        this.map = new Object[1][1];
    }

    public GameMap(int mapSizeY, int mapSizeX) {
        if (mapSizeY > 0 && mapSizeX > 0)
            this.map = new Object[mapSizeY][mapSizeX];
        else
            this.map = new Object[1][1];
    }
}

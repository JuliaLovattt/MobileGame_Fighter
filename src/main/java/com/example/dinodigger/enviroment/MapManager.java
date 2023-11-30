package com.example.dinodigger.enviroment;

import android.graphics.Canvas;
import com.example.dinodigger.helpers.GameConstants;
public class MapManager {
    private GameMap currentMap;
    private float cameraX, cameraY;

    public MapManager() {
        initTestMap();
    }

    public void setCameraValues(float cameraX, float cameraY) {
        this.cameraX = cameraX;
        this.cameraY = cameraY;
    }

    public boolean canMoveHere(float x, float y) {
        if (x < 0 || y < 0)
            return false;

        if (x >= getMaxWidthCurrentMap() || y >= getMaxHeightCurrentMap())
            return false;

        return true;
    }

    public int getMaxWidthCurrentMap() {
        return currentMap.getArrayWidth() * GameConstants.Sprite.SIZE;
    }

    public int getMaxHeightCurrentMap() {
        return currentMap.getArrayHeight() * GameConstants.Sprite.SIZE;
    }


    public void draw(Canvas c) {
        for (int j = 0; j < currentMap.getArrayHeight(); j++)
            for (int i = 0; i < currentMap.getArrayWidth(); i++)
                c.drawBitmap(floor.OUTSIDE.getSprite(currentMap.getSpriteID(i, j)), i * GameConstants.Sprite.SIZE + cameraX, j * GameConstants.Sprite.SIZE + cameraY, null);
    }

    private void initTestMap() {


        int[][] spriteIds = {
                {121, 122, 11, 12, 12, 12, 12, 12,12, 12, 12, 12, 12, 12, 12, 12, 12, 13, 121, 121, 121,121,121,121,121,125,121,121,121,123,121},
                {121, 121, 33, 34, 89, 23, 23, 34,99, 89, 23, 23, 23, 23, 89, 23, 23, 35, 123, 122, 121,121,123,121,121,121,121,121,121,121,121},
                {121, 122, 33, 34, 34, 34, 89, 34, 34, 34, 34, 34, 89, 99, 34, 34, 34, 35, 121, 121, 123,121,121,121,121,122,121,121,121,124,121},
                {121, 124, 33, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 35, 121,121, 121,121,121,123,121,121,121,121,121,125,121},
                {121, 121, 33, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 35, 125, 125, 121,121,121,125,121,124,121,121,121,121,121},
                {121, 121, 33, 34, 34, 89, 34, 34, 34, 34, 34, 89, 99, 34, 34, 34, 89, 35, 124, 121, 121,121,125,122,121,123,121,121,121,121,121},
                {121, 121, 33, 34, 34, 34, 99, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34,35, 121, 121, 121,121,121,121,121,121,121,121,121,123,121},
                {121, 125, 33, 34, 34, 34, 34, 34, 34, 34, 34, 34, 89, 34, 34, 34, 34, 35, 123, 121, 121,121,124,121,121,121,121,121,121,121,121},
                {121, 124, 33,34, 34, 34, 89, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 35, 121, 124, 121,121,121,121,121,121,121,121,121,125,121},
                {121, 125, 33, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 99, 34, 34, 34, 35, 125, 121, 121,121,121,121,121,121,121,121,121,121,121},
                {121, 121, 33, 34, 34, 34, 34, 34, 34, 34, 34, 34, 89, 34, 34, 34, 34, 35, 121, 121, 121,121,121,121,121,123,121,121,121,123,121},
                {121, 121, 33,34, 34, 34, 34, 34, 34, 34, 34, 34, 89, 34, 34, 34, 34, 35, 124, 121, 121,121,121,121,121,121,121,121,121,121,121},
                {121, 123, 33,34, 34, 34, 99, 34, 34, 89, 34, 34,34, 99, 34, 34, 34, 35, 122, 121, 121,121,121,124,121,121,121,121,121,121,121},
                {121, 125, 33,34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 35, 121, 122, 121,121,123,121,121,121,121,121,121,125,121},
                {121, 121, 55, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 57, 121, 123, 121,121,125,121,121,125,121,121,121,123,121}

        };

        currentMap = new GameMap(spriteIds);
    }
}

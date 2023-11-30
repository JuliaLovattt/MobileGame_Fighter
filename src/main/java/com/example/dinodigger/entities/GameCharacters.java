package com.example.dinodigger.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.dinodigger.main.MainActivity;
import com.example.dinodigger.R;
import com.example.dinodigger.helpers.GameConstants;
import com.example.dinodigger.helpers.interfaces.BitmapMethods;

public enum GameCharacters implements BitmapMethods{

    PLAYER(R.drawable.princess),
    SPIRIT(R.drawable.spirit);

    private Bitmap spriteSheet;
    private Bitmap[][] sprites = new Bitmap[4][4];
    //private BitmapFactory.Options options = new BitmapFactory.Options();

    GameCharacters(int resID) {
        options.inScaled = false;
        spriteSheet = BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), resID, options);
        for (int j = 0; j < sprites.length; j++)
            for (int i = 0; i < sprites[j].length; i++)
                sprites[j][i] = getScaledBitmap(Bitmap.createBitmap(spriteSheet, GameConstants.Sprite.DEFAULT_SIZE * i, GameConstants.Sprite.DEFAULT_SIZE * j, GameConstants.Sprite.DEFAULT_SIZE, GameConstants.Sprite.DEFAULT_SIZE));
    }

    public Bitmap getSpriteSheet() {

        return spriteSheet;
    }

    public Bitmap getSprite(int yPos, int xPos) {

        return sprites[yPos][xPos];
    }
}
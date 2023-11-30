package com.example.dinodigger.UI;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.dinodigger.R;
import com.example.dinodigger.helpers.interfaces.BitmapMethods;
import com.example.dinodigger.main.MainActivity;
public enum GameImages implements BitmapMethods{


    MAINMENU_MENUBG(R.drawable.mainmenu_menubackground);
    private final Bitmap image;

    GameImages(int resID) {
        options.inScaled = false;
        image = BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), resID, options);
    }

    public Bitmap getImage() {
        return image;
    }
}

package com.example.dinodigger.gamestates;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;

import com.example.dinodigger.entities.Character;
import com.example.dinodigger.entities.Player;
import com.example.dinodigger.entities.enemies.Spirit;
import com.example.dinodigger.enviroment.MapManager;
import com.example.dinodigger.helpers.GameConstants;
import com.example.dinodigger.helpers.interfaces.GameStateInterface;
import com.example.dinodigger.main.Game;
import com.example.dinodigger.UI.ButtonImages;
import com.example.dinodigger.UI.CustomButton;
import com.example.dinodigger.UI.PlayingUI;


import java.util.ArrayList;
public class Playing extends BaseState implements GameStateInterface {
    private float cameraX, cameraY;
    private boolean movePlayer;
    private PointF lastTouchDiff;
    private MapManager mapManager;
    private Player player;
    private ArrayList<Spirit> spirits;

    //For UI
    private float xCenter = 450, yCenter = 1100, radius = 150;
    private Paint circlePaint;
    private float xTouch, yTouch;
    private boolean touchDown;
    private PlayingUI playingUI;

    public Playing(Game game) {
        super(game);

        mapManager = new MapManager();
        player = new Player();
        spirits = new ArrayList<>();

        for (int i = 0; i < 50; i++)
            spirits.add(new Spirit(new PointF(100, 100)));
        playingUI = new PlayingUI(this);
    }

    @Override
    public void update(double delta) {
        updatePlayerMove(delta);
        player.update(delta, movePlayer);
        for (Spirit spirit : spirits)
            spirit.update(delta);
        mapManager.setCameraValues(cameraX, cameraY);
    }

    @Override
    public void render(Canvas c) {
        mapManager.draw(c);

        drawPlayer(c);
        for (Spirit spirit : spirits)
            drawCharacter(c, spirit);
        playingUI.draw(c);
    }

    //private void drawUI(Canvas c) {
       // c.drawCircle(xCenter, yCenter, radius, circlePaint);
   // }

    private void drawPlayer(Canvas c) {
        c.drawBitmap(
                player.getGameCharType().getSprite(player.getAniIndex(), player.getFaceDir()),
                player.getHitbox().left,
                player.getHitbox().top,
                null);
    }

    public void drawCharacter(Canvas canvas, Character c) {
        canvas.drawBitmap(
                c.getGameCharType().getSprite(c.getAniIndex(), c.getFaceDir()),
                c.getHitbox().left + cameraX,
                c.getHitbox().top + cameraY,
                null);
    }

    private void updatePlayerMove(double delta) {
        if (!movePlayer)
            return;

        float baseSpeed = (float) (delta * 300);
        float ratio = Math.abs(lastTouchDiff.y) / Math.abs(lastTouchDiff.x);
        double angle = Math.atan(ratio);

        float xSpeed = (float) Math.cos(angle);
        float ySpeed = (float) Math.sin(angle);

        if (xSpeed > ySpeed) {
            if (lastTouchDiff.x > 0) player.setFaceDir(GameConstants.Face_Dir.RIGHT);
            else player.setFaceDir(GameConstants.Face_Dir.LEFT);
        } else {
            if (lastTouchDiff.y > 0) player.setFaceDir(GameConstants.Face_Dir.DOWN);
            else player.setFaceDir(GameConstants.Face_Dir.UP);
        }

        if (lastTouchDiff.x < 0)
            xSpeed *= -1;
        if (lastTouchDiff.y < 0)
            ySpeed *= -1;

        int pWidth = GameConstants.Sprite.SIZE;
        int pHeight = GameConstants.Sprite.SIZE;

        if (xSpeed <= 0)
            pWidth = 0;
        if (ySpeed <= 0)
            pHeight = 0;


        float deltaX = xSpeed * baseSpeed * -1;
        float deltaY = ySpeed * baseSpeed * -1;

        if (mapManager.canMoveHere(player.getHitbox().left + cameraX * -1 + deltaX * -1 + pWidth, player.getHitbox().top + cameraY * -1 + deltaY * -1 + pHeight)) {
            cameraX += deltaX;
            cameraY += deltaY;
        }
    }
    public void setGameStateToMenu() {
        game.setCurrentGameState(Game.GameState.MENU);
    }

    public void setPlayerMoveTrue(PointF lastTouchDiff) {
        movePlayer = true;
        this.lastTouchDiff = lastTouchDiff;
    }

    public void setPlayerMoveFalse() {
        movePlayer = false;
        player.resetAnimation();
    }

    @Override
    public void touchEvents(MotionEvent event) {
        playingUI.touchEvents(event);
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN : {
//
//                float x = event.getX();
//                float y = event.getY();
//
//                float a = Math.abs(x - xCenter);
//                float b = Math.abs(y - yCenter);
//                float c = (float) Math.hypot(a, b);
//
//                if (c <= radius) {
//                    touchDown = true;
//                    xTouch = x;
//                    yTouch = y;
//                } else
//                    game.setCurrentGameState(Game.GameState.MENU);
//                break;
//            }
//
//            case MotionEvent.ACTION_MOVE : {
//                if (touchDown) {
//                    xTouch = event.getX();
//                    yTouch = event.getY();
//
//                    float xDiff = xTouch - xCenter;
//                    float yDiff = yTouch - yCenter;
//
//                    setPlayerMoveTrue(new PointF(xDiff, yDiff));
//                    break;
//
//                }
//
//            }
//            case MotionEvent.ACTION_UP : {
//                touchDown = false;
//                setPlayerMoveFalse();
//                break;
//
//            }
//        }
    }
}

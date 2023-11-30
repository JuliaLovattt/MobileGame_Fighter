package com.example.dinodigger.gamestates;
import com.example.dinodigger.main.Game;

public abstract class BaseState {
    protected Game game;

    public BaseState(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}

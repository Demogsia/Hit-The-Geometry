package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Screens.DifficultyScreen;
import com.mygdx.game.Screens.GameScreen;

/**
 * Created by Efe on 7/2/2017.
 */

public class GameClass extends Game {
    @Override
    public void create() {
        setScreen(new DifficultyScreen(this));
    }
    public void clearWhite() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    public void clearBlack() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    public void clearRed(){
        Gdx.gl.glClearColor(0.5f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

}

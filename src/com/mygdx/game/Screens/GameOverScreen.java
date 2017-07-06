package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameClass;
import com.mygdx.game.Objects.Obstacle;

/**
 * Created by Efe on 6/30/2017.
 */

public class GameOverScreen implements Screen, InputProcessor {
    GameClass game;
    BitmapFont font;
    SpriteBatch batch;


    public GameOverScreen(GameClass game){ this.game = game; }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        game.clearBlack();
        batch.begin();
        font.draw(batch, "Game Over!\nScore: "+ Obstacle.score, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }


    @Override
    public boolean keyDown(int keycode) {


        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT) {
            game.setScreen(new DifficultyScreen(game));
            System.out.println("Left button clicked!");
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

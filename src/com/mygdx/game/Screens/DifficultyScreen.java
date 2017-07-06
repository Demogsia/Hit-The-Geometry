package com.mygdx.game.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Buttons.DifficultyButtons.EasyButton;
import com.mygdx.game.Buttons.DifficultyButtons.HardButton;
import com.mygdx.game.Buttons.DifficultyButtons.MediumButton;
import com.mygdx.game.Constants;
import com.mygdx.game.GameClass;
import com.mygdx.game.Objects.BackgroundThing;
import com.mygdx.game.Objects.Obstacle;


/**
 * Created by Efe on 7/2/2017.
 */

public class DifficultyScreen implements Screen, InputProcessor {
    BitmapFont font;
    SpriteBatch batch;
    ShapeRenderer renderer;
    GameClass game;
    ExtendViewport viewport;
    Vector2 temp;
    Vector2 tempHover;
    Vector2 tempClick;
    EasyButton easyButton;
    MediumButton mediumButton;
    HardButton hardButton;
    BackgroundThing[] gKeys;
    float randomX, firstX, currentX;
    float randomY, firstY, currentY;
    public boolean isNormal;
    public boolean isHover;
    public boolean isPushed;
    int numberOfG;
    Texture texture;

    public DifficultyScreen(GameClass game){
        this.game = game;
    }
    @Override
    public void show() {
        numberOfG = 8;
        randomX = (float)(Math.random()* Gdx.graphics.getWidth());
        randomY = (float)(Math.random()* Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(this);
        font = new BitmapFont();
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        viewport = new ExtendViewport(480,480);
        temp = new Vector2();
        isNormal = true;
        isHover = false;
        isPushed = false;
        texture = new Texture("bg.png");
        tempHover = new Vector2();
        tempClick = new Vector2();
        easyButton = new EasyButton();
        mediumButton = new MediumButton();
        hardButton = new HardButton();
        easyButton.setState("normal");
        gKeys = new BackgroundThing[numberOfG];
        for (int i = 0; i < numberOfG; i++) {
            gKeys[i] = new BackgroundThing();
        }
        if(currentX!=0 && currentY!=0) {
            firstX = currentX;
            firstY = currentY;
        }

    }

    @Override
    public void render(float delta) {
        viewport.apply();
        game.clearWhite();
        renderer.setAutoShapeType(true);
        batch.begin();
        batch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        for (int i = 0; i < numberOfG; i++) {

            gKeys[i].render(batch);
            gKeys[i].position.x+=gKeys[i].velocity.x;
            gKeys[i].position.y+=gKeys[i].velocity.y;
        }

        batch.end();


        renderer.begin();
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.end();

        batch.begin();


        font.draw(batch, "first: "+firstX, 0, 30);
        font.draw(batch, "currentx: "+currentX, 0, 60);
        font.draw(batch, "currenty: "+currentY, 0, 90);
        font.draw(batch, "first: "+firstX, 0, 120);
        easyButton.render(batch);
        mediumButton.render(batch);
        hardButton.render(batch);



        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
        renderer.dispose();
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
        Vector2 worldClick = viewport.unproject(new Vector2(screenX, screenY));
        /*if(worldClick.dst(Constants.EASY_CENTER) <= Constants.EASY_RADIUS) {
            game.setScreen(new GameScreen(game));
            Obstacle.easy = true;
            Obstacle.medium = false;
            Obstacle.hard = false;
            GameScreen.bumbleBee.play();

        }
        else if(worldClick.dst(Constants.MEDIUM_CENTER) <= Constants.MEDIUM_RADIUS) {
            game.setScreen(new GameScreen(game));
            Obstacle.easy = false;
            Obstacle.medium = true;
            Obstacle.hard = false;
        }
        else if(worldClick.dst(Constants.HARD_CENTER) <= Constants.HARD_RADIUS) {
            game.setScreen(new GameScreen(game));
            Obstacle.easy = false;
            Obstacle.medium = false;
            Obstacle.hard = true;
        }*/
        temp = worldClick;
        if(worldClick.x >= Constants.EASY_START.x-Constants.DIFFICULTY_WIDTH/2
        && worldClick.x <= Constants.EASY_END.x-Constants.DIFFICULTY_WIDTH/2
        && worldClick.y >= Constants.EASY_START.y+5
        && worldClick.y <= Constants.EASY_END.y
                ) {
            isNormal = false;
            isHover = false;
            isPushed = true;
            easyButton.setState("push");
        }
        else if(worldClick.x >= Constants.MEDIUM_START.x-Constants.DIFFICULTY_WIDTH/2
                && worldClick.x <= Constants.MEDIUM_END.x-Constants.DIFFICULTY_WIDTH/2
                && worldClick.y >= Constants.MEDIUM_START.y+5
                && worldClick.y <= Constants.MEDIUM_END.y
                ) {
            isNormal = false;
            isHover = false;
            isPushed = true;
            mediumButton.setState("push");
        }
        else if(worldClick.x >= Constants.HARD_START.x-Constants.DIFFICULTY_WIDTH/2
                && worldClick.x <= Constants.HARD_END.x-Constants.DIFFICULTY_WIDTH/2
                && worldClick.y >= Constants.HARD_START.y+5
                && worldClick.y <= Constants.HARD_END.y
                ) {
            isNormal = false;
            isHover = false;
            isPushed = true;
            hardButton.setState("push");
        }
        tempClick.x = screenX;
        tempClick.y = screenY;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 worldClick = viewport.unproject(new Vector2(screenX, screenY));
        if(worldClick.x >= Constants.EASY_START.x-Constants.DIFFICULTY_WIDTH/2
        && worldClick.x <= Constants.EASY_END.x-Constants.DIFFICULTY_WIDTH/2
        && worldClick.y >= Constants.EASY_START.y+5
        && worldClick.y <= Constants.EASY_END.y
                ) {
            isNormal = false;
            isHover = true;
            isPushed = false;
            easyButton.setState("hover");
            game.setScreen(new GameScreen(game));
            Obstacle.easy = true;
            Obstacle.medium = false;
            Obstacle.hard = false;
            GameScreen.bumbleBee.play();
        }
        else if(worldClick.x >= Constants.MEDIUM_START.x-Constants.DIFFICULTY_WIDTH/2
                && worldClick.x <= Constants.MEDIUM_END.x-Constants.DIFFICULTY_WIDTH/2
                && worldClick.y >= Constants.MEDIUM_START.y+5
                && worldClick.y <= Constants.MEDIUM_END.y
                ) {
            isNormal = false;
            isHover = true;
            isPushed = false;
            mediumButton.setState("hover");
            game.setScreen(new GameScreen(game));
            Obstacle.easy = false;
            Obstacle.medium = true;
            Obstacle.hard = false;
            GameScreen.bumbleBee.play();
        }
        else if(worldClick.x >= Constants.HARD_START.x-Constants.DIFFICULTY_WIDTH/2
                && worldClick.x <= Constants.HARD_END.x-Constants.DIFFICULTY_WIDTH/2
                && worldClick.y >= Constants.HARD_START.y+5
                && worldClick.y <= Constants.HARD_END.y
                ) {
            isNormal = false;
            isHover = true;
            isPushed = false;
            hardButton.setState("hover");
            game.setScreen(new GameScreen(game));
            Obstacle.easy = false;
            Obstacle.medium = false;
            Obstacle.hard = true;
            GameScreen.bumbleBee.play();
        }
        else {
            isNormal = true;
            isHover = false;
            isPushed = false;
            easyButton.setState("normal");
            mediumButton.setState("normal");
            hardButton.setState("normal");
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {

        //screenX = Gdx.graphics.getWidth()-screenX;
        screenY = Gdx.graphics.getHeight()-screenY;
        currentX = screenX;
        currentY = screenY;
        if(screenX >= Constants.EASY_START.x-Constants.DIFFICULTY_WIDTH/2
        && screenX <= Constants.EASY_END.x-Constants.DIFFICULTY_WIDTH/2
        && screenY >= Constants.EASY_START.y+5
        && screenY <= Constants.EASY_END.y
                ) {
            isNormal = false;
            isHover = true;
            isPushed = false;
            easyButton.setState("hover");
        }
        else if(screenX >= Constants.MEDIUM_START.x-Constants.DIFFICULTY_WIDTH/2
                && screenX <= Constants.MEDIUM_END.x-Constants.DIFFICULTY_WIDTH/2
                && screenY >= Constants.MEDIUM_START.y+5
                && screenY <= Constants.MEDIUM_END.y
                ) {
            isNormal = false;
            isHover = true;
            isPushed = false;
            mediumButton.setState("hover");
        }
        else if(screenX >= Constants.HARD_START.x-Constants.DIFFICULTY_WIDTH/2
                && screenX <= Constants.HARD_END.x-Constants.DIFFICULTY_WIDTH/2
                && screenY >= Constants.HARD_START.y+5
                && screenY <= Constants.HARD_END.y
                ) {
            isNormal = false;
            isHover = true;
            isPushed = false;
            hardButton.setState("hover");
        }
        else {
            isNormal = true;
            isHover = false;
            isPushed = false;
            easyButton.setState("normal");
            mediumButton.setState("normal");
            hardButton.setState("normal");
        }




        tempHover.x = screenX;
        tempHover.y = screenY;
        return false;


    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

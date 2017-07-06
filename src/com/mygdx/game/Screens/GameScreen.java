package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Objects.Ball;
import com.mygdx.game.Objects.Control;
import com.mygdx.game.GameClass;
import com.mygdx.game.Objects.Obstacle;

import java.text.DecimalFormat;


/**
 * Created by Efe on 6/23/2017.
 */

public class GameScreen implements Screen, InputProcessor {
    /*Declerations*/
    public static final float WORLD_SIZE = 480.0f;
    float r,g,b;
    long initialTime;
    int interval, iteration;
    float cyclePosition;
    GameClass game;
    SpriteBatch batch;
    BitmapFont font;
    ShapeRenderer renderer;
    ExtendViewport viewport;
    Ball ball;
    Control control;
    Vector2 targetPosition;
    Vector2 dstVector;
    Obstacle obstacle;
    DecimalFormat df;
    Texture background;
    static Sound bumbleBee;
    public GameScreen(GameClass game){ this.game = game; }
    //TODO: Screen methods.
    /*Inıtializing*/
    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();//SpriteBatch initialized.
        font = new BitmapFont();//BitmapFont initialized.
        viewport = new ExtendViewport(WORLD_SIZE, WORLD_SIZE);//Renderer initialized.
        renderer = new ShapeRenderer();//Renderer initialized.
        ball = new Ball(viewport);//Ball and Control initialized.
        this.control = new Control(viewport, 0, 0);//Ball and Control initialized.
        initialTime  = TimeUtils.nanoTime();
        df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        obstacle = new Obstacle();
        bumbleBee = Gdx.audio.newSound(Gdx.files.internal("bumblebee.mp3"));
        background = new Texture("inn.jpg");

    }

    @Override
    public void render(float delta) {

        /**Time**/
        float elapsedNanoSeconds = TimeUtils.nanoTime() - initialTime;
        float elapsedSeconds = (elapsedNanoSeconds * MathUtils.nanoToSec);
        float elapsedPeriods = elapsedSeconds/interval;
        cyclePosition = elapsedPeriods%1;
        viewport.apply();
        game.clearBlack();

        batch.begin();
        batch.setColor(batch.getColor().r, batch.getColor().g, batch.getColor().b, 0.35f);

        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.end();
        /**Renderer begin**/
        renderer.setAutoShapeType(true);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin();
        renderer.set(ShapeRenderer.ShapeType.Filled);
        rendering();
        renderer.end();
        /**Renderer end**/
        if(collisionHappened() == true) {
            game.setScreen(new GameOverScreen(game));
            bumbleBee.stop();
        }
        /**Batch begin**/

        batch.begin();
        font.draw(batch, "Score: "+obstacle.position.x, viewport.getWorldWidth()-110,viewport.getWorldHeight()-20);
        font.draw(batch, "Score: "+obstacle.score, 0, viewport.getWorldHeight()-20);
        font.draw(batch, "r: "+r, 0, 30);

        batch.end();
        /**Functions**/
        ballPosition(delta);
        obstacle.obstaclePosition(delta);
        r = MathUtils.random();
        g = MathUtils.random();
        b = MathUtils.random();

        ball.COLOR.set(r,g,b,1);
    }

    public void ballPosition(float delta){
        ball.update(delta, viewport);
        ball.velocity.x=(control.innerCircleOrj.x)*15;
        ball.velocity.y=(control.innerCircleOrj.y)*15;
        if(ball.position.x-ball.radius <= 0 ) {
            ball.position.x = ball.radius;
            if(ball.position.y-ball.radius <= 0)
                ball.position.y = ball.radius;
            else if(ball.position.y+ball.radius >= viewport.getWorldHeight())
                ball.position.y = viewport.getWorldHeight()-ball.radius;
        }
        else if(ball.position.x+ball.radius >= viewport.getWorldWidth()) {
            ball.position.x =  viewport.getWorldWidth() - ball.radius;
            if(ball.position.y-ball.radius <= 0)
                ball.position.y = ball.radius;
            else if(ball.position.y+ball.radius >= viewport.getWorldHeight())
                ball.position.y = viewport.getWorldHeight()-ball.radius;
        }
        else if(ball.position.y-ball.radius <= 0)
            ball.position.y = ball.radius;
        else if(ball.position.y+ball.radius >= viewport.getWorldHeight())
            ball.position.y = viewport.getWorldHeight()-ball.radius;
    }

    public void rendering(){
        control.render(renderer);
        //ball.render(renderer);
        ball.renderStar(renderer);
        obstacle.renderOmer(renderer);
    }
    public boolean collisionHappened(){
        if(obstacle.position.dst(ball.position) <= ball.radius+obstacle.radius)
            return true;
        else
            return false;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        ball.init(viewport);



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
        renderer.dispose();
        batch.dispose();
        font.dispose();
        bumbleBee.dispose();
    }







    //TODO: Input controlling.

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
        if(button == Input.Buttons.LEFT){
            if(this.control==null)
                control = new Control(viewport, worldClick.x, worldClick.y);
            else {

                this.control.position = worldClick;
                this.control.tempVector = this.control.position;
            }
        }




        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        targetPosition = viewport.unproject(new Vector2(screenX, screenY));
        control.tempVector = targetPosition;
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

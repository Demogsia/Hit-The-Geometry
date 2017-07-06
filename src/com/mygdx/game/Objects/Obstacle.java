package com.mygdx.game.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Constants;

import java.util.Vector;

/**
 * Created by Efe on 6/26/2017.
 */

public class Obstacle {
    public Vector2 position;
    public Vector2 velocity;
    public Vector2 dstVector;

    public static final int radius = 10;
    public static final Color COLOR = Color.BLUE;
    float x,y;
    int segments, i, k;
    public static int score=0;
    public static boolean easy, medium, hard = false;
    Texture texture;
    SpriteBatch batch;
    Sound bumbleBee;
    public Obstacle(){
        init();
    }
    public void init(){
        texture = new Texture("monster.png");
        batch = new SpriteBatch();

        i = (int)(1+Math.random()*4);
        k = (int)(3+Math.random()*3);
        segments = k;
        switch (i){
            case 1:
                x=(float)Math.random()*640;y=0;
                break;
            case 2:
                x=640;y=(float)Math.random()*480;
                break;
            case 3:
                x=(float)Math.random()*640;y=480;
                break;
            case 4:
                x=0;y=(float)Math.random()*480;
                break;
        }

        position = new Vector2(x, y);
        velocity = new Vector2();
        dstVector = new Vector2(320-position.x, 240-position.y);


    }

    public void render(ShapeRenderer renderer){
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(COLOR);
        renderer.circle(position.x, position.y, radius, segments);

    }
    public void renderOmer(ShapeRenderer renderer){
        batch.begin();
        batch.draw(texture, position.x, position.y);
        batch.end();
    }
    public void obstaclePosition(float delta){
        if(easy == true) {
            velocity.x = (dstVector.x / Constants.DIFFICULTY_COFACTOR_EASY) * delta;
            velocity.y = (dstVector.y / Constants.DIFFICULTY_COFACTOR_EASY) * delta;
        }
        else if(medium == true) {
            velocity.x = (dstVector.x / Constants.DIFFICULTY_COFACTOR_MEDIUM) * delta;
            velocity.y = (dstVector.y / Constants.DIFFICULTY_COFACTOR_MEDIUM) * delta;
        }
        else if(hard == true) {
            velocity.x = (dstVector.x / Constants.DIFFICULTY_COFACTOR_HARD) * delta;
            velocity.y = (dstVector.y / Constants.DIFFICULTY_COFACTOR_HARD) * delta;
        }
        position.x+=velocity.x;
        position.y+=velocity.y;
        if(position.x < 0 || position.x > 640 || position.y < 0 || position.y > 480) {
            init();
            score++;
        }

    }



}

package com.mygdx.game.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;



/**
 * Created by Efe on 7/5/2017.
 */

public class BackgroundThing {
    Texture texture;
    SpriteBatch batch;
    public Vector2 velocity;
    public Vector2 position;
    //public float xCoord, yCoord;
    public BackgroundThing(){ init(); }

    public void init(){
        texture = new Texture("g.png");
        batch = new SpriteBatch();
        position = new Vector2();
        velocity = new Vector2();
        position.x = (float)(Gdx.graphics.getWidth()*Math.random());
        position.y = (float)(Gdx.graphics.getHeight()*Math.random());
        velocity.x = (float)(Math.random()*4)-3;
        velocity.y = (float)(Math.random()*4)-3;


    }
    public void render(SpriteBatch batch){
        batch.draw(texture, position.x, position.y);
        if(position.x <=0 || position.x >= Gdx.graphics.getWidth())
            velocity.x = -velocity.x;
        else if(position.y <=0 || position.y >= Gdx.graphics.getHeight())
            velocity.y = -velocity.y;
    }
}

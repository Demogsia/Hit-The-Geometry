package com.mygdx.game.Objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Efe on 6/19/2017.
 */

public class Control {

    public static final float INNER_RADIUS = 10;
    public static final float OUTER_RADIUS = 20;
    float dst = 0;
    float angle = 0;
    float ratio;
    double angleRad;
    double angleDeg;
    public Vector2 position;
    public Vector2 tempVector;
    public Vector2 innerCircle;
    public Vector2 innerCircleOrj;
    public Vector2 innerCircleTemp;
    public Vector2 speedVector;

    public Control(Viewport viewport, float screenX, float screenY){
        init(viewport, screenX, screenY);
    }
    public void init(Viewport viewport, float screenX, float screenY){
        ratio = 0;
        position = new Vector2(0,0);
        position.x = screenX;
        position.y = screenY;
        innerCircle = new Vector2();
        innerCircleOrj = new Vector2();
        innerCircleTemp = new Vector2();
        tempVector = new Vector2(0,0);
        speedVector = new Vector2(0,0);




        tempVector = position;



    }
    public void update(float delta, Viewport viewport){

    }
    public void render(ShapeRenderer renderer){


        renderer.set(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.WHITE);
        renderer.circle(position.x, position.y, INNER_RADIUS);
        renderer.circle(position.x, position.y, OUTER_RADIUS);
        renderer.line(position.x, position.y, position.x+OUTER_RADIUS, position.y);
        renderer.line(position.x, position.y, position.x, position.y+OUTER_RADIUS);
        renderer.line(position.x, position.y, position.x-OUTER_RADIUS, position.y);
        renderer.line(position.x, position.y, position.x, position.y-OUTER_RADIUS);

        dst = position.dst(tempVector);



        angleRad = (float)(Math.atan2(tempVector.y-position.y, tempVector.x-position.x));
        angleDeg = (float)angleRad/(Math.PI)*180;

        ratio = OUTER_RADIUS/tempVector.dst(position);
        if(ratio == Float.POSITIVE_INFINITY){
            ratio = 20;
        }
        renderer.set(ShapeRenderer.ShapeType.Filled);
        if(dst <=OUTER_RADIUS){
            innerCircle.x = position.x+((float)Math.cos(angleRad)*(tempVector.dst(position)));
            innerCircle.y = position.y+((float)Math.sin(angleRad)*(tempVector.dst(position)));
        }
        else{
            innerCircle.x = position.x+((float)Math.cos(angleRad)*OUTER_RADIUS);
            innerCircle.y = position.y+((float)Math.sin(angleRad)*OUTER_RADIUS);
        }

        innerCircleOrj.x = innerCircle.x - position.x;
        innerCircleOrj.y = innerCircle.y - position.y;

        if(dst <=OUTER_RADIUS){
            renderer.circle(tempVector.x, tempVector.y, INNER_RADIUS);

        }
        else{

            renderer.circle(innerCircle.x, innerCircle.y, INNER_RADIUS);
        }
    }
}

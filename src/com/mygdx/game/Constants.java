package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by Efe on 7/2/2017.
 */

public abstract class Constants {
    /**Difficulty colors.**/
    public static final Color EASY_COLOR = new Color(0.3f, 0, 0, 1);
    public static final Color MEDIUM_COLOR = new Color(0.7f, 0, 0, 1);
    public static final Color HARD_COLOR = new Color(1.0f, 0, 0, 1);
    /**Difficulty coordinates(Circle)**/
    public static final Vector2 EASY_CENTER = new Vector2(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/2);
    public static final Vector2 MEDIUM_CENTER = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
    public static final Vector2 HARD_CENTER = new Vector2((Gdx.graphics.getWidth()*3)/4, Gdx.graphics.getHeight()/2);
    /**Difficulty coordinates start(Image)**/
    public static final Vector2 EASY_START = new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()*3/4-Constants.DIFFICULTY_HEIGHT);
    public static final Vector2 MEDIUM_START = new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()*2/4-Constants.DIFFICULTY_HEIGHT);
    public static final Vector2 HARD_START = new Vector2(Gdx.graphics.getWidth()/2,(Gdx.graphics.getHeight())/4-Constants.DIFFICULTY_HEIGHT);

    /**Difficulty radiuses**/
    public static final float EASY_RADIUS = 50.0f;
    public static final float MEDIUM_RADIUS = 50.0f;
    public static final float HARD_RADIUS = 50.0f;
    /**Difficulty labels**/
    public static final String EASY_LABEL = "Hard";
    public static final String MEDIUM_LABEL = "Harder";
    public static final String HARD_LABEL = "Hardest";
    /**Difficulty label coordinates**/
    public static final Vector2 EASY_LABEL_COORDINATE = new Vector2(Gdx.graphics.getWidth()/4-EASY_RADIUS/2, Gdx.graphics.getHeight()/2);
    public static final Vector2 MEDIUM_LABEL_COORDINATE = new Vector2(Gdx.graphics.getWidth()/2-MEDIUM_RADIUS/2, Gdx.graphics.getHeight()/2);
    public static final Vector2 HARD_LABEL_COORDINATE = new Vector2((Gdx.graphics.getWidth()*3)/4-HARD_RADIUS/2, Gdx.graphics.getHeight()/2);

    public static final float DIFFICULTY_COFACTOR_EASY = 3.0f;
    public static final float DIFFICULTY_COFACTOR_MEDIUM = 2.0f;
    public static final float DIFFICULTY_COFACTOR_HARD = 1.0f;

    public static final float DIFFICULTY_WIDTH = 142;
    public static final float DIFFICULTY_HEIGHT = 40;
    /**Difficulty coordinates end(Image)**/
    public static final Vector2 EASY_END = new Vector2(EASY_START.x+DIFFICULTY_WIDTH, EASY_START.y+DIFFICULTY_HEIGHT);
    public static final Vector2 MEDIUM_END = new Vector2(MEDIUM_START.x+DIFFICULTY_WIDTH, MEDIUM_START.y+DIFFICULTY_HEIGHT);
    public static final Vector2 HARD_END = new Vector2(HARD_START.x+DIFFICULTY_WIDTH, HARD_START.y+DIFFICULTY_HEIGHT);


}

package com.mygdx.game.Buttons.DifficultyButtons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Constants;


/**
 * Created by Efe on 7/5/2017.
 */

public class MediumButton {
    SpriteBatch batch;
    Texture button1Normal;
    Texture button1Hover;
    Texture button1Push;
    public String state;
    public MediumButton(){
        init();
    }
    public void init(){
        batch = new SpriteBatch();
        button1Normal = new Texture("button2-normal.png");
        button1Hover = new Texture("button2-hover.png");
        button1Push = new Texture("button2-push.png");
        state = "normal";

    }
    public void setState(String currentState){
        this.state = currentState;
    }
    public String getState(){
        return this.state;
    }
    public void render(SpriteBatch batch){

        if(getState() == "normal")
            batch.draw(button1Normal, Constants.MEDIUM_START.x-Constants.DIFFICULTY_WIDTH/2, Constants.MEDIUM_START.y);
        else if(getState() == "hover")
            batch.draw(button1Hover, Constants.MEDIUM_START.x-Constants.DIFFICULTY_WIDTH/2, Constants.MEDIUM_START.y);
        else if(getState() == "push")
            batch.draw(button1Push, Constants.MEDIUM_START.x-Constants.DIFFICULTY_WIDTH/2, Constants.MEDIUM_START.y);

    }

}

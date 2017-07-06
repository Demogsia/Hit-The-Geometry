package com.mygdx.game.Objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Efe on 6/23/2017.
 */

public class Ball {
    public static final int radius = 20;
    public Color COLOR = Color.RED;
    public Vector2 position;
    public Vector2 velocity;
    Texture texture;
    SpriteBatch batch;

    public Ball(Viewport viewport){ init(viewport); }

    public void init(Viewport viewport){
        position = new Vector2(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2);
        velocity = new Vector2(0, 0);
        texture = new Texture("starBig.png");
        batch = new SpriteBatch();
    }
    public void update(float delta, Viewport viewport){
        position.x += delta * velocity.x;
        position.y += delta * velocity.y;
    }
    public void render(ShapeRenderer renderer){
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(COLOR);
        renderer.circle(position.x, position.y, radius);
    }
    public void renderStar(ShapeRenderer renderer){
        batch.begin();
        batch.draw(texture, position.x, position.y);
        batch.end();
    }
}

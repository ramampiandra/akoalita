package com.comteen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends Game {

    public SpriteBatch batch;

    public void create() {
        batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
    }
}

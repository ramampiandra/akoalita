package com.comteen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class MainMenuScreen implements Screen {

    private OrthographicCamera camera;
    private MainGame mainGame;
    private Texture boardImage;
    private Sprite spriteOne, spriteTwo;

    public MainMenuScreen(final MainGame game) {
        this.mainGame = game;

        boardImage = new Texture("board.png");
        spriteOne = new Sprite(new Texture("dark.png"));
        spriteTwo = new Sprite(new Texture("light.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        mainGame.batch.setProjectionMatrix(camera.combined);

        mainGame.batch.begin();
        mainGame.batch.draw(boardImage,0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        mainGame.batch.draw(spriteOne,140,560, 80,80);
        mainGame.batch.draw(spriteTwo,200,100,80,80);

        mainGame.batch.end();


        final Vector3 testPoint = new Vector3();
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return true;
            }

            public boolean touchUp (int screenX, int screenY, int pointer, int button) {
                Vector3 position = new Vector3(screenX, screenY, 0);
                camera.unproject(position);
                spriteOne.translateX(10f);
                return true;
            }
        });
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

    }
}

package com.comteen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


public class AkoalitaGame extends ApplicationAdapter {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture boardImage;
	private Sprite spriteOne, spriteTwo;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		boardImage = new Texture("board.png");
		spriteOne = new Sprite(new Texture("dark.png"));
		spriteTwo = new Sprite(new Texture("light.png"));

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// tell the camera to update its matrices.
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(boardImage,0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		batch.draw(spriteOne,140,560, 80,80);
		batch.draw(spriteTwo,200,100,80,80);

		batch.end();


		final Vector3 testPoint = new Vector3();
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				testPoint.set(screenX, screenY, 0);
				camera.unproject(testPoint);
				if(spriteOne.getBoundingRectangle().contains(screenX, screenY))
					System.out.println("Touch Down");
				return true;
			}

			public boolean touchUp (int screenX, int screenY, int pointer, int button) {
				if(spriteOne.getBoundingRectangle().contains(screenX, screenY))
					System.out.println("Touch Up");
				return true;
			}
		});
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		boardImage.dispose();
	}
}

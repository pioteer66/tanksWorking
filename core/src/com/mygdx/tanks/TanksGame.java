package com.mygdx.tanks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TanksGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture czolg_ziel,czolg_czer, czolg_nieb, czolg_pom;
	Texture blok,  krzak, mur;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		blok = new Texture("blok.png");
		czolg_czer = new Texture("czolg_czer.png");
		czolg_nieb = new Texture("czolg_nieb.png");
		czolg_pom = new Texture("czolg_pom.png");
		czolg_ziel = new Texture("czolg_ziel.png");
		krzak = new Texture("krzak.png");
		mur = new Texture("mur.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(blok, 0, 0);
		batch.draw(czolg_czer, 25,0);
		batch.draw(czolg_nieb, 50,0);
		batch.draw(czolg_pom, 75,0);
		batch.draw(czolg_ziel, 100, 0);
		batch.draw(krzak, 125, 0);
		batch.draw(mur, 150, 0);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose() {
		batch.dispose();
		blok.dispose();
		czolg_czer.dispose();
		czolg_nieb.dispose();
		czolg_pom.dispose();
		czolg_ziel.dispose();
		krzak.dispose();
		mur.dispose();


	}

	public TanksGame() {
		super();
	}
}

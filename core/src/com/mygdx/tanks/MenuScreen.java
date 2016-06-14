package com.mygdx.tanks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import model.Tank;

public class MenuScreen implements Screen {
    private Stage stage;
    private Table table;
    private TanksGame game;
    private Skin skin;
    private SpriteBatch batch;
    private Image logo;
    private TextField textField;

    public MenuScreen(TanksGame game){
        this.game=game;
        create();
    }
    public void create(){
        stage= new Stage();
        skin= new Skin(Gdx.files.internal("uiskin.json"));
        logo=  new Image(new Texture("logo.png"));
        logo.setPosition(150, 500);
        logo.setHeight(250);
        logo.setWidth(500);
        batch= new SpriteBatch();
        final TextButton textButton= new TextButton("Join the game", skin);
        Label label =new Label("Enter game ip:", skin);
        label.setPosition(Gdx.graphics.getWidth()/2 -100, Gdx.graphics.getHeight()/2);
        textField= new TextField("",skin);
        textField.setPosition(Gdx.graphics.getWidth()/2 -100, Gdx.graphics.getHeight()/2 -30);
        textField.setWidth(200);
        textField.setHeight(30);
        textButton.setWidth(200);
        textButton.setHeight(20);
        textButton.setPosition(Gdx.graphics.getWidth()/2 -100, Gdx.graphics.getHeight()/2 -60);
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.nextScreen();
            }
        });
        stage.addActor(textButton);
        stage.addActor(textField);
        stage.addActor(label);
        stage.addActor(logo);
        Gdx.input.setInputProcessor(stage);

        //Sprite sprite= new Sprite(new Texture("czerwonyCzolg.png"));



        /*
        batch=new SpriteBatch();
        stage=new Stage();
        Gdx.input.setInputProcessor(stage);
        skin=new Skin();
        BitmapFont bitmapFont = new BitmapFont();
        skin.add("default", bitmapFont);
        //TextureAtlas buttonAtlas = new TextureAtlas("czerwonyCzolg.png");
        //skin.addRegions(buttonAtlas);
        /*
        TextButton.TextButtonStyle textButtonStyle= new TextButton.TextButtonStyle();
        textButtonStyle.font=bitmapFont;
        textButtonStyle.up = skin.getDrawable("czerwonyCzolg.png");
        textButtonStyle.down = skin.getDrawable("czerwonyCzolg.png");
        textButtonStyle.checked = skin.getDrawable("czerwonyCzolg.png");
        Button button=new Button(skin);
        stage.addActor(button);*/

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        //stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
        batch.end();
        //Table.
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

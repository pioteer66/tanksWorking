package com.mygdx.tanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen implements Screen {
    private Stage stage;
    private Table table;
    private TanksGame game;
    private Skin skin;
    private SpriteBatch batch;
    private Image logo;
    private TextField serverField;
    private TextField portField;

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
        serverField = new TextField("",skin);
        serverField.setPosition(Gdx.graphics.getWidth()/2 -100, Gdx.graphics.getHeight()/2 -30);
        serverField.setWidth(200);
        serverField.setHeight(30);
        serverField.setText("localhost");

        Label portLabel =new Label("Enter port number:", skin);
        portLabel.setPosition(Gdx.graphics.getWidth()/2 -100, Gdx.graphics.getHeight()/2-55);
        portField = new TextField("",skin);
        portField.setPosition(Gdx.graphics.getWidth()/2 -100, Gdx.graphics.getHeight()/2-80);
        portField.setWidth(200);
        portField.setHeight(30);
        portField.setText("8088");

        textButton.setWidth(200);
        textButton.setHeight(20);
        textButton.setPosition(Gdx.graphics.getWidth()/2 -100, Gdx.graphics.getHeight()/2 -110);
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setIpAndPort(serverField.getText(), portField.getText());
                game.nextScreen();
            }
        });
        stage.addActor(textButton);
        stage.addActor(serverField);
        stage.addActor(portField);
        stage.addActor(portLabel);
        stage.addActor(label);
        stage.addActor(logo);
        Gdx.input.setInputProcessor(stage);

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

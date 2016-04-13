package com.mygdx.tanks;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import model.Blok;
import model.Czolg;
import model.Kierunek;
import model.Plansza;

public class TanksGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture czolg_ziel,czolg_czer, czolg_nieb, czolg_pom;
	Texture  zarosla, cegly, kamien;
    Czolg czolg = new Czolg(1,5, Stale.CZOLG_START_X*Stale.ROZMIAR_CZOLGU, Stale.CZOLG_START_Y*Stale.ROZMIAR_CZOLGU );
    Plansza plansza;

	@Override
	public void create () {
        plansza = new Plansza("plansza.txt");
        batch = new SpriteBatch();
        kamien= new Texture("niezniszczalny.png");
        czolg_czer = new Texture("czerwonyCzolg.png");
        czolg_nieb = new Texture("niebieskiCzolg.png");
        czolg_pom = new Texture("zoltyCzolg.png");
        czolg_ziel = new Texture("zielonyCzolg.png");
        zarosla = new Texture("krzak.png");
        cegly = new Texture("cegla.png");
        czolg.setKierunek(Kierunek.LEWO);
	}

	@Override
	public void render () {
        update();
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        double x = czolg.getX();
        double y = czolg.getY();
        batch.draw(new TextureRegion(czolg_czer), (float)x, Stale.SZEROKOSC-(float)y,
                (float)czolg.getCenterX()-(float)x, (float)czolg.getCenterY()-(float)y,
                (float)czolg.getWidth(), (float)czolg.getHeight(), 1f, 1f, (float)czolg.getKierunek().getValue()*90);
        drawBoard();
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

    public void drawBoard(){
        for (Blok obiekt:plansza.listaObiektow){
            switch (obiekt.getSymbol()){
                case 'C':{
                    batch.draw(cegly, (int)obiekt.getX(), Stale.WYSOKOSC-(int)obiekt.getY());
                    break;
                }
                case 'K':{
                    batch.draw(kamien, (int)obiekt.getX(), Stale.WYSOKOSC-(int)obiekt.getY());
                    break;
                }
                case 'Z':{
                    batch.draw(zarosla, (int)obiekt.getX(), Stale.WYSOKOSC-(int)obiekt.getY());
                    break;
                }
            }
        }
    }

    public void update(){
        int x = (int)czolg.getX();
        int y = (int)czolg.getY();
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                czolg.x--;
                czolg.setKierunek(Kierunek.LEWO);
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                czolg.x++;
                czolg.setKierunek(Kierunek.PRAWO);
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.UP)){
                czolg.y--;
                czolg.setKierunek(Kierunek.GORA);
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                czolg.y++;
                czolg.setKierunek(Kierunek.DOL);
            }
        boolean jest_kolizja = false;
        for (Blok obiekt:plansza.listaObiektow){
            if (obiekt.intersects(czolg) && obiekt.getSymbol() != 'Z') {
                jest_kolizja = true;
                break;
            }
        }
        if (jest_kolizja) {
            czolg.x = x;
            czolg.y = y;
        }
    }

	@Override
	public void dispose() {
		batch.dispose();
		kamien.dispose();
		czolg_czer.dispose();
		czolg_nieb.dispose();
		czolg_pom.dispose();
		czolg_ziel.dispose();
		zarosla.dispose();
		cegly.dispose();
	}

	public TanksGame() {
		super();
	}
}

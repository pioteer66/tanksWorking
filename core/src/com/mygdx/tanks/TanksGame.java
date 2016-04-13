package com.mygdx.tanks;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import model.*;
import java.util.Date;
import java.awt.Rectangle;
import static model.Kierunek.LEWO;

public class TanksGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture czolg_ziel,czolg_czer, czolg_nieb, czolg_pom;
	Texture  zarosla, cegly, kamien, pociskTexture;
    Czolg czolg = new Czolg(1,5, Stale.CZOLG_START_X*Stale.ROZMIAR_CZOLGU, Stale.CZOLG_START_Y*Stale.ROZMIAR_CZOLGU );
    Plansza plansza;

    /* Zmienne do przeładowywania działa */
    private Date date;
    private long czasP;
    private long czasK;
    private long przeladowanieDziala; //milisekundy
    private double predkoscPocisku;     // jednostki odswiezen

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
        pociskTexture = new Texture("pocisk.png");
        czolg.setKierunek(LEWO);

        this.date = new Date();
        this.czasP = date.getTime();
        this.czasK = date.getTime();
        this.przeladowanieDziala = 800; //ms
        this.predkoscPocisku = 200.0; // jednostek
	}

	@Override
	public void render () {
        update();
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(cegly, 775,775);
        double x = czolg.getX();
        double y = czolg.getY();
        batch.draw(new TextureRegion(czolg_czer), (float)x, (float)y,
                (float)czolg.getCenterX()-(float)x, (float)czolg.getCenterY()-(float)y,
                (float)czolg.getWidth(), (float)czolg.getHeight(), 1f, 1f, (float)czolg.getKierunek().getValue()*90);
        drawMissiles();
        updateMissilesState();
        drawBoard();
        batch.end();
        this.date = new Date(); // aktualizuje czas
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

    private void drawBoard(){
        Rectangle rect;
        for (Blok obiekt:plansza.listaObiektow){
            switch (obiekt.getSymbol()){
                case 'C':{
                    batch.draw(cegly, (int)obiekt.getX(), (int)obiekt.getY());
                    break;
                }
                case 'K':{
                    batch.draw(kamien, (int)obiekt.getX(), (int)obiekt.getY());
                    break;
                }
                case 'Z':{
                    batch.draw(zarosla, (int)obiekt.getX(), (int)obiekt.getY());
                    break;
                }
            }

            // szybka kolizja pociskow -- potem zastapi ja serwer
            if(obiekt.getSymbol() != 'Z') {
                rect = new Rectangle((int) obiekt.getX(), (int) obiekt.getY() + 25, 25, 25);
                for (int i = 0; i < this.plansza.listaPociskow.size(); i++) {
                    if (rect.contains(this.plansza.listaPociskow.get(i).getCenterX(), this.plansza.listaPociskow.get(i).getCenterY())) {
                        this.plansza.listaPociskow.remove(i);
                    }
                }
            }
        }
    }

    private void drawMissiles(){
        double fWsp = this.predkoscPocisku *( 1.0 / Gdx.graphics.getFramesPerSecond()); // predkosc = jednoski / ramke
        //Podobna funkcja jak dla rysowania czołgu
        for (Pocisk pocisk:plansza.listaPociskow){
            batch.draw(new TextureRegion(pociskTexture),
                    (float) pocisk.getX(), (float) pocisk.getY(),
                    (float) pocisk.getCenterX()-(float) pocisk.getX(), (float) pocisk.getCenterY()-(float) pocisk.getY(),
                    (float) pocisk.getWidth(), (float) pocisk.getHeight(),
                    1f, 1f,
                    (float) pocisk.getKierunek().getValue()*90);

            switch ( pocisk.getKierunek()){
                case LEWO: {
                    pocisk.x -= fWsp;
                    break;
                }
                case PRAWO: {
                    pocisk.x += fWsp;
                    break;
                }
                case GORA: {
                    pocisk.y += fWsp;
                    break;
                }
                case DOL: {
                    pocisk.y -= fWsp;
                    break;
                }
            }
        }
    }

    private void updateMissilesState(){
        //Usuwanie pocisków po wylocie z planszy
        for (int i=0; i<plansza.listaPociskow.size(); i++){
            Pocisk pocisk = plansza.listaPociskow.get(i);
            switch(pocisk.getKierunek()){
                case LEWO: {
                    if (pocisk.getX() <= 0) plansza.listaPociskow.remove(i);
                    break;
                }
                case PRAWO: {
                    if (pocisk.getX() >= Stale.SZEROKOSC) plansza.listaPociskow.remove(i);
                    break;
                }
                case GORA: {
                    if (pocisk.getY() >= Stale.WYSOKOSC) plansza.listaPociskow.remove(i);
                    break;
                }
                case DOL: {
                    if (pocisk.getY() <= 0) plansza.listaPociskow.remove(i);
                    break;
                }
            }
        }
    }
    private void launchMissile(){
        if(this.czasP >= czasK) { /* sprawdza czy upłyna rzadany czas przaładowania */
            int start_x = 0;
            int start_y = 0;
            switch (czolg.getKierunek()) {
                case LEWO: {
                    start_x = (int) (czolg.getX());
                    start_y = (int) (czolg.getY() + czolg.height / 2 - 5);
                    break;
                }
                case PRAWO: {
                    start_x = (int) (czolg.getX() + czolg.width);
                    start_y = (int) (czolg.getY() + czolg.height / 2 - 5);
                    break;
                }
                case GORA: {
                    start_x = (int) (czolg.getX() + czolg.width / 2 - 5);
                    start_y = (int) (czolg.getY() + czolg.height);
                    break;
                }
                case DOL: {
                    start_x = (int) (czolg.getX() + czolg.width / 2 - 5);
                    start_y = (int) (czolg.getY());
                    break;
                }

            }
            //start_x i start_y to początkowa pozycja pocisku
            Pocisk pocisk = new Pocisk(czolg, czolg.getKierunek());
            pocisk.x = start_x;
            pocisk.y = start_y;
            plansza.listaPociskow.add(pocisk);
            czasK = this.date.getTime() + this.przeladowanieDziala;
        }
    }
    private void collisionDetector(int x, int y){
        //uniemożliwienie wyjechania poza planszę
        if (czolg.getX() >= Stale.SZEROKOSC-Stale.ROZMIAR_CZOLGU || czolg.getX() <=0 ||
            czolg.getY() <= 0 || czolg.getY() >= Stale.WYSOKOSC-Stale.ROZMIAR_CZOLGU)
        {
            czolg.x = x;
            czolg.y = y;
        } else { /* //sprawdzenie kolizji, czyli dzięki temu czołg nie wjeżdża na bloki (chyba, że to zarośla) */
            boolean jest_kolizja = false;
            for (Blok obiekt : plansza.listaObiektow)
            {
                if (obiekt.intersection(czolg).width >2 && obiekt.intersection(czolg).height >2 && obiekt.getSymbol() != 'Z')
                {
                    jest_kolizja = true;
                    break;
                }
            }

            if (jest_kolizja) /*  //Jeśli wystąpiła kolizja z blokiem, to cofnij na pole sprzed zmiany */
            {
                czolg.x = x;
                czolg.y = y;
            }
        }
    }

    private void update(){
        this.czasP = date.getTime();
        int x = (int)czolg.getX();
        int y = (int)czolg.getY();
        //Odczyt klawiszy
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                czolg.x-=Stale.PREDKOSC_CZOLGU ;
                czolg.setKierunek(LEWO);
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                czolg.x+=Stale.PREDKOSC_CZOLGU;
                czolg.setKierunek(Kierunek.PRAWO);
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.UP)){
                czolg.y+=Stale.PREDKOSC_CZOLGU;
                czolg.setKierunek(Kierunek.GORA);
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                czolg.y-=Stale.PREDKOSC_CZOLGU;
                czolg.setKierunek(Kierunek.DOL);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                this.launchMissile();
            }
        collisionDetector(x,y);
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
        pociskTexture.dispose();
		cegly.dispose();
	}

	public TanksGame() {
		super();
	}
}

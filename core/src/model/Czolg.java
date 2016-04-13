package model;


import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Dawid on 2016-04-12.
 */
public class Czolg extends Rectangle{
    private int idGracza;
    private int zycia;
    private Kierunek kierunek;
    private boolean czyWRuchu;

    public Kierunek getKierunek() {
        return kierunek;
    }

    public void setKierunek(Kierunek kierunek) {
        this.kierunek = kierunek;
    }

    public int getZycia() {
        return zycia;
    }

    public void setZycia(int zycia) {
        this.zycia = zycia;
    }

    public int getIdGracza() {
        return idGracza;
    }

    public void setIdGracza(int idGracza) {
        this.idGracza = idGracza;
    }

    public boolean isCzyWRuchu() {
        return czyWRuchu;
    }

    public void setCzyWRuchu(boolean czyWRuchu) {
        this.czyWRuchu = czyWRuchu;
    }



    public Czolg(int idGracza,int iloscZyc, int polozenieX, int polozenieY){
        super(polozenieX,polozenieY,100,100);
        this.idGracza=idGracza;
        this.zycia=iloscZyc;
        this.czyWRuchu=false;
        this.kierunek=Kierunek.GORA;
    }
}

package model;


import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Dawid on 2016-04-12.
 */
public class Czolg {
    private int zycia;
    private Point polozenie;
    private Point polozenieNaPlanszy;
    private int idGracza;
    private Kierunek kierunek;
    private boolean czyWRuchu;
    private Texture tekstura;

    public Czolg(int idGracza,int iloscZyc, Point polozeniePoczatkowe){
        this.idGracza=idGracza;
        this.zycia=iloscZyc;
        this.polozenie=polozeniePoczatkowe;
        this.czyWRuchu=false;
        this.kierunek=null;
        this.tekstura = null;
        this.polozenieNaPlanszy = polozeniePoczatkowe;
    }
    public int getZycia() {
        return zycia;
    }

    public void setZycia(int zycia) {
        this.zycia = zycia;
    }
    //zwraca true gdy czolg ma wiecej niz 0 zyc
    public boolean odejmijZycieISprawdz(){
        this.zycia--;
        if(zycia>0)
            return true;
        else
            return false;
    }

    public Point getPolozenie() {
        return polozenie;
    }

    public void setPolozenie(double x, double y) {
        polozenie.setLocation(x,y);
    }

    public boolean isCzyWRuchu() {
        return czyWRuchu;
    }

    public void setCzyWRuchu(boolean czyWRuchu) {
        this.czyWRuchu = czyWRuchu;
    }

    public int getIdGracza() {
        return idGracza;
    }

    public void setIdGracza(int idGracza) {
        this.idGracza = idGracza;
    }

    public Kierunek getKierunek() {
        return kierunek;
    }

    public void setKierunek(Kierunek kierunek) {
        this.kierunek = kierunek;
    }


    public Texture getTekstura() {
        return tekstura;
    }

    public void setTekstura(Texture tekstura) {
        this.tekstura = tekstura;
    }

    public Point getPolozenieNaPlanszy() {
        return polozenieNaPlanszy;
    }

    public void setPolozenieNaPlanszy(Point polozenieNaPlanszy) {
        this.polozenieNaPlanszy = polozenieNaPlanszy;
    }
}

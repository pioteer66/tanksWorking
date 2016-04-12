package model;


import java.awt.*;

/**
 * Created by Dawid on 2016-04-12.
 */
public class Czolg {
    private int zycia;
    private Point polozenie;
    private int idGracza;
    private Kierunek kierunek;
    private boolean czyWRuchu;

    public Czolg(int idGracza,int iloscZyc, Point polozeniePoczatkowe){
        this.idGracza=idGracza;
        this.zycia=iloscZyc;
        this.polozenie=polozeniePoczatkowe;
        this.czyWRuchu=false;
        this.kierunek=null;
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

    public void setPolozenie(Point polozenie) {
        this.polozenie = polozenie;
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





}

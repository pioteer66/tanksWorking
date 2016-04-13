package model;

import java.awt.*;

/**
 * Created by Dawid on 2016-04-12.
 */
public class Pocisk extends Rectangle {
    private Kierunek kierunek;
    private Czolg wystrzelil;
    public Pocisk(Czolg wystrzelil){
        this.kierunek=wystrzelil.getKierunek();
        this.wystrzelil=wystrzelil;
    }

    public Kierunek getKierunek() {
        return kierunek;
    }

    public void setKierunek(Kierunek kierunek) {
        this.kierunek = kierunek;
    }

    public Czolg getWystrzelil() {
        return wystrzelil;
    }

    public void setWystrzelil(Czolg wystrzelil) {
        this.wystrzelil = wystrzelil;
    }
}

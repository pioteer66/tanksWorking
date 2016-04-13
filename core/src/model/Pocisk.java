package model;

import java.awt.*;
import com.mygdx.tanks.Stale;

public class Pocisk extends Rectangle {
    private Kierunek kierunek;
    private Czolg wystrzelil;
    private int koniec_x;
    private int koniec_y;

    public Pocisk(Czolg wystrzelil, Kierunek kierunek){
        super();
        this.width = 13;
        this.height = 10;
        if (kierunek == Kierunek.LEWO || kierunek == Kierunek.DOL)
        {
            this.koniec_x = 0;
            this.koniec_y = 0;
        } else {
            this.koniec_x = Stale.SZEROKOSC;
            this.koniec_y = Stale.WYSOKOSC;
        }
        this.kierunek = wystrzelil.getKierunek();
        this.wystrzelil = wystrzelil;
    }

    public Kierunek getKierunek() {
        return this.kierunek;
    }

    public void setKierunek(Kierunek kierunek) {
        this.kierunek = kierunek;
    }

    public Czolg getWystrzelil() {
        return this.wystrzelil;
    }

    public void setWystrzelil(Czolg wystrzelil) {
        this.wystrzelil = wystrzelil;
    }
}

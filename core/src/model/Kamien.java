package model;

import java.awt.*;

public class Kamien extends Blok {

    public Kamien(int polozenieX, int polozenieY){
        super(polozenieX, polozenieY);
        setWytrzymalosc(2);
        symbol = 'K';
    }
}

package model;

import java.awt.*;

public class Kamien extends Blok {
  
    public Kamien(int x, int y){
        setPolozenie(x,y);
        setWytrzymalosc(5);
        symbol = 'K';
    }
}

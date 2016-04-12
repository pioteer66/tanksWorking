package model;


import java.awt.*;


public class Blok {
    protected Point polozenie = new Point();
    protected int wytrzymalosc;
    public char symbol;

    public Point getPolozenie() {
        return polozenie;
    }

    public void setPolozenie(int x, int y) {
        polozenie.setLocation(x,y);
    }

    public int getWytrzymalosc() {
        return wytrzymalosc;
    }

    public void setWytrzymalosc(int wytrzymalosc) {
        this.wytrzymalosc = wytrzymalosc;
    }
}

package model;

/**
 * Created by Dawid on 2016-04-12.
 */
public class Pocisk {
    private int startowyX, startowyY, koncowyX,koncowyY;
    private Czolg wystrzelil;
    private int idPocisku;

    public Pocisk(int sX,int sY, int kX, int kY){
        this.startowyX = sX;
        this.startowyY = sY;
        this.koncowyX = kX;
        this.koncowyY = kY;
        // tds
    }

    public boolean Aktualizuj(){
        if (this.startowyX == this.koncowyX && this.startowyY == this.koncowyY ) return false;

        if(this.startowyX == this.koncowyX){
            if(this.startowyY > this.koncowyY) this.startowyY--;
            else if(this.startowyY < this.koncowyY) this.startowyY++;
        } else if (this.startowyY == this.koncowyY){
            if(this.startowyX > this.koncowyX) this.startowyX--;
            else if(this.startowyX < this.koncowyY) this.startowyX++;
        }
        return true;
    }

}

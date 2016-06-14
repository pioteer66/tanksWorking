package com.mygdx.tanks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import java.net.Socket;
import java.util.ArrayList;

public class TanksGame extends Game {
    private ArrayList<Screen> screenList;
    private int currentScreen;
    private PacketMagazine magazine;
    public static final int PORT = 8088;
    private String ipAdress;

    @Override
    public void create() {
        currentScreen=0;
        screenList=new ArrayList<Screen>(2);
        screenList.add(new MenuScreen(this));
        screenList.add(new GameScreen(this, magazine));

        setScreen(screenList.get(currentScreen));
    }

    public void nextScreen(){
        startSocketWorker();
        currentScreen++;
        currentScreen%=screenList.size();
        setScreen(screenList.get(currentScreen));
    }

    public TanksGame(PacketMagazine magazine) {
        this.magazine = magazine;
    }

    public void setIP(String text){
        this.ipAdress = text;
    }

    private void startSocketWorker(){
        try{
            Socket socket = new Socket(this.ipAdress, PORT);
            SocketWorker sw = new SocketWorker(socket,this.magazine);
        new Thread(sw).start();
        } catch (Exception ex){

        }

    }
}
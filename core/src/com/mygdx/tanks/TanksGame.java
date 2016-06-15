package com.mygdx.tanks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import java.net.Socket;
import java.util.ArrayList;

public class TanksGame extends Game {
    private ArrayList<Screen> screenList;
    private int currentScreen;
    private PacketMagazine magazine;
    private int portNumber = 8088;
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

    public void setIpAndPort(String server, String port){
        this.ipAdress = server;
        this.portNumber = Integer.parseInt(port);
    }

    private void startSocketWorker(){
        try{
            Socket socket = new Socket(this.ipAdress, portNumber);
            SocketWorker sw = new SocketWorker(socket,this.magazine);
        new Thread(sw).start();
        } catch (Exception ex){
            System.out.println("Nie można połączyć z serwerem z podanymi ustawieniami");
            return;
        }

    }
}
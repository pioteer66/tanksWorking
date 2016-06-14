package com.mygdx.tanks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import java.util.ArrayList;

public class TanksGame extends Game {
    private ArrayList<Screen> screenList;
    private int currentScreen;
    private PacketMagazine magazine;

    @Override
    public void create() {
        currentScreen=0;
        screenList=new ArrayList<Screen>(2);
        screenList.add(new MenuScreen(this));
        screenList.add(new GameScreen(this, magazine));

        setScreen(screenList.get(currentScreen));
    }

    public void nextScreen(){
        currentScreen++;
        currentScreen%=screenList.size();
        setScreen(screenList.get(currentScreen));
    }

    public TanksGame(PacketMagazine magazine)
    {
        this.magazine = magazine;
    }
}
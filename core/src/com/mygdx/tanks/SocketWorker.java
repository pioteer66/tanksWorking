package com.mygdx.tanks;

import model.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;

/**
 * Created by Kornel on 2016-06-14.
 */


// nadal nie ruszać robie jeszcze
public class SocketWorker implements Runnable {
    private Socket socket;
    private Magazine magazine;

    public SocketWorker(Socket socket, Magazine magazine) {
        this.socket = socket;
        this.magazine = magazine;
    }


    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
            magazine.setOis(ois);
            magazine.setOos(oos);
            SocketAddress sockaddr = this.socket.getRemoteSocketAddress();

            System.out.println("Nawiązano połaczenie z: " + sockaddr.toString());
            process(magazine.getOis(), magazine.getOos());
            System.out.println("Klient zakończył połaczenie: " + sockaddr.toString());

            oos.close();
            ois.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


    public void process(ObjectInputStream ois, ObjectOutputStream oos) {
        try {
            int playerId = ((Integer) ois.readObject()).intValue();
            this.magazine.setActivePlayerId(playerId);
            char[][] charPlansza = (char[][]) ois.readObject();
            this.magazine.setMap(charPlansza);
            int lives = ((Integer) ois.readObject()).intValue();
            this.magazine.setLivesOnStart(lives);

            String message = ((String) ois.readObject());
            System.out.println("Id gracza: "+playerId);
            System.out.println("Początkowy stan planszy");
            for (int i = 0; i < 32; i++) {
                for (int j = 0; j < 32; j++)
                    System.out.print(charPlansza[i][j] + " ");
                System.out.println();
            }
            System.out.println("Dostępne życia: "+lives);
            System.out.println("Oczekiwanie na dołączenie 4 graczy..");
            String message1 = ((String) ois.readObject());
            System.out.println(message1);


            while (true) {
                ArrayList<ArrayList<? extends Packet>> returnedPacket = (ArrayList<ArrayList<? extends Packet>>) ois.readObject();

                ArrayList<PositionPacket> positionPackets = (ArrayList<PositionPacket>) returnedPacket.get(0);
                ArrayList<MissilePacket> missilePackets = (ArrayList<MissilePacket>) returnedPacket.get(1);
                ArrayList<HitsPacket> hitsPackets = (ArrayList<HitsPacket>) returnedPacket.get(2);
                ArrayList<PlayerStatisticsPacket> statisticsPackets = (ArrayList<PlayerStatisticsPacket>) returnedPacket.get(3);
                synchronized (this.magazine)
                {
                    this.magazine.addPosition(positionPackets);
                    this.magazine.addMissile(missilePackets);
                    this.magazine.addHits(hitsPackets);
                    this.magazine.addStatistic(statisticsPackets);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
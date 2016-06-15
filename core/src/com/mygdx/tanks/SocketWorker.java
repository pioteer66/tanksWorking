package com.mygdx.tanks;

import model.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;

/**
 * Created by Kornel on 2016-06-14.
 */


// nadal nie ruszać robie jeszcze
public class SocketWorker implements Runnable {
    private Socket socket;
    private PacketMagazine packetMagazine;
    private int playerId;

    public SocketWorker(Socket socket, PacketMagazine packetMagazine) {
        this.socket = socket;
        this.packetMagazine = packetMagazine;
    }


    public void run() {
        try {
            ObjectInputStream dis = new ObjectInputStream(this.socket.getInputStream());
            ObjectOutputStream dos = new ObjectOutputStream(this.socket.getOutputStream());
            SocketAddress sockaddr = this.socket.getRemoteSocketAddress();

            System.out.println("Nawiązano połaczenie z: " + sockaddr.toString());
            process(dis, dos);
            System.out.println("Klient zakończył połaczenie: " + sockaddr.toString());

            dos.close();
            dis.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


    public void process(ObjectInputStream ois, ObjectOutputStream oos) {
        try {
            int playerId = ((Integer) ois.readObject()).intValue();
            this.playerId = playerId;
            char[][] charPlansza = (char[][]) ois.readObject();
            this.packetMagazine.setMap(charPlansza);


            int lives = ((Integer) ois.readObject()).intValue();
            String message = ((String) ois.readObject());
            System.out.println(playerId);
            for (int i = 0; i < 32; i++) {
                for (int j = 0; j < 32; j++)
                    System.out.print(charPlansza[i][j] + " ");
                System.out.println();
            }
            System.out.println(lives);
            System.out.println(message);
            String message1 = ((String) ois.readObject());
            System.out.println(message1);

            while (true) {
                ArrayList<ArrayList<? extends Packet>> returnedPacket = (ArrayList<ArrayList<? extends Packet>>) ois.readObject();

                ArrayList<PositionPacket> positionPackets = (ArrayList<PositionPacket>) returnedPacket.get(0);
                ArrayList<MissilePacket> missilePackets = (ArrayList<MissilePacket>) returnedPacket.get(1);
                ArrayList<HitsPacket> hitsPackets = (ArrayList<HitsPacket>) returnedPacket.get(2);
                ArrayList<PlayerStatisticsPacket> statisticsPackets = (ArrayList<PlayerStatisticsPacket>) returnedPacket.get(3);

                this.packetMagazine.addPosition(positionPackets);
                this.packetMagazine.addMissile(missilePackets);
                this.packetMagazine.addHits(hitsPackets);
                this.packetMagazine.addStatistic(statisticsPackets);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
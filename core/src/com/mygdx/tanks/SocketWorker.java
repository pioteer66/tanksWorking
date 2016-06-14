package com.mygdx.tanks;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;

// nadal nie ruszać robie jeszcze a ja nie
public class SocketWorker implements Runnable {
    private Socket socket;
    private PacketMagazine packetMagazine;

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
            char[][] charPlansza = (char[][]) ois.readObject();
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

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
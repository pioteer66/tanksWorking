package com.mygdx.tanks;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Kornel on 2016-06-14.
 */


// nadal nie ruszać robie jeszcze
public class SocketWorker implements Runnable{
    private Socket socket;
    private PacketMagazine packetMagazine;

    public SocketWorker(Socket socket, PacketMagazine packetMagazine){
        this.socket = socket;
        this.packetMagazine = packetMagazine;
    }


    public void run(){
        try{
            InputStream inputStream = this.socket.getInputStream();
            OutputStream outputStream = this.socket.getOutputStream();
            DataInputStream dis = new DataInputStream(inputStream);
            DataOutputStream dos = new DataOutputStream(outputStream);
            SocketAddress sockaddr = this.socket.getRemoteSocketAddress();

            System.out.println("Nawiązano połaczenie z: " + sockaddr.toString() );
            process(dis,dos);
            System.out.println("Klient zakończył połaczenie: " + sockaddr.toString() );

            dos.close();
            dis.close();
            inputStream.close();
            outputStream.close();



        } catch (Exception ex){
            System.out.println(ex);
        }
    }



    public void process(DataInputStream dis, DataOutputStream dos){
        try{
            int bytes;
            byte[] buffer = new byte[1024];

            int packetID = 1;
            while ((bytes = dis.read(buffer)) != -1) {
                this.packetMagazine.addPacket(buffer,bytes,packetID);
                packetID++;
                if(packetID == 10) packetID = 6;
            }

        } catch (Exception ex){
            System.out.println(ex);
        }
    }
}

package com.mygdx.tanks;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import net.tanks.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;



/* tutaj macie magazyn danych
i mozna tu wsadzac wszystko co potrzebe jest do kumunikacji klasy planszy z socketem
potem dodam tu semafory - na razie nie potrzebne,
robcie tu metody get,set i check i trzyajcie obiekty
 */

public class Magazine implements Runnable{
    private Queue<ArrayList<ArrayList<? extends  Packet>>> packetsQueue;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    private Board board;
    private char[][] map =null;
    private int activePlayerId;
    private int livesOnStart;

    public Magazine()
    {
        packetsQueue = new LinkedList<ArrayList<ArrayList<? extends Packet>>>();
    }

    public void addPacketToQueue(ArrayList<ArrayList<? extends  Packet>> position)
    {
        packetsQueue.add(position);
    }

    /*public void addPosition(ArrayList<PositionPacket> positionPackets){
        for (PositionPacket packet:positionPackets) {
            packetsQueue.add(new PositionPacket(packet.getId(), packet.getPositionX(), packet.getPositionY()));
        }
    }

    public void addMissile(ArrayList<MissilePacket> missilePackets){
        for (MissilePacket packet:missilePackets) {
            packetsQueue.add(new MissilePacket(packet.getPlayerId(), packet.getPositionX(), packet.getPositionY()));
        }
    }

    public void addHits(ArrayList<HitsPacket> hitsPackets){
        for (HitsPacket packet:hitsPackets) {
            packetsQueue.add(new HitsPacket(packet.getPlayerId(), packet.getPositionX(), packet.getPositionY()));
        }
    }

    public void addStatistic(ArrayList<PlayerStatisticsPacket> statisticsPackets){
        for (PlayerStatisticsPacket packet: statisticsPackets) {
            packetsQueue.add(new PlayerStatisticsPacket(packet.getPlayerId(), packet.getPoints(), packet.getRemainingLives()));
        }
    }*/

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Boolean checkMap(){
        return (this.map == null);
    }

    public Boolean checkBoard(){
        return (this.board == null);
    }

    public void setMap(char [][] map){
        this.map = map;
    }

    public char[][] getMap(){
        return this.map;
    }

    public int getActivePlayerId() {
        return activePlayerId;
    }

    public void setActivePlayerId(int activePlayerId) {
        this.activePlayerId = activePlayerId;
    }

    public int getLivesOnStart() {
        return livesOnStart;
    }

    public void setLivesOnStart(int livesOnStart) {
        this.livesOnStart = livesOnStart;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public void run(){

    }
}
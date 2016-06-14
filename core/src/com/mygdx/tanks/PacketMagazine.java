package com.mygdx.tanks;

import model.*;

import java.util.ArrayList;
import java.util.Queue;



/* tutaj macie magazyn danych
i mozna tu wsadzac wszystko co potrzebe jest do kumunikacji klasy planszy z socketem
potem dodam tu semafory - na razie nie potrzebne,
robcie tu metody get,set i check i trzyajcie obiekty
 */

public class PacketMagazine implements Runnable{
    private Queue<Packet> packetsQueue;
    private char[][] map =null;

    public PacketMagazine()
    {

    }

    public void addPosition(ArrayList<PositionPacket> positionPackets){
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
    }

    public Boolean checkMap(){
        return (this.map == null)? false : true;
    }

    public void setMap(char [][] map){
        this.map = map;
    }

    public char[][] getMap(){
        return this.map;
    }

    public void run(){

    }
}
package com.mygdx.tanks;

import model.*;

import java.util.ArrayList;
import java.util.Queue;

public class PacketMagazine implements Runnable{
    private Queue<Packet> packetsQueue;

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

    public void run(){

    }
}
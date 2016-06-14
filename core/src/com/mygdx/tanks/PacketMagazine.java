package com.mygdx.tanks;

import model.*;

import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by Piotr Ługowski on 14.06.2016.
 */
public class PacketMagazine implements Runnable{
    private Queue<Packet> packetsQueue;

    public PacketMagazine()
    {

    }
    public void addPacket(byte[] buffer, int bytesCount, int packetId)
    {

    }

    public void addPosition(ArrayList<PositionPacket> positionPackets){

    }

    public void addMissile(ArrayList<MissilePacket> missilePackets){

    }

    public void addHits(ArrayList<HitsPacket> hitsPackets){

    }

    public void addStatistic(ArrayList<PlayerStatisticsPacket> statisticsPackets){

    }




    public void run(){

    }
}

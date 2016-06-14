package model;

import java.io.Serializable;

public class HitsPacket extends Packet implements Serializable {
    private int playerId;
    private double missileId;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public double getMissileId() {
        return missileId;
    }

    public void setMissileId(double missileId) {
        this.missileId = missileId;
    }

    @Override
    public void parsePacket(byte[] buffer, int bytesCount) {

    }
}

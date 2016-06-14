package model;

/**
 * Created by Piotr Ługowski on 14.06.2016.
 */
public class HitsPacket extends Packet {
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
}

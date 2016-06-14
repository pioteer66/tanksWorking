package model;

import java.io.Serializable;

public class HitsPacket extends Packet implements Serializable {
    private int playerId;
    private double positionX;
    private double positionY;

    public HitsPacket(int playerId, double positionY, double positionX) {
        this.playerId = playerId;
        this.positionY = positionY;
        this.positionX = positionX;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public void parsePacket(byte[] buffer, int bytesCount) {

    }
}

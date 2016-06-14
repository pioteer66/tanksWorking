package model;

import java.io.Serializable;

public class MissilePacket extends Packet implements Serializable {
    private int playerId;
    private double positionX;
    private double positionY;

    public MissilePacket(int playerId, double positionX, double positionY) {
        this.playerId = playerId;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
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

    @Override
    public void parsePacket(byte[] buffer, int bytesCount) {

    }
}

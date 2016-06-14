package model;

/**
 * Created by Piotr Ługowski on 14.06.2016.
 */
public class MissilePacket extends Packet{
    private int playerId;
    private double positionX;
    private double positionY;

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
}

package model;

import java.io.Serializable;

/**
 * Created by Piotr Ługowski on 14.06.2016.
 */
public class PositionPacket extends Packet implements Serializable {
    private int Id;
    private double positionX;
    private double positionY;

    @Override
    public void parsePacket(byte[] buffer, int bytesCount) {
        byte buff[] = {0,1,3,'.',0,1,9,'.',0};
        }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }
}

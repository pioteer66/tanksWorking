package model;

/**
 * Created by Piotr Ługowski on 14.06.2016.
 */
public class PositionPacket extends Packet {
    private int Id;
    private double positionX;
    private double positionY;

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

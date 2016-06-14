package model;

import java.io.Serializable;

/**
 * Created by Piotr Ługowski on 14.06.2016.
 */
public class PlayerStatisticsPacket extends Packet implements Serializable {
    private int playerId;
    private int points;
    private int remainingLives;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRemainingLives() {
        return remainingLives;
    }

    public void setRemainingLives(int remainingLives) {
        this.remainingLives = remainingLives;
    }

    @Override
    public void parsePacket(byte[] buffer, int bytesCount) {

    }
}

package model;

import java.io.Serializable;

public abstract class Packet {
    public abstract void parsePacket(byte[] buffer, int bytesCount);
}

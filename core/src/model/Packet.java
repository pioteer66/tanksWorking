package model;

import java.io.Serializable;

/**
 * Created by Piotr Ługowski on 14.06.2016.
 */
public abstract class Packet {
    public abstract void parsePacket(byte[] buffer, int bytesCount);
}

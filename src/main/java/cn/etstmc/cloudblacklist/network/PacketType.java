package cn.etstmc.cloudblacklist.network;

public abstract class PacketType {
    public abstract Class<? extends Packet> getPacketType (int subtype);
    public abstract void registerPacket (int id, Class<? extends Packet> packet);
}

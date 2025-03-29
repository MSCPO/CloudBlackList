package cn.etstmc.cloudblacklist.api.network;

import cn.etstmc.cloudblacklist.network.Packet;
import cn.etstmc.cloudblacklist.utils.TypeParameterMatcher;

public abstract class PacketListener<P extends Packet> {
    private final TypeParameterMatcher.Matcher<P> matcher;


    public PacketListener () {
        matcher = TypeParameterMatcher.find(this, this.getClass(), "P");
    }

    public void checkPacket(Object msg) {
        if (matcher.matches(msg)) onPacket((P) msg);
    }

    public abstract void onPacket (P packet);
}

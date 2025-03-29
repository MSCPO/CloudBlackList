package cn.etstmc.cloudblacklist.network;


public abstract class Packet {
    private int type;
    private int subType;
    private String body;

    public Packet (int type, int subType, String body) {
        this.type = type;
        this.subType = subType;
        this.body = body;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public @interface packet {

    }
}
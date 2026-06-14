package upimesh.dto;

public class SettlementContext {

    private String packetHash;
    private int hopCount;
    private String bridgeNodeId;

    public SettlementContext() {
    }

    public SettlementContext(
            String packetHash,
            int hopCount,
            String bridgeNodeId) {

        this.packetHash = packetHash;
        this.hopCount = hopCount;
        this.bridgeNodeId = bridgeNodeId;
    }

    public String getPacketHash() {
        return packetHash;
    }

    public int getHopCount() {
        return hopCount;
    }

    public String getBridgeNodeId() {
        return bridgeNodeId;
    }
}
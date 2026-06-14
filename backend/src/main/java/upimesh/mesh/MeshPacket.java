package upimesh.mesh;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MeshPacket {

    private String packetId;

    private String encryptedPayload;

    private Instant createdAt;

    private int hopCount;

    private int ttl;

    private List<String> route =
            new ArrayList<>();

    public MeshPacket() {
    }

    public MeshPacket(
            String packetId,
            String encryptedPayload,
            Instant createdAt,
            int hopCount,
            int ttl) {

        this.packetId = packetId;
        this.encryptedPayload = encryptedPayload;
        this.createdAt = createdAt;
        this.hopCount = hopCount;
        this.ttl = ttl;
    }

    public MeshPacket copy() {

        MeshPacket clone =
                new MeshPacket(
                        this.packetId,
                        this.encryptedPayload,
                        this.createdAt,
                        this.hopCount,
                        this.ttl
                );

        clone.setRoute(
                new ArrayList<>(
                        this.route
                )
        );

        return clone;
    }

    public String getPacketId() {
        return packetId;
    }

    public void setPacketId(
            String packetId) {

        this.packetId = packetId;
    }

    public String getEncryptedPayload() {
        return encryptedPayload;
    }

    public void setEncryptedPayload(
            String encryptedPayload) {

        this.encryptedPayload =
                encryptedPayload;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(
            Instant createdAt) {

        this.createdAt = createdAt;
    }

    public int getHopCount() {
        return hopCount;
    }

    public void setHopCount(
            int hopCount) {

        this.hopCount = hopCount;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(
            int ttl) {

        this.ttl = ttl;
    }

    public List<String> getRoute() {
        return route;
    }

    public void setRoute(
            List<String> route) {

        this.route = route;
    }
}
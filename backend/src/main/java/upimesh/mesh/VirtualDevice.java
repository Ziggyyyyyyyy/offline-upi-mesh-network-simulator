package upimesh.mesh;

import java.util.ArrayList;
import java.util.List;

public class VirtualDevice {

    private String deviceId;

    private boolean internetAvailable;

    private boolean active = true;

    private List<MeshPacket> heldPackets =
            new ArrayList<>();

    public VirtualDevice(
            String deviceId,
            boolean internetAvailable) {

        this.deviceId = deviceId;
        this.internetAvailable =
                internetAvailable;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(
            String deviceId) {

        this.deviceId = deviceId;
    }

    public boolean isInternetAvailable() {
        return internetAvailable;
    }

    public void setInternetAvailable(
            boolean internetAvailable) {

        this.internetAvailable =
                internetAvailable;
    }

    public List<MeshPacket> getHeldPackets() {
        return heldPackets;
    }

    public void setHeldPackets(
            List<MeshPacket> heldPackets) {

        this.heldPackets = heldPackets;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(
            boolean active) {

        this.active = active;
    }

    public int getPacketCount() {
        return heldPackets.size();
    }
}
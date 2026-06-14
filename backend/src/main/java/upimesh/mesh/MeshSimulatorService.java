package upimesh.mesh;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import upimesh.service.BridgeIngestionService;
import upimesh.service.MeshAnalyticsService;

@Service
public class MeshSimulatorService {

    private final List<VirtualDevice> devices =
            new ArrayList<>();

    private final BridgeIngestionService bridgeIngestionService;

    private final MeshAnalyticsService analyticsService;

    private final NetworkTopology topology =
            new NetworkTopology();

    public MeshSimulatorService(
            BridgeIngestionService bridgeIngestionService,
            MeshAnalyticsService analyticsService) {

        this.bridgeIngestionService =
                bridgeIngestionService;

        this.analyticsService =
                analyticsService;

        devices.add(
                new VirtualDevice(
                        "Device-A",
                        false
                )
        );

        devices.add(
                new VirtualDevice(
                        "Device-B",
                        true
                )
        );

        devices.add(
                new VirtualDevice(
                        "Device-C",
                        false
                )
        );

        devices.add(
                new VirtualDevice(
                        "Device-D",
                        true
                )
        );
    }

    public void injectPacket(
            MeshPacket packet) {

        packet.getRoute()
                .add("Device-A");

        devices.get(0)
                .getHeldPackets()
                .add(packet);

        analyticsService.packetInjected();

        System.out.println(
                packet.getPacketId()
                        + " injected into Device-A"
        );
    }

    public void gossipOnce() {

        List<MeshPacket> packetsToDeliver =
                new ArrayList<>();

        for (VirtualDevice current : devices) {

            if (current.getHeldPackets()
                    .isEmpty()) {
                continue;
            }

            List<MeshPacket> currentPackets =
                    new ArrayList<>(
                            current.getHeldPackets()
                    );

            for (MeshPacket packet :
                    currentPackets) {

                List<String> neighbors =
                        topology.getNeighbors(
                                current.getDeviceId()
                        );

                for (String neighborId :
                        neighbors) {

                    VirtualDevice neighbor =
                            findDevice(
                                    neighborId
                            );

                    if (neighbor == null ||
                            !neighbor.isActive()) {

                        continue;
                    }

                    MeshPacket clone =
                            packet.copy();

                    clone.setHopCount(
                            clone.getHopCount() + 1
                    );

                    if (clone.getHopCount()
                            >= clone.getTtl()) {

                        analyticsService
                                .packetDropped();

                        continue;
                    }

                    clone.getRoute()
                            .add(
                                    neighbor.getDeviceId()
                            );

                    neighbor.getHeldPackets()
                            .add(clone);

                    System.out.println(
                            clone.getPacketId()
                                    + " broadcast from "
                                    + current.getDeviceId()
                                    + " to "
                                    + neighbor.getDeviceId()
                    );

                    if (neighbor
                            .isInternetAvailable()) {

                        System.out.println(
                                neighbor.getDeviceId()
                                        + " DISCOVERED AS BRIDGE"
                        );

                        packetsToDeliver
                                .add(clone);
                    }
                }
            }

            current.getHeldPackets().clear();
        }

        for (MeshPacket packet :
                packetsToDeliver) {

            try {

                bridgeIngestionService
                        .ingest(packet);

                analyticsService
                        .packetDelivered(
                                packet.getHopCount()
                        );

            } catch (Exception e) {

                throw new RuntimeException(e);
            }
        }
    }

    private VirtualDevice findDevice(
            String deviceId) {

        return devices.stream()
                .filter(
                        d -> d.getDeviceId()
                                .equals(deviceId)
                )
                .findFirst()
                .orElse(null);
    }

    public void disableDevice(
            String deviceId) {

        VirtualDevice device =
                findDevice(deviceId);

        if (device != null) {

            device.setActive(false);

            System.out.println(
                    deviceId + " OFFLINE"
            );
        }
    }

    public void enableDevice(
            String deviceId) {

        VirtualDevice device =
                findDevice(deviceId);

        if (device != null) {

            device.setActive(true);

            System.out.println(
                    deviceId + " ONLINE"
            );
        }
    }

    public void showNetwork() {

        System.out.println(
                "\n------ NETWORK ------"
        );

        for (VirtualDevice device :
                devices) {

            System.out.println(
                    device.getDeviceId()
                            + " -> "
                            + device.getHeldPackets().size()
                            + " packets"
            );
        }
    }

    public List<VirtualDevice> getDevices() {
        return devices;
    }

    public void resetMesh() {

        for (VirtualDevice device : devices) {

            device.getHeldPackets().clear();

            device.setActive(true);
        }

        System.out.println(
                "Mesh Reset Complete"
        );
    }
}
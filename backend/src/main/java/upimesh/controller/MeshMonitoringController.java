package upimesh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import upimesh.mesh.VirtualDevice;
import upimesh.mesh.MeshSimulatorService;

@RestController
@RequestMapping("/api/mesh")
public class MeshMonitoringController {

    private final MeshSimulatorService meshService;

    public MeshMonitoringController(
            MeshSimulatorService meshService) {

        this.meshService = meshService;
    }

    @GetMapping("/state")
    public List<Map<String, Object>> getState() {

        List<Map<String, Object>> result =
                new ArrayList<>();

        for (VirtualDevice device :
                meshService.getDevices()) {

            Map<String, Object> data =
                    new HashMap<>();

            data.put(
                    "deviceId",
                    device.getDeviceId()
            );

            data.put(
                    "active",
                    device.isActive()
            );

            data.put(
                    "bridge",
                    device.isInternetAvailable()
            );

            data.put(
                    "packetCount",
                    device.getPacketCount()
            );

            result.add(data);
        }

        return result;
    }

    @GetMapping("/devices")
    public List<VirtualDevice> getDevices() {

        return meshService.getDevices();
    }

    @PostMapping("/gossip")
    public String gossip() {

        meshService.gossipOnce();

        return "Gossip Round Executed";
    }

    @PostMapping("/reset")
    public String reset() {

        meshService.resetMesh();

        return "Mesh Reset Successful";
    }
}
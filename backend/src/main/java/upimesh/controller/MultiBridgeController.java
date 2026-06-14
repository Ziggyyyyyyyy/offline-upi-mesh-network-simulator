package upimesh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import upimesh.mesh.MeshSimulatorService;

@RestController
public class MultiBridgeController {

    private final MeshSimulatorService
            meshService;

    public MultiBridgeController(
            MeshSimulatorService meshService) {

        this.meshService =
                meshService;
    }

    @GetMapping(
            "/api/mesh/multi-bridge-status"
    )
    public String status() {

        long bridges =
                meshService
                        .getDevices()
                        .stream()
                        .filter(
                                d -> d.isInternetAvailable()
                        )
                        .count();

        return "Active Bridges : "
                + bridges;
    }
}
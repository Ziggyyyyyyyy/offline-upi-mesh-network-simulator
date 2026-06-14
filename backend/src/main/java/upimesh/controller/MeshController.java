package upimesh.controller;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import upimesh.crypto.HybridCryptoService;
import upimesh.mesh.MeshPacket;
import upimesh.mesh.MeshSimulatorService;
import upimesh.model.PaymentInstruction;

@RestController
public class MeshController {

    private final MeshSimulatorService simulatorService;
    private final HybridCryptoService cryptoService;

    public MeshController(
            MeshSimulatorService simulatorService,
            HybridCryptoService cryptoService) {

        this.simulatorService = simulatorService;
        this.cryptoService = cryptoService;
    }

    @GetMapping("/mesh-test")
    public String testMesh() {

        MeshPacket packet =
                new MeshPacket(
                        "packet-101",
                        "encrypted-data",
                        Instant.now(),
                        0,
                        10
                );

        simulatorService.injectPacket(packet);

        simulatorService.gossipOnce();
        simulatorService.gossipOnce();
        simulatorService.gossipOnce();

        simulatorService.showNetwork();

        return "Mesh Simulation Complete";
    }

    @GetMapping("/failure-test")
    public String failureTest()
            throws Exception {

        simulatorService.disableDevice(
                "Device-B"
        );

        PaymentInstruction instruction =
                new PaymentInstruction(
                        "alice@demo",
                        "bob@demo",
                        new BigDecimal("500"),
                        "",
                        UUID.randomUUID().toString(),
                        Instant.now()
                );

        String encrypted =
                cryptoService.encrypt(
                        instruction.serialize()
                );

        MeshPacket packet =
                new MeshPacket(
                        "packet-fail",
                        encrypted,
                        Instant.now(),
                        0,
                        10
                );

        simulatorService.injectPacket(packet);

        simulatorService.gossipOnce();
        simulatorService.gossipOnce();
        simulatorService.gossipOnce();

        simulatorService.showNetwork();

        return "Failure Test Complete";
    }
}
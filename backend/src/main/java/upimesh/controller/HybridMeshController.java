package upimesh.controller;

import java.time.Instant;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import upimesh.crypto.EncryptedPayload;
import upimesh.crypto.HybridCryptoService;
import upimesh.mesh.MeshPacket;
import upimesh.mesh.MeshSimulatorService;
import upimesh.model.PaymentInstruction;
import upimesh.model.TransferRequest;
import upimesh.util.InstructionFactory;

@RestController
@RequestMapping("/api/hybrid")
public class HybridMeshController {

    private final HybridCryptoService cryptoService;
    private final MeshSimulatorService meshService;

    public HybridMeshController(
            HybridCryptoService cryptoService,
            MeshSimulatorService meshService) {

        this.cryptoService = cryptoService;
        this.meshService = meshService;
    }

    @PostMapping("/send")
    public String sendPayment(
            @RequestBody TransferRequest request)
            throws Exception {

        PaymentInstruction instruction =
                InstructionFactory.createInstruction(
                        request.getSenderVpa(),
                        request.getReceiverVpa(),
                        request.getAmount(),
                        "dummy-pin"
                );

        EncryptedPayload payload =
                cryptoService.encryptHybrid(
                        instruction.serialize()
                );

        String packetPayload =
                payload.getEncryptedKey()
                        + "::"
                        + payload.getEncryptedData();

        MeshPacket packet =
                new MeshPacket(
                        UUID.randomUUID().toString(),
                        packetPayload,
                        Instant.now(),
                        0,
                        10
                );

        meshService.injectPacket(packet);

        return "Hybrid Payment Injected Into Mesh";
    }
}
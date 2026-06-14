package upimesh.service;

import org.springframework.stereotype.Service;

import upimesh.crypto.EncryptedPayload;
import upimesh.crypto.HybridCryptoService;
import upimesh.dto.SettlementContext;
import upimesh.mesh.MeshPacket;
import upimesh.model.PaymentInstruction;
import upimesh.util.HashUtil;
import upimesh.util.PaymentInstructionParser;

@Service
public class BridgeIngestionService {

    private final HybridCryptoService cryptoService;

    private final IdempotencyService idempotencyService;

    private final SettlementService settlementService;

    private final ReplayProtectionService replayProtectionService;

    private final PacketRouteRegistry routeRegistry;

    public BridgeIngestionService(
            HybridCryptoService cryptoService,
            IdempotencyService idempotencyService,
            SettlementService settlementService,
            ReplayProtectionService replayProtectionService,
            PacketRouteRegistry routeRegistry) {

        this.cryptoService = cryptoService;
        this.idempotencyService = idempotencyService;
        this.settlementService = settlementService;
        this.replayProtectionService =
                replayProtectionService;
        this.routeRegistry =
                routeRegistry;
    }

    public void ingest(
            MeshPacket packet)
            throws Exception {

        String packetHash =
                HashUtil.sha256(
                        packet.getEncryptedPayload()
                );

        boolean accepted =
                idempotencyService.claim(
                        packetHash
                );

        if (!accepted) {

            System.out.println(
                    "\nDUPLICATE PACKET REJECTED : "
                            + packet.getPacketId()
            );

            return;
        }

        System.out.println(
                "\n===== BRIDGE RECEIVED PACKET ====="
        );

        System.out.println(
                "Packet Id : "
                        + packet.getPacketId()
        );

        System.out.println(
                "Hop Count : "
                        + packet.getHopCount()
        );

        System.out.println(
                "Route : "
                        + packet.getRoute()
        );

        String payload =
                packet.getEncryptedPayload();

        String decrypted;

        if (payload.contains("::")) {

            String[] parts =
                    payload.split(
                            "::",
                            2
                    );

            EncryptedPayload encryptedPayload =
                    new EncryptedPayload(
                            parts[0],
                            parts[1]
                    );

            decrypted =
                    cryptoService.decryptHybrid(
                            encryptedPayload
                    );

        } else {

            decrypted =
                    cryptoService.decrypt(
                            payload
                    );
        }

        System.out.println(
                "DECRYPTED PAYLOAD : "
                        + decrypted
        );

        PaymentInstruction instruction =
                PaymentInstructionParser.parse(
                        decrypted
                );

        boolean fresh =
                replayProtectionService.isFresh(
                        instruction.getSignedAt()
                );

        if (!fresh) {

            System.out.println(
                    "STALE PACKET REJECTED"
            );

            return;
        }

        routeRegistry.saveRoute(
                packet.getPacketId(),
                packet.getRoute()
        );

        System.out.println(
                "SETTLING PAYMENT..."
        );

        SettlementContext context =
                new SettlementContext(
                        packetHash,
                        packet.getHopCount(),
                        "BRIDGE-NODE"
                );

        settlementService.transfer(
                instruction.getSenderVpa(),
                instruction.getReceiverVpa(),
                instruction.getAmount(),
                context
        );

        System.out.println(
                "PAYMENT SETTLED SUCCESSFULLY"
        );
    }
}
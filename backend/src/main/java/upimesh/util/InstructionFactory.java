package upimesh.util;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import upimesh.model.PaymentInstruction;

public class InstructionFactory {

    public static PaymentInstruction createInstruction(
            String senderVpa,
            String receiverVpa,
            BigDecimal amount,
            String pinHash) {

        return new PaymentInstruction(
                senderVpa,
                receiverVpa,
                amount,
                pinHash,
                UUID.randomUUID().toString(),
                Instant.now()
        );
    }
}
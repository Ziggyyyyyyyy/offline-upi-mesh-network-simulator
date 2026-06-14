package upimesh.util;

import java.math.BigDecimal;
import java.time.Instant;

import upimesh.model.PaymentInstruction;

public class PaymentInstructionParser {

    public static PaymentInstruction parse(
            String payload) {

        String[] parts =
                payload.split("\\|");

        return new PaymentInstruction(
                parts[0],
                parts[1],
                new BigDecimal(parts[2]),
                "",
                parts[3],
                Instant.parse(parts[4])
        );
    }
}
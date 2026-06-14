package upimesh.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashUtil {

    public static String sha256(
            String input)
            throws Exception {

        MessageDigest digest =
                MessageDigest.getInstance(
                        "SHA-256"
                );

        byte[] hash =
                digest.digest(
                        input.getBytes(
                                StandardCharsets.UTF_8
                        )
                );

        StringBuilder builder =
                new StringBuilder();

        for (byte b : hash) {

            builder.append(
                    String.format(
                            "%02x",
                            b
                    )
            );
        }

        return builder.toString();
    }
}
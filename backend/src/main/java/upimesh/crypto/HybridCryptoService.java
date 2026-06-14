package upimesh.crypto;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class HybridCryptoService {

    private final ServerKeyHolder keyHolder;

    private static final String AES_ALGORITHM =
            "AES";

    private static final String AES_TRANSFORMATION =
            "AES/GCM/NoPadding";

    private static final int GCM_TAG_LENGTH =
            128;

    private static final int GCM_IV_LENGTH =
            12;

    public HybridCryptoService(ServerKeyHolder keyHolder) {
        this.keyHolder = keyHolder;
    }

    /*
     * ============================================================
     * EXISTING RSA METHODS
     * (Do NOT remove - current project uses these)
     * ============================================================
     */

    public String encrypt(String data) throws Exception {

        PublicKey publicKey =
                keyHolder.getPublicKey();

        Cipher cipher =
                Cipher.getInstance("RSA");

        cipher.init(
                Cipher.ENCRYPT_MODE,
                publicKey
        );

        byte[] encrypted =
                cipher.doFinal(
                        data.getBytes(
                                StandardCharsets.UTF_8
                        )
                );

        return Base64
                .getEncoder()
                .encodeToString(encrypted);
    }

    public String decrypt(String encryptedText)
            throws Exception {

        PrivateKey privateKey =
                keyHolder.getPrivateKey();

        Cipher cipher =
                Cipher.getInstance("RSA");

        cipher.init(
                Cipher.DECRYPT_MODE,
                privateKey
        );

        byte[] decrypted =
                cipher.doFinal(
                        Base64.getDecoder()
                                .decode(encryptedText)
                );

        return new String(
                decrypted,
                StandardCharsets.UTF_8
        );
    }

    /*
     * ============================================================
     * NEW HYBRID METHODS
     * AES-GCM + RSA
     * ============================================================
     */

    public EncryptedPayload encryptHybrid(
            String data) throws Exception {

        KeyGenerator keyGenerator =
                KeyGenerator.getInstance(
                        AES_ALGORITHM
                );

        keyGenerator.init(256);

        SecretKey aesKey =
                keyGenerator.generateKey();

        byte[] iv =
                new byte[GCM_IV_LENGTH];

        new SecureRandom()
                .nextBytes(iv);

        Cipher aesCipher =
                Cipher.getInstance(
                        AES_TRANSFORMATION
                );

        aesCipher.init(
                Cipher.ENCRYPT_MODE,
                aesKey,
                new GCMParameterSpec(
                        GCM_TAG_LENGTH,
                        iv
                )
        );

        byte[] encryptedData =
                aesCipher.doFinal(
                        data.getBytes(
                                StandardCharsets.UTF_8
                        )
                );

        Cipher rsaCipher =
                Cipher.getInstance("RSA");

        rsaCipher.init(
                Cipher.ENCRYPT_MODE,
                keyHolder.getPublicKey()
        );

        byte[] encryptedKey =
                rsaCipher.doFinal(
                        aesKey.getEncoded()
                );

        String finalKey =
                Base64.getEncoder()
                        .encodeToString(
                                encryptedKey
                        );

        String finalData =
                Base64.getEncoder()
                        .encodeToString(
                                encryptedData
                        );

        return new EncryptedPayload(
                finalKey,
                Base64.getEncoder()
                        .encodeToString(iv)
                        + ":"
                        + finalData
        );
    }

    public String decryptHybrid(
            EncryptedPayload payload)
            throws Exception {

        Cipher rsaCipher =
                Cipher.getInstance("RSA");

        rsaCipher.init(
                Cipher.DECRYPT_MODE,
                keyHolder.getPrivateKey()
        );

        byte[] aesKeyBytes =
                rsaCipher.doFinal(
                        Base64.getDecoder()
                                .decode(
                                        payload.getEncryptedKey()
                                )
                );

        SecretKey aesKey =
                new SecretKeySpec(
                        aesKeyBytes,
                        AES_ALGORITHM
                );

        String[] parts =
                payload.getEncryptedData()
                        .split(":");

        byte[] iv =
                Base64.getDecoder()
                        .decode(parts[0]);

        byte[] encryptedData =
                Base64.getDecoder()
                        .decode(parts[1]);

        Cipher aesCipher =
                Cipher.getInstance(
                        AES_TRANSFORMATION
                );

        aesCipher.init(
                Cipher.DECRYPT_MODE,
                aesKey,
                new GCMParameterSpec(
                        GCM_TAG_LENGTH,
                        iv
                )
        );

        byte[] decrypted =
                aesCipher.doFinal(
                        encryptedData
                );

        return new String(
                decrypted,
                StandardCharsets.UTF_8
        );
    }
}
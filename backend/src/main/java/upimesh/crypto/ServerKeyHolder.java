package upimesh.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class ServerKeyHolder {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @PostConstruct
    public void init() throws Exception {

        KeyPairGenerator generator =
                KeyPairGenerator.getInstance("RSA");

        generator.initialize(2048);

        KeyPair pair = generator.generateKeyPair();

        publicKey = pair.getPublic();
        privateKey = pair.getPrivate();

        System.out.println("RSA Key Pair Generated");
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}
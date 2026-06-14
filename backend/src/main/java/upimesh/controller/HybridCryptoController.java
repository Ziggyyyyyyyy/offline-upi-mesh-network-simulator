package upimesh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import upimesh.crypto.EncryptedPayload;
import upimesh.crypto.HybridCryptoService;

@RestController
public class HybridCryptoController {

    private final HybridCryptoService cryptoService;

    public HybridCryptoController(
            HybridCryptoService cryptoService) {

        this.cryptoService =
                cryptoService;
    }

    @GetMapping("/hybrid-test")
    public String hybridTest()
            throws Exception {

        EncryptedPayload payload =
                cryptoService.encryptHybrid(
                        "Alice Pays Bob 500"
                );

        return cryptoService
                .decryptHybrid(
                        payload
                );
    }
}
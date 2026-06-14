package upimesh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import upimesh.crypto.HybridCryptoService;

@RestController
public class CryptoController {

    private final HybridCryptoService cryptoService;

    public CryptoController(
            HybridCryptoService cryptoService) {

        this.cryptoService = cryptoService;
    }

    @GetMapping("/crypto-test")
    public String test() throws Exception {

        String encrypted =
                cryptoService.encrypt(
                        "Alice Pays Bob 500"
                );

        String decrypted =
                cryptoService.decrypt(
                        encrypted
                );

        return
                "Encrypted: "
                        + encrypted
                        + "\n\nDecrypted: "
                        + decrypted;
    }
}
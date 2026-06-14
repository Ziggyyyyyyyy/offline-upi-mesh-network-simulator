package upimesh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import upimesh.model.Transaction;
import upimesh.repository.TransactionRepository;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    private final TransactionRepository
            transactionRepository;

    public AuditController(
            TransactionRepository transactionRepository) {

        this.transactionRepository =
                transactionRepository;
    }

    @GetMapping
    public List<Transaction> all() {

        return transactionRepository.findAll();
    }

    @GetMapping("/latest")
    public List<Transaction> latest() {

        return transactionRepository
                .findTop10ByOrderByCreatedAtDesc();
    }

    @GetMapping("/hash/{hash}")
    public Transaction byHash(
            @PathVariable String hash) {

        return transactionRepository
                .findByPacketHash(hash)
                .orElse(null);
    }

    @GetMapping("/stats")
    public Map<String, Object> stats() {

        Map<String, Object> response =
                new HashMap<>();

        long total =
                transactionRepository.count();

        long successful =
                transactionRepository
                        .findAll()
                        .stream()
                        .filter(
                                t -> "SUCCESS"
                                        .equals(
                                                t.getStatus()
                                        )
                        )
                        .count();

        response.put(
                "totalTransactions",
                total
        );

        response.put(
                "successfulTransactions",
                successful
        );

        response.put(
                "successRate",
                total == 0
                        ? 0
                        : successful * 100.0 / total
        );

        return response;
    }
}
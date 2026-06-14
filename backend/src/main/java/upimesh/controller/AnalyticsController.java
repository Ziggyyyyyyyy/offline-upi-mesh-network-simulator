package upimesh.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import upimesh.dto.SystemSummaryResponse;
import upimesh.model.Transaction;
import upimesh.repository.AccountRepository;
import upimesh.repository.TransactionRepository;

@RestController
@RequestMapping("/api/stats")
public class AnalyticsController {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AnalyticsController(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository) {

        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/summary")
    public SystemSummaryResponse summary() {

        return new SystemSummaryResponse(
                accountRepository.count(),
                transactionRepository.count()
        );
    }

    @GetMapping("/volume")
    public Map<String, Object> volume() {

        BigDecimal totalVolume =
                transactionRepository.findAll()
                        .stream()
                        .map(Transaction::getAmount)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "totalVolume",
                totalVolume
        );

        return response;
    }
}
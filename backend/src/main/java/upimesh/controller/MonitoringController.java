package upimesh.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import upimesh.model.Account;
import upimesh.model.Transaction;
import upimesh.repository.AccountRepository;
import upimesh.repository.TransactionRepository;

@RestController
@RequestMapping("/api")
public class MonitoringController {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public MonitoringController(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository) {

        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts() {

        return accountRepository.findAll();
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {

        return transactionRepository.findAll();
    }

    @GetMapping("/transactions/latest")
    public List<Transaction> latestTransactions() {

        return transactionRepository
                .findTop10ByOrderByIdDesc();
    }
}
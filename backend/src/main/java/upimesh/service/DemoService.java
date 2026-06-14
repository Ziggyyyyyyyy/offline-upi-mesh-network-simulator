package upimesh.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import upimesh.model.Account;
import upimesh.repository.AccountRepository;

@Service
public class DemoService {

    private final AccountRepository accountRepository;

    public DemoService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostConstruct
    public void init() {

        if (accountRepository.count() == 0) {

            accountRepository.save(
                    new Account(
                            "alice@demo",
                            "Alice",
                            new BigDecimal("5000")
                    )
            );

            accountRepository.save(
                    new Account(
                            "bob@demo",
                            "Bob",
                            new BigDecimal("1000")
                    )
            );

            accountRepository.save(
                    new Account(
                            "carol@demo",
                            "Carol",
                            new BigDecimal("2500")
                    )
            );
        }
    }
}
package upimesh.service;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import upimesh.dto.SettlementContext;
import upimesh.model.Account;
import upimesh.model.Transaction;
import upimesh.repository.AccountRepository;
import upimesh.repository.TransactionRepository;



@Service
public class SettlementService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public SettlementService(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository) {

        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void transfer(
            String senderVpa,
            String receiverVpa,
            BigDecimal amount) {

        System.out.println(
                "\n===== SETTLEMENT STARTED ====="
        );

        Account sender =
                accountRepository.findById(senderVpa)
                        .orElseThrow();

        Account receiver =
                accountRepository.findById(receiverVpa)
                        .orElseThrow();

        if (sender.getBalance()
                .compareTo(amount) < 0) {

            throw new RuntimeException(
                    "Insufficient Balance"
            );
        }

        sender.setBalance(
                sender.getBalance()
                        .subtract(amount)
        );

        receiver.setBalance(
                receiver.getBalance()
                        .add(amount)
        );

        accountRepository.save(sender);
        accountRepository.save(receiver);

        Transaction transaction =
                new Transaction(
                        senderVpa,
                        receiverVpa,
                        amount,
                        "SUCCESS",
                        Instant.now()
                );

        transactionRepository.save(
                transaction
        );

        System.out.println(
                "Transferred "
                        + amount
                        + " from "
                        + senderVpa
                        + " to "
                        + receiverVpa
        );

        System.out.println(
                "===== SETTLEMENT COMPLETED ====="
        );
    }

    @Transactional
public void transfer(
        String senderVpa,
        String receiverVpa,
        BigDecimal amount,
        SettlementContext context) {

    Account sender =
            accountRepository.findById(senderVpa)
                    .orElseThrow();

    Account receiver =
            accountRepository.findById(receiverVpa)
                    .orElseThrow();

    if (sender.getBalance()
            .compareTo(amount) < 0) {

        throw new RuntimeException(
                "Insufficient Balance"
        );
    }

    sender.setBalance(
            sender.getBalance()
                    .subtract(amount)
    );

    receiver.setBalance(
            receiver.getBalance()
                    .add(amount)
    );

    accountRepository.save(sender);
    accountRepository.save(receiver);

    Transaction transaction =
            new Transaction(
                    senderVpa,
                    receiverVpa,
                    amount,
                    "SUCCESS",
                    Instant.now()
            );

    transaction.setPacketHash(
            context.getPacketHash()
    );

    transaction.setHopCount(
            context.getHopCount()
    );

    transaction.setBridgeNodeId(
            context.getBridgeNodeId()
    );

    transaction.setSettledAt(
            Instant.now()
    );

    transactionRepository.save(
            transaction
    );
}
}
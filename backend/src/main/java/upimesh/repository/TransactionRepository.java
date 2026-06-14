package upimesh.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import upimesh.model.Transaction;



public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    List<Transaction> findTop10ByOrderByIdDesc();

    List<Transaction> findTop10ByOrderByCreatedAtDesc();

Optional<Transaction> findByPacketHash(
        String packetHash
);
}
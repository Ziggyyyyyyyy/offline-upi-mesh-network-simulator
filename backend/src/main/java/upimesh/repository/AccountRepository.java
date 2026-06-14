package upimesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import upimesh.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
package iuh.fit.dhktpm117ctt.group06.repository;

import iuh.fit.dhktpm117ctt.group06.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByEmail(String email);
    Boolean existsByEmail(String email);
    @Query("SELECT a FROM Account a WHERE a.user.id = ?1")
    Optional<Account> findByUser(String userId);
    void deleteByEmail(String email);

}

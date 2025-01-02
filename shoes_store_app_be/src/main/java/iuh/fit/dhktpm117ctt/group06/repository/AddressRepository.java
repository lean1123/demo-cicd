package iuh.fit.dhktpm117ctt.group06.repository;

import iuh.fit.dhktpm117ctt.group06.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,String> {
    @Query("SELECT a FROM Address a WHERE a.user.id = ?1")
    List<Address> findAllByUser(String userId);
    Optional<Address> findById(String id);
}

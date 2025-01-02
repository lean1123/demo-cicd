package iuh.fit.dhktpm117ctt.group06.repository;

import iuh.fit.dhktpm117ctt.group06.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByUserId(String userId);
}

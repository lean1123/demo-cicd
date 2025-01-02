package iuh.fit.dhktpm117ctt.group06.repository;

import iuh.fit.dhktpm117ctt.group06.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    Optional<Order> findById(String orderId);
    List<Order> findByUserId(String userId);
    @Query("SELECT o FROM Order o ORDER BY o.createdDate DESC")
    List<Order> findAll();
    @Query("SELECT o FROM Order o WHERE o.id LIKE %?1% OR o.user.lastName LIKE %?1% OR o.user.firstName LIKE %?1% OR o.user.phone LIKE %?1%")
    List<Order> search(String keyword);
}

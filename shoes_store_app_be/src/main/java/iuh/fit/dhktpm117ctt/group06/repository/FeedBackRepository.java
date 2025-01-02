package iuh.fit.dhktpm117ctt.group06.repository;

import iuh.fit.dhktpm117ctt.group06.entities.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, String> {
    List<FeedBack> findByProductId(String productId);
    Optional<FeedBack> findFirstByUserId(String userId);
    Optional<FeedBack> findFirstByOrderId(String orderId);
  //  Optional<FeedBack> save(FeedBack feedBack);
}

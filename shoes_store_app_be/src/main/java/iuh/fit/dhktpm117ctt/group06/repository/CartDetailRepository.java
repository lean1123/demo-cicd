package iuh.fit.dhktpm117ctt.group06.repository;

import iuh.fit.dhktpm117ctt.group06.entities.CartDetail;
import iuh.fit.dhktpm117ctt.group06.entities.CartDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, CartDetailPK> {
    // Tìm tất cả CartDetail theo cartId
    @Query("SELECT cd FROM CartDetail cd WHERE cd.cart.id = ?1")
    List<CartDetail> findAllByCartId(String cartId);

    // Tìm CartDetail theo CartDetailPK
    Optional<CartDetail> findByCartDetailPK(CartDetailPK cartDetailPK);

    // Xóa CartDetail theo CartDetailPK
    void deleteByCartDetailPK(CartDetailPK cartDetailPK);
}

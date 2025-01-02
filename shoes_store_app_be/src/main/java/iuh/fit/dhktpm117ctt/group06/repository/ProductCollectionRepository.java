package iuh.fit.dhktpm117ctt.group06.repository;

import iuh.fit.dhktpm117ctt.group06.entities.ProductCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCollectionRepository extends JpaRepository<ProductCollection, String> {
    @Query("SELECT p FROM ProductCollection p WHERE p.name = ?1")
    Optional<ProductCollection> findByName(String name);
    @Query("SELECT p FROM ProductCollection p WHERE p.brand.id = ?1")
    List<ProductCollection> findByBrand(String id);
    @Query("SELECT p FROM ProductCollection p WHERE p.name LIKE %?1% AND p.brand.id = ?2")
    List<ProductCollection> search(String keyword, String brandId);
}

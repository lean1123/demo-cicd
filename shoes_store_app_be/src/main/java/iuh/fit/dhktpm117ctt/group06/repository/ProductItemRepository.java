package iuh.fit.dhktpm117ctt.group06.repository;


import java.util.List;
import java.util.Optional;

import iuh.fit.dhktpm117ctt.group06.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import iuh.fit.dhktpm117ctt.group06.entities.OrderDetail;
import iuh.fit.dhktpm117ctt.group06.entities.ProductItem;
import iuh.fit.dhktpm117ctt.group06.entities.enums.ProductColor;
import iuh.fit.dhktpm117ctt.group06.entities.enums.ProductStatus;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, String> {
    @Query("SELECT p FROM ProductItem p WHERE p.product.id = ?1")
    List<ProductItem> findByProduct(String productId);
    List<ProductItem> findByStatus(ProductStatus status);
    @Query("SELECT DISTINCT p.color FROM ProductItem p WHERE p.product.id = :productId")
    List<ProductColor> findDistinctColorsByProductId(String productId);
    @Query("SELECT DISTINCT p.size FROM ProductItem p WHERE p.product.id = :productId")
    List<String> findDistinctSizesByProductId(String productId);
    @Query("SELECT p FROM ProductItem p WHERE p.color = :color AND p.size = :size AND p.product.id = :productId")
    Optional<ProductItem> findByColorAndSizeAndProductId(String color, String size, String product);

    @Query("select o from OrderDetail o where o.productItem.id = ?1")
    List<OrderDetail> exexistedByOrders(String productItemId);

    @Query("select p from ProductItem p join OrderDetail o on p.id = o.productItem.id group by p.id order by sum(o.quantity) desc")
    Page<ProductItem> listTopSaleProductItems(Pageable pageable);

    @Query("SELECT p FROM ProductItem p JOIN p.product pu ORDER BY pu.createdDate DESC")
    Page<ProductItem> listNewProductItems(Pageable pageable);

    @Query("SELECT p FROM ProductItem p WHERE p.product.id = ?1 and p.color = ?2 and p.size = ?3")
    Optional<ProductItem> findByProductAndSizeAndColor(String productId, ProductColor color, String size );

    @Query("SELECT p FROM ProductItem p " +
            "join p.product pd " +
            "WHERE (:color IS NULL OR p.color = :color) " +
            "AND (:size IS NULL OR p.size = :size) " +
            "AND (p.price >= :minPrice AND p.price <= :maxPrice) " +
            "AND (:productName IS NULL OR LOWER(pd.name) LIKE LOWER(CONCAT('%', :productName, '%')))")
    List<ProductItem> searchProductItemsByFilters(@Param("color") ProductColor color,
                                                  @Param("size") String size,
                                                  @Param("minPrice") double minPrice,
                                                  @Param("maxPrice") double maxPrice,
                                                  @Param("productName") String productName);


}

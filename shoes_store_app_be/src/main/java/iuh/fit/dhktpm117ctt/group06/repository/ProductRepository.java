package iuh.fit.dhktpm117ctt.group06.repository;

import iuh.fit.dhktpm117ctt.group06.entities.Product;
import iuh.fit.dhktpm117ctt.group06.entities.User;
import iuh.fit.dhktpm117ctt.group06.entities.enums.ProductColor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
	// Tìm kiếm theo tên
	List<Product> findByDescription(String name);

	@Query("SELECT p FROM Product p WHERE p.category.id = ?1")
	List<Product> findByCategory(String category);

	@Query("SELECT p FROM Product p " +
		       "JOIN p.collection pc " +
		       "LEFT JOIN pc.brand b " +
		       "WHERE p.name LIKE %?1% " +
		       "OR p.description LIKE %?1% " +
		       "OR b.brandName LIKE %?1% " +
		       "OR p.category.name LIKE %?1% " +
		       "OR pc.name LIKE %?1%")
	List<Product> search(String keyword);


	@Query("select DISTINCT pi.size from ProductItem pi where pi.product.id = ?1")
	List<String> getListSizes(String productId);
	
	@Query("select DISTINCT pi.color from ProductItem pi where pi.product.id = ?1")
	List<ProductColor> getListColors(String productId);


}

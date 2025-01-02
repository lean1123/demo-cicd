package iuh.fit.dhktpm117ctt.group06.service;

import iuh.fit.dhktpm117ctt.group06.dto.request.ProductItemRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.ProductItemResponse;
import iuh.fit.dhktpm117ctt.group06.entities.ProductItem;
import iuh.fit.dhktpm117ctt.group06.entities.enums.ProductColor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductItemService {
    Optional<ProductItemResponse> save(ProductItemRequest productItemRequest);
    Optional<ProductItemResponse> update(String id, ProductItemRequest productItem);
    void deleteById(String productItemId);
    Optional<ProductItemResponse> updateDetailImage(String productItemId, MultipartFile[] newDetailImages);
    Optional<ProductItem> updateQuantity(String productItemId, int qty);
    List<ProductItemResponse> findByProduct(String productId);
    Optional<ProductItemResponse> findByColorAndSize(String color, String size, String productId);
    List<ProductColor> findDistinctColorsByProductId(String productId);
    List<String> findDistinctSizesByProductId(String productId);
	Optional<ProductItem> findById(String id);
	List<ProductItem> findAll();
	Page<ProductItem> listNewProductItems(Pageable pageable);
	Page<ProductItem> listTopSaleProductItems(Pageable pageable);
	Optional<ProductItem> findByProductAndSizeAndColor(String productId, String size, ProductColor color);

    List<ProductItem> searchProductItemsByColorOrAndSizeOrPriceBetweenOrProductName(ProductColor color, String size,
                                                                                    double minPrice, double maxPrice, String productName);
}

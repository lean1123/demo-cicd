package iuh.fit.dhktpm117ctt.group06.service;

import iuh.fit.dhktpm117ctt.group06.dto.request.ProductRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.ProductResponse;
import iuh.fit.dhktpm117ctt.group06.entities.Product;
import iuh.fit.dhktpm117ctt.group06.entities.ProductItem;
import iuh.fit.dhktpm117ctt.group06.entities.enums.ProductColor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {
	Optional<ProductResponse> addProduct(ProductRequest productRequest);
    Optional<ProductResponse> updateProduct(String productId, ProductRequest productRequest);
    void deleteById(String productId);
    Optional<ProductResponse> updateProductAvatar(String productId, MultipartFile avatar);
    List<ProductResponse> searchProducts(String keyword);
    ProductResponse getProductById(String productId);
    List<ProductResponse> getAllProducts();
    List<ProductColor>  getProductColors(String productId);
	Optional<Product> findById(String id);
	List<ProductColor> getListColors(String productId);
	List<String> getListSizes(String productId);
}
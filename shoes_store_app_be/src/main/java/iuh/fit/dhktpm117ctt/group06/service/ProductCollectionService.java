package iuh.fit.dhktpm117ctt.group06.service;

import iuh.fit.dhktpm117ctt.group06.dto.request.ProductCollectionRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.ProductCollectionResponse;
import iuh.fit.dhktpm117ctt.group06.entities.ProductCollection;

import java.util.List;
import java.util.Optional;

public interface ProductCollectionService {
    Optional<ProductCollectionResponse> save(ProductCollectionRequest request);
    Optional<ProductCollectionResponse> findById(String id);
    void deleteById(String id);
    Optional<ProductCollectionResponse> update(String id, ProductCollectionRequest request);
    Optional<ProductCollectionResponse> findByName(String name);
    List<ProductCollectionResponse> findByBrand(String brandId);
    List<ProductCollectionResponse> search(String keyword, String brandId);
}

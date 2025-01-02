package iuh.fit.dhktpm117ctt.group06.service.impl;

import iuh.fit.dhktpm117ctt.group06.dto.request.ProductCollectionRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.ProductCollectionResponse;
import iuh.fit.dhktpm117ctt.group06.entities.Brand;
import iuh.fit.dhktpm117ctt.group06.entities.ProductCollection;
import iuh.fit.dhktpm117ctt.group06.repository.BrandRepository;
import iuh.fit.dhktpm117ctt.group06.repository.ProductCollectionRepository;
import iuh.fit.dhktpm117ctt.group06.service.ProductCollectionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCollectionServiceImpl implements ProductCollectionService {

    @Autowired
    private ProductCollectionRepository productCollectionRepository;

    @Autowired
    private BrandRepository brandRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private ProductCollectionResponse mapToProductCollectionResponse(ProductCollection productCollection) {
        return modelMapper.map(productCollection, ProductCollectionResponse.class);
    }

    private ProductCollection mapToProductCollection(ProductCollectionRequest productCollectionRequest) {
        return modelMapper.map(productCollectionRequest, ProductCollection.class);
    }

    @Override
    public Optional<ProductCollectionResponse> save(ProductCollectionRequest request) {
        ProductCollection productCollection = new ProductCollection();
        productCollection.setName(request.getName());
        Brand brand = brandRepository.findById(request.getBrandId()).orElse(null);
        productCollection.setBrand(brand);
        return Optional.of(mapToProductCollectionResponse(productCollectionRepository.save(productCollection)));
    }

    @Override
    public Optional<ProductCollectionResponse> findById(String id) {
        return Optional.of(mapToProductCollectionResponse(productCollectionRepository.findById(id).orElse(null)));
    }

    @Override
    public void deleteById(String id) {
        productCollectionRepository.deleteById(id);
    }

    @Override
    public Optional<ProductCollectionResponse> update(String id, ProductCollectionRequest request) {
        Optional<ProductCollection> productCollection = productCollectionRepository.findById(id);
        if (productCollection.isPresent()) {
            ProductCollection updatedProductCollection = mapToProductCollection(request);
            updatedProductCollection.setId(productCollection.get().getId());
            return Optional.of(mapToProductCollectionResponse(productCollectionRepository.save(updatedProductCollection)));
        }
        return Optional.empty();
    }

    @Override
    public Optional<ProductCollectionResponse> findByName(String name) {
        return Optional.of(mapToProductCollectionResponse(productCollectionRepository.findByName(name).get()));
    }

    @Override
    public List<ProductCollectionResponse> findByBrand(String brandId) {
        return List.of(productCollectionRepository.findByBrand(brandId).stream().map(this::mapToProductCollectionResponse).toArray(ProductCollectionResponse[]::new));
    }

    @Override
    public List<ProductCollectionResponse> search(String keyword, String brandId) {
        return List.of(productCollectionRepository.search(keyword, brandId).stream().map(this::mapToProductCollectionResponse).toArray(ProductCollectionResponse[]::new));
    }
}

package iuh.fit.dhktpm117ctt.group06.service;

import iuh.fit.dhktpm117ctt.group06.dto.request.BrandRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.BrandResponse;
import iuh.fit.dhktpm117ctt.group06.entities.Brand;
import iuh.fit.dhktpm117ctt.group06.repository.BrandRepository;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    public Optional<BrandResponse> findById(String id);
    public Optional<BrandResponse> save(BrandRequest brandRequest);
    public Optional<BrandResponse> update(String id,BrandRequest brandRequest);
    public void deleteById(String id);
    public List<BrandResponse> findAll();
    public List<BrandResponse> search(String keyword);
}

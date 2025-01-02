package iuh.fit.dhktpm117ctt.group06.service;

import iuh.fit.dhktpm117ctt.group06.dto.request.CategoryRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.CategoryResponse;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<CategoryResponse> save(CategoryRequest categoryRequest);
    Optional<CategoryResponse> update(String id,CategoryRequest categoryRequest);
    Optional<CategoryResponse> findById(String id);
    void deleteById(String id);
    List<CategoryResponse> findAll();
    List<CategoryResponse> search(String keyword);
}

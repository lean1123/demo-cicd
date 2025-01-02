package iuh.fit.dhktpm117ctt.group06.service.impl;

import iuh.fit.dhktpm117ctt.group06.dto.request.CategoryRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.CategoryResponse;
import iuh.fit.dhktpm117ctt.group06.entities.Category;
import iuh.fit.dhktpm117ctt.group06.repository.CategoryRepository;
import iuh.fit.dhktpm117ctt.group06.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private ModelMapper modelMapper = new ModelMapper();
    private CategoryRepository categoryRepository;


    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category mapToCategory(CategoryRequest categoryRequest) {
        return modelMapper.map(categoryRequest, Category.class);
    }

    public CategoryResponse mapToCategoryResponse(Category category) {
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public Optional<CategoryResponse> save(CategoryRequest categoryRequest) {
        return Optional.of(mapToCategoryResponse(categoryRepository.save(mapToCategory(categoryRequest))));
    }

    @Override
    public Optional<CategoryResponse> update(String id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.getReferenceById(id);
        Category updatedCategory = mapToCategory(categoryRequest);
        updatedCategory.setId(category.getId());
        return Optional.of(mapToCategoryResponse(categoryRepository.save(updatedCategory)));
    }

    @Override
    public Optional<CategoryResponse> findById(String id) {
        Category category = categoryRepository.findById(id).orElse(null);
        return Optional.of(mapToCategoryResponse(category));
    }

    @Override
    public void deleteById(String id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::mapToCategoryResponse).toList();
    }

    @Override
    public List<CategoryResponse> search(String keyword) {
        return categoryRepository.search(keyword).stream().map(this::mapToCategoryResponse).toList();
    }
}

package com.kunam.pustaka.category;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findByid(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category category, Integer id) {

        var findCategory = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));

        findCategory.setName(category.getName());

        return categoryRepository.save(findCategory);
    }

    public String delete(Integer id) {

        String response = "Category not found";

        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            response = "Category deleted";
        } else {
            throw new IllegalArgumentException("Category not found");
        }
        return id.toString();
    }
}

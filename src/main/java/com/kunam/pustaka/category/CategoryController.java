package com.kunam.pustaka.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> findAllCategory() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findCategoryById(@PathVariable Integer id) {
        return categoryService.findByid(id);
    }

    @PostMapping
    public Category saveCategory(@RequestBody Category category) {

        return categoryService.save(category);
    }

    @PatchMapping("/{id}")
    public Category updateCategory(@RequestBody Category category, @PathVariable Integer id) {
        return categoryService.update(category, id);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        return categoryService.delete(id);
    }
}

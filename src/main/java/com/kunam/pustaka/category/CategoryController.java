package com.kunam.pustaka.category;

import com.kunam.pustaka.response.ApiResponse;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Object> findAllCategory() {
        try {
            List<Category> categories = categoryService.findAll();

            return new ResponseEntity<>(
                    new ApiResponse<>("success",
                            "Found " + categories.size() + " categories",
                            categories
                    ), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse<>("error",
                            e.getMessage(),
                            null
                    ), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findCategoryById(@PathVariable Integer id) {
        try {
            Category category = categoryService.findByid(id);

            if (category == null) {
                return new ResponseEntity<>(
                        new ApiResponse<>("error", "Category" + id + " not found", null),
                        HttpStatus.NOT_FOUND
                );
            }

            return new ResponseEntity<>(
                    new ApiResponse<>("success", "Found category", category),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse<>("error", e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveCategory(@RequestBody Category category) {

        try {
            Category result = categoryService.save(category);

            return new ResponseEntity<>(
                    new ApiResponse<>("success",
                            "Category created successfully",
                            result
                    ), HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse<>("error",
                            e.getMessage(),
                            null
                    ), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@RequestBody Category category, @PathVariable Integer id) {

        try {
            Category result = categoryService.update(category, id);

            return new ResponseEntity<>(
                    new ApiResponse<>("success",
                            "Category updated successfully",
                            result
                    ), HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse<>("error",
                            e.getMessage(),
                            null
                    ), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Integer id) {
        try {

            categoryService.delete(id);

            return new ResponseEntity<>(
                    new ApiResponse<>("success",
                            "Category deleted successfully",
                            null
                    ), HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse<>("error",
                            e.getMessage(),
                            null
                    ), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}

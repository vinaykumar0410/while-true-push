package com.vinay.blog_app.service;

import com.vinay.blog_app.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO createCategory(String name);
    CategoryResponseDTO updateCategory(Long id, String name);
    void deleteCategory(Long id);
    CategoryResponseDTO getCategoryById(Long id);
    List<CategoryResponseDTO> getAllCategories();

}

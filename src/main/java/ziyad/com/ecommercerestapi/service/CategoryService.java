package ziyad.com.ecommercerestapi.service;

import ziyad.com.ecommercerestapi.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto getCategory(Long categoryId);
    List<CategoryDto> getAllCategory();
    CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);
    void deleteCategory(Long categoryId);
}

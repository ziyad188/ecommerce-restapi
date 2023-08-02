package ziyad.com.ecommercerestapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ziyad.com.ecommercerestapi.payload.CategoryDto;
import ziyad.com.ecommercerestapi.entity.Category;
import ziyad.com.ecommercerestapi.exception.ResourceNotFoundException;
import ziyad.com.ecommercerestapi.repository.CategoryRepository;
import ziyad.com.ecommercerestapi.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }
//create category
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = mapper.map(categoryDto,Category.class);
        Category savedCategory = categoryRepository.save(category);
        return mapper.map(savedCategory,CategoryDto.class);
    }
    //get category by id

    @Override
    public CategoryDto getCategory(Long categoryId) {
        Category category =categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        return mapper.map(category,CategoryDto.class);
    }
    //get all category
    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map((category -> mapper.map(category,CategoryDto.class)))
                .collect(Collectors.toList());
    }
    //update category

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        category.setCategoryName(categoryDto.getCategoryName());
        category.setImageUrl(categoryDto.getImageUrl());
        category.setId(categoryId);
        Category savedCategory = categoryRepository.save(category);
        return mapper.map(savedCategory,CategoryDto.class);
    }
    //delete category

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        categoryRepository.delete(category);
    }
}

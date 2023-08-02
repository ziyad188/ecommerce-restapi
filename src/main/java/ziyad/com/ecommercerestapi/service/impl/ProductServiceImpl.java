package ziyad.com.ecommercerestapi.service.impl;

import org.springframework.stereotype.Service;
import ziyad.com.ecommercerestapi.payload.ProductDto;
import ziyad.com.ecommercerestapi.entity.Product;
import ziyad.com.ecommercerestapi.entity.Category;
import ziyad.com.ecommercerestapi.exception.ResourceNotFoundException;
import ziyad.com.ecommercerestapi.repository.CategoryRepository;
import ziyad.com.ecommercerestapi.repository.ProductRepository;
import ziyad.com.ecommercerestapi.service.ProductService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl  implements ProductService {
    private ProductRepository productRepository;
    private ModelMapper mapper;
    private CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper mapper, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(()->new ResourceNotFoundException("Product category","id",productDto.getCategoryId()));
        Product product = mapToEntity(productDto);
        product.setCategory(category);
        Product newProduct = productRepository.save(product);
        return mapToDto(newProduct);
    }
    //get product by id

    @Override
    public ProductDto getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product","id",productId));
        return mapToDto(product);
    }
    //get all products
    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products =productRepository.findAll();
        return products.stream().map((product -> mapToDto(product))).collect(Collectors.toList());
    }
//update product
    @Override
    public ProductDto updateProduct(ProductDto productDto, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product","id",productId));
        Category category =categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category","id", productDto.getCategoryId()));
        product.setId(productId);
        product.setActive(productDto.getActive());
        product.setDescription(productDto.getDescription());
        product.setSku(productDto.getSku());
        product.setName(productDto.getName());
        product.setCategory(category);
        product.setImageUrl(productDto.getImageUrl());
        product.setUnitPrice(productDto.getUnitPrice());
        product.setUnitsInStock(productDto.getUnitsInStock());
        Product savedProduct = productRepository.save(product);
        return mapToDto(savedProduct);
    }
    //delete product
    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product","id",productId));
        productRepository.delete(product);

    }

    //mapto dto
    private ProductDto mapToDto(Product product){
        return mapper.map(product,ProductDto.class);
    }
    //map to entity
    private Product mapToEntity(ProductDto productDto){
        return mapper.map(productDto,Product.class);
    }
}

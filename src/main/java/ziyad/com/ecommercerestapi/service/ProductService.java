package ziyad.com.ecommercerestapi.service;

import ziyad.com.ecommercerestapi.payload.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto getProduct(Long productId);
    List<ProductDto> getAllProduct();
    ProductDto updateProduct(ProductDto productDto,Long productId);
    void deleteProduct(Long productId);

}

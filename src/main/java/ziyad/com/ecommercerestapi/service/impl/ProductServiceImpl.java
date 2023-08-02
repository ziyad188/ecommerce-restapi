package ziyad.com.ecommercerestapi.service.impl;

import org.springframework.stereotype.Service;
import ziyad.com.ecommercerestapi.dto.ProductDto;
import ziyad.com.ecommercerestapi.repository.ProductCategoryRepository;
import ziyad.com.ecommercerestapi.repository.ProductRepository;
import ziyad.com.ecommercerestapi.service.ProductService;

@Service
public class ProductServiceImpl  implements ProductService {
    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        return null;
    }
}

package com.Project.Ecommerce.service;


import com.Project.Ecommerce.model.Product;
import com.Project.Ecommerce.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return  productRepository.findAll();
    }

    public Product getProductById(Long id) {

        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {

        return  productRepository.save(product);
    }


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

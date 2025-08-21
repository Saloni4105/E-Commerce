package com.Project.Ecommerce;

import com.Project.Ecommerce.model.Product;
import com.Project.Ecommerce.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DataLoader running...");
        System.out.println("Current product count: " + productRepository.count());
        
        productRepository.deleteAll();
        
        productRepository.save(new Product(null, "Cotton T-Shirt", "Comfortable cotton t-shirt", 299.0, "Clothing", "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=300"));
        productRepository.save(new Product(null, "Blue Jeans", "Classic blue denim jeans", 899.0, "Clothing", "https://images.unsplash.com/photo-1542272604-787c3835535d?w=300"));
        productRepository.save(new Product(null, "Gaming Laptop", "High performance gaming laptop", 45000.0, "Electronics", "https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=300"));
        productRepository.save(new Product(null, "Smartphone", "Latest Android smartphone", 15000.0, "Electronics", "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=300"));
        productRepository.save(new Product(null, "Sneakers", "Comfortable running shoes", 1299.0, "Trending", "https://images.unsplash.com/photo-1549298916-b41d501d3772?w=300"));
        
        System.out.println("Sample data loaded! Total products: " + productRepository.count());
    }
}
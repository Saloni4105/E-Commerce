package com.Project.Ecommerce.Controller;

import com.Project.Ecommerce.model.Product;
import com.Project.Ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts()
    {
        try {
            List<Product> products = productService.getAllProducts();
            System.out.println("Returning " + products.size() + " products");
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            System.err.println("Error getting products: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping ("/{id}")
    public Product getProductById(@PathVariable Long id)
    {
        return productService.getProductById(id);
    }

    @PostMapping
    public  Product addproduct(@RequestBody Product product)
    {
        return  productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct (@PathVariable Long id)
    {
        productService.deleteProduct(id);
    }
    
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Backend is working! Products endpoint: /products");
    }
}

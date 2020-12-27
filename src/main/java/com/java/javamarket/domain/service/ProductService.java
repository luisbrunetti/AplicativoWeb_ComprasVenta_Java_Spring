package com.java.javamarket.domain.service;

import com.java.javamarket.domain.Product;
import com.java.javamarket.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@Component//Tambien funciona con componentp eor mejor es Service porque se da entender
public class ProductService {
    @Autowired // iniciara una clase ProductRepository
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    /**
     *
     * @param productId
     * @code{getPro}
     * @return un OptionalProduct
     */
    public Optional<Product> getProduct(int productId){
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public boolean delete(int productId){
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);
    }
    // Elservicio trabaja en lo que mas conoce que en este caso seria el dominio

}

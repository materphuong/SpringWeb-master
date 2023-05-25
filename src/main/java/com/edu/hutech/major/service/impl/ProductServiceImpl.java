package com.edu.hutech.major.service.impl;

import com.edu.hutech.major.model.Product;
import com.edu.hutech.major.repository.CategoryRepository;
import com.edu.hutech.major.repository.ProductRepository;
import com.edu.hutech.major.service.ProductService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.hibernate.query.Query;

import javax.transaction.Transactional;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }




    @Override
    public void removeProductById(long id) {
        productRepository.deleteById(id);
    }




    @Override
    public List<Product> getAllProductByCategoryId(int id) {
        return productRepository.findAllByCategory_Id(id);
    }
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> searchProducts(String query) {
        List<Product> foundProducts = new ArrayList<>();
        List<Product> allProducts = productRepository.findAll();

        for (Product product : allProducts) {
            if (product.getTitle().toLowerCase().contains(query.toLowerCase())
                    || product.getDescription().toLowerCase().contains(query.toLowerCase())) {
                foundProducts.add(product);
            }
        }

        return foundProducts;
    }

}
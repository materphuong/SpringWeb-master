package com.edu.hutech.major.repository;

import com.edu.hutech.major.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory_Id(int id);
    Optional<Product> findById(int id);
    Product save(Product product);
}

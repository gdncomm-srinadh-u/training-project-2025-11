package com.major.Product.repository;

import com.major.Product.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositoryCustom extends MongoRepository<Product, String> {
//    Page<Product> findbyActivetrue(Pageable pageable);
    List<Product>findByNameContainingIgnoreCase(String keyword);

    @Query("{ '$or': [ " +
            "{ 'name': { $regex: ?0, $options: 'i' } }, " +
            "{ 'sku': { $regex: ?0, $options: 'i' } }, " +
            "{ 'categoryID': { $regex: ?0, $options: 'i' } } " +
            "] }")
    Page<Product> search(String keyword, Pageable pageable);
}

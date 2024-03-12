package com.ecommerce.pharmacy.DAO;

import com.ecommerce.pharmacy.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.List;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

}

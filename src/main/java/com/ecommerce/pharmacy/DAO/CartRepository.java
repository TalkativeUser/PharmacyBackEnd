package com.ecommerce.pharmacy.DAO;

import com.ecommerce.pharmacy.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findCartByUser(String user);
}

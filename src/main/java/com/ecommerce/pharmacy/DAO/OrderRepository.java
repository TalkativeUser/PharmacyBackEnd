package com.ecommerce.pharmacy.DAO;

import com.ecommerce.pharmacy.Entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByCustomerEmail(String email);
    Order findByOrderTrackingNumber(String orderTrackingNumber);
}

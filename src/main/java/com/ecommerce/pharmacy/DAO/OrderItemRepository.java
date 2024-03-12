package com.ecommerce.pharmacy.DAO;

import com.ecommerce.pharmacy.Entity.Order;
import com.ecommerce.pharmacy.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}

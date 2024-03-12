package com.ecommerce.pharmacy.DTO;

import com.ecommerce.pharmacy.Entity.Address;
import com.ecommerce.pharmacy.Entity.Customer;
import com.ecommerce.pharmacy.Entity.Order;
import com.ecommerce.pharmacy.Entity.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class Invoice {
    private Order order;
    private Customer customer;
    private Address address;
}

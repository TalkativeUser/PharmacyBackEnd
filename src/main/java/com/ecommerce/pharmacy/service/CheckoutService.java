package com.ecommerce.pharmacy.service;

import com.ecommerce.pharmacy.DTO.Invoice;
import com.ecommerce.pharmacy.DTO.Purchase;
import com.ecommerce.pharmacy.DTO.PurchaseResponse;
import com.ecommerce.pharmacy.Entity.Order;

import java.util.List;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase,String email) throws Exception;
    Invoice invoice (String orderTrackingNumber);
    List<Order> findOrdersByUserEmail (String email);
}

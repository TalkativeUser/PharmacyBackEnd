package com.ecommerce.pharmacy.service.Impl;

import com.ecommerce.pharmacy.DAO.*;
import com.ecommerce.pharmacy.DTO.Invoice;
import com.ecommerce.pharmacy.DTO.Purchase;
import com.ecommerce.pharmacy.DTO.PurchaseResponse;
import com.ecommerce.pharmacy.Entity.*;
import com.ecommerce.pharmacy.service.CartService;
import com.ecommerce.pharmacy.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {
    private CustomerRepository customerRepository;
    private CartRepository cartRepository;
    private CartService cartService;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;


    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository , CartRepository cartRepository,
                               CartService cartService,OrderRepository orderRepository,
                               OrderItemRepository orderItemRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }
    @Override
    public PurchaseResponse placeOrder(Purchase purchase, String email) throws Exception {
        Order order = new Order();

        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        Customer customer = purchase.getCustomer();
        Customer customerFromDB = customerRepository.findByEmail(email);

       if (customerFromDB != null) {
            customer = customerFromDB;
        }
       customer.setEmail(email);
       customer.setFirstName(purchase.getCustomer().getFirstName());
       customer.setLastName(purchase.getCustomer().getLastName());
        order.setCustomer(customer);

        Address address = new Address();
        address.setOrder(order);
        address.setStreet(purchase.getAddressDTO().getStreet());
        address.setCity(purchase.getAddressDTO().getCity());
        address.setState(purchase.getAddressDTO().getState());
        address.setCountry(purchase.getAddressDTO().getCountry());

        order.setAddress(address);
        order.setDateCreated(new Date());

        List<OrderItem> orderItems = new ArrayList<>();
        List<Cart> carts = cartRepository.findCartByUser(email);
        int totalQuantity = 0;
        for (Cart cart : carts) {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setQuantity(cart.getQuantity());
            oi.setProduct(cart.getProduct());
            oi.setImgUrl(cart.getProduct().getImgUrl());
            oi.setUnitPrice(cart.getProduct().getPriceAfterDiscount());
            totalQuantity += cart.getQuantity();
            orderItemRepository.save(oi);
            orderItems.add(oi);
        }
        order.setOrderItems(orderItems);
        order.setTotalPrice(cartService.listCartItems(email).getTotalCost());
        order.setTotalPriceAfterDiscount(cartService.listCartItems(email).getTotalCostAfterDiscount());
        order.setTotalQuantity(totalQuantity);
        orderRepository.save(order);

        for (Cart value : carts) {
            cartRepository.delete(value);
        }
        return new PurchaseResponse(orderTrackingNumber);
    }
    private String generateOrderTrackingNumber() {
        // generate a random UUID number (UUID version-4)
        return UUID.randomUUID().toString();
    }

    @Override
    public Invoice invoice (String orderTrackingNumber){
        Order order = orderRepository.findByOrderTrackingNumber(orderTrackingNumber);
        Address address = order.getAddress();
        Customer customer = order.getCustomer();
        Invoice myInvoice = new Invoice();
        myInvoice.setOrder(order);
        myInvoice.setAddress(address);
        myInvoice.setCustomer(customer);
        return myInvoice;
    }

    @Override
    public List<Order> findOrdersByUserEmail (String email){
        return orderRepository.findByCustomerEmail(email);
    }
}

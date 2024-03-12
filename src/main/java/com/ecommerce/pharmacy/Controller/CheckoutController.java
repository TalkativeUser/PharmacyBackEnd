package com.ecommerce.pharmacy.Controller;

import com.ecommerce.pharmacy.DTO.Invoice;
import com.ecommerce.pharmacy.DTO.Purchase;
import com.ecommerce.pharmacy.DTO.PurchaseResponse;
import com.ecommerce.pharmacy.Entity.Order;
import com.ecommerce.pharmacy.service.CheckoutService;
import com.ecommerce.pharmacy.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin("http://localhost:4200")
public class CheckoutController {
    private CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/secure/purchase")
    public PurchaseResponse placeOrder(@RequestHeader(value = "Authorization") String token,
            @RequestBody Purchase purchase)throws Exception{
        String user = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if (user == null) {
            throw new Exception("sign in to complete this order");
        }
        PurchaseResponse pr = checkoutService.placeOrder(purchase,user);
        return pr;
    }

    @GetMapping("/secure/invoice")
    public Invoice invoice (@RequestParam("order_tracking_number") String orderTrackingNumber){
        return checkoutService.invoice(orderTrackingNumber);
    }

    @GetMapping("/secure/orders-by-user")
    public List<Order> findByUserEmail(@RequestHeader(value = "Authorization") String token) throws Exception{
        String user = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if (user == null) {
            throw new Exception("sign in to complete this order");
        }
        return checkoutService.findOrdersByUserEmail(user);
    }

}

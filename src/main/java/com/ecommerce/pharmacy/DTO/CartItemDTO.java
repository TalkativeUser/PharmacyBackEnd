package com.ecommerce.pharmacy.DTO;

import com.ecommerce.pharmacy.Entity.Cart;
import com.ecommerce.pharmacy.Entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDTO {
    private Long id;
    private int quantity;
    private Product product;

    public CartItemDTO(Cart cart){
        this.id = cart.getId();
        this.quantity=cart.getQuantity();
        this.product = cart.getProduct();
    }
}

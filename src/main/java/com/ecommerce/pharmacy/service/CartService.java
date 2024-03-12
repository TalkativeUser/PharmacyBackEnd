package com.ecommerce.pharmacy.service;

import com.ecommerce.pharmacy.DTO.CartDTO;
import com.ecommerce.pharmacy.DTO.CartItemDTO;

public interface CartService {
    public void addToCart(String user, CartItemDTO cartItemDto)throws Exception;
    public CartDTO listCartItems(String user)throws Exception;
    public void updateCartItem(String user , Long cartItemId , CartItemDTO cartItemDto)throws Exception;
    public void deleteCartItem(String user , Long itemId) throws Exception;
}

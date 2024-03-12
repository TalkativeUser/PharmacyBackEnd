package com.ecommerce.pharmacy.service.Impl;

import com.ecommerce.pharmacy.DAO.CartRepository;
import com.ecommerce.pharmacy.DAO.ProductRepository;
import com.ecommerce.pharmacy.DTO.CartDTO;
import com.ecommerce.pharmacy.DTO.CartItemDTO;
import com.ecommerce.pharmacy.Entity.Cart;
import com.ecommerce.pharmacy.Entity.Product;
import com.ecommerce.pharmacy.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }


    @Override
    public void addToCart(String user, CartItemDTO cartItemDto)throws Exception{
        Optional<Product> product = productRepository.findById(cartItemDto.getProduct().getId());
        if (product.isEmpty()) {
            throw new Exception("Product not found");
        }

        Cart cart = new Cart();
        if (cartItemDto.getQuantity() > product.get().getQuantity()){
            throw new Exception("Sorry we only have " + product.get().getQuantity() + " items of this product now");
        }
        cart.setQuantity(cartItemDto.getQuantity());
        cart.setProduct(product.get());
        cart.setUser(user);
        cart.setCreatedDate(new Date());
        product.get().setQuantity(product.get().getQuantity() - cart.getQuantity());
        cartRepository.save(cart);
    }

    @Override
    public CartDTO listCartItems(String user)throws Exception{
        List<Cart> carts = cartRepository.findCartByUser(user);
        if (carts == null) {
            throw new Exception("there's no cart added by this user");
        }
        List<CartItemDTO> cartItem = new ArrayList<>();
        double totalCost = 0;

        for (Cart cart: carts) {
            CartItemDTO cartItemDto = new CartItemDTO(cart);
            totalCost += cartItemDto.getQuantity() * cart.getProduct().getPriceAfterDiscount();
            cartItem.add(cartItemDto);
        }
        CartDTO cartDto = new CartDTO();
        if (totalCost >= 1000){
            cartDto.setTotalCostAfterDiscount(totalCost - (totalCost * .1));
        }else {
            cartDto.setTotalCostAfterDiscount(totalCost);
        }
        cartDto.setTotalCost(totalCost);
        cartDto.setCartItem(cartItem);
        return cartDto;
    }

    @Override
    public void updateCartItem(String user , Long cartItemId , CartItemDTO cartItemDto) throws Exception{
        Optional<Cart> cart = cartRepository.findById(cartItemId);
        if (cart.isEmpty()) {
            throw new Exception("no carts found");
        }
        Optional<Product> product = productRepository.findById(cart.get().getProduct().getId());
        product.get().setQuantity(product.get().getQuantity() + cart.get().getQuantity());


        cart.get().setQuantity(cartItemDto.getQuantity());
        cart.get().setProduct(product.get());
        cart.get().setUser(user);
        cart.get().setCreatedDate(new Date());

        product.get().setQuantity(product.get().getQuantity() - cart.get().getQuantity());
    }

    @Override
    public void deleteCartItem(String user, Long cartItemId) throws Exception {
        Optional<Cart> cart = cartRepository.findById(cartItemId);
        if (cart.isEmpty()) {
            throw new Exception("no carts found");
        }
        Optional<Product> product = productRepository.findById(cart.get().getProduct().getId());
        product.get().setQuantity(product.get().getQuantity() + cart.get().getQuantity());
        cartRepository.delete(cart.get());
    }
}

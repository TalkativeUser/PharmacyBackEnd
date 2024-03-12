package com.ecommerce.pharmacy.Controller;

import com.ecommerce.pharmacy.DTO.CartDTO;
import com.ecommerce.pharmacy.DTO.CartItemDTO;
import com.ecommerce.pharmacy.service.CartService;
import com.ecommerce.pharmacy.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart/secure")
@CrossOrigin("http://localhost:4200")
public class CartController {
    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public void addToCart(@RequestHeader(value = "Authorization") String token,
                          @RequestBody CartItemDTO cartItemDto)throws Exception{
        String user = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if (user == null) {
            throw new Exception("User email is missing");
        }
        cartService.addToCart(user,cartItemDto);
    }

    @GetMapping("/all")
    public CartDTO getCartItem(@RequestHeader(value = "Authorization") String token
    )throws Exception{
        String user = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if (user == null) {
            throw new Exception("User email is missing");
        }
        return cartService.listCartItems(user);
    }

    @PutMapping("/update")
    public CartDTO updateCartItem(@RequestHeader(value = "Authorization") String token,
                                  @RequestParam("cart_item_id") Long cartItemId,
                                  @RequestBody CartItemDTO cartItemDto)throws Exception{
        String user = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if (user == null) {
            throw new Exception("User email is missing");
        }
        cartService.updateCartItem(user,cartItemId,cartItemDto);
        return cartService.listCartItems(user);
    }

    @DeleteMapping("/delete")
    public CartDTO deleteCartItem(@RequestHeader(value = "Authorization") String token,
                               @RequestParam("cart_item_id") Long cartItemId)throws Exception{
        String user = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if (user == null) {
            throw new Exception("User email is missing");
        }
        cartService.deleteCartItem(user,cartItemId);
        return cartService.listCartItems(user);
    }
}

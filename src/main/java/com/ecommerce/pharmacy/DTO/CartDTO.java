package com.ecommerce.pharmacy.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private List<CartItemDTO> cartItem;
    private double totalCost;

    private double totalCostAfterDiscount;
}

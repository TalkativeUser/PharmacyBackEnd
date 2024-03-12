package com.ecommerce.pharmacy.DTO;

import com.ecommerce.pharmacy.Entity.Category;
import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private int quantity;
    private double price;
    private double priceAfterDiscount;
    private String imgUrl;
    private Category category ;

}

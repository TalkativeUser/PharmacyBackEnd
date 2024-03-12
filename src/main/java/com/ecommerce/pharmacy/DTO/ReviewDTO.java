package com.ecommerce.pharmacy.DTO;

import com.ecommerce.pharmacy.Entity.Product;
import lombok.Data;

import java.util.Optional;

@Data
public class ReviewDTO {
    private double rating;
    private Optional<String> description;
    private Product product;
}

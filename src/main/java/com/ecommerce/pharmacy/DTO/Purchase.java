package com.ecommerce.pharmacy.DTO;

import com.ecommerce.pharmacy.Entity.*;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private AddressDTO addressDTO;
}

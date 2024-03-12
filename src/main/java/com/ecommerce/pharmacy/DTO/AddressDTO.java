package com.ecommerce.pharmacy.DTO;

import lombok.Data;

import javax.persistence.Column;

@Data
public class AddressDTO {
    private String street;
    private String city;
    private String state;
    private String country;
}

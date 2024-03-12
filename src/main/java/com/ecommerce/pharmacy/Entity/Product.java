package com.ecommerce.pharmacy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "product")
@NoArgsConstructor
public class Product {

    //not important
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    //not important
    @Column(name = "price_after_discount")
    private double priceAfterDiscount;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    //not important
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews;

    //not important
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Cart> carts;

    //not important
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private OrderItem orderItem;

    public Product(String name, int quantity, double price, double priceAfterDiscount, String imgCover, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.priceAfterDiscount = priceAfterDiscount;
        this.imgUrl = imgCover;
        this.category = category;
    }

}

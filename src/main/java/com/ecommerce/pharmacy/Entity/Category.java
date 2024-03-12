package com.ecommerce.pharmacy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    important
    @Column(name = "title")
    private String title;
//important
    @Column(name = "img_url")
    @JsonIgnore
    private String imgURL;

    @OneToMany(mappedBy = "category",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;
}

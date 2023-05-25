package com.edu.hutech.major.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    public String getTitle() {
        return name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    private double price;

    private double weight;

    private String description;

    private String imageName;
    @Column(name = "quantity")
    private Integer quantity;
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}//create table mapping trong db

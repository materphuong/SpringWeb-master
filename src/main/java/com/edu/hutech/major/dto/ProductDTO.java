package com.edu.hutech.major.dto;

import lombok.Data;


@Data
public class ProductDTO {
    private Long id;

    private String name;

    private int categoryId;

    private double price;

    private double weight;

    private String description;

    private String imageName;
    private int quantity; // Add the quantity field

    // Constructor, getters, setters
    // ...

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

package com.spring.lab.springmvc_demo.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;  // Sử dụng BigDecimal cho giá thay vì String
    private String description;
    
    // Constructor với String price để tương thích ngược
    public Product(Long id, String name, String price, String description) {
        this.id = id;
        this.name = name;
        this.price = price != null ? new BigDecimal(price) : null;
        this.description = description;
    }
    
    // Getter/Setter cho price dưới dạng String
    public String getPriceAsString() {
        return price != null ? price.toString() : null;
    }
    
    public void setPriceFromString(String price) {
        this.price = price != null ? new BigDecimal(price) : null;
    }
}

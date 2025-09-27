package com.iuh.fit.ontapgiohang_2.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private int id;
    String model;
    Double price;
    Integer quantity;
    String imgURL;
    String description;
}
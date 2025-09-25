package com.iuh.fit.ontapgiohang_1.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
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

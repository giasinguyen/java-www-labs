package com.iuh.fit.ontapgiohang_1.model;

import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartItemBean {
    Product product;
    int quantity;

    public double getSubtotal(){
        return product.getPrice() * quantity;
    }
}

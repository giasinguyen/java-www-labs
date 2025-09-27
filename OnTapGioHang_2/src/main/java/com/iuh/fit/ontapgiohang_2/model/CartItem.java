package com.iuh.fit.ontapgiohang_2.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    private Product product;
    private int quantity;

    public double getSubTotal(){
        return product.getPrice() * quantity;
    }
}
